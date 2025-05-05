package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.entity.DoctorEntity;

import com.jpacourse.repository.PatientRepository;
import com.jpacourse.repository.DoctorRepository;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PatientDao {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @PersistenceContext
    private EntityManager em;

    public PatientDao(PatientRepository patientRepository, DoctorRepository doctorRepository) {
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

    
    public List<PatientEntity> findByLastName(String lastName) {
        return em.createQuery("SELECT p FROM PatientEntity p WHERE p.lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }


    public List<PatientEntity> findWithMoreThanXVisits(long x) {
        return em.createQuery("SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :x", PatientEntity.class)
                .setParameter("x", x)
                .getResultList();
    }


    public List<PatientEntity> findRegisteredBefore(LocalDate date) {
        return em.createQuery("SELECT p FROM PatientEntity p WHERE p.registrationDate < :date", PatientEntity.class)
                .setParameter("date", date)
                .getResultList();
    }
}
