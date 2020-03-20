INSERT INTO `t_chart_config` VALUES(103, '设备数量按状态统计圆心图', 'dev', 'summary', 'Doughnut', '圆心图', 'status','状态','count','设备数量', now(), now());
INSERT INTO `t_chart_config` VALUES(104, '设备分布地图', 'dev', 'list', 'Map', '地图', 'dev','设备','dev','设备', now(), now());
INSERT INTO `t_chart_config` VALUES(105, '业务统计柱状图', 'business', 'summary', 'Bar', '柱状图', 'business','业务','business','业务', now(), now());
INSERT INTO `t_chart_config` VALUES(106, '动态展示滚动列表', 'dynamic', 'list', 'Rolling', '滚动列表', 'dynamic','业务动态','dynamic','业务动态', now(), now());
INSERT INTO `t_chart_config` VALUES(107, '安全状态', 'security', 'latest', 'SafeInfo', '安全状态', 'security','安全状态','security','安全状态', now(), now());
INSERT INTO `t_chart_config` VALUES(108, '用户活跃度随时间变化', 'user_login', 'summary', 'Line', '曲线图', 'time','时间','count','活跃用户数', now(), now());

-- home dashboard config
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(1, 0, 103);
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(1, 1, 104);
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(1, 2, 105);
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(1, 3, 106);
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(1, 4, 107);
INSERT INTO `t_dashboard_config` (dashboard_id, block_index, config_id)  VALUES(1, 5, 108);
