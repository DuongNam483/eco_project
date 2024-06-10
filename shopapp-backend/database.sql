CREATE DATABASE shopapp;
USE shopapp;
CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT '',  
    created_at DATETiME,
    updated_at DATETIME,
    is_active TINYINT(1) DEFAULT 1,
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0
);

ALTER TABLE users ADD COLUMN role_id int;


CREATE TABLE roles(
    id int PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id);

CREATE TABLE tokens (
    id int PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL.
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

--Đăng nhập fb, gg
CREATE TABLE social_accounts (
    id int PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(20) NOT NULL COMMENT 'Tên nhà social',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL,
    name VARCHAR(100) NOT NULL,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

--Bảng danh mục sản phẩm
CREATE TABLE categories(
    id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '',
);

--Bảng sản phẩm
CREATE TABLE products(
    id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(350),
    price FLOAT NOT NULL CHECK (price >=0 ),
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT DEFAULT '',
    created_at DATETIME,
    updated_at DATETIME,
    category_id int,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

--Đặt hàng
CREATE TABLE orders(
    id int PRIMARY KEY AUTO_INCREMENT,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    fullname VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20),
    total_money FLOAT CHECK(total_money >= 0)
);

ALTER TABLE orders ADD COLUMN shipping_method VARCHAR(100);
ALTER TABLE orders ADD COLUMN shipping_address VARCHAR(100);
ALTER TABLE orders ADD COLUMN shipping_date DATE;
ALTER TABLE orders ADD COLUMN track_number VARCHAR(100);
ALTER TABLE orders ADD COLUMN payment_method VARCHAR(100);

--Xóa 1 đơn hàng -> xóa mềm ->  thêm trường active
ALTER TABLE orders ADD COLUMN active TINYINT(1);
--Trạng thái đơn hàng chỉ đc phép nhận một số giá trị cụ thể
ALTER TABLE orders MODIFY COLUMN status ENUM('pending', 'processing', 'shipped', 'delevered', 'cancelled');

CREATE TABLE orders_details(
    id int PRIMARY KEY AUTO_INCREMENT,
    order_id int,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    product_id int,
    FOREIGN KEY (product_id) REFERENCES products(id),
    price FLOAT CHECK(price >= 0),
    number_of_products int CHECK(number_of_products > 0),
    total_money FLOAT CHECK(total_money >= 0),
    color VARCHAR(20) DEFAULT ''
);

CREATE TABLE product_images(
    id int PRIMARY KEY AUTO_INCREMENT,
    product_id int,
    FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_product_images_product_id 
        FOREIGN Key (product_id) 
        REFERENCES products(id) ON DELETE CASCADE,
    image_url VARCHAR(300)
);