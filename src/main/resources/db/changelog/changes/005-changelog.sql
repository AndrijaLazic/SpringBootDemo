-- liquibase formatted sql

-- changeset kebab:1728755416027-4
DROP TABLE order_item;
CREATE TABLE order_item
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    quantity   INT    NULL,
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

DROP TABLE orders;
CREATE TABLE orders
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    order_number     VARCHAR(255) NOT NULL,
    date_of_creation date         NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

DROP TABLE product;
CREATE TABLE product
(
    product_id      BIGINT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255) NOT NULL,
    unit_of_measure VARCHAR(255) NOT NULL,
    product_type_id BIGINT       NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (product_id)
);

DROP TABLE product_type;
CREATE TABLE product_type
(
    Id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (Id)
);

ALTER TABLE order_item
    ADD CONSTRAINT order_item_ibfk_1 FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE;
CREATE INDEX order_item_ibfk_1 ON order_item (order_id);

ALTER TABLE order_item
    ADD CONSTRAINT order_item_product_product_id_fk FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE;
CREATE INDEX order_item_product_product_id_fk ON order_item (product_id);

ALTER TABLE product
    ADD CONSTRAINT product_ibfk_1 FOREIGN KEY (product_type_id) REFERENCES product_type (Id) ON DELETE NO ACTION;
CREATE INDEX product_type_id ON product (product_type_id);


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
