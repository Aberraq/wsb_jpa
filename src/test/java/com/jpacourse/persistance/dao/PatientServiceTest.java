package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.repository.DoctorRepository;
import com.jpacourse.repository.PatientRepository;
import com.jpacourse.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    PatientService service;

    @Autowired
    PatientRepository repository;

    @Autowired
    DoctorRepository doctorRepository;

    @Transactional
    @Rollback
    @Test
    void shouldDeletePatientAndKeepDoctors() {

        AddressEntity address = new AddressEntity();
        address.setAddressLine1("ul. Tymczasowa 99");
        address.setAddressLine2("lok. 9");
        address.setCity("Tymczasowo");
        address.setPostalCode("99-999");


        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Test");
        patient.setLastName("Delete");
        patient.setTelephoneNumber("000000000");
        patient.setPatientNumber("TMP001");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setRegistrationDate(LocalDate.of(2023, 1, 1));
        patient.setInsured(true);
        patient.setAddress(address);

        patient = repository.save(patient);


        service.deleteById(patient.getId());


        assertThat(repository.findById(patient.getId())).isEmpty();
        assertThat(doctorRepository.findById(1L)).isPresent();
    }




    @Test
    void shouldGetPatientTOWithVisits() {
        var to = service.getById(1L);
        assertThat(to).isNotNull();
        assertThat(to.getVisits()).isNotEmpty();
        assertThat(to.isInsured()).isTrue();
        assertThat(to.getRegistrationDate()).isEqualTo(LocalDate.of(2023, 1, 10));
    }
}
