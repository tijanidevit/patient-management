package com.pm.patientservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientDto {
    @NotBlank
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotBlank
    @Email(message = "Please provide a valid email address.")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank
    private String address;

    @NotNull
    @Past(message = "Date of birth must be a date in the past.")
    private LocalDate dateOfBirth;

    @NotNull
    @PastOrPresent(message = "Registered date cannot be in the future.")
    private LocalDate registeredDate;
}
