package com.jpacourse.service;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.repository.DoctorRepository;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.repository.PatientRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final DoctorRepository doctorRepository;

    public PatientService(PatientRepository repository, PatientMapper mapper, DoctorRepository doctorRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.doctorRepository = doctorRepository;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PatientTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToTO)
                .orElse(null);
    }

    public PatientEntity addVisit(Long patientId, VisitEntity visit) {
        PatientEntity patient = repository.findById(patientId).orElseThrow();
        visit.setPatient(patient);
        patient.getVisits().add(visit);
        return repository.save(patient);
    }

    public PatientEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        VisitEntity visit = new VisitEntity();
        visit.setTime(time);
        visit.setDescription(description);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        patient.getVisits().add(visit);

        return repository.save(patient);
    }


    public List<VisitEntity> getVisitsByPatientId(Long patientId) {
        return repository.findById(patientId)
                .map(PatientEntity::getVisits)
                .orElse(List.of());
    }
}
