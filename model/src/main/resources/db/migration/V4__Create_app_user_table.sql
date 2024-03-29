DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户唯一id',
  `user_name` varchar(20) NOT NULL COMMENT '用户昵称',
  `account` varchar(30) NOT NULL COMMENT '登陆帐户',
  `password` varchar(50) NOT NULL COMMENT '密码 BASE64加密',
  `phone_number` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '手机号',
  `profile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '头像',
  `sex` tinyint(1) unsigned DEFAULT '0' COMMENT '0 男\r\n            1 女\r\n            2 未知',
  `is_certification` tinyint(1) unsigned DEFAULT '0' COMMENT '0 未\r\n            1 是',
  `is_identity_authentication` tinyint(1) DEFAULT '0' COMMENT '是否身份认证',
  `status` tinyint(1) unsigned DEFAULT '0' COMMENT '0正常\r\n            1锁定',
  `flag` tinyint(1) unsigned DEFAULT '0' COMMENT '0 普通用户\r\n            1 自媒体人\r\n            2 大V',
  `region` varchar(30) NOT NULL COMMENT '所在地区',
  `birthday` timestamp COMMENT '生日',
  `created_time` timestamp COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息表';