DROP TABLE IF EXISTS `comment`;
CREATE TABLE `user_article_list` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `author_id` bigint unsigned NOT NULL COMMENT '评论作者的ID',
  `article_id` bigint unsigned NOT NULL COMMENT '评论文章的ID',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `author_id`(`author_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章评论列表';