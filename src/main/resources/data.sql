-- 插入电影示例数据
INSERT INTO movie
(name, name_english, copy_right, film_img, story_type, place, time_length, time_show, cinema, room, show_time, unit_price)
VALUES
    ('星际穿越', 'Interstellar', '正版IMAX', 'assets/interstellar.png', '科幻/冒险', '美国', 169, '2014-11-07', '星际影院', '1号厅', '2025-09-25 18:00:00', 45.00),
    ('蝴蝶效应', 'The Butterfly Effect', '正版2D', 'assets/butterfly.png', '剧情/科幻', '美国', 113, '2004-03-12', '星际影院', '2号厅', '2025-09-25 20:30:00', 38.00);

-- 插入票示例数据
INSERT INTO ticket
(movie_id, seat_number, buyer_name)
VALUES
    (1, 1, '张三'),
    (1, 2, '李四'),
    (2, 10, '王五');
