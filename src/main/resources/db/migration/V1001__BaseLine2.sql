/*
Navicat MySQL Data Transfer

Source Server         : 114.115.248.39
Source Server Version : 80013
Source Host           : 114.115.248.39:3306
Source Database       : exp

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-29 00:19:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stats_biz_abstract
-- ----------------------------
DROP TABLE IF EXISTS `stats_biz_abstract`;
CREATE TABLE `stats_biz_abstract` (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '业务动态ID',
  `biz_type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '业务类型',
  `biz_time` datetime DEFAULT NULL COMMENT '业务时间',
  `biz_abstract` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '业务摘要',
  `op_user` char(32) COLLATE utf8_bin DEFAULT '' COMMENT '操作人',
  `org_id` char(32) COLLATE utf8_bin DEFAULT '' COMMENT '区域ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务动态表';

-- ----------------------------
-- Table structure for stats_biz_summary
-- ----------------------------
DROP TABLE IF EXISTS `stats_biz_summary`;
CREATE TABLE `stats_biz_summary` (
  `summary_id` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '统计ID',
  `org_id` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '区域ID',
  `biz_date` datetime DEFAULT NULL COMMENT '业务日期',
  `schedule_quantity` int(11) DEFAULT NULL COMMENT '播单次数',
  `live_quantity` int(255) DEFAULT NULL COMMENT '喊话次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务统计';

-- ----------------------------
-- Table structure for stats_security_summary
-- ----------------------------
DROP TABLE IF EXISTS `stats_security_summary`;
CREATE TABLE `stats_security_summary` (
  `security_id` char(32) COLLATE utf8_bin DEFAULT NULL,
  `virus_detection` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '病毒检测',
  `network_defense` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '网络防御',
  `functional_service` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '功能服务',
  `certificate_authorities` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '证书授权',
  `encipherment_mechanisms` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '加解密机制',
  `scan_time` datetime DEFAULT NULL COMMENT '扫描时间',
  `op_user` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='安全状态';

-- ----------------------------
-- Table structure for stats_user_login
-- ----------------------------
DROP TABLE IF EXISTS `stats_user_login`;
CREATE TABLE `stats_user_login` (
  `login_id` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '日活表',
  `user_id` char(32) COLLATE utf8_bin DEFAULT NULL,
  `login_date` date DEFAULT NULL,
  `client_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端类型(WEB/IOS/ANDROID)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='活跃度统计';
