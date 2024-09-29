INSERT INTO product_type
    (id, name)
VALUES (1, 'Pivo'),
       (2, 'Sok'),
       (3, 'Voce');

INSERT INTO product
(product_id,
 name,
 unit_of_measure,
 product_type_id)
VALUES (1,
        'Sprite',
        'Komad',
        2),
       (2,
        'Kola',
        'Komad',
        1);


