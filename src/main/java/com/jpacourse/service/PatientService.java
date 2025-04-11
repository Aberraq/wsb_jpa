package com.jpacourse.service;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatientService(PatientRepository repository, PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
}
