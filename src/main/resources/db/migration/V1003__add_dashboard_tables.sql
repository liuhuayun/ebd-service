DROP TABLE IF EXISTS `t_dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8;
create table `t_dashboard` 
(	
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', 
	`dashboard_name` varchar(200) DEFAULT NULL COMMENT '大屏名称', 
	`module_name` varchar(200) DEFAULT NULL COMMENT '模块名称', 
	`tmpl_id` int(11) DEFAULT NULL COMMENT '模板ID', 
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='大屏数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_dashboard_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `t_dashboard_config` 
(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', 
	`dashboard_id` int(11) DEFAULT NULL COMMENT '大屏ID', 
	`block_index` int(11) DEFAULT NULL COMMENT '块的索引', 
	`config_id` int(11) DEFAULT NULL COMMENT '对应的图表配置ID', 
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='大屏配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_dashboard_tmpl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `t_dashboard_tmpl` 
(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', 
	`tmpl_name` varchar(200) DEFAULT NULL COMMENT '模板名称', 
	`block_num` int(11) DEFAULT NULL COMMENT '块的个数',
	`search_position` int(11) DEFAULT NULL COMMENT '查询按钮所在块的索引', 
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='大屏模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_dashboard_tmpl_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8;
CREATE TABLE `t_dashboard_tmpl_detail` 
(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', 
	`tmpl_id` int(11) DEFAULT NULL COMMENT '模板ID', 
	`x` int(11) DEFAULT NULL COMMENT '当前块左上角X坐标',
	`y` int(11) DEFAULT NULL COMMENT '当前块左上角Y坐标', 
	`w` int(11) DEFAULT NULL COMMENT '当前块的宽度', 
	`h` int(11) DEFAULT NULL COMMENT '当前块的高度', 
	`i` int(11) DEFAULT NULL COMMENT '当前块的索引', 
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='大屏模板详情表';
/*!40101 SET character_set_client = @saved_cs_client */;
