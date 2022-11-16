CREATE TABLE person
(
    id           long    not null,
    firstname    varchar not null,
    lastname     varchar not null,
    address      text    not null,
    phonenumber  varchar not null,
    mobilenumber varchar,
    emailaddress varchar not null,
    CONSTRAINT PK_PERSON primary key (id)
);
create sequence continent_seq start with 1 increment by 1;


CREATE TABLE user
(
    id               long not null,
    licenseplate     text not null,
    registrationdate date not null,
    FK_PERSON_ID     long,
    CONSTRAINT FK_USER_PERSON foreign key (FK_PERSON_ID) references person (id),
    CONSTRAINT PK_USER primary key (id)
);
create sequence continent_seq start with 1 increment by 1;



