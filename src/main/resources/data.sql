insert into address (id, address_line1, address_line2, city, postal_code)
            values (901, 'xx', 'yy', 'city', '60-400');

-- 1. Adresy
INSERT INTO ADDRESS (id, city, address_line1, address_line2, postal_code) VALUES
(1, 'Warszawa', 'ul. Kwiatowa 12', NULL, '00-123'),
(2, 'Kraków', 'ul. Długa 5', 'm. 10', '31-456');

-- 2. Doktorzy
INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES
(1, 'Anna', 'Kowalska', '123456789', 'anna.kowalska@example.com', 'D123', 'GP', 1);

-- 3. Pacjenci
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES
(1, 'Jan', 'Wiśniewski', '555444333', 'jan.w@example.com', 'P001', '1980-05-15', 2);

-- 4. Wizyty
INSERT INTO VISIT (id, description, time, doctor_id, patient_id) VALUES
(1, 'Konsultacja ogólna', '2025-03-30 10:00:00', 1, 1);

-- 5. Leczenia
INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id) VALUES
(1, 'Badanie EKG', 'EKG', 1);
