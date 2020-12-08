DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '标题',
  `author_id` bigint unsigned NOT NULL COMMENT '文章作者的ID',
  `author_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '作者昵称',
  `channel_id` int unsigned DEFAULT '0' COMMENT '文章所属频道ID',
  `channel_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '频道名称',
  `images` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '文章图片\r\n            多张逗号分隔',
  `labels` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '文章标签最多3个 逗号分隔',
  `likes` int unsigned DEFAULT '0' COMMENT '点赞数量',
  `collection` int unsigned DEFAULT '0' COMMENT '收藏数量',
  `comment` int unsigned DEFAULT '0' COMMENT '评论数量',
  `views` int unsigned DEFAULT '0' COMMENT '阅读数量',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `origin` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '来源',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '文章内容',
  `status` tinyint(1) unsigned DEFAULT '0' COMMENT '文章状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `author_id`(`author_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章信息表，存储已发布的文章';