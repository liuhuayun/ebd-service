/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : cz_eb_dev

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-06-18 23:44:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_region_area`
-- ----------------------------
DROP TABLE IF EXISTS `sys_region_area`;
CREATE TABLE `sys_region_area` (
  `areaCode` varchar(20) NOT NULL COMMENT '区域编码',
  `areaName` varchar(100) NOT NULL COMMENT '区域名称',
  `parentAreaCode` varchar(20) DEFAULT NULL COMMENT '上级区域',
  `areaLevel` int(11) DEFAULT NULL COMMENT '区域级别(省、市、县...）',
  `areaSquare` decimal(8,2) DEFAULT NULL COMMENT '区域面积， 单位平方公里',
  `areaPopulation` decimal(8,2) DEFAULT NULL COMMENT '区域人口， 单位万',
  `centerLongitude` varchar(30) DEFAULT NULL COMMENT '应急中心经度',
  `centerLatitude` varchar(30) DEFAULT NULL COMMENT '应急中心纬度',
  PRIMARY KEY (`areaCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行政区划表';

-- ----------------------------
-- Records of sys_region_area
-- ----------------------------
INSERT INTO `sys_region_area` VALUES ('510181', '都江堰市', '5101', '3', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001', '奎光塔街道', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001001', '勤俭人家社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001002', '金江社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001003', '安青社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001005', '安顺社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001006', '滨河社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001007', '龙潭湾社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001008', '观江社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001009', '奎光塔社区', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181001201', '张家湾社区村', '510181001', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002', '幸福街道', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002001', '莲花社区', '510181002', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002002', '观凤楼社区', '510181002', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002003', '翔凤桥社区', '510181002', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002004', '幸福社区', '510181002', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002005', '彩虹社区', '510181002', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002006', '岷江路社区', '510181002', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181002007', '解放社区', '510181002', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003', '永丰街道', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003001', '永丰社区', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003002', '民丰社区', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003003', '友爱社区', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003004', '联盟社区', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003005', '新联社区', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003200', '民主社区村', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003201', '石马社区村', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181003202', '永寿社区村', '510181003', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004', '灌口街道', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004001', '紫东社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004002', '南桥社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004003', '文庙社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004004', '瑞莲社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004005', '柳河社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004006', '栏马桥社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004007', '平义社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004008', '蒲阳路社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004009', '建兴社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004010', '玉带桥社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004011', '太平社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004012', '伏龙社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004013', '西川社区', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181004200', '灵岩社区村', '510181004', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005', '银杏街道', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005001', '王家桥社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005002', '宁江社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005003', '银杏社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005004', '高梗社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005005', '学府社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005006', '锦绣社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005007', '惠民社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005008', '朝阳社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005009', '高桥社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005010', '上游社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005011', '壹街社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005012', '尚阳社区', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005200', '百花社区村', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181005201', '万岭社区村', '510181005', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102', '蒲阳镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102005', '和平社区', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102009', '会元桥社区', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102206', '栏厢社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102208', '南溪社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102209', '旗松社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102214', '同义社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102218', '凉水社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102221', '长河社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102223', '银杏社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102224', '互助社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102225', '双柏社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102226', '花溪社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102227', '建设社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102228', '金凤社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102229', '蟠龙社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181102230', '团结社区村', '510181102', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103', '聚源镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103001', '聚兴社区', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103205', '导江社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103208', '羊桥社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103215', '金鸡社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103218', '普星社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103219', '五龙社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103220', '泉水社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103221', '迎祥社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103222', '三坝社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103223', '龙泉社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181103224', '大合社区村', '510181103', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104', '崇义镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104002', '崇德社区', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104201', '飞桥社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104202', '崇义社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104206', '罗桥社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104210', '海云社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104211', '吴塘社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104225', '江安社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104226', '新华社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104227', '古桥社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104228', '圣庵社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104229', '双土社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104230', '大桥社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104231', '桂桥社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104232', '笆桥社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181104233', '界牌社区村', '510181104', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105', '天马镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105002', '碧鸡桥社区', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105201', '向荣社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105203', '金马社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105208', '绿凤社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105211', '长虹社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105221', '禹王社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105222', '建华社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105223', '金华社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105224', '仙鹤社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105225', '金玉社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105226', '童山社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105227', '二郎社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181105228', '金陵社区村', '510181105', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106', '石羊镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106007', '三羊社区', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106213', '红花社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106216', '卫星社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106223', '马祖社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106234', '清水社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106235', '皂角社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106236', '金花社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106237', '天平社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106238', '竹瓦社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106239', '书院社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106240', '同心社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106241', '广益社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106242', '丰乐社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106243', '义和社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106244', '金羊社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106245', '宁静社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106246', '徐渡社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106247', '古城社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181106248', '风堆社区村', '510181106', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107', '柳街镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107001', '御柳社区', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107002', '顺江社区', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107201', '民安社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107202', '水月社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107204', '鹤鸣社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107222', '清凉社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107228', '战洪社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107229', '集祥社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107230', '红雄社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107231', '柳顺社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107232', '同乐社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107233', '双凤社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107234', '五一社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107235', '金龙社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181107236', '七里社区村', '510181107', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108', '玉堂镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108001', '青城桥社区', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108002', '永康社区', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108202', '永固社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108203', '宝瓶社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108205', '白玉社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108206', '南华社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108207', '三台社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108208', '石牛社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108209', '新生社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108212', '白马社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108217', '水泉社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108218', '凤岐社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181108219', '龙凤社区村', '510181108', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109', '中兴镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109001', '耆亭社区', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109203', '梓桐社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109208', '两河社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109209', '三溪社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109211', '九龙社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109212', '永安社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109216', '红旗社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109219', '新益社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109220', '梅花社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109221', '上元社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181109222', '永胜社区村', '510181109', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110', '青城山镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110001', '长寿社区', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110003', '和乐社区', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110004', '桃花社区', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110006', '青景社区', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110007', '民兴社区', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110202', '青城社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110214', '五里社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110215', '石桥社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110223', '味江社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110224', '沙坪社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110225', '尖峰社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110226', '泰安社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110227', '芒城社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110228', '赤城社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110229', '青田社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110230', '五桂社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110231', '永益社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110232', '永兴社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110233', '新桥社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110234', '清江社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181110235', '沿江社区村', '510181110', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111', '龙池镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111002', '栗坪社区', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111003', '岷江社区', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111203', '南岳社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111204', '东岳社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111206', '查关社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111210', '云华社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111211', '都江社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111212', '白沙社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111213', '沙湾社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111214', '紫坪社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111215', '望江社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111216', '黎明社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111217', '红色社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111218', '高原社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111219', '虹口社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111220', '联合社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111221', '棕花社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111222', '久红社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111223', '深溪社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181111224', '光荣社区村', '510181111', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112', '胥家镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112001', '石龙社区', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112206', '土什社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112211', '桂花社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112212', '大林社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112213', '桂林社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112215', '共和社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112220', '实践社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112221', '新民社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112224', '实新社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112225', '匡家社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112226', '圣寿社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112227', '柏河社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112228', '金胜社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112229', '南店社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181112230', '龙马社区村', '510181112', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113', '安龙镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113001', '卉景社区', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113002', '成青社区', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113208', '泊江社区村', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113221', '官田社区村', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113222', '何家社区村', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113223', '东义社区村', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113224', '蒲津社区村', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113227', '东风社区村', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181113228', '玉兰社区村', '510181113', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114', '大观镇', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114001', '大观社区', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114211', '茶坪社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114212', '红梅社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114221', '欣禾社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114222', '双乐社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114223', '大通社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114224', '丽江社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114225', '桃源社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114226', '艳景社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114227', '宿仙社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114229', '两河社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181114230', '荷园社区村', '510181114', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202', '向峨乡', '510181', '4', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202003', '爱莲社区', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202201', '鹿池社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202202', '茶房社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202203', '东林社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202206', '红光社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202207', '石翁社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202208', '龙竹社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202209', '海虹社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202210', '棋盘社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202211', '石花社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202216', '莲月社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202217', '石碑社区村', '510181202', '5', '100000.00', '200000.00', null, null);
INSERT INTO `sys_region_area` VALUES ('510181202218', '红火社区村', '510181202', '5', '100000.00', '200000.00', null, null);
