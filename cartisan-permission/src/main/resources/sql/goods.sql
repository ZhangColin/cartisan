-- 部门
DROP TABLE IF EXISTS `sys_departments`;
CREATE TABLE `sys_departments` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门Id',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '上级部门',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `level` varchar(255) NOT NULL COMMENT '级别',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- 用户
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `department_id` bigint NOT NULL DEFAULT 0 COMMENT '部门Id',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `telephone` varchar(32) NOT NULL COMMENT '电话',
  `mail` varchar(64) NOT NULL COMMENT '邮箱',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `slat` varchar(64) NOT NULL COMMENT '盐',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：1、正常，0：冻结',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- 权限模块
DROP TABLE IF EXISTS `sys_permission_modules`;
CREATE TABLE `sys_permission_modules` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限模块Id',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '上级模块',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `level` varchar(255) NOT NULL COMMENT '级别',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：1、正常，0：冻结',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限模块';

-- 权限
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限模块Id',
  `module_id` bigint NOT NULL DEFAULT 0 COMMENT '模块Id',
  `code` varchar(32) NOT NULL COMMENT '权限码',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '请求的Url，可以是正则表达式',
  `type` tinyint NOT NULL DEFAULT 3 COMMENT '类型：1、菜单，2：按钮，3、其它',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：1、正常，0：冻结',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

-- 角色
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `type` tinyint NOT NULL DEFAULT 1 COMMENT '类型：1、管理员，2：其他',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：1、正常，0：冻结',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';


-- 角色用户关联表
DROP TABLE IF EXISTS `sys_role_users`;
CREATE TABLE `sys_role_users` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色Id',
  `user_id` bigint NOT NULL COMMENT '用户Id',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户关联表';


-- 角色权限关联表
DROP TABLE IF EXISTS `sys_role_acls`;
CREATE TABLE `sys_role_acls` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色Id',
  `permission_id` bigint NOT NULL COMMENT '权限Id',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- 权限相关更新记录表
DROP TABLE IF EXISTS `sys_permission_logs`;
CREATE TABLE `sys_permission_logs` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint NOT NULL DEFAULT 1 COMMENT '权限更新类型：1、部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` bigint NOT NULL COMMENT '基于type后指定的对象Id',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '当前是否复原过：1、复原过，0：没有',
  `oldValue` varhcar(255) NOT NULL DEFAULT '' COMMENT '旧值',
  `newValue` varchar(255) NOT NULL DEFAULT '' COMMENT '新值',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限日志表';
