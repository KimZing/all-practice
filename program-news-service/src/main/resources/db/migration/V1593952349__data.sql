INSERT INTO `label`(name)
VALUES ("Java"),
       ("前端"),
       ("后端"),
       ("Vue"),
       ("React"),
       ("UniApp"),
       ("Python"),
       ("PHP");

INSERT INTO `user`(id, username, avatar)
VALUES (1, 'KimZing', '/static/avatar1.png');

INSERT
INTO `article`(id, author_id, title, content, images, classify, mode, read_count)
VALUES (1, 1, "Java从入门到精通然后到放弃", "世界上最好的语言", "/static/demo-normal4.gif,/static/demo-big.png,/static/demo-normal5.gif", 'Java,后端', 'basic', 100),
       (2, 1, "Go从入门到精通", "世界上最好的语言",
        "/static/demo-normal1.png,/static/demo-normal2.png,/static/demo-normal3.png,/static/demo-normal4.gif", 'Go,后端',
        'multi', 200),
       (3, 1, "Python从入门到精通", "世界上最好的语言", "/static/demo-normal3.png,/static/demo-big.png", '后端,Python', 'basic', 300),
       (4, 1, "UniApp从入门到精通", "世界上最好的语言", "/static/demo-normal2.png,/static/demo-normal3.png", '前端,UniApp', 'multi',
        1230),
       (5, 1, "前端从入门到精通", "世界上最好的语言", "/static/demo-normal1.png,/static/demo-big.png", '前端', 'basic', 34),
       (6, 1, "后端从入门到精通", "世界上最好的语言", "/static/demo-big.png", '后端', 'main', 45),
       (7, 1, "PHP从入门到精通", "世界上最好的语言", "/static/demo-normal4.gif,/static/demo-big.png", 'PHP,后端,前端', 'basic', 45),
       (8, 1, "Ruby从入门到精通", "世界上最好的语言", "/static/demo-normal1.png,/static/demo-normal4.gif", 'Ruby,后端', 'multi', 23),
       (9, 1, "Rust从入门到精通", "世界上最好的语言", "/static/demo-normal3.png,/static/demo-big.png", 'Rust,后端', 'basic', 1),
       (10, 1, "JavaScript从入门到精通", "世界上最好的语言", "/static/demo-big.png", '前端,后端', 'main', 23),
       (11, 1, "HTML从入门到精通", "世界上最好的语言", "/static/demo-normal1.png,/static/demo-big.png", '前端,HTML', 'basic', 55),
       (12, 1, "CSS从入门到精通", "世界上最好的语言", "/static/demo-normal3.png,/static/demo-normal2.png,/static/demo-normal5.gif", '前端', 'multi', 88),
       (13, 1, "Vue从入门到精通", "世界上最好的语言", "/static/demo-normal1.png,/static/demo-big.png", 'Vue,前端', 'basic', 56),
       (14, 1, "React从入门到精通", "世界上最好的语言", "/static/demo-normal2.png,/static/demo-normal1.png", 'React,前端', 'multi', 86),
       (15, 1, "Android从入门到精通", "世界上最好的语言", "/static/demo-normal1.png,/static/demo-big.png", '前端,Android', 'basic', 37),
       (16, 1, "IOS从入门到精通", "世界上最好的语言", "/static/demo-normal2.png,/static/demo-big.png", 'IOS,前端', 'basic', 27);

INSERT INTO `r_user_like_article`(id, user_id, article_id) VALUES (1, 1, 1),(2, 1, 2),(3, 1, 3);