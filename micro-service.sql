/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : micro-service

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-02-09 21:21:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for machine
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine` (
  `machine_id` varchar(255) NOT NULL,
  `sj_flag` varchar(255) DEFAULT NULL,
  `machine_address` varchar(255) DEFAULT NULL,
  `machine_ip` varchar(255) DEFAULT NULL,
  `machine_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for umbrella
-- ----------------------------
DROP TABLE IF EXISTS `umbrella`;
CREATE TABLE `umbrella` (
  `umbrella_id` varchar(255) NOT NULL,
  `cs_flag` varchar(255) DEFAULT NULL,
  `machine_id` varchar(255) DEFAULT NULL,
  `umbrella_name` varchar(255) DEFAULT NULL,
  `umbrella_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`umbrella_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for umbrella_order
-- ----------------------------
DROP TABLE IF EXISTS `umbrella_order`;
CREATE TABLE `umbrella_order` (
  `order_id` varchar(255) NOT NULL,
  `begin_machine_id` varchar(255) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cs_flag` varchar(255) DEFAULT NULL,
  `end_machine_id` varchar(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `machine_ip` varchar(255) DEFAULT NULL,
  `sj_flag` varchar(255) DEFAULT NULL,
  `umbrella_id` varchar(255) DEFAULT NULL,
  `umbrella_type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
