CREATE TABLE IF NOT EXISTS `label`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(20) NOT NULL COMMENT '标签名',
    `show`        int(1)      NOT NULL DEFAULT 1 COMMENT '是否显示',
    `deleted`     int(1)      NOT NULL DEFAULT 0 COMMENT '是否删除 0:未删除 1:已删除',
    `creator`     varchar(20)          DEFAULT NULL COMMENT '创建人',
    `modifier`    varchar(20)          DEFAULT NULL COMMENT '更新人',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` datetime    NOT NUll DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `index_deleted` (`deleted`) USING BTREE COMMENT '是否删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT '标签信息';

CREATE TABLE IF NOT EXISTS `user`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`    varchar(20)  NOT NULL COMMENT '用户名',
    `avatar`      varchar(200) NOT NULL COMMENT '头像',
    `deleted`     int(1)       NOT NULL DEFAULT 0 COMMENT '是否删除 0:未删除 1:已删除',
    `creator`     varchar(20)           DEFAULT NULL COMMENT '创建人',
    `modifier`    varchar(20)           DEFAULT NULL COMMENT '更新人',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` datetime     NOT NUll DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `index_deleted` (`deleted`) USING BTREE COMMENT '是否删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT '用户信息';

CREATE TABLE IF NOT EXISTS `article`
(
    `id`               int(11)       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `author_id`          int(11)       NOT NULL COMMENT '作者ID',
    `title`            varchar(50)   NOT NULL COMMENT '文章标题',
    `content`          text          NOT NULL COMMENT '文章内容',
    `images`           varchar(2000) NOT NULL COMMENT '文章图片',
    `classify`         varchar(200)           DEFAULT NULL COMMENT '分类信息',
    `mode`             varchar(50)   NOT NULL DEFAULT 'basic' COMMENT '卡片模式',
    `read_count`       int(10)                DEFAULT 0 COMMENT '阅读量',
    `collection_count` int(10)                DEFAULT 0 COMMENT '文章集合',
    `comment_count`    int(10)                DEFAULT 0 COMMENT '评论数',
    `thumbs_up_count`  int(10)                DEFAULT 0 COMMENT '点赞量',
    `deleted`          int(1)        NOT NULL DEFAULT 0 COMMENT '是否删除 0:未删除 1:已删除',
    `creator`          varchar(20)            DEFAULT NULL COMMENT '创建人',
    `modifier`         varchar(20)            DEFAULT NULL COMMENT '更新人',
    `create_time`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time`      datetime      NOT NUll DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `index_deleted` (`deleted`) USING BTREE COMMENT '是否删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT '文章信息';

CREATE TABLE IF NOT EXISTS `r_user_like_article`
(
    `id`          int(11)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`     int(11)  NOT NULL COMMENT '用户ID',
    `article_id`  int(11)  NOT NULL COMMENT '文章ID',
    `creator`     varchar(20)       DEFAULT NULL COMMENT '创建人',
    `modifier`    varchar(20)       DEFAULT NULL COMMENT '更新人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` datetime NOT NUll DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY user_article(`user_id`, `article_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT '用户文章收藏';