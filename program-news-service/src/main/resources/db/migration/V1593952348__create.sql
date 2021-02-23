CREATE TABLE IF NOT EXISTS `label`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(20) NOT NULL COMMENT '标签名',
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
