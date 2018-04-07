/*
Navicat MySQL Data Transfer

Source Server         : kenhome
Source Server Version : 50639
Source Host           : 120.79.35.34:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-04-07 19:35:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_alarm
-- ----------------------------
DROP TABLE IF EXISTS `t_alarm`;
CREATE TABLE `t_alarm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id，告警表',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发生时间',
  `type` tinyint(2) unsigned zerofill NOT NULL COMMENT '告警类型 0、超时告警，1、异常告警',
  `grade` tinyint(2) unsigned zerofill NOT NULL COMMENT '紧急等级，默认0：一般，1：较紧急，2：特紧急',
  `event` varchar(100) NOT NULL DEFAULT '' COMMENT '触发告警事件（接口）',
  `detail` varchar(255) NOT NULL DEFAULT '' COMMENT '告警详情',
  `use_time` int(10) unsigned zerofill NOT NULL COMMENT '耗时情况，单位毫秒（ms）',
  PRIMARY KEY (`id`),
  KEY `event` (`event`) USING BTREE COMMENT '接口'
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
