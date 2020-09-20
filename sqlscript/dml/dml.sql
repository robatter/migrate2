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

