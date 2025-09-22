-- 1. 创建数据库
CREATE DATABASE IF NOT EXISTS cinema_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 2. 使用数据库
USE cinema_db;

-- 3. 创建电影表（与 Movie 实体对应）
CREATE TABLE IF NOT EXISTS movie (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     name VARCHAR(255),
                                     name_english VARCHAR(255),
                                     copy_right VARCHAR(50),
                                     film_img VARCHAR(500),
                                     story_type VARCHAR(255),
                                     place VARCHAR(100),
                                     time_length INT,
                                     time_show DATE,
                                     cinema VARCHAR(100),
                                     room VARCHAR(50),
                                     show_time DATETIME,
                                     unit_price DECIMAL(10,2)
);

-- 4. 创建票表（与 Ticket 实体对应）
CREATE TABLE IF NOT EXISTS ticket (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      movie_id BIGINT NOT NULL,
                                      seat_number INT NOT NULL,
                                      buyer_name VARCHAR(100),
                                      CONSTRAINT fk_ticket_movie FOREIGN KEY (movie_id) REFERENCES movie(id)
);
