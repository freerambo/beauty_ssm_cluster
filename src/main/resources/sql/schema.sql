
--需要 MySQL 5.6.5以上的版本
CREATE DATABASE beauty_ssm;
USE beauty_ssm;

CREATE TABLE `merchants` (
  `merchant_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_code` varchar(32) NOT NULL COMMENT '商户编号',
  `merchant_name` varchar(200) DEFAULT NULL COMMENT '商户名称',
  `open_id` varchar(32) DEFAULT NULL COMMENT '微信openid号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `enable` bit(1) DEFAULT b'0' COMMENT '是否启用（0否；1是）',
  PRIMARY KEY (`merchant_id`),
  UNIQUE KEY `uni_merchant_code` (`merchant_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商户表';


CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户号（沃智）',
  `merchant_name` varchar(128) DEFAULT NULL COMMENT '商户名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `txn_amt` decimal(16,2) DEFAULT NULL COMMENT '交易金额',
  `currency_code` varchar(3) DEFAULT NULL COMMENT '货币种类（如CNY）',
  `order_state` varchar(1) DEFAULT NULL COMMENT '订单状态',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '订单完成时间',
  `settle_amt` decimal(16,2) DEFAULT NULL COMMENT '清算金额',
  `settle_date` timestamp NULL DEFAULT NULL COMMENT '清算日期',
  `card_type` varchar(2) DEFAULT NULL COMMENT '卡类型',
  `up_stream_id` bigint(20) DEFAULT NULL COMMENT '上游订单号',
  PRIMARY KEY (`order_id`),
  KEY `fk_merchant_id` (`merchant_id`),
  CONSTRAINT `fk_merchant_id` FOREIGN KEY (`merchant_id`) REFERENCES `merchants` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单流水表';


CREATE TABLE `event_msg` (
  `event_msg_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id字段',
  `to_user_name` varchar(32) DEFAULT NULL COMMENT '开发者微信号',
  `from_user_name` varchar(32) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '消息创建时间',
  `msg_type` varchar(10) DEFAULT NULL COMMENT '消息类型（地理消息为 event ）',
  `event` varchar(32) DEFAULT NULL COMMENT '事件类型',
  `msg_id` int(11) DEFAULT NULL COMMENT '消息id，64位整型',
  `callback_status` varchar(16) DEFAULT NULL COMMENT '微信回复推送状态',
  `push_status` varchar(16) DEFAULT NULL COMMENT '推送状态',
  `order_id` bigint(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`event_msg_id`),
  KEY `fk_order_id` (`order_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于保存微信主动通知送达情况明细';


CREATE TABLE `routes` (
  `route_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '路由记录id',
  `merchant_id` bigint(20) NOT NULL COMMENT '（外键）商户id',
  `weixin_route` varchar(200) DEFAULT NULL COMMENT '微信跳转路由',
  `alipay_route` varchar(200) DEFAULT NULL COMMENT '支付宝路由',
  `priority` int(11) NOT NULL DEFAULT '50' COMMENT '优先级（默认50，值越大优先度越高）',
  PRIMARY KEY (`route_id`),
  KEY `fk_route_merchant_id` (`merchant_id`),
  CONSTRAINT `fk_route_merchant_id` FOREIGN KEY (`merchant_id`) REFERENCES `merchants` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='各个商户路由记录配置';;

