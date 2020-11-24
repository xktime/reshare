DROP TABLE IF EXISTS `crwaler_article`;
CREATE TABLE `crwaler_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '标题',
`content` longtext CHARSET utf8mb4 COMMENT '文章内容',
`author_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '作者昵称',
`channel_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '频道名称',
`labels` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '文章标签最多3个 逗号分隔',
`publish_time` datetime DEFAULT '0' COMMENT '发布时间',
`url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '文章url',
`origin` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '来源',
`status` int(5) unsigned DEFAULT '0' COMMENT '文章状态',
`bind_id` int(11) unsigned DEFAULT '0' COMMENT '在article表里的id',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='存储爬虫爬取的文章';