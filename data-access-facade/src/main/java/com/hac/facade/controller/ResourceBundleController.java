package com.hac.facade.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.UUID;

import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Appointment;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Immunization;
import org.hl7.fhir.r4.model.MedicationAdministration;
import org.hl7.fhir.r4.model.MedicationDispense;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hac.facade.aspects.IntrospectedAndAddUsername;
import com.hac.facade.aspects.IntrospectedToken;
import com.hac.facade.persistence.FHIRContextProvider;
import com.hac.facade.persistence.FHIRDataProvider;
import com.hac.facade.persistence.FHIRDataStore;
import com.hac.facade.service.KeycloakService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/v2/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
@Log4j2
public class ResourceBundleController {

  private final FHIRDataProvider fhirDataProvider;
  private final FHIRDataStore dataStore;
  private final FHIRContextProvider contextProvider;
  private final KeycloakService keycloakService;

  @IntrospectedAndAddUsername
  @PostMapping(value = "appointments")
  public ResponseEntity<String> createAppointmentForUser(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String appointmentS) {
    var jsonParser = contextProvider.getJsonParser();
    var appointment = jsonParser.parseResource(Appointment.class, appointmentS);
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var description = appointment.getDescription();
    var appointmentParticipantComponent = extracted(userIDForToken);
    var keycloakID = keycloakService.createResource("Appointment", String.valueOf(userIDForToken), username, "appointment" + "-" + UUID.randomUUID());
    appointment.setParticipant(List.of(appointmentParticipantComponent));
    appointment.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(appointment);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "conditions")
  public ResponseEntity<String> createConditionsForUser(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String conditionS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var condition = jsonParser.parseResource(Condition.class, conditionS);
    var keycloakId = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "condition" + "-" + UUID.randomUUID());
    condition.setSubject(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    condition.addIdentifier().setValue(keycloakId);
    var resource = dataStore.storeResource(condition);
    return ResponseEntity.status(CREATED).body(keycloakId);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "observations")
  public ResponseEntity<String> createObservationssForUser(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String observationS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var observation = jsonParser.parseResource(Observation.class, observationS);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "observation" + "-" + UUID.randomUUID());
    observation.setSubject(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    observation.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(observation);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "allergyIntolerances")
  public ResponseEntity<String> createIntoleraceForUser(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String allergyIntoleranceS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var allergyIntolerance = jsonParser.parseResource(AllergyIntolerance.class, allergyIntoleranceS);
    allergyIntolerance.setEncounter(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    var resource = dataStore.storeResource(allergyIntolerance);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "allergyIntolerance" + "-" + UUID.randomUUID());
    allergyIntolerance.addIdentifier().setValue(keycloakID);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "immunizations")
  public ResponseEntity<String> createForUser(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String immunizationS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var immunization = jsonParser.parseResource(Immunization.class, immunizationS);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "immunization" + "-" + UUID.randomUUID());
    immunization.setEncounter(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    immunization.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(immunization);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "carePlan")
  public ResponseEntity<String> createForUserCarePlan(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String  carePlanS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var carePlan = jsonParser.parseResource(CarePlan.class, carePlanS);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "carePlan" + "-" + UUID.randomUUID());
    carePlan.setEncounter(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    carePlan.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(carePlan);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "medicationDispense")
  public ResponseEntity<String> createForUserMedicationDispense(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String  medicationDispenseS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var medicationDispense = jsonParser.parseResource(MedicationDispense.class, medicationDispenseS);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "medicationDispense" + "-" + UUID.randomUUID());
    medicationDispense.addReceiver(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    medicationDispense.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(medicationDispense);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "medicationRequest")
  public ResponseEntity<String> createForUserMedicationRequest(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String medicationRequestS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var medicationRequest = jsonParser.parseResource(MedicationRequest.class, medicationRequestS);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "medicationRequest" + "-" + UUID.randomUUID());
    medicationRequest.setSubject(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    medicationRequest.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(medicationRequest);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "medicationAdministration")
  public ResponseEntity<String> createForUserMedicationAdministration(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String medicationAdministrationS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var medicationAdministration = jsonParser.parseResource(MedicationAdministration.class, medicationAdministrationS);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "medicationAdministration" + "-" + UUID.randomUUID());
    medicationAdministration.setSubject(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    medicationAdministration.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(medicationAdministration);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  @IntrospectedAndAddUsername
  @PostMapping(value = "medicationStatement")
  public ResponseEntity<String> createForUserMedicationStatement(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody String medicationStatementS) {
    if (userIDForToken == null) {
      throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
    }
    var jsonParser = contextProvider.getJsonParser();
    var medicationStatement = jsonParser.parseResource(MedicationStatement.class, medicationStatementS);
    var keycloakID = keycloakService.createResource("Condition", String.valueOf(userIDForToken), username, "medicationStatement" + "-" + UUID.randomUUID());
    medicationStatement.setSubject(new Reference(fhirDataProvider.getPatientForID(userIDForToken).getIdElement()));
    medicationStatement.addIdentifier().setValue(keycloakID);
    var resource = dataStore.storeResource(medicationStatement);
    return ResponseEntity.status(CREATED).body(keycloakID);
  }

  private Appointment.AppointmentParticipantComponent extracted(long userID) {
    var appointmentParticipantComponent = new Appointment.AppointmentParticipantComponent();
    var patientForID = fhirDataProvider.getPatientForID(userID);
    log.info("Got patient {}", patientForID);
    var value = new Reference("Patient/" + patientForID.getIdElement().getIdPart());
    appointmentParticipantComponent.setActor(value);
    return appointmentParticipantComponent;
  }

  @IntrospectedToken
  @GetMapping(value = "patientInfo/medications/requests", produces = "application/json")
  public String getMedicationRequest(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null)
      return fhirDataProvider.getBundleOfMedicationRequestForId(userIDForToken);
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "patientInfo/medications/dispenses", produces = "application/json")
  public String getMedicationDispense(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null)
      return fhirDataProvider.getBundleOfMedicationDispensesForId(userIDForToken);
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "patientInfo/medications/administrations", produces = "application/json")
  public String getMedicationAdministration(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null)
      return fhirDataProvider.getBundleOfMedicationAdministrationForId(userIDForToken);
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "patientInfo/medications/statements", produces = "application/json")
  public String getMedicationStatement(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null)
      return fhirDataProvider.getBundleOfMedicationStatementForId(userIDForToken);
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "/patientInfo/observations", produces = "application/json")
  public String getObservations(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null) {
      return fhirDataProvider.getBundleOfObservationsForId(userIDForToken);
    }
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "/patientInfo/appointments", produces = "application/json")
  public String getAppointments(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null) {
      return fhirDataProvider.getBundleOfAppointmentsForId(userIDForToken);
    }
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "/patientInfo/allergyIntolerances", produces = "application/json")
  public String getAllergyIntolerance(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null) {
      return fhirDataProvider.getBundleOfAllergyIntoleranceForId(userIDForToken);
    }
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "/patientInfo/conditions", produces = "application/json")
  public String getConditions(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null) {
      return fhirDataProvider.getBundleOfConditionsForId(userIDForToken);
    }
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "/patientInfo/immunizations", produces = "application/json")
  public String getImmunizations(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null) {
      return fhirDataProvider.getBundleOfImmunizationForId(userIDForToken);
    }
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }

  @IntrospectedToken
  @GetMapping(value = "/patientInfo/carePlans", produces = "application/json")
  public String getCarePlan(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
    if (userIDForToken != null) {
      return fhirDataProvider.getBundleOfCarePlanFroId(userIDForToken);
    }
    throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
  }


}
