create table address
(
    id          bigint not null
        primary key,
    country     varchar(255),
    housenumber varchar(255),
    city        varchar(255),
    postal_code varchar(255),
    streetname  varchar(255)
);

create sequence address_seq start with 1 increment by 1;

create table license_plate
(
    id                   bigint not null
        primary key,
    issuing_country      varchar(255),
    license_plate_number varchar(255)
);

create sequence licenseplate_seq start with 1 increment by 1;

create table person
(
    id            bigint not null
        primary key,
    email_address varchar(255),
    first_name    varchar(255),
    last_name     varchar(255),
    mobile_number varchar(255),
    phone_number  varchar(255),
    fk_address_id bigint
        constraint fk_address_id
            references address
);

create sequence person_seq start with 1 increment by 1;

create table users
(
    id                 bigint not null
        primary key,
    registration_date  date,
    fk_licenseplate_id bigint
        constraint fk_licenseplate_id
            references license_plate,
    fk_person_id       bigint
        constraint fk_person_id
            references person
);

create sequence user_seq start with 1 increment by 1;


