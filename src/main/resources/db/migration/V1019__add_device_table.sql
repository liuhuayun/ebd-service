-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: gdb
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id` varchar(36) NOT NULL COMMENT 'ID(UUID)',
  `device_id` varchar(36) NOT NULL DEFAULT '' COMMENT '???????ID',
  `device_name` varchar(55) NOT NULL DEFAULT '' COMMENT '????',
  `org_id` varchar(56) NOT NULL DEFAULT '' COMMENT '????',
  `resource_code` varchar(55) NOT NULL DEFAULT '' COMMENT '????',
  `hardware_version` varchar(56) NOT NULL DEFAULT '' COMMENT '????',
  `software_version` varchar(56) NOT NULL DEFAULT '' COMMENT '????',
  `manufacturer` varchar(255) NOT NULL DEFAULT '' COMMENT '????',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '????: 0 - ???1 - ???2 - ????3- ??',
  `volume` int(4) NOT NULL DEFAULT '80' COMMENT '??',
  `fault_info` varchar(255) NOT NULL DEFAULT '' COMMENT '????',
  `location` varchar(255) DEFAULT NULL COMMENT '????',
  `longitude` varchar(55) DEFAULT NULL COMMENT '??',
  `latitude` varchar(55) DEFAULT NULL COMMENT '??',
  `image_urls` varchar(255) DEFAULT NULL COMMENT '??',
  `fm_keep_mode` int(1) DEFAULT NULL COMMENT 'FM???????0 - disabled, 1 - enabled',
  `fm_frequency` varchar(25) DEFAULT NULL COMMENT 'FM????',
  `device_ip` varchar(56) DEFAULT NULL COMMENT 'IP??',
  `ip_mask` varchar(55) DEFAULT NULL COMMENT '????',
  `gateway` varchar(55) DEFAULT NULL COMMENT '??',
  `create_time` datetime DEFAULT NULL COMMENT '????',
  `update_time` datetime DEFAULT NULL COMMENT '????',
  `device_type` int(11) NOT NULL DEFAULT '0' COMMENT '????: 0 - terminal, 1 - adapter',
  `sub_type` varchar(55) NOT NULL DEFAULT '' COMMENT '???, ????????????',
  `passback_mode` int(1) DEFAULT NULL COMMENT '????',
  `passback_ip` varchar(35) DEFAULT NULL COMMENT '????',
  `passback_period` varchar(35) DEFAULT NULL COMMENT '????',
  `power_amplifier` int(11) DEFAULT '0' COMMENT '?????0 - ????1 - ??',
  `cert_id` varchar(90) DEFAULT '' COMMENT '证书ID',
  `cert_content` varchar(2048) DEFAULT '' COMMENT '证书签名内容',
  `adapter_id` varchar(90) DEFAULT '' COMMENT '适配器ID-只对终端有效',
  `module_id` varchar(90) DEFAULT '' COMMENT '模块编号',
  `certh` varchar(4000) DEFAULT '' COMMENT '证书信任列表数据',
  `area_code` varchar(60) DEFAULT '' COMMENT '区域编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES ('0df3753c-304d-4dd7-b5ce-5a4288803b32','11111','11111','康','111111','','','1111',1,80,'','1111111','11','11','static/file_1560412604537_0e0a8891-896d-45e7-adad-8ebc095ba647.jpg',NULL,NULL,NULL,NULL,NULL,'2019-06-13 16:24:49','2019-06-14 14:42:09',1,'',0,'111','111',0,'43415230000000314010201','043415230000000100000000000E0000000000019E30A930465627E81EA59B2B728BB962F301AB90E6E5A87A1BD73B19DD3A54FA88B969CF9B40C9D3E719420B4AD09C2BEBAAC0B309CBA36368CAA042C1E27E78','','','',''),('290916c3-c48d-4fe7-b374-d14964b12874','11111','111','康通','1111','','','11',1,11,'','11','11','11',NULL,NULL,NULL,'11','11','11','2019-06-14 16:30:16','2019-06-14 16:30:16',0,'',0,'11','11',1,'000000001328','00000000132807020000000013270000000012FC000000000001F79C19142A4B5F304E86832BFA8059449659B65A5CFF71D04B2325D78A24DA1F5A02BF5EF1D415DB1F7EF45E6F91CAB8A79E43D4A959711C5281535F74538B65','4b842dc8-98d0-4536-83e0-bbb1870d137d','','',''),('4b842dc8-98d0-4536-83e0-bbb1870d137d','111','111','康','111','','','11',1,80,'','11','11','11','static/file_1559618815859_a6b3fbe5-dbae-4c73-b48e-2157cafd111a.jpg',NULL,NULL,NULL,NULL,NULL,'2019-06-04 11:27:10','2019-06-18 10:51:50',1,'',0,'111','111',0,'000000001328','00000000132808010000000012FC00000000000157FD071FEB33B3EBD8D91DDD21BCAFA50508FC58CC90EB1E0903BECE9A4B59E9BA3BEDE36806EDF0481835CE15906B9E3C53A741589D8E842652636DEE165B02','','','100000000000010000000012FC48093A07B43807190205F29EAF99E0DBA74F367E7DA814BA0BE7405A4508A3C0C0DD8E5F67D09FB32F0E36A5882571C91B5B376F09193AC9AFD6E5FA3A71786CA1DB87106A6B6AC63CFBAE464DBFEFDD3F213F61057800C4D34BE678E388E2A0171561A13F9FF8A2C6B196795746BE58F51C166CEE0EA97C5DE592756FAD8C39BF01',''),('4e0f42bc-49b3-4e1d-a592-614505e50677','11111','111','康通','1111','','','11',1,11,'','11','11','11',NULL,NULL,NULL,'11','11','11','2019-06-19 10:43:41','2019-06-19 10:43:41',0,'',0,'11','11',1,'000000001328','00000000132809020000000013270000000012FC000000000001AF300C9E0F5458631FBD2629D86FB3401EA69D9238BCAD9EBB5CB776C01160716CB07B09471677262A2F295F3171DD661567007376C070141DA4005FDBF061F0','ecea8c5e-d173-49a2-94c7-95e5688d8016','','10000000000001000000001327480960ACFFA66A4E8385E5E3F2ED76F2D99AFD234E28809D33AF0A4C9DC41B3B1BD99DEEBB4865867BBE5FF04670F87095FD7162B8E0A4FF033A95C4816FEFCAF753B55430AC0708F58680B64E80DC73E6FCF6A5FD13A719F64379C61DD41BF91BF777C03B130AB02F2C01322D3E9F5D01EC08ED45CEEE94704D6F339392F8E86945,100000000000010000000012FC48093A07B43807190205F29EAF99E0DBA74F367E7DA814BA0BE7405A4508A3C0C0DD8E5F67D09FB32F0E36A5882571C91B5B376F09193AC9AFD6E5FA3A71786CA1DB87106A6B6AC63CFBAE464DBFEFDD3F213F61057800C4D34BE678E388E2A0171561A13F9FF8A2C6B196795746BE58F51C166CEE0EA97C5DE592756FAD8C39BF01','510181001'),('8241dbb0-ffc2-4b78-81d6-ae291529f039','11111111','111111111','康通','111','','','11',1,11,'','11','11','11',NULL,NULL,NULL,NULL,NULL,NULL,'2019-06-17 18:09:40','2019-06-17 18:09:40',1,'',0,'11','11',1,'000000001327','00000000132707010000000012FC00000000000107E7454F8EA17838ABFBFF3D73FB3BBAE2D729B2490599400E541421AFD4A301307271688F88AD89B265EC07998FC9E47D378C46B7884D23B907B9F6228DA5C4','','11','100000000000010000000012FC48093A07B43807190205F29EAF99E0DBA74F367E7DA814BA0BE7405A4508A3C0C0DD8E5F67D09FB32F0E36A5882571C91B5B376F09193AC9AFD6E5FA3A71786CA1DB87106A6B6AC63CFBAE464DBFEFDD3F213F61057800C4D34BE678E388E2A0171561A13F9FF8A2C6B196795746BE58F51C166CEE0EA97C5DE592756FAD8C39BF01',''),('b919e40c-639f-4134-b34f-07b2cf60812b','12233','ttt','','123455','','','33',1,44,'','33','33','33',NULL,NULL,NULL,'33','33','33','2019-06-19 17:57:41','2019-06-19 17:57:41',0,'',0,'33','33',1,'000000001328','00000000132809020000000013270000000012FC000000000001838E9B01C46BE2ED8B89D7D1B7E463AE573FFCDAED30370D8E20FAE9BDF04F355D493F38D5975C211940BDEA987403452F96C8B852FDB966F44B7731683613BD','ecea8c5e-d173-49a2-94c7-95e5688d8016','','10000000000001000000001327480960ACFFA66A4E8385E5E3F2ED76F2D99AFD234E28809D33AF0A4C9DC41B3B1BD99DEEBB4865867BBE5FF04670F87095FD7162B8E0A4FF033A95C4816FEFCAF753B55430AC0708F58680B64E80DC73E6FCF6A5FD13A719F64379C61DD41BF91BF777C03B130AB02F2C01322D3E9F5D01EC08ED45CEEE94704D6F339392F8E86945,100000000000010000000012FC48093A07B43807190205F29EAF99E0DBA74F367E7DA814BA0BE7405A4508A3C0C0DD8E5F67D09FB32F0E36A5882571C91B5B376F09193AC9AFD6E5FA3A71786CA1DB87106A6B6AC63CFBAE464DBFEFDD3F213F61057800C4D34BE678E388E2A0171561A13F9FF8A2C6B196795746BE58F51C166CEE0EA97C5DE592756FAD8C39BF01','510181001'),('c469da57-2666-4f2f-b6bb-b36fe171428e','12232','231423','','35346426','','','we',1,23,'','ww','ww','ww',NULL,NULL,NULL,NULL,NULL,NULL,'2019-06-19 11:43:22','2019-06-19 14:03:17',1,'',0,'www','ww',1,'000000001327','00000000132707010000000012FC0000000000018499E351E922A79C4F79F175961F4E116C22560D511180FC676936E6D201F44132D87EDAD07576AB41EC548FD7E3560D7CD618EBBDE5E5DFDB15B77E1D0542AD','','dsfsdf','100000000000010000000012FC48093A07B43807190205F29EAF99E0DBA74F367E7DA814BA0BE7405A4508A3C0C0DD8E5F67D09FB32F0E36A5882571C91B5B376F09193AC9AFD6E5FA3A71786CA1DB87106A6B6AC63CFBAE464DBFEFDD3F213F61057800C4D34BE678E388E2A0171561A13F9FF8A2C6B196795746BE58F51C166CEE0EA97C5DE592756FAD8C39BF01','510181001'),('d3b13d50-ac81-42f5-b667-fefe0347740f','1231353','term1','康','1232342','','','111',1,12,'','11','11','11','static/file_1559619728824_98446295-ffaa-41a9-a1ee-dca927c0eb7c.jpeg',NULL,NULL,'1111','1111','1111','2019-06-04 11:42:16','2019-06-14 16:27:12',0,'',0,'111','111',1,'000000001328','00000000132807020000000013270000000012FC000000000001DD6DFC2495136A1550416513736952DE0D8FC35520AFD9E751A09529F141D9724ACBF4A18C91F977D7E4C6CD5F673F550CC8B5F83436EB93F9AE101BE8756117','4b842dc8-98d0-4536-83e0-bbb1870d137d','','',''),('e88be887-88b4-47fb-a36b-7688be723bfa','333334455','aaa','','23454364','','','34',1,34,'','2345','23','2345',NULL,NULL,NULL,'234543','2345','2345','2019-06-19 18:11:08','2019-06-19 18:11:08',1,'',0,'34545','2345',1,'000000001327','00000000132709010000000012FC000000000001C0325E29802421A304954B18FF25EC5BFD51ACD75CAB8185E0BB5C4E34DA8D17CC808686B7CDC94F1E4690C1EE18FE9F46463C43712C4CE42A14DCF3BFF11356','','34ttt425345','100000000000010000000012FC48093A07B43807190205F29EAF99E0DBA74F367E7DA814BA0BE7405A4508A3C0C0DD8E5F67D09FB32F0E36A5882571C91B5B376F09193AC9AFD6E5FA3A71786CA1DB87106A6B6AC63CFBAE464DBFEFDD3F213F61057800C4D34BE678E388E2A0171561A13F9FF8A2C6B196795746BE58F51C166CEE0EA97C5DE592756FAD8C39BF01','510181001'),('ecea8c5e-d173-49a2-94c7-95e5688d8016','333','333','','333','','','33',1,33,'','33','33','33',NULL,NULL,NULL,NULL,NULL,NULL,'2019-06-19 09:55:36','2019-06-19 11:29:45',1,'',0,'33','33',1,'000000001327','00000000132707010000000012FC0000000000013DA1C4A8CE038E63E74421DE26773FD29B66B4FE1DD4AB7FA22500261D193723A97BD6D30DF5F2539DA7FFF441F10F5937D5021D511B99373BFC0D63E96E16DA','','33','100000000000010000000012FC48093A07B43807190205F29EAF99E0DBA74F367E7DA814BA0BE7405A4508A3C0C0DD8E5F67D09FB32F0E36A5882571C91B5B376F09193AC9AFD6E5FA3A71786CA1DB87106A6B6AC63CFBAE464DBFEFDD3F213F61057800C4D34BE678E388E2A0171561A13F9FF8A2C6B196795746BE58F51C166CEE0EA97C5DE592756FAD8C39BF01','510181001001');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-19 11:58:45
