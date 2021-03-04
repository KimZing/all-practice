INSERT INTO `label`(name)
VALUES ("Java"),
       ("前端"),
       ("后端"),
       ("Vue"),
       ("React"),
       ("UniApp"),
       ("Python"),
       ("PHP");

INSERT INTO `author`(id, username, avatar) VALUES(1, 'KimZing', '/static/avatar1.png');

INSERT
INTO `article`(author_id, title, content, images, classify, mode, read_count)
VALUES (1, "Java从入门到精通然后到放弃", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", 'Java', 'basic', 100),
    (1, "Go从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-normal.png,/static/demo-normal.png,/static/demo-normal.png,", '后端', 'multi', 200),
    (1, "Python从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", 'Python', 'basic', 300),
    (1, "UniApp从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '前端', 'multi', 1230),
    (1, "前端从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '后端', 'basic', 34),
    (1, "后端从入门到精通", "世界上最好的语言", "/static/demo-big.png", '后端', 'main', 45),
    (1, "PHP从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", 'PHP', 'basic', 45),
    (1, "Ruby从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '后端', 'multi', 23),
    (1, "Rust从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '后端', 'basic', 1),
    (1, "JavaScript从入门到精通", "世界上最好的语言", "/static/demo-big.png", '后端', 'main', 23),
    (1, "HTML从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '后端', 'basic', 55),
    (1, "CSS从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '后端', 'multi', 88),
    (1, "Vue从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", 'Vue', 'basic', 56),
    (1, "React从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", 'React', 'multi', 86),
    (1, "Android从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '后端', 'basic', 37),
    (1, "IOS从入门到精通", "世界上最好的语言", "/static/demo-normal.png,/static/demo-big.png", '后端', 'basic', 27);
