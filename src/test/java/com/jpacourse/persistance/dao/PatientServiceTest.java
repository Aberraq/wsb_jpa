package com.jpacourse.service;

import com.jpacourse.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    PatientService service;

    @Autowired
    PatientRepository repository;

    @Test
    void shouldDeletePatientAndKeepDoctors() {
        Long patientId = 1L;
        service.deleteById(patientId);

        assertThat(repository.findById(patientId)).isEmpty();
        
    }

    @Test
    void shouldGetPatientTOWithVisits() {
        var to = service.getById(1L);
        assertThat(to).isNotNull();
        assertThat(to.getVisits()).isNotEmpty();
        assertThat(to.isInsured()).isTrue();
    }
}
