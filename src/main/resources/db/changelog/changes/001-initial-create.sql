--liquibase formatted sql
USE
--changeset htec-front-office:createTable
CREATE TABLE voucher
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    date_of_entry DATETIME,
    available_vouchers INT NOT NULL,
    initial_vouchers INT NOT NULL
);

CREATE TABLE issued_voucher
(
    id binary(16) PRIMARY KEY NOT NULL,
    issued_date DATETIME NOT NULL,
    invoice_date DATE,
    realised BOOLEAN,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    comment VARCHAR(255),
    voucher_id BIGINT,
    FOREIGN KEY (voucher_id) REFERENCES voucher(id)
);

CREATE TABLE parking_ticket
(
    id binary(16) PRIMARY KEY NOT NULL,
    start_date DATE,
    end_date DATE,
    registration_plate VARCHAR(255) NOT NULL,
    internal BOOLEAN,
    archived BOOLEAN,
    user_id BIGINT
);

CREATE TABLE flag
(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    alreadySentFlag BIT
);

CREATE TABLE parking_user
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    seniority_type VARCHAR(255) NOT NULL,
    position SMALLINT,
    date_of_employment DATE,
    disability BOOLEAN
);

CREATE TABLE section
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE task
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    frequency VARCHAR(255) NOT NULL,
    section VARCHAR(255)
);

CREATE TABLE activity
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    task_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES task(id)
);

CREATE TABLE orders
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    order_number VARCHAR(255) NOT NULL,
    date_of_creation DATE NOT NULL
);

CREATE TABLE order_item
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    quantity INTEGER,
    name VARCHAR(255) NOT NULL,
    unit_of_measure VARCHAR(255) NOT NULL,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

CREATE TABLE product_type
(
    Id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE product
(
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    unit_of_measure VARCHAR(255) NOT NULL,
    product_type_id BIGINT,
    FOREIGN KEY (product_type_id) REFERENCES product_type(Id)
);


