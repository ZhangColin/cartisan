-- 分类表
DROP TABLE IF EXISTS `cpn_categories`;
CREATE TABLE `cpn_categories` (
  `id` bigint NOT NULL COMMENT '分类Id',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_category_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 商家表
DROP TABLE IF EXISTS `cpn_merchants`;
CREATE TABLE `cpn_merchants` (
  `id` bigint NOT NULL COMMENT '商家Id',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `logo` varchar(255) NOT NULL DEFAULT '' COMMENT 'logo',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家表';

-- 门店表
DROP TABLE IF EXISTS `cpn_stores`;
CREATE TABLE `cpn_stores` (
  `id` bigint NOT NULL COMMENT '门店Id',
  `merchant_id` bigint NULL COMMENT '商家Id',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `phone` varchar(64) NOT NULL DEFAULT '' COMMENT '电话',
  `area` varchar(255) NOT NULL DEFAULT '' COMMENT '所属区域',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `description` varchar(2048) NOT NULL DEFAULT '' COMMENT '描述（门店指引）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_store_sort`(`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='门店表';

-- 微信用户表
DROP TABLE IF EXISTS `cpn_weixin_users`;
CREATE TABLE `cpn_weixin_users` (
  `id` bigint NOT NULL COMMENT '用户Id',
  `nick_name` varchar(32) NOT NULL COMMENT '用户昵称',
  `open_id` varchar(64) NOT NULL COMMENT 'openId',
  `union_id` varchar(64) NOT NULL COMMENT 'unionId',
  `country` varchar(32) NOT NULL COMMENT '国家',
  `province` varchar(32) NOT NULL COMMENT '省',
  `city` varchar(64) NOT NULL COMMENT '城市',
  `gender` tinyint NOT NULL COMMENT '性别',
  `avatarUrl` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `referrer_id` bigint NOT NULL DEFAULT 0 COMMENT '推荐人Id',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_weixin_user_open_id` (`open_id`),
  INDEX `index_cpn_weixin_user_union_id` (`union_id`),
  INDEX `index_cpn_weixin_user_referrer_id` (`referrer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信用户表';


-- 推荐人信息表
DROP TABLE IF EXISTS `cpn_referrers`;
CREATE TABLE `cpn_referrers` (
  `id` bigint NOT NULL COMMENT '主键Id',
  `user_id` bigint NOT NULL COMMENT '用户Id',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `phone` varchar(32) NOT NULL COMMENT '手机',
  `profession` varchar(16) NOT NULL COMMENT '职业',
  `debit_cart` varchar(32) NOT NULL COMMENT '银行卡号',
  `bank` varchar(32) NOT NULL COMMENT '银行',
  `level` tinyint NOT NULL DEFAULT 1 COMMENT '级别',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_referrer_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐人信息表';


-- 优惠券schema表
DROP TABLE IF EXISTS `cpn_coupon_schemas`;
CREATE TABLE `cpn_coupon_schemas` (
  `id` bigint NOT NULL COMMENT '优惠券schemaId',
  `merchant_id` bigint NULL COMMENT '商家Id',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `image` varchar(255) NOT NULL DEFAULT '' COMMENT '图片',
  `introduction` varchar(255) NOT NULL DEFAULT '' COMMENT '基本介绍',
  `commission` int NOT NULL DEFAULT 0 COMMENT '佣金',
  `get_start` timestamp NOT NULL COMMENT '开始领取时间',
  `get_end` timestamp NOT NULL COMMENT '结束领取时间',
  `valid_start` timestamp NOT NULL COMMENT '有效开始时间',
  `valid_end` timestamp NOT NULL COMMENT '有效结束时间',
  `type` tinyint NOT NULL COMMENT '类型（1、通用型；2、一券一码型）',
  `get_method` tinyint NOT NULL COMMENT '领取方式（1、一人一张；2、使用后再领）',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_coupon_schema_merchant_id`(`merchant_id`),
  INDEX `index_cpn_coupon_schema_get_start_end`(`get_start`, `get_end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券schema表';

-- 优惠券门店使用指南表
DROP TABLE IF EXISTS `cpn_store_guides`;
CREATE TABLE `cpn_store_guides` (
  `id` bigint NOT NULL COMMENT '主键Id',
  `coupon_schema_id` bigint NOT NULL COMMENT '优惠券schemaId',
  `store_id` bigint NOT NULL COMMENT '门店Id',
  `guide` varchar(255) NOT NULL DEFAULT '' COMMENT '使用指南',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券门店使用指南表';

-- 热门优惠券表
DROP TABLE IF EXISTS `cpn_coupon_hots`;
CREATE TABLE `cpn_coupon_hots` (
  `id` bigint NOT NULL COMMENT '主键Id',
  `coupon_schema_id` bigint NOT NULL COMMENT '优惠券schemaId',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_coupon_hot_sort`(`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='热门优惠券表';

-- 推荐人佣金配置表
DROP TABLE IF EXISTS `cpn_referrer_commission_settings`;
CREATE TABLE `cpn_referrer_commission_settings` (
  `id` bigint NOT NULL COMMENT '主键Id',
  `coupon_schema_id` bigint NOT NULL COMMENT '优惠券schemaId',
  `commission_rate` int NOT NULL DEFAULT 0 COMMENT '佣金率',
  `level` tinyint NOT NULL DEFAULT 0 COMMENT '应用级别，0表示适用所有级别',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operate_ip` varchar(20) NOT NULL COMMENT '操作IP',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_referrer_commission_setting_coupon_schema_id`(`coupon_schema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐人佣金配置表';

-- 优惠券表
DROP TABLE IF EXISTS `cpn_coupons`;
CREATE TABLE `cpn_coupons` (
  `id` bigint NOT NULL COMMENT '优惠券Id',
  `coupon_schema_id` bigint NOT NULL COMMENT '优惠券schemaId',
  `user_id` bigint NOT NULL COMMENT '用户Id',
  `coupon_code` varchar(64) NOT NULL COMMENT '优惠码',
  `get_date` timestamp NOT NULL COMMENT '领取日期',
  `use_date` timestamp NULL COMMENT '使用日期',
  `amount` int NULL COMMENT '使用金额',
  `valid_start` timestamp NOT NULL COMMENT '有效开始时间',
  `valid_end` timestamp NOT NULL COMMENT '有效结束时间',
  `bill_id` bigint NULL COMMENT '商家结佣账单Id',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0、未使用；1、已使用；2、已过期）',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_coupon_coupon_schema_id`(`coupon_schema_id`),
  INDEX `index_cpn_coupon_user_id`(`user_id`),
  INDEX `index_cpn_coupon_bill_id`(`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';

-- 推荐人佣金表
DROP TABLE IF EXISTS `cpn_referrer_commissions`;
CREATE TABLE `cpn_referrer_commissions` (
  `id` bigint NOT NULL COMMENT '主键Id',
  `coupon_schema_id` bigint NOT NULL COMMENT '优惠券schemaId',
  `coupon_id` bigint NOT NULL COMMENT '优惠券Id',
  `user_id` bigint NOT NULL COMMENT '用户Id',
  `referrer_id` bigint NOT NULL COMMENT '推荐人Id',
  `bill_id` bigint NULL COMMENT '佣金账单Id',
  `commission_rate` int NOT NULL DEFAULT 0 COMMENT '佣金率',
  `amount` int NOT NULL DEFAULT 0 COMMENT '佣金',
  `settlement_date` timestamp NULL COMMENT '结算日期',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0、未结佣；1、已结佣）',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_referrer_commission_referrer_id`(`referrer_id`),
  INDEX `index_cpn_referrer_commission_bill_id`(`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐人佣金表';


-- 推荐人佣金账单表
DROP TABLE IF EXISTS `cpn_referrer_commission_bills`;
CREATE TABLE `cpn_referrer_commission_bills` (
  `id` bigint NOT NULL COMMENT '佣金账单Id',
  `referrer_id` bigint NOT NULL COMMENT '推荐人Id',
  `amount` int NOT NULL DEFAULT 0 COMMENT '总佣金',
  `apply_date` timestamp NOT NULL COMMENT '申请日期',
  `settlement_document_no` varchar(64) NULL COMMENT '结算凭证号',
  `settlement_date` timestamp NULL COMMENT '结算日期',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0、未结佣；1、已结佣）',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_referrer_commission_bill_referrer_id`(`referrer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐人佣金表';

-- 商家佣金账单表
DROP TABLE IF EXISTS `cpn_merchant_commission_bills`;
CREATE TABLE `cpn_merchant_commission_bills` (
  `id` bigint NOT NULL COMMENT '佣金账单Id',
  `merchant_id` bigint NULL COMMENT '推荐人Id',
  `amount` int NOT NULL DEFAULT 0 COMMENT '应结总佣金',
  `actual_settlement_amount` int NOT NULL DEFAULT 0 COMMENT '实际结算金额',
  `settlement_document_no` varchar(64) NOT NULL COMMENT '结算凭证号',
  `settlement_date` timestamp NOT NULL COMMENT '结算日期',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `index_cpn_merchant_commission_bill_merchant_id`(`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家佣金账单表';


