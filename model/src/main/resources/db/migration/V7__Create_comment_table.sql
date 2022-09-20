DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `author_id` bigint unsigned NOT NULL COMMENT '评论作者的ID',
  `bind_id` bigint unsigned NOT NULL COMMENT '绑定ID(文章为article_id)',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
  `publish_time` timestamp COMMENT '发布时间',
  `type` tinyint(1) unsigned DEFAULT '0' COMMENT '评论类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `author_id`(`author_id`) USING BTREE,
  INDEX `bind_id`(`bind_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章评论列表';