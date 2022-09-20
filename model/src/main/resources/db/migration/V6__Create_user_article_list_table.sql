DROP TABLE IF EXISTS `user_article_list`;
CREATE TABLE `user_article_list` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `channel_id` int(11) unsigned DEFAULT '0' COMMENT '频道ID',
  `article_id` int(11) unsigned DEFAULT '0' COMMENT '动态ID',
  `is_show` tinyint(1) unsigned DEFAULT '0' COMMENT '是否展示',
  `recommend_time` timestamp COMMENT '推荐时间',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否阅读',
  `strategy_id` int(5) unsigned DEFAULT '0' COMMENT '推荐算法',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户文章列表';