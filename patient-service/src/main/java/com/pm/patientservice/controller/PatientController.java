package com.pm.patientservice.controller;

import com.pm.patientservice.dto.ApiResponse;
import com.pm.patientservice.dto.CreatePatientDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.UpdatePatientDTO;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.util.ApiResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<ApiResponse<PatientResponseDTO>> create(@Valid @RequestBody CreatePatientDTO createPatientDto) {
        PatientResponseDTO patient = patientService.create(createPatientDto);

        return ResponseEntity.status(201).body(ApiResponseUtil.success("Patient created successfully", patient));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDTO>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePatientDTO updatePatientDto) {
        PatientResponseDTO patient = patientService.update(id, updatePatientDto);

        return ResponseEntity.status(200).body(ApiResponseUtil.success("Patient data updated successfully", patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        patientService.delete(id);

        return ResponseEntity.status(200).body(ApiResponseUtil.success("Patient data deleted successfully"));
    }
}
