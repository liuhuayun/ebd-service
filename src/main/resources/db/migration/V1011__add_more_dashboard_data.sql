INSERT INTO `t_chart_config` VALUES(115, '新增用户数量随时间变化曲线', 'user', 'summary', 'Line', '折线图', 'time','创建日期','count','新增用户数量', now(), now());
INSERT INTO `t_chart_config` VALUES(116, '喊话次数随时间变化曲线', 'statsBizSummary', 'summary', 'Line', '折线图', 'bizDate','日期','liveQuantity','喊话次数', now(), now());
INSERT INTO `t_chart_config` VALUES(117, '播单次数随时间变化曲线', 'statsBizSummary', 'summary', 'Line', '折线图', 'bizDate','日期','scheduleQuantity','播单次数', now(), now());

-- dynamic info dashboard config
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(3, 0, 106);

-- user info dashboard config
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(4, 0, 108);
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(4, 1, 115);

-- business stats dashboard config
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(5, 0, 116);
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(5, 1, 117);
