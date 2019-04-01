-- 产品属性参数分类（产品类型）
DROP TABLE IF EXISTS `goods_product_attribute_categories`;
CREATE TABLE `goods_product_attribute_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `attribute_count` int(11) NOT NULL DEFAULT 0 COMMENT '属性数量',
  `param_count` int(11) NOT NULL DEFAULT 0 COMMENT '参数数量',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  INDEX (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品属性参数分类（产品类型）';

-- 产品属性参数
DROP TABLE IF EXISTS `goods_product_attributes`;
CREATE TABLE `goods_product_attributes` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_attribute_id` bigint NOT NULL COMMENT '产品属性参数分类',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `select_type` tinyint NOT NULL DEFAULT 0 COMMENT '属性选择类型：0->唯一；1->单选；2->多选',
  `input_type` tinyint NOT NULL DEFAULT 0 COMMENT '属性录入方式：0->手工录入；1->从列表中选取',
  `input_list` varchar(255) NOT NULL DEFAULT '' COMMENT '可选值列表',
  `filter_type` tinyint NOT NULL DEFAULT 0 COMMENT '分类筛选样式：0->普通；1->颜色',
  `search_type` tinyint NOT NULL DEFAULT 0 COMMENT '检索类型；0->不需要进行检索；1->关键字检索；2->范围检索',
  `related` tinyint NOT NULL DEFAULT 0 COMMENT '相同属性产品是否关联；0->不关联；1->关联',
  `hand_add` tinyint NOT NULL DEFAULT 0 COMMENT '是否支持手动新增；0->不支持；1->支持',
  `type` tinyint NOT NULL DEFAULT 0 COMMENT '属性的类型；0->规格；1->参数',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序字段：最高的可以单独上传图片',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  INDEX (`product_attribute_id`, `type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品属性参数';

-- 品牌
DROP TABLE IF EXISTS `goods_brands`;
CREATE TABLE `goods_brands` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '品牌名',
  `first_letter` varchar(1) NOT NULL COMMENT '首字母',
  `is_manufacturer` tinyint NOT NULL DEFAULT 0 COMMENT '是否为品牌制造商：0->不是；1->是',
  `is_show` tinyint NOT NULL DEFAULT 0 COMMENT '是否显示：0->显示；1->不显示',
  `logo` varchar(255) NOT NULL DEFAULT '' COMMENT 'logo',
  `big_pic` varchar(255) NOT NULL DEFAULT '' COMMENT '专区大图',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  INDEX (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌';

-- 添加品牌数据
INSERT INTO `goods_brands`(id, `name`, first_letter, is_manufacturer, is_show, logo, big_pic, sort) VALUES
(1, '万和', 'W', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(5).jpg', '', 0),
(2, '三星', 'S', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (1).jpg', '', 100),
(3, '格力', 'G', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/dc794e7e74121272bbe3ce9bc41ec8c3_222_222.jpg', '', 30),
(4, '方太', 'F', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (4).jpg', '', 20),
(5, '小米', 'M', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/1e34aef2a409119018a4c6258e39ecfb_222_222.png', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180518/5afd7778Nf7800b75.jpg', 0),
(6, 'OPPO', 'O', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(6).jpg', '', 40),
(7, '七匹狼', 'S', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/18d8bc3eb13533fab466d702a0d3fd1f40345bcd.jpg', '', 200),
(8, '海澜之家', 'H', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/99d3279f1029d32b929343b09d3c72de_222_222.jpg', '', 200),
(9, '苹果', 'A', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', '', 0),
(10, 'NIKE', 'N', 1, 1, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/timg (51).jpg', '', 0);

-- 品牌故事
DROP TABLE IF EXISTS `goods_brand_stories`;
CREATE TABLE `goods_brand_stories` (
  `brand_id` bigint NOT NULL COMMENT '品牌Id',
  `brand_story` text COMMENT '品牌故事',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌故事';

-- 添加品牌故事
INSERT INTO `goods_brand_stories`(brand_id, brand_story) VALUES
(1, 'Victoria\'s Secret的故事'),
(2, '三星的故事'),
(3, 'Victoria\'s Secret的故事'),
(4, 'Victoria\'s Secret的故事'),
(5, '小米手机的故事'),
(6, 'string'),
(7, 'BOOB的故事'),
(8, '海澜之家的故事'),
(9, '苹果的故事'),
(10, 'NIKE的故事');
