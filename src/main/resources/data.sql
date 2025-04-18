ALTER TABLE PATIENT ADD COLUMN registration_date DATE;
UPDATE PATIENT SET registration_date = '2023-01-10' WHERE id = 1;
UPDATE PATIENT SET registration_date = '2023-06-15' WHERE id = 2;
UPDATE PATIENT SET registration_date = '2024-12-02' WHERE id = 3;


-- Dummy adres (możesz go wywalić jak niepotrzebny)
INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (901, 'xx', 'yy', 'city', '60-400');


-- 1. Adresy
INSERT INTO ADDRESS (id, city, address_line1, address_line2, postal_code) VALUES
(1, 'Warszawa', 'ul. Kwiatowa 12', NULL, '00-123'),
(2, 'Kraków', 'ul. Długa 5', 'm. 10', '31-456');

-- 2. Doktorzy
INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES
(1, 'Anna', 'Kowalska', '123456789', 'anna.kowalska@example.com', 'D123', 'GP', 1);

-- 3. Pacjenci
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, insured) VALUES
(1, 'Jan', 'Wiśniewski', '555444333', 'jan.w@example.com', 'P001', '1980-05-15', 2, true),
(2, 'Anna', 'Nowak', '123123123', 'anna.nowak@example.com', 'P002', '1995-03-10', 1, false);
(3, 'Tomasz', 'Lis', '666777888', 't.lis@example.com', 'P003', '2003-09-20', 2, true);

-- 4. Wizyty
-- Uwaga! podane ręcznie ID, muszą być unikalne!
INSERT INTO VISIT (id, description, time, doctor_id, patient_id) VALUES
(1, 'Konsultacja ogólna', '2025-03-30 10:00:00', 1, 1),
(2, 'Wizyta kontrolna', '2024-01-01 10:00:00', 1, 2),
(3, 'Badanie krwi', '2024-02-15 12:00:00', 1, 2),
(4, 'Szczepienie', '2024-03-01 09:00:00', 1, 2);
(5, 'Wizyta specjalistyczna', '2025-04-01 14:00:00', 1, 1),
(6, 'Szczepienie grypowe', '2025-04-05 08:00:00', 1, 2);
(7, 'Porada telefoniczna', '2025-04-10 11:00:00', 1, 3);
-- 5. Leczenia
INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id) VALUES
(1, 'Badanie EKG', 'EKG', 1);
