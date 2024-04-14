using Hl7.Fhir.Model;
using Hl7.Fhir.Serialization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Reflection.Metadata;
using System.Threading.Tasks;
using static Hl7.Fhir.Model.Bundle;

namespace LC_Backend
{
    [ApiController]
    [Route("fhir")]
    public class FhirController : ControllerBase
    {

        readonly FhirbaseWrapper wrapper;
        readonly FhirJsonSerializer serializer = new FhirJsonSerializer();
        readonly FhirJsonParser parser = new FhirJsonParser();

        public FhirController(FhirbaseWrapper wrapper)
        {
            this.wrapper = wrapper;
        }

        void CheckLoggedIn()
        {
            if (HttpContext.Session.GetString("user") == null)
                throw new BadHttpRequestException("Unauthorized", 401);
        }

        [HttpGet("{ressourceName}")]
        public string GetAll(string ressourceName, [FromQuery] string subject, [FromQuery] string type, [FromQuery] string performer, [FromQuery] int page, [FromQuery] string status,[FromQuery] int pagesize = 20)
        {
            CheckLoggedIn();
            bool opendis = Request.Query["opendis"].Any();
            bool selPat = Request.Query["selectPatient"].Any();
            bool isPaged = Request.Query["page"].Any();
            var list = wrapper.GetAll(ressourceName, subject, type, performer, opendis, status);
            if (string.Equals(ressourceName, "careplan", StringComparison.InvariantCultureIgnoreCase) && selPat) {
                list = list.Cast<CarePlan>()
                    .Select(c => c.Subject.ElementId)
                    .Where(id => Guid.TryParse(id, out _))
                    .Distinct()
                    .Select(id => wrapper.Get("patient", Guid.Parse(id)));
            }
            var bundle = new Bundle()
            {
                Type = Bundle.BundleType.Collection,
                Total = list.Count()
            };
            if (isPaged)
                list = list.Skip((page - 1) * pagesize).Take(pagesize);
            foreach (var r in list)
            {
                bundle.AddResourceEntry(r, "https://127.0.0.1/fhir/" + r.ResourceType.ToString() + "/" + r.Id);
            }
            return serializer.SerializeToString(bundle, Hl7.Fhir.Rest.SummaryType.False);
        }

        [HttpGet("{ressourceName}/{id}")]
        public string Get(string ressourceName, string id)
        {
            CheckLoggedIn();
            if (!Guid.TryParse(id, out Guid gid))
                throw new FormatException();

            var result = wrapper.Get(ressourceName, gid);
            return serializer.SerializeToString(result, Hl7.Fhir.Rest.SummaryType.False);
        }

        [HttpPost("{ressourceName}")]
        public string Post(string ressourceName, [FromBody] string jsonBody)
        {
            CheckLoggedIn();
            var obj = wrapper.Save(ressourceName, jsonBody);
            return serializer.SerializeToString(obj, Hl7.Fhir.Rest.SummaryType.False);
        }

        [HttpPut("{ressourceName}/{id}")]
        public string Put(string ressourceName, string id, [FromBody] string jsonBody)
        {
            CheckLoggedIn();
            if (!Guid.TryParse(id, out Guid gid))
                throw new FormatException();

            var obj = wrapper.Save(ressourceName, jsonBody);
            return serializer.SerializeToString(obj, Hl7.Fhir.Rest.SummaryType.False);
        }

        [HttpDelete("{ressourceName}/{id}")]
        public void Delete(string ressourceName, string id)
        {
            CheckLoggedIn();
            if (!Guid.TryParse(id, out Guid gid))
                throw new FormatException();

            wrapper.Delete(ressourceName, gid);
        }
    }
}
