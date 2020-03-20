/*
 * Copyright 2018
 * Created by janz (zhangzhi93@hotmail.com)
 */

import * as types from './mutation-types';
import axios from '../utils/axios';

const Dashboard = {
  state: {
    dashboardList: {},
    dashboardConfigList: {},
    dashboardDetail: {},
    dashboardConfig: {},
    dashboardData: {},
    dashboardBlockConfig: {},
    dashboardBlockInfo: {},
  },
  // 提供页面用到的参数
  getters: {
    // 获取dashboard列表
    get_dashboard_list: state => state.dashboardList,
    // 获取dashboard config列表
    get_dashboard_config_list: state => state.dashboardConfigList,
    // 获取dashboard详情
    get_dashboard_detail: state => state.dashboardDetail,
    // 获取dashboard配置信息，包括使用的模板ID，模块名等
    get_dashboard_config: state => state.dashboardConfig,
    // 获取dashboard使用的所有数据
    get_dashboard_data: state => state.dashboardData,
    // 获取某个小块需要的所有数据，包括DashboardBlockConfig, ChartConfig, ChartData
    get_dashboard_block_info: state => state.dashboardBlockInfo,
    // 获取一个小块的配置信息
    get_dashboard_block_config: state => state.dashboardBlockConfig,
  },
  // 同步处理 actions操作state必须通过mutations
  mutations: {
    [types.GET_DASHBOARD_LIST](state, data) {
      state.dashboardList = data.data;
    },
    [types.GET_DASHBOARD_CONFIG_LIST](state, data) {
      state.dashboardConfigList = data.data;
    },
    [types.GET_DASHBOARD_DETAIL](state, data) {
      state.dashboardDetail = data.data;
    },
    [types.GET_DASHBOARD_CONFIG](state, data) {
      state.dashboardConfig = data.data;
    },
    [types.GET_DASHBOARD_DATA](state, data) {
      state.dashboardData = data.data;
    },
    [types.GET_DASHBOARD_BLOCK_INFO](state, data) {
      state.dashboardBlockInfo = data.data;
    },
    [types.GET_DASHBOARD_BLOCK_CONFIG](state, data) {
      state.dashboardBlockInfo = data.data;
    },
  },
  // 异步处理
  actions: {
    /**
     * 获取所有dashboard信息
     */
    async getDashboardList({ commit }, { params, callback }) {
      const { data } = await axios.get('/dashboard', { params });
      if (data) {
        commit(types.GET_DASHBOARD_LIST, data);
        if (callback) callback(data);
      }
    },
    /**
     * 获取所有dashboard config信息
     */
    async getDashboardConfigList({ commit }, { params, callback }) {
      const { data } = await axios.get('/dashboard/config', { params });
      if (data) {
        commit(types.GET_DASHBOARD_CONFIG_LIST, data);
        if (callback) callback(data);
      }
    },
    /**
     * 通过moduleName得到dashboard配置信息
     */
    async getDashboardConfig({ commit }, { params, callback }) {
      const { data } = await axios.get(`/dashboard/config/${params.id}`);
      if (data) {
        commit(types.GET_DASHBOARD_CONFIG, data);
        if (callback) callback(data);
      }
    },
    /**
     * 从后台获取dashboard需要的所有数据
     */
    async getDashboardData({ commit }, { params, callback }) {
      const { data } = await axios.get('/dashboard/detail', { params });
      if (data) {
        commit(types.GET_DASHBOARD_DATA, data);
        if (callback) callback(data);
      }
    },
    /**
     * 添加dashboard中某一小块的算法
     */
    async postDashboardConfig({ commit }, { params, callback }) {
      const { data } = await axios.post('/dashboard/config', params);
      if (data) {
        commit(types.GET_DASHBOARD_BLOCK_CONFIG, data);
        if (callback) callback(data);
      }
    },
    /**
     * 更新dashboard中某一小块的算法
     */
    async putDashboardConfig(context, { params, callback }) {
      const { data } = await axios.put('/dashboard/config', params);
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
     * 获取dashboard详情
     */
    async getDashboardDetail({ commit }, { params, callback }) {
      const { data } = await axios.get(`/dashboard/${params.id}`);
      if (data) {
        commit(types.GET_DASHBOARD_DETAIL, data);
        if (callback) callback(data);
      }
    },
    /**
     * 更新dashboard使用的tmplId
     */
    async putDashboard(context, { params, callback }) {
      const { data } = await axios.put('/dashboard', params);
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
     * 获取dashboard某一小块的所有信息
     */
    async getDashboardBlockInfo({ commit }, { params, callback }) {
      const { data } = await axios.get(`/dashboard/config/detail/${params.id}`);
      if (data) {
        commit(types.GET_DASHBOARD_BLOCK_INFO, data);
        if (callback) callback(data);
      }
    },
  },
};

export default Dashboard;
