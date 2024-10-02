--liquibase formatted sql
USE
--changeset AndrijaLazic:secondDataInsert
    INSERT INTO product_type
(Id,name)
VALUES
(1,
'Sok');

INSERT INTO product
(product_id,
 name,
 unit_of_measure,
 product_type_id)
VALUES (1,
        'Kola',
        'Komad',
        1),
       (2,
        'Fanta',
        'Komad',
        1),
        (4,
         'Sokic',
         'Komad',
         1),
       (3,
        'Kofola',
        'Komad',
        1);