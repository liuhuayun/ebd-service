/*
 * Copyright 2018
 * Created by janz (zhangzhi93@hotmail.com)
 */

/**
 * app.js 为入口和全局性的状态提供
 * 其他功能的状态管理需新建文件
 */
import Qs from 'qs';
import axios from '../utils/axios';
import * as types from './mutation-types';

const app = {
  state: {
    BatchStatsBizSummary: '',
    MenuLocationName: '',
    securityScanStatus: 0,
    userLoginInfo: {},
    securityStatus: {},
    orgList: {},
    userList: {},
    dynamicInfo: {},
    statsBizSummary: {},
    userLoginSummary: {},
    UserSummary: {},
    ebrStatisticInfo: [],
    WarningAccess: {},
    GeoJson: {},
    EbmInfo: {},
    Mousestate: 0,
    ResourceInfo: {},
    NextAccess: 0,
  },
  // 提供页面用到的参数
  getters: {
    // 获取菜单点击区域
    get_location_name: state => state.MenuLocationName,
    // 获取安全扫描状态：0 - 没有扫描；1 - 正在扫描
    get_security_scan_status: state => state.securityScanStatus,
    // 获取用户数据
    get_user_list: state => state.userList,
    // 获取登录信息
    get_user_login_info: state => state.userLoginInfo,
    // 获取安全检查信息
    get_latest_security_status: state => state.securityStatus,
    // 获取区域数据
    get_org_list: state => state.orgList,
    // 获取最新业务动态
    get_dynamic_info: state => state.dynamicInfo,
    // 获取业务统计汇总信息
    get_stats_biz_summary: state => state.statsBizSummary,
    // 批量添加业务信息
    post_batch_stats_biz_summary: state => state.BatchStatsBizSummary,
    // 获取用户活跃度数据
    get_user_login_summary: state => state.userLoginSummary,
    // 查看用户统计数据
    get_user_summary: state => state.UserSummary,
    // 获取资源统计信息
    get_ebrStatistic_info: state => state.ebrStatisticInfo,
    // 查看应急消息
    get_warning_access: state => state.WarningAccess,
    // 获取地图边界数据
    get_geo_json: state => state.GeoJson,
    // 获取单个应急信息的详细信息
    get_ebm_info: state => state.EbmInfo,
    // 屏幕鼠标状态
    get_mouse_state: state => state.Mousestate,
    // 资源列表
    get_resource_info: state => state.ResourceInfo,
    // 地图轮播完毕状态
    get_next_access: state => state.NextAccess,
  },

  // 同步处理 actions操作state必须通过mutations
  mutations: {
    [types.GET_USER_LOGIN_INFO](state, data) {
      state.userLoginInfo = data.data;
    },
    [types.GET_LATEST_SECURITY_STATUS](state, data) {
      state.securityStatus = data.data;
    },
    [types.GET_USER_LIST](state, data) {
      state.userList = data.data;
    },
    [types.GET_USER_SUMMARY](state, data) {
      state.UserSummary = data.data;
    },
    [types.GET_ORG_LIST](state, data) {
      state.orgList = data.data;
    },
    [types.GET_DYNAMIC_INFO](state, data) {
      state.dynamicInfo = data.data;
    },
    [types.PUT_SECURITY_SCAN_STATUS](state, data) {
      state.securityScanStatus = data;
    },
    [types.PUT_LOCATION_NAME](state, data) {
      state.MenuLocationName = data;
    },
    [types.GET_STATS_BIZ_SUMMARY](state, data) {
      state.statsBizSummary = data.data;
    },
    [types.POST_BATCH_STATUS_BIZ_SUMMARYS](state, data) {
      state.BatchStatsBizSummary = data;
    },
    [types.GET_USER_LOGIN_SUMMARY](state, data) {
      state.userLoginSummary = data.data;
    },
    [types.GET_EBR_STATISTIC_INFO](state, data) {
      state.ebrStatisticInfo = data.data;
    },
    [types.GET_WARNING_ACCESS_INFO](state, data) {
      state.WarningAccess = data;
    },
    [types.GET_GEO_JSON](state, data) {
      state.GeoJson = data;
    },
    [types.GET_EBM_INFO](state, data) {
      state.EbmInfo = data;
    },
    [types.GET_MOUSE_STATE](state, data) {
      state.Mousestate = data;
    },
    [types.GET_RESOURCE_INFO](state, data) {
      state.ResourceInfo = data;
    },
    [types.GET_NEXT_ACCESS](state, data) {
      state.NextAccess = data;
    },
  },
  // 异步处理
  actions: {
    /**
    * 获取用户登陆信息
    */
    async getUserLoginInfo({ commit }, { params, callback }) {
      const { data } = await axios.get('stats/user', { params });
      if (data) {
        commit(types.GET_USER_LOGIN_INFO, data);
        if (callback) callback(data);
      }
    },
    /**
    * 获取登录token
    */
    async getUserLoginToken({ commit }, { params, callback }) {
      params = Qs.stringify(params);
      const { data } = await axios.post('/oauth/token', params, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          Authorization: 'Basic bXlhcHA6bXlhcHBzZWNyZXQ=',
        },
      });
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
    * 获取用户列表
    */
    async getUserList({ commit }, { params, callback }) {
      const { data } = await axios.get('/user', { params });
      if (data) {
        commit(types.GET_USER_LIST, data);
        if (callback) callback(data);
      }
    },
    /**
    * 查看用户统计数据
    */
    async getUserSummary({ commit }, { params, callback }) {
      const { data } = await axios.get('/user/summary', { params });
      if (data) {
        commit(types.GET_USER_SUMMARY, data);
        if (callback) callback(data);
      }
    },
    /**
    * 获取左侧树状菜单
    */
    async getOrgList({ commit }, { params, callback }) {
      const { data } = await axios.get('/org', { params });
      if (data) {
        commit(types.GET_ORG_LIST, data);
        if (callback) callback(data);
      }
    },
    /**
    * 获取安全信息
    */
    async getLatestSecurityStatus({ commit }, { params, callback }) {
      const { data } = await axios.get('/stats/security/summary/latest', { params });
      if (data) {
        commit(types.GET_LATEST_SECURITY_STATUS, data);
        if (callback) callback(data);
      }
    },
    /**
    * 业务信息批量添加
    */
    async postBatchStatsBizSummary({ commit }, { params, callback }) {
      const { data } = await axios.get('/stats/biz/summary/batch', { params });
      if (data) {
        commit(types.POST_BATCH_STATUS_BIZ_SUMMARYS, data);
        if (callback) callback(data);
      }
    },
    /**
    * 获取最新业务动态
    */
    async getDynamicInfo({ commit }, { params, callback }) {
      const { data } = await axios.get('/stats/biz/abstract/latest', { params });
      if (data) {
        commit(types.GET_DYNAMIC_INFO, data);
        if (callback) callback(data);
      }
    },
    /**
    * 批量插入用户登录信息
    */
    async postBatchAddUserLogin(commit, { params, callback }) {
      const { data } = await axios.post(`/stats/user/batch?count=${params.count}`);
      if (data) {
        if (callback) callback(data);
      }
    },

    /**
    * 获取业务统计汇总信息
    */
    async getStatsBizSummary({ commit }, { params, callback }) {
      const { data } = await axios.get(`/stats/biz/summary/summary?sumType=${params.sumType}`);
      console.log(data);
      if (data) {
        commit(types.GET_EBR_STATISTIC_INFO, data);
        if (callback) callback(data);
      }
    },

    /**
     *
      获得用户活跃度统计信息
     */
    async getUserLoginSummary({ commit }, { params, callback }) {
      const { data } = await axios.get('stats/user/summary', { params });
      if (data) {
        commit(types.GET_USER_LOGIN_SUMMARY, data);
        if (callback) callback(data);
      }
    },

    /**
   *
    获取资源统计展示
   */
    async getEbrStatisticInfo({ commit }, { params, callback }) {
      const { data } = await axios.get('/ebr/count/typecount');
      if (data) {
        commit(types.GET_EBR_STATISTIC_INFO, data);
        if (callback) callback(data);
      }
    },

    /**
     *
      获得应急广播信息
     */
    async getWarningAccessInfo({ commit }, { params, callback }) {
      const { data } = await axios.get('bc/ebm/queryListLimit', { params });
      if (data) {
        commit(types.GET_WARNING_ACCESS_INFO, data);
        if (callback) callback(data);
      }
    },
    /**
     *
      获得应急广播地图边界信息
     */
    async getGeoJson({ commit }, { params, callback }) {
      const { data } = await axios.get('bc/map/geo', { params });
      if (data) {
        commit(types.GET_GEO_JSON, data);
        if (callback) callback(data);
      }
    },
    /**
     *
      获得应急消息详情信息
     */
    async getEbmInfo({ commit }, { params, callback }) {
      const { data } = await axios.get('bc/ebm/queryEbmInfoAll', { params });
      if (data) {
        commit(types.GET_EBM_INFO, data);
        if (callback) callback(data);
      }
    },
    /**
     *
      获得资源信息
     */
    async getResourceInfo({ commit }, { params, callback }) {
      const { data } = await axios.get('ebr/count/getPlatformEbrlist', { params });
      if (data) {
        commit(types.GET_RESOURCE_INFO, data);
        if (callback) callback(data);
      }
    },
  },
};

export default app;
