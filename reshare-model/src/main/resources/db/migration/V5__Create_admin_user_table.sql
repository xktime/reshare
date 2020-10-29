DROP TABLE IF EXISTS `admin_user`;
-- 管理员用户
CREATE TABLE `admin_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `account` varchar(30) NOT NULL COMMENT '登陆帐户',
  `password` varchar(50) NOT NULL COMMENT '密码 BASE64加密',
  `sex` tinyint(1) unsigned NOT NULL default '0' COMMENT '性别(0:未知;1:男;2:女)',
  `dept_id` varchar(100) NOT NULL COMMENT '部门编号',
  `locked` tinyint(1) unsigned NOT NULL default '0' COMMENT '锁定标志(1:锁定;0:激活)',
  `remark` varchar(50) default NULL COMMENT '备注',
  `user_type` tinyint(1) unsigned default '1' COMMENT '人员类型(1:经办员;2:管理员;3:系统内置人员;)',
  `enable` tinyint(1) NOT NULL default '1' COMMENT '启用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE `index_account`(`account`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='管理系统用户表';