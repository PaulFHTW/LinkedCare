using Hl7.Fhir.Model;
using Hl7.Fhir.Serialization;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace LC_Backend
{
    public class FhirbaseWrapper
    {

        readonly FhirJsonParser parser = new FhirJsonParser();
        readonly FhirbaseConnector.Connector connector;

        public FhirbaseWrapper(IConfiguration configuration)
        {
            connector = new FhirbaseConnector.Connector(configuration.GetConnectionString("Default"));
        }

        Type GetRessourceType(string ressourceName) => AppDomain.CurrentDomain.GetAssemblies().SelectMany(a => a.GetTypes())
                .Single(t => t.IsClass && string.Compare(t.FullName, $"Hl7.Fhir.Model.{ressourceName}", true) == 0);

        public IEnumerable<Resource> GetAll(string ressourceName, string subjectQuery = null, string typeQuery = null, string performerQuery = null, bool openDis = false, string statusQuery = null)
        {
            var type = GetRessourceType(ressourceName);
            var methodType = typeof(FhirbaseConnector.Connector).GetMethod("Read", new[] { typeof(int) });
            var method = methodType.MakeGenericMethod(type);

            var result = (IEnumerable)method.Invoke(connector, new object[] { -1 });

            var enumerator = result.GetEnumerator();
            while (enumerator.MoveNext())
            {
                var r = enumerator.Current as Resource;
                if(!string.IsNullOrWhiteSpace(subjectQuery))
                {
                    var val = (ResourceReference)type.GetProperty("Subject").GetValue(r);
                    if (val.ElementId != subjectQuery)
                        continue;
                }
                if (!string.IsNullOrWhiteSpace(typeQuery))
                {
                    var val = (CodeableConcept)type.GetProperty("Type").GetValue(r);
                    if (!val.Coding.Any(code => code.Code == typeQuery))
                        continue;
                }
                if (!string.IsNullOrWhiteSpace(performerQuery))
                {
                    if (!(r is CarePlan))
                        continue;
                    var carePlan = (CarePlan)r;
                    if (!carePlan.Activity.Any(a => a.Detail.Performer.Any(p => p.ElementId == performerQuery)))
                        continue;
                }
                if(!string.IsNullOrWhiteSpace(statusQuery))
                {
                    if (!(r is MedicationRequest))
                        continue;
                    if (!string.Equals(((MedicationRequest)r).Status?.ToString(), statusQuery, StringComparison.InvariantCultureIgnoreCase))
                        continue;
                }
                if (openDis)
                {
                    if (!(r is CarePlan))
                        continue;
                    var carePlan = (CarePlan)r;
                    if (carePlan.Activity.Where(a => a.Detail.Code.Coding[0].Code == "16076005")
                        .All(a => a.Detail.Status == CarePlan.CarePlanActivityStatus.Completed))
                        continue;
                }
                if (string.Equals(ressourceName, nameof(MedicationRequest), StringComparison.InvariantCultureIgnoreCase))
                {
                    var notes = ((MedicationRequest)r).Note;
                    if (notes.Any())
                    {
                        var medText = notes.Last().Text;
                        notes.Remove(notes.Last());
                        var str = new FhirJsonSerializer().SerializeToString(r);
                        var dict = JsonConvert.DeserializeObject<Dictionary<string, object>>(str);
                        dict.Add("medicationCodeableConcept", JsonConvert.DeserializeObject<object>(medText));
                        str = JsonConvert.SerializeObject(dict);
                        r = new FhirJsonParser().Parse<MedicationRequest>(str);
                    }
                }
                yield return r;
            }
        }

        public Resource Get(string ressourceName, Guid id)
        {
            var type = GetRessourceType(ressourceName);
            var methodType = typeof(FhirbaseConnector.Connector).GetMethod("Read", new[] { typeof(string) });
            var method = methodType.MakeGenericMethod(type);

            var result = (IEnumerable)method.Invoke(connector, new object[] {
                "SELECT _fhirbase_to_resource(row(r.*)::_resource) FROM " + ressourceName + " AS r WHERE id = '" + id + "'"
            });

            var enumerator = result.GetEnumerator();
            enumerator.MoveNext();
            var res = enumerator.Current as Resource;

            if (string.Equals(ressourceName, nameof(MedicationRequest), StringComparison.InvariantCultureIgnoreCase))
            {
                var notes = ((MedicationRequest)res).Note;
                if (notes.Any())
                {
                    var medText = notes.Last().Text;
                    notes.Remove(notes.Last());
                    var str = new FhirJsonSerializer().SerializeToString(res);
                    var dict = JsonConvert.DeserializeObject<Dictionary<string, object>>(str);
                    dict.Add("medicationCodeableConcept", JsonConvert.DeserializeObject<object>(medText));
                    str = JsonConvert.SerializeObject(dict);
                    res = new FhirJsonParser().Parse<MedicationRequest>(str);
                }
            }

            return res;
        }

        public Base Save(string ressourceName, string jsonObj, Guid id = default)
        {
            var type = GetRessourceType(ressourceName);
            var methodType = typeof(FhirbaseConnector.Connector).GetMethod("Create");
            var method = methodType.MakeGenericMethod(type);

            string medSubstr = null;
            if (string.Equals(ressourceName, nameof(MedicationRequest), StringComparison.InvariantCultureIgnoreCase))
            {
                var dict = JsonConvert.DeserializeObject<Dictionary<string, object>>(jsonObj);
                medSubstr = JsonConvert.SerializeObject(dict["medicationCodeableConcept"]);
                dict.Remove("medicationCodeableConcept");
                jsonObj = JsonConvert.SerializeObject(dict);
            }

            var obj = parser.Parse(jsonObj, type) as Resource;
            if (medSubstr != null)
                ((MedicationRequest)obj).Note.Add(new Annotation { Text = medSubstr });

            if (id != default)
                if (id.ToString() != obj.Id)
                    throw new InvalidOperationException();

            var dbObj = (Base)method.Invoke(connector, new object[] { obj });

            return dbObj;
        }

        public void Delete(string ressourceName, Guid id)
        {
            var type = GetRessourceType(ressourceName);
            var methodType = typeof(FhirbaseConnector.Connector).GetMethod("Delete");
            var method = methodType.MakeGenericMethod(type);

            var dummyRessource = Activator.CreateInstance(type) as Resource;
            dummyRessource.Id = id.ToString();

            method.Invoke(connector, new object[] {
                dummyRessource
            });
        }

    }
}
