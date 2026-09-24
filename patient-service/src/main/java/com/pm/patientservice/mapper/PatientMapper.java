package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.CreatePatientDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

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

    public static Patient toModel(CreatePatientDto createPatientDto) {
        return Patient.builder()
                .name(createPatientDto.getName())
                .email(createPatientDto.getEmail())
                .address(createPatientDto.getAddress())
                .dateOfBirth(LocalDate.parse(createPatientDto.getDateOfBirth()))
                .registeredDate(LocalDate.parse(createPatientDto.getRegisteredDate()))
                .build();
    }
}
