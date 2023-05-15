INSERT INTO PATIENT (ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, GENDER,
                     ADDRESS, ADDRESS_TYPE, CONTACT, CONTACT_TYPE,
                     POLICY_NUMBER, SNILS)
VALUES
    (1, 'Alex', 'Volkanovsky', '1974-12-19', 1, 'Australia', 1, 'volk777', 2, '123-456', '111-2233-4456-7789'),
    (2, 'Max', 'Holloway', '1985-06-07', 1, 'Hawaii', 1, '+7950', 1, '4356-77', '4747-84547-474'),
    (3, 'Jon', 'Jones', '1981-01-01', 1, 'USA', 1, 'bones', 2, '888-999-77', '36636-7373373'),

    (4, 'Alex', 'Ivanov', '1999-10-10', 1, 'Armenia', 2, 'axel333', 2, '1134-44', '22-333-444-5555');


INSERT INTO DOCUMENT (ID, DOCUMENT_TYPE, DOCUMENT_NUMBER,
                      DATE_OF_ISSUE, EXPIRY_DATE, ISSUER_NAME,
                      IS_ACTIVE, PATIENT_ID)
VALUES
    -- Alex documents
    (1, 1, '10-21-777', '2018-05-15', '2022-05-15', 'AU-GOS', FALSE, 1),
    (2, 1, '10-22-777', '2022-05-15', '2028-05-15', 'AU-GOS', TRUE, 1),
    -- Max documents
    (3, 2, '66-2-777', '2022-05-15', '2028-05-15', 'HAWAII_POLICE', TRUE, 2),
    (4, 1, '777-21-777', '2022-05-15', '2028-05-15', 'HAWAII_GOS', TRUE, 2),
    -- Jon documents
    (5, 1, '6666666', '2021-01-01', '2031-01-01', 'USA-GOS', TRUE, 3);