package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.CreatePatientDTO;
import com.pm.patientservice.dto.PatientDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.UpdatePatientDTO;
import com.pm.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }

    public static Patient toModel(PatientDto patientDto) {
        return Patient.builder()
                .name(patientDto.getName())
                .email(patientDto.getEmail())
                .address(patientDto.getAddress())
                .dateOfBirth(patientDto.getDateOfBirth())
                .registeredDate(patientDto.getRegisteredDate())
                .build();
    }
}
