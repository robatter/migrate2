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