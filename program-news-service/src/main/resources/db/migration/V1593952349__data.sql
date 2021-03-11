INSERT INTO `label`(id, name, `show`)
VALUES (1, "前端", 0),
       (2, "后端", 0),
       (3, "Java", 1),
       (4, "Vue", 1),
       (5, "React", 1),
       (6, "UniApp", 1),
       (7, "Python", 1),
       (8, "PHP", 1),
       (9, "Go", 1),
       (10, "Rust", 1),
       (11, "HTML", 1),
       (12, "Android", 1),
       (13, "IOS", 1),
       (14, "Ruby", 1);

INSERT INTO `user`(id, username, avatar)
VALUES (1, 'KimZing', '/static/avatar1.png');

INSERT
INTO `article`(id, author_id, title, content, images, classify, mode, read_count)
VALUES (1, 1, "Java从入门到精通然后到放弃", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png,/static/demo-normal.png",
        'Java,后端', 'basic', 100),
       (2, 1, "Go从入门到精通", "世界上最好的语言",
        "/static/demo-normal.png,/static/demo-normal.png,/static/demo-normal.png,/static/demo-normal.png", 'Go,后端',
        'multi', 200),
       (3, 1, "Python从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", '后端,Python', 'basic', 300),
       (4, 1, "UniApp从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", '前端,UniApp', 'multi',
        1230),
       (5, 1, "前端从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", '前端', 'basic', 34),
       (6, 1, "后端从入门到精通", "世界上最好的语言", "/static/demo-normal.png", '后端', 'main', 45),
       (7, 1, "PHP从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", 'PHP,后端,前端', 'basic', 45),
       (8, 1, "Ruby从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", 'Ruby,后端', 'multi', 23),
       (9, 1, "Rust从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", 'Rust,后端', 'basic', 1),
       (10, 1, "JavaScript从入门到精通", "世界上最好的语言", "/static/demo-normal.png", '前端,后端', 'main', 23),
       (11, 1, "HTML从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", '前端,HTML', 'basic', 55),
       (12, 1, "CSS从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png,/static/demo-normal.png",
        '前端', 'multi', 88),
       (13, 1, "Vue从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", 'Vue,前端', 'basic', 56),
       (14, 1, "React从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", 'React,前端', 'multi', 86),
       (15, 1, "Android从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", '前端,Android', 'basic', 37),
       (16, 1, "IOS从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png", 'IOS,前端', 'basic', 27);

INSERT INTO `r_user_like_article`(id, user_id, article_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3);