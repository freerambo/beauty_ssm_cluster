
--需要 MySQL 5.6.5以上的版本
CREATE DATABASE beauty_ssm;
USE beauty_ssm;

-- 用户表
CREATE TABLE _user(
`user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
`user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
`user_phone` BIGINT NOT NULL COMMENT '手机号',
`score` INT NOT NULL COMMENT '积分',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`user_id`),
KEY `idx_user_phone`(`user_phone`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 商品表
CREATE TABLE _goods(
`goods_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID',
`title` VARCHAR(120) NOT NULL COMMENT '商品名称',
`state` INT NOT NULL COMMENT '商品状态',
`price` FLOAT NOT NULL COMMENT '商品价格',
`number` INT NOT NULL COMMENT '商品数量',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`goods_id`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 订单表
CREATE TABLE _order(
`order_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
`user_id` BIGINT NOT NULL  COMMENT '用户ID',
`goods_id` BIGINT NOT NULL  COMMENT '商品ID',
`title` VARCHAR(120) NOT NULL COMMENT '订单名称',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`order_id`),
KEY `idx_user_id`(`user_id`),
KEY `idx_goods_id`(`goods_id`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='订单表';

#begin
CREATE TABLE `routes` (
	`route_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '路由记录id',
	`merchant_id` bigint(20) NOT NULL COMMENT '（外键）商户id',
	`weixin_route` varchar(200) NULL COMMENT '微信跳转路由',
	`alipay_route` varchar(200) NULL COMMENT '支付宝路由',
	`priority` int(11) NULL DEFAULT 50 COMMENT '优先级（默认50，值越大优先度越高）',
	PRIMARY KEY (`route_id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='各个商户路由记录配置';

CREATE TABLE `merchants` (
	`merchant_id` bigint NOT NULL,
	`merchant_code` varchar(32) NOT NULL COMMENT '商户编号',
	`merchant_name` varchar(200) NULL COMMENT '商户名称',
	PRIMARY KEY (`merchant_id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='商户表';

--插入初始数据
INSERT INTO 
	_user(user_name, user_phone, score)
VALUES
	('阿坚', 18768128888, 0),
	('小明', 18968129999, 0);



INSERT INTO 
	_goods(title, state, price,number)
VALUES
	('iphone7', 1, 3999, 100),
	('ipad3', 1, 1999, 2000);
	