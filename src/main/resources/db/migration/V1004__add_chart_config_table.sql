DROP TABLE IF EXISTS `t_chart_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `t_chart_config` 
(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', 
	`config_name` varchar(200) DEFAULT NULL COMMENT '图表配置名称', 
	`module_name` varchar(40) DEFAULT NULL COMMENT '模块名称', 
	`api_name` varchar(200) DEFAULT NULL COMMENT '接口名称', 
	`chart_type` varchar(40) DEFAULT NULL COMMENT '图表类型', 
	`chart_name` varchar(40) DEFAULT NULL COMMENT '图表名称', 
	`dimension` varchar(200) DEFAULT NULL COMMENT '维度', 
	`dim_name` varchar(200) DEFAULT NULL COMMENT '维度名称', 
	`perf_index` varchar(40) DEFAULT NULL COMMENT '性能指标', 
	`index_name` varchar(40) DEFAULT NULL COMMENT '性能指标名称', 
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='图表配置表';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `t_chart_config` VALUES(101, '设备数量随时间变化曲线', 'dev', 'summary', 'Line', '折线图', 'date','日期','count','设备数量', now(), now());
INSERT INTO `t_chart_config` VALUES(102, '设备数量按类型分布饼图', 'dev', 'summary', 'Pie', '饼图', 'type','设备类型','count','设备数量', now(), now());


