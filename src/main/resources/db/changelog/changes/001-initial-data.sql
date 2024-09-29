--liquibase formatted sql
USE
--changeset AndrijaLazic:firstDataInsert

INSERT INTO flag VALUES (1, 0);

INSERT INTO parking_user VALUES (1, 'Ivan', 'Paunovic', 'MANAGER', 5, '2022-01-01', FALSE);
INSERT INTO parking_ticket VALUES ('18594311236a55be', NULL, NULL, 'KG-123-IP', 1, 0, 1);

