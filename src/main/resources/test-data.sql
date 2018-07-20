use database_contact;
insert into CONTACT (FIRST_NAME, LAST_NAME, BIRTH_DATE) values ('Clarence', 'Ho', '1980-07-30');
insert into CONTACT (FIRST_NAME, LAST_NAME, BIRTH_DATE) values ('Scott', 'Tiger', '1990-11-02');
insert into CONTACT (FIRST_NAME, LAST_NAME, BIRTH_DATE) values ('John', 'Smith', '1964-02-28');

insert into CONTACT_TEL_DETAIL(CONTACT_ID, TEL_TYPE, TEL_NUMBER) values (1, 'Mobile', '1234567890');
insert into CONTACT_TEL_DETAIL(CONTACT_ID, TEL_TYPE, TEL_NUMBER) values (1, 'Home', '1234567890');
insert into CONTACT_TEL_DETAIL(CONTACT_ID, TEL_TYPE, TEL_NUMBER) values (2, 'Home', '1234567890');