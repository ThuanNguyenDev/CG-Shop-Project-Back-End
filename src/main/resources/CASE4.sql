


CREATE DATABASE case_m4;

USE case_m4;

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    price INT NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone_number VARCHAR(20) UNIQUE,
    address VARCHAR(255)
);


CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE shopping_carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE cart_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shopping_cart_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO category (name) VALUES
             ('Electronics'),
             ('Clothing'),
             ('Home & Garden'),
             ('Toys'),
             ('Books'),
             ('Sports'),
             ('Beauty'),
             ('Automotive'),
             ('Health'),
             ('Music'),
             ('Movies'),
             ('Jewelry'),
             ('Furniture'),
             ('Food & Drink'),
             ('Pets'),
             ('Baby'),
             ('Office'),
             ('Tools'),
             ('Travel'),
             ('Art');

INSERT INTO product (name, price, category_id) VALUES
    ('Smartphone', 499, 1),
    ('Laptop', 899, 1),
    ('T-shirt', 19, 2),
    ('Jeans', 39, 2),
    ('Lawn Mower', 299, 3),
    ('Grill', 399, 3),
    ('Action Figure', 9, 4),
    ('Board Game', 29, 4),
    ('Science Fiction Novel', 12, 5),
    ('Fitness Equipment', 199, 6),
    ('Soccer Ball', 15, 6),
    ('Shampoo', 5, 7),
    ('Car Battery', 79, 8),
    ('Vitamin C', 10, 9),
    ('Vinyl Record', 20, 10),
    ('Blu-ray Disc', 15, 11),
    ('Diamond Ring', 2999, 12),
    ('Sofa', 599, 13),
    ('Coffee', 5, 14),
    ('Dog Food', 20, 15);


SELECT * from users;
SELECT * from category;
SELECT * from product;

SELECT * from shopping_carts;
SELECT * from cart_items;

SELECT * from orders;
SELECT * from order_items;

DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP DATABASE case_m4;




ALTER TABLE product
ADD image_url VARCHAR(255);


INSERT INTO users (username, password, email, phone_number, address)
VALUES

    ('user3', 'password3', 'user3@example.com', '5555555555', 'Address 3'),
    ('user4', 'password4', 'user4@example.com', '1111111111', 'Address 4'),
    ('user5', 'password5', 'user5@example.com', '9999999999', 'Address 5'),
    ('user6', 'password6', 'user6@example.com', '7777777777', 'Address 6'),
    ('user7', 'password7', 'user7@example.com', '8888888888', 'Address 7'),
    ('user8', 'password8', 'user8@example.com', '2222222222', 'Address 8'),
    ('user9', 'password9', 'user9@example.com', '3333333333', 'Address 9'),
    ('user10', 'password10', 'user10@example.com', '4444444444', 'Address 10');
