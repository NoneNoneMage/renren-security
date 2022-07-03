-- 用户表
CREATE TABLE `tb_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) COMMENT '密码',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- 用户Token表
CREATE TABLE `tb_token` (
  `user_id` bigint NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime COMMENT '过期时间',
  `update_time` datetime COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

alter table  tb_user add column email varchar(128) comment '邮箱';
-- 账号：13612345678  密码：admin
INSERT INTO `tb_user` (`username`, `mobile`, `password`, `create_time`) VALUES ('mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');


# 訂單
CREATE TABLE `tb_order`
(
    `order_id`             bigint NOT NULL AUTO_INCREMENT,
    `user_id`              bigint COMMENT  '用戶ID',
    `restaurant_id`           varchar(255) COMMENT '餐館id',
    `consignee_id`    bigint COMMENT '收貨人信息',
    `price`          decimal(6,2) COMMENT '實付價格',
    `old_price`      decimal(6,2) COMMENT '原價',
    `description`    varchar(2048) COMMENT '備註',
    `status`         tinyint COMMENT '状态  0：未支付 1：已支付 2：已完成 3：交易取消 4：支付超時',
    `pay_type`       tinyint COMMENT '支付方式 0：線下 1：PayPal 2：AliPay 3：WeChat Pay 4：Card Pay',
    `create_time`    datetime COMMENT '创建时间',
    `timeout_time`   datetime COMMENT '支付超時時間',
    `update_time`    datetime COMMENT '修改时间',
    PRIMARY KEY (`order_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='訂單';

CREATE TABLE `tb_order_item`
(
    `order_item_id`             bigint NOT NULL AUTO_INCREMENT,
    `order_id`              bigint COMMENT  '訂單id',
    `goods_id`          varchar(2048) COMMENT '商品id',
    `status`      tinyint COMMENT '狀態 0：正常 1：取消',
    `amount`    INTEGER COMMENT '數量',
    `create_time`    datetime COMMENT '创建时间',
    `update_time`    datetime COMMENT '修改時間',
    PRIMARY KEY (`order_item_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='訂單商品';

CREATE TABLE `tb_consignee`
(
    `consignee_id`             bigint NOT NULL AUTO_INCREMENT,
    `user_id`              bigint COMMENT  '用戶ID',
    `address`          varchar(2048) COMMENT '收貨地址',
    `house_number`      varchar(256) COMMENT '門牌號',
    `consignee_name`    varchar(256) COMMENT '收貨人姓名',
    `consignee_sex`         tinyint COMMENT '性別 0：男 1：女',
    `count`           int COMMENT '使用次數',
    `consignee_phone` varchar(32) COMMENT '收貨人電話',
    `create_time`    datetime COMMENT '创建时间',
    `update_time`    datetime COMMENT '修改时间',
    PRIMARY KEY (`consignee_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='收貨地址';