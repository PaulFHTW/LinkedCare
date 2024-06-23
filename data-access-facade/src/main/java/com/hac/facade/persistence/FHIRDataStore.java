package com.hac.facade.persistence;

import com.hac.facade.dto.AppointmentDTO;
import com.hac.facade.mapper.ApplicationDtoToFhirMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Appointment;
import org.hl7.fhir.r4.model.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FHIRDataStore {

    private final FHIRContextProvider fhirContextProvider;
    private final FHIRDataProvider fhirDataProvider;
    private final ApplicationDtoToFhirMapper fhirMapper;

    public IBaseResource storeResource(IBaseResource resource) {
        return fhirContextProvider.getClient().create().resource(resource).execute().getResource();
    }

    public String addAppointmentForUser(long userID, String resourceID, AppointmentDTO appointment) {
        var fhirAppointment = fhirMapper.map(appointment);
        var appointmentParticipantComponent = new Appointment.AppointmentParticipantComponent();
        var patientForID = fhirDataProvider.getPatientForID(userID);
        log.info("Got patient {}", patientForID);
        var value = new Reference("Patient/" + patientForID.getIdElement().getIdPart());
        log.info("Reference: {}", value);
        appointmentParticipantComponent.setActor(value);
        fhirAppointment.setParticipant(List.of(appointmentParticipantComponent));
        fhirAppointment.addIdentifier().setValue(resourceID);
        var resource = fhirContextProvider.getClient().create().resource(fhirAppointment).execute().getResource();
        log.info("Stored to hapi {}", fhirAppointment);
        return resource.getIdElement().getIdPart();
    }
}
