package com.jpacourse.repository;

import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {


    @Query("SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId")
    List<VisitEntity> findAllByPatientId(@Param("patientId") Long patientId);
}
