alter table person
    drop column mobile_number;

alter table person
    drop column phone_number;

alter table person
    add column landline_country_code varchar(255);

alter table person
    add column landline_body varchar(255);

alter table person
    add column mobile_phone_country_code varchar(255);

alter table person
    add column mobile_phone_body varchar(255);
