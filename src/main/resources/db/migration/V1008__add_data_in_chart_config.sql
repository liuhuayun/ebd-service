DELETE from `t_chart_config` WHERE id='101';
DELETE from `t_chart_config` WHERE id='102';

INSERT INTO `t_chart_config` VALUES(101, '新增设备数量随时间变化曲线', 'dev', 'summary', 'Line', '折线图', 'bindDate','日期','count','新增设备数量', now(), now());
INSERT INTO `t_chart_config` VALUES(102, '设备总数随时间变化曲线', 'dev', 'total', 'Line', '折线图', 'bindDate','日期','total','设备总数', now(), now());
INSERT INTO `t_chart_config` VALUES(109, '设备数量按状态分布饼图', 'dev', 'summary', 'Pie', '饼图', 'status','设备状态','count','设备数量', now(), now());
INSERT INTO `t_chart_config` VALUES(110, '设备数量按类型分布饼图', 'dev', 'summary', 'Pie', '饼图', 'devType','设备类型','count','设备数量', now(), now());
INSERT INTO `t_chart_config` VALUES(111, '不同状态设备总数随时间变化堆叠曲线图', 'dev', 'total', 'StackLine', '堆叠曲线图', 'bindDate,status','设备状态','total','设备总数', now(), now());
INSERT INTO `t_chart_config` VALUES(112, '不同状态新增设备数量随时间变化多曲线图', 'dev', 'summary', 'MLine', '多曲线图', 'bindDate,status','设备状态','total','设备总数', now(), now());
INSERT INTO `t_chart_config` VALUES(113, '不同类型设备总数随时间变化堆叠曲线图', 'dev', 'total', 'StackLine', '堆叠曲线图', 'bindDate,devType','设备类型','total','设备总数', now(), now());
INSERT INTO `t_chart_config` VALUES(114, '不同类型新增设备数量随时间变化多曲线图', 'dev', 'summary', 'MLine', '多曲线图', 'bindDate,devType','设备类型','total','设备总数', now(), now());
