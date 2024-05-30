CREATE DATABASE IF NOT EXISTS db_pao;
USE db_pao;

CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    hashed_password VARCHAR(255) NOT NULL,
    user_type ENUM('Admin', 'Manager', 'Bidder') NOT NULL
    );

CREATE TABLE IF NOT EXISTS bidder (
    id INT,
    balance FLOAT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS staff (
    id INT,
    experience_years INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
    );

CREATE TABLE IF NOT EXISTS auction (
    auction_id INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    manager_id INT,
    FOREIGN KEY (manager_id) REFERENCES staff(id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS bid (
    item_id INT,
    bidder_id INT,
    auction_id INT,
    startingPrice FLOAT NOT NULL,
    currentPrice FLOAT NOT NULL,
    sold BOOLEAN NOT NULL,
    PRIMARY KEY (item_id, auction_id),
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE,
    FOREIGN KEY (bidder_id) REFERENCES bidder(id),
    FOREIGN KEY (auction_id) REFERENCES auction(auction_id)
    );
