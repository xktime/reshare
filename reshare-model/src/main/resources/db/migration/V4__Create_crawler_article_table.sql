CREATE TABLE `crwaler_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
`title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
`content` longtext CHARSET utf8mb4 COMMENT '文章内容',
`author_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者昵称',
`channel_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '频道名称',
`images` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章图片\r\n            多张逗号分隔',
`labels` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章标签最多3个 逗号分隔',
`publish_time` datetime DEFAULT NULL COMMENT '发布时间',
`url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章url',
`origin` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '来源',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='存储爬虫爬取的文章';