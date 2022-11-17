create table DIVISION
(
    ID            integer unique not null,
    PARENT        integer,
    NAME          varchar(52)    not null,
    ORIGINAL_NAME varchar(52)    not null,
    DIRECTOR      varchar(52)    not null,
    CONSTRAINT PK_ID PRIMARY KEY (ID),
    CONSTRAINT fk_parent_id FOREIGN KEY (PARENT) REFERENCES DIVISION (ID)
);

CREATE SEQUENCE division_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;