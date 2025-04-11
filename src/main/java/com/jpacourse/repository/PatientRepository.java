package com.jpacourse.repository;

import com.jpacourse.persistance.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    // 1. Znajdź pacjentów po nazwisku
    @Query("SELECT p FROM PatientEntity p WHERE p.lastName = :lastName")
    List<PatientEntity> findByLastName(@Param("lastName") String lastName);

    // 3. Pacjenci z więcej niż X wizyt
    @Query("SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :visitCount")
    List<PatientEntity> findPatientsWithMoreThanXVisits(@Param("visitCount") long visitCount);

    // 4. Znajdź pacjentów ubezpieczonych
    @Query("SELECT p FROM PatientEntity p WHERE p.insured = true")
    List<PatientEntity> findAllInsured();
}
