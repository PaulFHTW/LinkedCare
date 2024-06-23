package com.hac.facade.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hac.facade.aspects.IntrospectedAndAddUsername;
import com.hac.facade.aspects.IntrospectedToken;
import com.hac.facade.dto.AppointmentDTO;
import com.hac.facade.dto.TokenIntrospectionResponse;
import com.hac.facade.persistence.FHIRDataProvider;
import com.hac.facade.persistence.FHIRDataStore;
import com.hac.facade.service.KeycloakService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class ResourceController {

    private final KeycloakService keycloakService;
    private final FHIRDataProvider fhirDataProvider;
    private final FHIRDataStore fhirDataStore;

    @GetMapping("/PoC")
    public ResponseEntity<String> GetData(@RequestHeader(name = "Authorization") String token){
        /*if (token == null || !token.contains("Bearer")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String splitToken = token.split("Bearer")[1].trim();
        if ("Admin".equals(splitToken)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } */
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/tokenInfo", produces = "application/json")
    public TokenIntrospectionResponse getTokenInfo(@RequestHeader(name = "Authorization") String token) {
        return keycloakService.introspectToken(token);
    }

    @IntrospectedToken
    @GetMapping(value = "/patientInfo", produces = "application/json")
    public String getPatientInfo(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null)
            return fhirDataProvider.getPatientDataForID(userIDForToken);
        throw new ResponseStatusException(NOT_FOUND, "Patient not found");
    }

    @IntrospectedToken
    @GetMapping(value = "patientInfo/medications/request", produces = "application/json")
    public String getMedicationRequest(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null)
            return fhirDataProvider.getMedicationRequestFroId(userIDForToken);
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @GetMapping(value = "patientInfo/medications/dispense", produces = "application/json")
    public String getMedicationDispense(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null)
            return fhirDataProvider.getMedicationDispensFroId(userIDForToken);
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @GetMapping(value = "patientInfo/medications/administration", produces = "application/json")
    public String getMedicationAdministration(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null)
            return fhirDataProvider.getMedicationAdministrationFroId(userIDForToken);
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @GetMapping(value = "patientInfo/medications/statement", produces = "application/json")
    public String getMedicationStatement(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null)
            return fhirDataProvider.getMedicationStatementFroId(userIDForToken);
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @GetMapping(value = "/patientInfo/observation", produces = "application/json")
    public String getObservations(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null) {
            return fhirDataProvider.getObservationsForId(userIDForToken);
        }
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @GetMapping(value = "/patientInfo/appointments", produces = "application/json")
    public String getAppointments(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null) {
            return fhirDataProvider.getAppointmentsForId(userIDForToken);
        }
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @GetMapping(value = "/patientInfo/allergyIntolerance", produces = "application/json")
    public String getAllergyIntolerance(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null) {
            return fhirDataProvider.getAllergyIntoleranceForId(userIDForToken);
        }
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedAndAddUsername
    @PostMapping(value = "appointments", produces = "application/json")
    public ResponseEntity<String> createAppointment(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @RequestBody AppointmentDTO appointmentDTO) {
        if (userIDForToken == null) {
            throw new ResponseStatusException(FORBIDDEN, "INVALID TOKEN");
        }
        var appointmentID = keycloakService.createResource("Appointment", String.valueOf(userIDForToken), username, appointmentDTO.getName() + "-" + UUID.randomUUID());
        log.info("Generated UUID: {}", appointmentID);
        var generatedID = fhirDataStore.addAppointmentForUser(userIDForToken, appointmentID, appointmentDTO);
        log.info("FHIR Returned {}", generatedID);
        return ResponseEntity.status(CREATED).body(appointmentID);
    }

    @IntrospectedToken
    @PostMapping(value = "/patientInfo/conditions", produces = "application/json")
    public String getConditions(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null) {
            return fhirDataProvider.getConditionsForId(userIDForToken);
        }
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @PostMapping(value = "/patientInfo/immunizations", produces = "application/json")
    public String getImmunizations(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null) {
            return fhirDataProvider.getImmunizationFroId(userIDForToken);
        }
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }

    @IntrospectedToken
    @PostMapping(value = "/patientInfo/carePlan", produces = "application/json")
    public String getCarePlan(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken) {
        if (userIDForToken != null) {
            return fhirDataProvider.getCarePlanFroId(userIDForToken);
        }
        throw new ResponseStatusException(NOT_FOUND, "No Resources found");
    }


}
