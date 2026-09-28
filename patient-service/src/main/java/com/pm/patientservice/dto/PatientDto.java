package com.pm.patientservice.dto;

import java.time.LocalDate;

public interface PatientDto {
    String getName();
    String getEmail();
    String getAddress();
    LocalDate getDateOfBirth();
    LocalDate getRegisteredDate();
}
