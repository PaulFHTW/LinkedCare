package com.hac.facade.persistence;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ReferenceClientParam;
import ca.uhn.fhir.rest.gclient.TokenClientParam;
import ca.uhn.fhir.util.BundleUtil;
import com.hac.facade.aspects.LogHapiCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FHIRDataProvider {

    private Map<String, Class<? extends IBaseResource>> typeMapClass = Map.of(
      "Condition" , Condition.class,
      "Appointment", Appointment.class,
      "Observation", Observation.class,
      "AllergyIntolerance", AllergyIntolerance.class,
      "Immunization", Immunization.class,
      "CarePlan", CarePlan.class,
      "MedicationDispense", MedicationDispense.class,
      "MedicationRequest", MedicationRequest.class,
      "MedicationAdministration", MedicationAdministration.class,
      "MedicationStatement", MedicationStatement.class
    );

    private final FHIRContextProvider fhirContextProvider;

    @LogHapiCall
    public String getPatientDataForID(long id) {
        IGenericClient client = fhirContextProvider.getClient();
        long start = System.currentTimeMillis();
        log.info("Fetching Patient Data from FHIR Server");
        Patient patient = client.read().resource(Patient.class).withId(id).execute();
        String resourceToString = fhirContextProvider.getJsonParser().encodeResourceToString(patient);
        log.info("Fetched from FHIR in {}ms", System.currentTimeMillis() - start);
        return resourceToString;
    }

    @LogHapiCall
    public Patient getPatientForID(long id) {
        IGenericClient client = fhirContextProvider.getClient();
        long start = System.currentTimeMillis();
        log.info("Fetching Patient Data from FHIR Server for id {}", id);
        Patient patient = client.read().resource(Patient.class).withId(id).execute();
        //var iBaseBundle = client.search().forResource(Patient.class).where(new TokenClientParam("_id").exactly().code("1")).execute();
        log.info("Fetched from FHIR in {}ms", System.currentTimeMillis() - start);
        return patient;
    }


    public IBaseResource getResourceForID(String id, String resourceType) {
        Class<? extends IBaseResource> type = typeMapClass.get(resourceType);
        if (type == null) {
            throw new UnsupportedOperationException("Did not find any type for " + resourceType);
        }

        IGenericClient client = fhirContextProvider.getClient();
        var identifier = client.search().forResource(type).where(new TokenClientParam("identifier").exactly().code(id)).execute();
        List<IBaseResource> resources = new ArrayList<>(BundleUtil.toListOfResources(fhirContextProvider.getCtx(), identifier));
        return resources.size() > 0 ? resources.get(0) : null;
    }

    public String getResourceStringForID(String id, String resourceType) {
        Class<? extends IBaseResource> type;
        switch (resourceType) {
            case "Appointment": type = Appointment.class;
            break;
            case "Observation": type = Observation.class;
            default: type = Condition.class;
        }
        IGenericClient client = fhirContextProvider.getClient();
        var identifier = client.search().forResource(type).where(new TokenClientParam("identifier").exactly().code(id)).execute();
        List<IBaseResource> resources = new ArrayList<>(BundleUtil.toListOfResources(fhirContextProvider.getCtx(), identifier));
        return fhirContextProvider.getJsonParser().encodeResourceToString(resources.get(0));
    }

    @LogHapiCall
    public String getConditionsForId(long id) {
        List<IBaseResource> conditions = getResourceListForPatientId(id, Condition.class);
        return serializeListToString(conditions);
    }

    @LogHapiCall
    public String getAppointmentsForId(long id) {
        List<IBaseResource> appointments = getResourceListForPatientId(id, Appointment.class);
        return serializeListToString(appointments);
    }

    @LogHapiCall
    public String getObservationsForId(long id) {
        List<IBaseResource> observations = getResourceListForPatientId(id, Observation.class);
        return serializeListToString(observations);
    }

    @LogHapiCall
    public String getAllergyIntoleranceForId(long id) {
        List<IBaseResource> allergyIntolerances = getResourceListForPatientId(id, AllergyIntolerance.class);
        return serializeListToString(allergyIntolerances);
    }

    @LogHapiCall
    public String getImmunizationFroId(long id) {
        List<IBaseResource> immunizations = getResourceListForPatientId(id, Immunization.class);
        return serializeListToString(immunizations);
    }

    @LogHapiCall
    public String getCarePlanFroId(long id) {
        List<IBaseResource> carePlans = getResourceListForPatientId(id, CarePlan.class);
        return serializeListToString(carePlans);
    }

    @LogHapiCall
    public String getMedicationDispensFroId(long id) {
        List<IBaseResource> medicationDispenses = getResourceListForPatientId(id, MedicationDispense.class);
        return serializeListToString(medicationDispenses);
    }

    @LogHapiCall
    public String getMedicationRequestFroId(long id) {
        List<IBaseResource> medicationRequests = getResourceListForPatientId(id, MedicationRequest.class);
        return serializeListToString(medicationRequests);
    }

    @LogHapiCall
    public String getMedicationAdministrationFroId(long id) {
        List<IBaseResource> medicationAdministration = getResourceListForPatientId(id, MedicationAdministration.class);
        return serializeListToString(medicationAdministration);
    }

    @LogHapiCall
    public String getMedicationStatementFroId(long id) {
        List<IBaseResource> medicationStatement = getResourceListForPatientId(id, MedicationStatement.class);
        return serializeListToString(medicationStatement);
    }

    public String getBundleOfConditionsForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, Condition.class));
    }

    public String getBundleOfAppointmentsForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, Appointment.class));
    }

    public String getBundleOfObservationsForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, Observation.class));
    }

    public String getBundleOfAllergyIntoleranceForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, AllergyIntolerance.class));
    }

    public String getBundleOfImmunizationForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, Immunization.class));
    }

    public String getBundleOfCarePlanFroId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, CarePlan.class));
    }

    public String getBundleOfMedicationDispensesForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, MedicationDispense.class));
    }

    public String getBundleOfMedicationRequestForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, MedicationRequest.class));
    }

    public String getBundleOfMedicationAdministrationForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, MedicationAdministration.class));
    }

    public String getBundleOfMedicationStatementForId(long id) {
        return serializeBundle(getResourceBundleForPatientId(id, MedicationStatement.class));
    }

    private List<IBaseResource> getResourceListForPatientId(long id, Class<? extends IBaseResource> resourceClass) {
        Bundle result = getResourceBundleForPatientId(id, resourceClass);
        return new ArrayList<>(BundleUtil.toListOfResourcesOfType(fhirContextProvider.getCtx(), result, resourceClass));
    }

    private Bundle getResourceBundleForPatientId(long id, Class<? extends IBaseResource> resourceClass) {
        var client = fhirContextProvider.getClient();
        ReferenceClientParam patient = null;
        Field genericPatient;
        try {
            genericPatient = resourceClass.getField("PATIENT");
            if (genericPatient.getType() == ReferenceClientParam.class) {
                patient = (ReferenceClientParam) genericPatient.get(null);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Problem using reflective access to static Field.");
        }
        if (patient == null) {
            throw new RuntimeException("Problem using reflective access to static Field, was null.");
        }

        var result = client.search()
                .forResource(resourceClass)
                .where(patient.hasId(String.valueOf(id)))
                .returnBundle(Bundle.class).execute();
        return result;
    }

    public String serializeBundle(Bundle bundle) {
        return fhirContextProvider.getJsonParser().encodeResourceToString(bundle);
    }

    private String serializeListToString(List<IBaseResource> resourceList) {
        if (resourceList.size() == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        resourceList.forEach(resource -> stringBuilder.append(fhirContextProvider.getJsonParser().encodeResourceToString(resource)).append(","));
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }


    /*public String getAppointmentsForId(long id) {
        var client = fhirContextProvider.getClient();
        log.info("Fetching Appointments for user {}", id);
        long start = System.currentTimeMillis();
        String query = "/patient/" + id + "/appointment";
        List<Appointment> appointments = new ArrayList<>();
        var bundle = client.search().byUrl(query).returnBundle(Bundle.class).execute();
        appointments.addAll(BundleUtil.toListOfResourcesOfType(fhirContextProvider.getCtx(), bundle, Appointment.class));
        fhirContextProvider.getJsonParser().encodeResourceToString(appointments);


    }*/

}
