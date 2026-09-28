package com.pm.patientservice.service;

import com.pm.patientservice.dto.CreatePatientDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.UpdatePatientDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.ModelNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public PatientResponseDTO create(CreatePatientDTO createPatientDto) {
        String userEmail = createPatientDto.getEmail();

        if (patientRepository.existsByEmail(userEmail)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists.");
        }

        Patient patient = patientRepository.save(PatientMapper.toModel(createPatientDto));
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO update(UUID id, UpdatePatientDTO updatePatientDto) {
        Patient patient = this.patientRepository.findById(id).orElseThrow(()-> new ModelNotFoundException("Patient not found"));

        if (!patient.getEmail().equals(updatePatientDto.getEmail())
                && patientRepository.existsByEmail(updatePatientDto.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists."
            );
        }

        patient.setName(updatePatientDto.getName());
        patient.setEmail(updatePatientDto.getEmail());
        patient.setAddress(updatePatientDto.getAddress());
        patient.setDateOfBirth(updatePatientDto.getDateOfBirth());
        patient.setRegisteredDate(updatePatientDto.getRegisteredDate());

        return PatientMapper.toDTO(patientRepository.save(patient));
    }
}
