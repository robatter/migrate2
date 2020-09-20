-- product_category(category_type) -> product_info(product_id) -> order_detail(order_id) -> order_master(buyer_openid) -> seller_info
create table `migrate_log` (
    `id` int not null auto_increment,
    `migrate_type` tinyint not null comment 'MIGRATE(1)/BACK(2)',
    `migrate_status` tinyint not null comment 'SELECTED(1)/SELECT_FAIL(2),INSERTED(3)/INSERT_FAIL(4),DELETED(5)/DELETE_FAIL(6)',
	`selected_cost`	int comment 'selected cost , unit:ms',
	`inserted_cost`	int comment 'inserted cost , unit:ms',
	`deleted_cost`	int comment 'deleted cost , unit:ms',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	`remark` varchar(5000) comment 'remark',
    primary key (`id`)
);

CREATE TABLE `order_detail` (
	`detail_id` VARCHAR(32) NOT NULL,
	`order_id` VARCHAR(32) NOT NULL,
	`product_id` VARCHAR(32) NOT NULL,
	`product_name` VARCHAR(64) NOT NULL COMMENT '商品名称',
	`product_price` DECIMAL(8,2) NOT NULL COMMENT '当前价格,单位分',
	`product_quantity` INT(11) NOT NULL COMMENT '数量',
	`product_icon` VARCHAR(512) NULL DEFAULT NULL COMMENT '小图',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`detail_id`),
	INDEX `idx_order_id` (`order_id`)
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `order_master` (
	`order_id` VARCHAR(32) NOT NULL,
	`buyer_name` VARCHAR(32) NOT NULL COMMENT '买家名字',
	`buyer_phone` VARCHAR(32) NOT NULL COMMENT '买家电话',
	`buyer_address` VARCHAR(128) NOT NULL COMMENT '买家地址',
	`buyer_openid` VARCHAR(64) NOT NULL COMMENT '买家微信openid',
	`order_amount` DECIMAL(8,2) NOT NULL COMMENT '订单总金额',
	`order_status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
	`pay_status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`order_id`),
	INDEX `idx_buyer_openid` (`buyer_openid`)
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `product_category` (
	`category_id` INT(11) NOT NULL AUTO_INCREMENT,
	`category_name` VARCHAR(64) NOT NULL COMMENT '类目名字',
	`category_type` INT(11) NOT NULL COMMENT '类目编号',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`category_id`)
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;

CREATE TABLE `product_info` (
	`product_id` VARCHAR(32) NOT NULL,
	`product_name` VARCHAR(64) NOT NULL COMMENT '商品名称',
	`product_price` DECIMAL(8,2) NOT NULL COMMENT '单价',
	`product_stock` INT(11) NOT NULL COMMENT '库存',
	`product_description` VARCHAR(64) NULL DEFAULT NULL COMMENT '描述',
	`product_icon` VARCHAR(512) NULL DEFAULT NULL COMMENT '小图',
	`product_status` TINYINT(3) NULL DEFAULT '0' COMMENT '商品状态,0正常1下架',
	`category_type` INT(11) NOT NULL COMMENT '类目编号',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`product_id`)
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `seller_info` (
	`id` VARCHAR(32) NOT NULL,
	`username` VARCHAR(32) NOT NULL,
	`password` VARCHAR(32) NOT NULL,
	`openid` VARCHAR(64) NOT NULL COMMENT '微信openid',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)
)
COMMENT='卖家信息表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;


-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- Server version:               5.7.24 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------
-- product_category(category_type) -> product_info(product_id) -> order_detail(order_id) -> order_master(buyer_openid) -> seller_info

INSERT INTO `product_category` (`category_id`, `category_name`, `category_type`, `create_time`, `update_time`) VALUES
	(5, '中文xx1', 2, '2019-04-28 18:33:42', '2019-04-28 18:33:42');
	
INSERT INTO `product_info` (`product_id`, `product_name`, `product_price`, `product_stock`, `product_description`, `product_icon`, `product_status`, `category_type`, `create_time`, `update_time`) VALUES
	('123456', '皮蛋粥', 3.20, 100, '很好喝的粥', 'http://xxxx.jpg', 0, 2, '2019-05-03 09:29:39', '2019-05-03 09:29:39');	

INSERT INTO `order_detail` (`detail_id`, `order_id`, `product_id`, `product_name`, `product_price`, `product_quantity`, `product_icon`, `create_time`, `update_time`) VALUES
	('1234567810', '111111111', '123456', '皮蛋粥', 2.20, 3, 'http://xxx.jpg', '2019-05-03 09:28:29', '2019-05-03 09:28:29');

INSERT INTO `order_master` (`order_id`, `buyer_name`, `buyer_phone`, `buyer_address`, `buyer_openid`, `order_amount`, `order_status`, `pay_status`, `create_time`, `update_time`) VALUES
	('111111111', '师兄', '123456789123', '慕课网', '110110', 2.50, 0, 0, '2019-05-03 09:28:58', '2019-05-03 09:28:58');


INSERT INTO `seller_info` (`id`, `username`, `password`, `openid`, `create_time`, `update_time`) VALUES
	('110110', 'admin', 'admin', 'abc', '2019-05-03 09:35:06', '2019-05-03 09:35:06');

