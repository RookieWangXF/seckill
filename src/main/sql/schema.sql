-- 数据库初始化脚本
-- mysql版本为5.6以上的

-- 创建数据库
CREATE DATABASE seckill;

-- 使用数据库
use seckill;

-- 创建秒杀库存表  InnoDb是支持事务操作的
CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  name VARCHAR(120) NOT NULL COMMENT '商品名称',
  number INT NOT NULL COMMENT '商品数量',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间', -- 在创建的时候设值为当前时间，也可以设值更新的时候进行更改时间
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
 )ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

 -- 初始化数据
 INSERT INTO seckill(name,number ,start_time,end_time)
 VALUES ('1000元秒杀ipone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
  ('200元秒杀Mp3',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
  ('300元秒杀红米Note',50,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
  ('500元秒杀小米4',40,'2015-11-01 00:00:00','2015-11-02 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_killed(
  seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
  user_phone BIGINT NOT NULL COMMENT '用户手机号',
  state tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1:无效 0:成功 1:已付款 2:意发货',
  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),/*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- 查看建立表的明细，在命令行下执行
-- show create table success_killed\G




