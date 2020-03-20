alter table `t_dashboard` add column `org_id` char(32) COLLATE utf8_bin DEFAULT '' COMMENT '区域ID';
alter table `t_dashboard_config` add column `org_id` char(32) COLLATE utf8_bin DEFAULT '' COMMENT '区域ID';
alter table `t_dashboard_tmpl` add column `org_id` char(32) COLLATE utf8_bin DEFAULT '' COMMENT '区域ID';
alter table `t_dashboard_tmpl_detail` add column `org_id` char(32) COLLATE utf8_bin DEFAULT '' COMMENT '区域ID';
