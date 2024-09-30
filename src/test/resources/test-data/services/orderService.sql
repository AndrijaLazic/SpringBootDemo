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


-- INSERT INTO order_item
-- (id , quantity, name, unit_of_measure, order_id)
-- VALUES ();


INSERT INTO orders
    (id, order_number, date_of_creation)
VALUES (1,
        1,
        '2024-10-10'),
       (2,
        2,
        '2024-10-10');
