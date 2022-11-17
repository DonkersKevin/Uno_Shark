insert into address (id, country, housenumber, city, postal_code, streetname)
values (nextval('address_seq'),'Belgium','22','Leuven','3000','Langestraat');

insert into address (id, country, housenumber, city, postal_code, streetname)
values (nextval('address_seq'),'Belgium','1','Antwerpen','2000','Kortestraat');

insert into address (id, country, housenumber, city, postal_code, streetname)
values (nextval('address_seq'),'Belgium','5','Brussel','1000','Hogestraat');

insert into address (id, country, housenumber, city, postal_code, streetname)
values (nextval('address_seq'),'Belgium','7','Hasselt','4000','Lagestraat');

insert into address (id, country, housenumber, city, postal_code, streetname)
values (nextval('address_seq'),'Belgium','78','Luik','5000','Rue haute');

insert into address (id, country, housenumber, city, postal_code, streetname)
values (nextval('address_seq'),'Belgium','22','Namur','4500','Rue courte');

insert into address (id, country, housenumber, city, postal_code, streetname)
values (nextval('address_seq'),'Belgium','175','Brussel','1030','Boulevard de la distance');



insert into division (id, director, name, original_name)
values (nextval('division_seq'),'Freddy', 'UGC', 'KBC');

insert into division (id, director, name, original_name)
values (nextval('division_seq'),'Tommy', 'KinePolis', 'Belfius');

insert into division (id, director, name, original_name)
values (nextval('division_seq'),'Freddy', 'Pathé', 'BNP');


INSERT INTO person (id, email_address, first_name, last_name, mobile_number, phone_number, fk_address_id)
VALUES (nextval('person_seq'), 'stefbemindt@switchfully.com', 'Stef', 'Bemindt', '04700000', '022500000', 1);

INSERT INTO person (id, email_address, first_name, last_name, mobile_number, phone_number, fk_address_id)
VALUES (nextval('person_seq'), 'janbroeckx@switchfully.com', 'Jan', 'Broeckx', '04700001', '022500001', 2);

INSERT INTO person (id, email_address, first_name, last_name, mobile_number, phone_number, fk_address_id)
VALUES (nextval('person_seq'), 'rubenfobe@switchfully.com', 'Ruben', 'Fobe', '04700002', '022500002', 3);

INSERT INTO person (id, email_address, first_name, last_name, mobile_number, phone_number, fk_address_id)
VALUES (nextval('person_seq'), 'stijnschouppe@switchfully.com', 'Stijn', 'Schouppe', '04700003', '022500003', 4);

INSERT INTO person (id, email_address, first_name, last_name, mobile_number, phone_number, fk_address_id)
VALUES (nextval('person_seq'), 'kevindonkers@switchfully.com', 'Kevin', 'Donkers', '04700004', '022500004', 5);

INSERT INTO person (id, email_address, first_name, last_name, mobile_number, phone_number, fk_address_id)
VALUES (nextval('person_seq'), 'haroldvandoo@switchfully.com', 'Harold', 'van Doorslaer', '04700005', '022500005', 6);

INSERT INTO person (id, email_address, first_name, last_name, mobile_number, phone_number, fk_address_id)
VALUES (nextval('person_seq'), 'timvercruysse@switchfully.com', 'Tim', 'Vercruysse', '04700006', '022500006', 7);

INSERT INTO license_plate(id, issuing_country, license_plate_number)
VALUES (nextval('licenseplate_seq'), 'BE', 'ABC123');

INSERT INTO license_plate(id, issuing_country, license_plate_number)
VALUES (nextval('licenseplate_seq'), 'BE', 'ABC124');

INSERT INTO license_plate(id, issuing_country, license_plate_number)
VALUES (nextval('licenseplate_seq'), 'BE', 'ABC125');

INSERT INTO license_plate(id, issuing_country, license_plate_number)
VALUES (nextval('licenseplate_seq'), 'BE', 'ABC126');

INSERT INTO license_plate(id, issuing_country, license_plate_number)
VALUES (nextval('licenseplate_seq'), 'BE', 'ABC127');

INSERT INTO license_plate(id, issuing_country, license_plate_number)
VALUES (nextval('licenseplate_seq'), 'BE', 'ABC128');

INSERT INTO license_plate(id, issuing_country, license_plate_number)
VALUES (nextval('licenseplate_seq'), 'BE', 'ABC129');

insert into parkinglot (id, capacity, name, parking_category, amount, currency, fk_address_id, fk_person_id, fk_division_id)
values (nextval('parkinglot_seq'),400,'Goeminne','UNDERGROUND', 8.00,'EUR',1,3,2);

insert into parkinglot (id, capacity, name, parking_category, amount, currency, fk_address_id, fk_person_id, fk_division_id)
values (nextval('parkinglot_seq'),250,'Center','ABOVEGROUND', 6.00,'EUR',3,4,1);

insert into parkinglot (id, capacity, name, parking_category, amount, currency, fk_address_id, fk_person_id, fk_division_id)
values (nextval('parkinglot_seq'),20,'rue Haute','UNDERGROUND', 2.00,'EUR',5,6,3);

insert into users(id, registration_date, fk_licenseplate_id, fk_person_id, role, member_level)
values (nextval('user_seq'), '2010-12-01',  1, 1, 'MANAGER','GOLD');

insert into users(id, registration_date, fk_licenseplate_id, fk_person_id, role, member_level)
values (nextval('user_seq'), '2015-08-05',  2, 2, 'MEMBER','GOLD');

insert into users(id, registration_date, fk_licenseplate_id, fk_person_id, role, member_level)
values (nextval('user_seq'), '2021-03-11',  3, 3, 'MEMBER','SILVER');

insert into users(id, registration_date, fk_licenseplate_id, fk_person_id, role, member_level)
values (nextval('user_seq'), '2016-06-06',  4, 4, 'MEMBER','SILVER');



