DROP TABLE IF EXISTS `original_article`;
CREATE TABLE `original_article` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '标题',
`author_id` BINARY(16) DEFAULT NULL COMMENT '文章作者的ID',
`content` longtext CHARSET utf8mb4 COMMENT '文章内容',
`author_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '作者昵称',
`channel_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '频道名称',
`labels` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '文章标签最多3个 逗号分隔',
`publish_time` datetime DEFAULT NULL COMMENT '发布时间',
`url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '文章url',
`origin` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '来源',
`status` int(5) unsigned DEFAULT '0' COMMENT '文章状态',
`bind_id` int(11) unsigned DEFAULT '0' COMMENT '在article表里的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章信息表，存储已发布的文章';