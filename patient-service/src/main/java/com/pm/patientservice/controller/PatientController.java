package com.pm.patientservice.controller;

import com.pm.patientservice.dto.ApiResponse;
import com.pm.patientservice.dto.CreatePatientDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.util.ApiResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Endpoints", description = "Endpoints for retrieving and managing patient records")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientResponseDTO>>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAll();

        return ResponseEntity.ok().body(ApiResponseUtil.success("Patients retrieved successfully", patients));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponseDTO>> create(@Valid @RequestBody CreatePatientDto createPatientDto) {
        PatientResponseDTO patient = patientService.create(createPatientDto);

        return ResponseEntity.status(201).body(ApiResponseUtil.success("Patient created successfully", patient));
    }
}
