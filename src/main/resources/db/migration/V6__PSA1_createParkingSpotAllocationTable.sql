create table PARKINGSPOTALLOCATION
(
    ID                 bigint unique not null primary key ,
    fk_users_id        bigint references users,
    fk_licensePlate_id bigint references license_plate,
    fk_parkinglot_id   bigint references parkinglot,
    startTime          time,
    endTime            time,
    isActive           boolean
);

