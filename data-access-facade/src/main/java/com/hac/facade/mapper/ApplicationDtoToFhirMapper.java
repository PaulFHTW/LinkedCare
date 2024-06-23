package com.hac.facade.mapper;

import com.hac.facade.dto.AppointmentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.r4.model.Appointment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationDtoToFhirMapper {

    public Appointment map (AppointmentDTO appointmentDTO) {
        var appointment = new Appointment();
        appointment.setStart(appointmentDTO.getStartDate());
        appointment.setEnd(appointmentDTO.getEndDate());
        appointment.setDescription(appointmentDTO.getDescription());
        appointment.setComment(appointmentDTO.getName());
        log.info("Mapped appointment {}", appointmentDTO);
        return appointment;
    }
}
