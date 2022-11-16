CREATE TABLE person
(
    id           bigint  not null,
    firstname    varchar not null,
    lastname     varchar not null,
    address      text    not null,
    phonenumber  varchar not null,
    mobilenumber varchar,
    emailaddress varchar not null,
    CONSTRAINT PK_PERSON primary key (id)
);
create sequence person_seq start with 1 increment by 1;


CREATE TABLE users
(
    id                bigint not null,
    license_plate     text   not null,
    registration_date date   not null,
    FK_PERSON_ID      bigint not null,
    CONSTRAINT FK_USER_PERSON foreign key (FK_PERSON_ID) references person (id),
    CONSTRAINT PK_USER primary key (id)
);
create sequence user_seq start with 1 increment by 1;



