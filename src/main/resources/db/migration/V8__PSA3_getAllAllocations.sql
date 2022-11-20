drop table PARKINGSPOTALLOCATION;

drop sequence spotallocation_seq;

create table PARKINGSPOTALLOCATION
(
    ID                 bigint unique not null primary key ,
    fk_users_id        bigint references users,
    fk_licensePlate_id bigint references license_plate,
    fk_parkinglot_id   bigint references parkinglot,
    startTime          timestamp,
    endTime            timestamp,
    isActive           boolean
);

CREATE SEQUENCE spotallocation_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;


insert into parkingspotallocation(id, fk_users_id, fk_licenseplate_id, fk_parkinglot_id, starttime, endtime, isactive)
values (nextval('spotallocation_seq'), 1,  1, 1, '20220618 10:34:09','20220618 12:34:09',false);
insert into parkingspotallocation(id, fk_users_id, fk_licenseplate_id, fk_parkinglot_id, starttime, endtime, isactive)
values (nextval('spotallocation_seq'), 1,  1, 1, '20220619 10:34:09','20220619 12:34:09',false);
insert into parkingspotallocation(id, fk_users_id, fk_licenseplate_id, fk_parkinglot_id, starttime, isactive)
values (nextval('spotallocation_seq'), 1,  1, 1, '20221118 10:34:09',true);