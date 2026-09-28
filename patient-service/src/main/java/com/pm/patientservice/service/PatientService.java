package com.pm.patientservice.service;

import com.pm.patientservice.dto.CreatePatientDto;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    public PatientResponseDTO create(CreatePatientDto createPatientDto) {
        String userEmail = createPatientDto.getEmail();

        if (patientRepository.existsByEmail(userEmail)) {
            throw new RuntimeException("A patient with this email already exists.");
        }

        Patient patient = patientRepository.save(PatientMapper.toModel(createPatientDto));
        return PatientMapper.toDTO(patient);
    }
}
