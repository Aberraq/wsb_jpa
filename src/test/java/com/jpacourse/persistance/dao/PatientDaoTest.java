package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.entity.DoctorEntity;

import com.jpacourse.repository.PatientRepository;
import com.jpacourse.repository.DoctorRepository;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientDaoTest {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientDaoTest(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = patientRepository.findById(patientId).orElseThrow();
        DoctorEntity doctor = doctorRepository.findById(doctorId).orElseThrow();

        VisitEntity visit = new VisitEntity();
        visit.setTime(time);
        visit.setDescription(description);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        patient.getVisits().add(visit);
        patientRepository.save(patient);
    }
}
