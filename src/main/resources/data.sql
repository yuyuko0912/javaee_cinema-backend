-- 先清空票表
DELETE FROM ticket;

-- 再清空电影表
DELETE FROM movie;

ALTER TABLE movie AUTO_INCREMENT = 1;
ALTER TABLE ticket AUTO_INCREMENT = 1;

-- 插入电影示例数据
INSERT INTO movie
(name, name_english, copy_right, film_img, story_type, place, time_length, time_show, cinema, room, show_time, unit_price)
VALUES
    ('星际穿越', 'Interstellar', '正版IMAX', 'assets/interstellar.png', '科幻/冒险', '美国', 169, '2014-11-07', '星际影院', '1号厅', '2025-09-25 18:00:00', 45.00),
    ('蝴蝶效应', 'The Butterfly Effect', '正版2D', 'assets/butterfly.png', '剧情/科幻', '美国', 113, '2004-03-12', '星际影院', '2号厅', '2025-09-25 20:30:00', 38.00),
    ('泰坦尼克号', 'Titanic', '正版4K修复', 'assets/titanic.png', '爱情/灾难', '美国', 195, '1997-12-19', '经典影院', 'IMAX厅', '2025-09-26 14:00:00', 55.00),
    ('关于莉莉周的一切', 'All About Lily Chou-Chou', '日版修复', 'assets/lilychou.png', '剧情/青春', '日本', 146, '2001-10-06', '艺术影院', '3号厅', '2025-09-26 16:30:00', 42.00),
    ('风之谷', 'Nausicaä of the Valley of the Wind', '吉卜力正版', 'assets/nausicaa.png', '动画/科幻', '日本', 117, '1984-03-11', '动漫影院', '4号厅', '2025-09-26 19:00:00', 48.00),
    ('千与千寻', 'Spirited Away', '吉卜力正版', 'assets/spirited.png', '动画/奇幻', '日本', 125, '2001-07-20', '动漫影院', '4号厅', '2025-09-27 15:00:00', 50.00),
    ('盗梦空间', 'Inception', '正版IMAX', 'assets/inception.png', '科幻/悬疑', '美国', 148, '2010-07-16', '星际影院', '1号厅', '2025-09-27 20:00:00', 52.00),
    ('霸王别姬', 'Farewell My Concubine', '4K修复版', 'assets/farewell.png', '剧情/爱情', '中国', 171, '1993-01-01', '经典影院', '2号厅', '2025-09-28 13:30:00', 46.00);
-- 插入票示例数据
INSERT INTO ticket
(movie_id, seat_number, buyer_name)
VALUES
    (1, 1, '张洋'),
    (6, 6, '聂志辉'),
    (2, 10, '张傲'),
    (3,6,'李嘉鑫'),
    (4,5,'张宏'),
    (7,2,'谢茜');

# 更新数据库图片存储路径。
UPDATE movie SET film_img = CONCAT('/', film_img) WHERE film_img NOT LIKE '/%';
SELECT id, name, film_img FROM movie;


