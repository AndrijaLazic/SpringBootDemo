--liquibase formatted sql

--changeset htec-front-office:createTable
create table generated_reports
(
    id            int          not null
        primary key,
    file_path     VARCHAR(500) not null,
    creation_time DATETIME     null,
    file_name     varchar(255) not null,
    constraint generated_reports_pk_2
        unique (file_path)
);