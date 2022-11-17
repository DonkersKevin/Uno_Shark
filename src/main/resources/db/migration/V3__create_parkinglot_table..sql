create sequence parkinglot_seq start with 1 increment by 1;

create table parkinglot
(
    id               bigint           not null
        primary key,
    capacity         integer          not null,
    name             varchar(255),
    parking_category varchar(255),
    amount           double precision not null,
    currency         varchar(255),
    fk_address_id       bigint
            references address,
    fk_division_id      bigint
            references division,
    fk_person_id        bigint
            references person
);
