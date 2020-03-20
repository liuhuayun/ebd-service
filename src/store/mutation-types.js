/*
 * Copyright 2018
 * Created by janz (zhangzhi93@hotmail.com)
 */

/*
*1.请求类型全部大写，命名规则以请求类型_方法名 example：GET_NAME
*2.类型按大功能区分，每个功能之间写明功能描述
*/

// 功能描述

// 获取菜单点击位置
export const PUT_LOCATION_NAME = 'PUT_LOCATION_NAME';
// 获取安全扫描状态：0- 没有扫描；1 - 正在扫描
export const PUT_SECURITY_SCAN_STATUS = 'PUT_SECURITY_SCAN_STATUS';

// 获取区域列表
export const GET_ORG_LIST = 'GET_ORG_LIST';
// 获取用户列表
export const GET_USER_LIST = 'GET_USER_LIST';
// 获取设备列表
export const GET_DEV_LIST = 'GET_DEV_LIST';
// 获取设备统计信息
export const GET_DEV_SUMMARY = 'GET_DEV_SUMMARY';
// 获取设备信息
export const GET_DEV_TOTAL = 'GET_DEV_TOTAL';
// 获取最新的安全检查状态
export const GET_LATEST_SECURITY_STATUS = 'GET_LATEST_SECURITY_STATUS';
// 获取最新业务动态
export const GET_DYNAMIC_INFO = 'GET_DYNAMIC_INFO';
// 获取业务统计信息列表
export const GET_BUSINESS_STATS_LIST = 'GET_BUSINESS_STATS_LIST';
// 获取业务统计信息
export const GET_BUSINESS_STATS = 'GET_BUSINESS_STATS';
// 获取用户登录信息
export const GET_USER_LOGIN_INFO = 'GET_USER_LOGIN_INFO';
// 获取业务统计汇总信息
export const GET_STATS_BIZ_SUMMARY = 'GET_STATS_BIZ_SUMMARY';

// 统计用户活跃度
export const GET_USER_LOGIN_SUMMARY = 'GET_USER_LOGIN_SUMMARY';
// 统计用户
export const GET_USER_SUMMARY = 'GET_USER_SUMMARY';
// dashboard模板
export const GET_DASHBOARD_TMPL = 'GET_DASHBOARD_TMPL';
// dashboard模板布局
export const GET_DASHBOARD_TMPL_DETAIL = 'GET_DASHBOARD_TMPL_DETAIL';
// 得到分析算法列表
export const GET_CHART_CONFIG = 'GET_CHART_CONFIG';
// 获取dashboard列表
export const GET_DASHBOARD_LIST = 'GET_DASHBOARD_LIST';
// 获取dashboard config列表
export const GET_DASHBOARD_CONFIG_LIST = 'GET_DASHBOARD_CONFIG_LIST';
// 获取dashboard详情
export const GET_DASHBOARD_DETAIL = 'GET_DASHBOARD_DETAIL';
// 获取dashboard配置信息
export const GET_DASHBOARD_CONFIG = 'GET_DASHBOARD_CONFIG';
// 获取dashboard需要的所有数据
export const GET_DASHBOARD_DATA = 'GET_DASHBOARD_DATA';
// 获取dashboard某一小块需要的所有信息
export const GET_DASHBOARD_BLOCK_INFO = 'GET_DASHBOARD_BLOCK_INFO';
// 获取dashboard某一小块的配置信息
export const GET_DASHBOARD_BLOCK_CONFIG = 'GET_DASHBOARD_BLOCK_CONFIG';
export const POST_BATCH_STATUS_BIZ_SUMMARYS = 'POST_BATCH_STATUS_BIZ_SUMMARYS';
export const GET_DYNAMIC_EBM_INFO = 'GET_DYNAMIC_EBM_INFO';
export const GET_EBR_STATISTIC_INFO = 'GET_EBR_STATISTIC_INFO';


// 获取应急广播接入信息
export const GET_WARNING_ACCESS_INFO = 'GET_WARNING_ACCESS_INFO';
// 获取应急广播地图边界信息
export const GET_GEO_JSON = 'GET_GEO_JSON';
// 获取应急广播详细信息
export const GET_EBM_INFO = 'GET_EBM_INFO';
// 获取鼠标事件信息
export const GET_MOUSE_STATE = 'GET_MOUSE_STATE';
// 获取资源信息
export const GET_RESOURCE_INFO = 'GET_RESOURCE_INFO';
// 地图轮播状态
export const GET_NEXT_ACCESS = 'GET_NEXT_ACCESS';
