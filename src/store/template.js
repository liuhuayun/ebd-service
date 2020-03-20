/*
 * Copyright 2018
 * Created by janz (zhangzhi93@hotmail.com)
 */

/**
 * app.js 为入口和全局性的状态提供
 * 其他功能的状态管理需新建文件
 */

import * as types from './mutation-types';
import axios from '../utils/axios';

const Template = {
  state: {
    tmplList: {},
    tmplDetail: {},
  },
  // 提供页面用到的参数
  getters: {
    // 获取datasource里面的特定参数
    get_dashboard_tmpl_list: state => state.tmplList,
    get_dashboard_tmpl_detail: state => state.tmplDetail,
  },
  // 同步处理 actions操作state必须通过mutations
  mutations: {
    [types.GET_DASHBOARD_TMPL](state, data) {
      state.tmplList = data.data;
    },
    [types.GET_DASHBOARD_TMPL_DETAIL](state, data) {
      state.tmplDetail = data.data;
    },
  },
  // 异步处理
  actions: {
    /**
   * 模板列表
   */
    async getDashboardTmplList({ commit }, { params, callback }) {
      const { data } = await axios.get('/dashboard/tmpl', { params });
      if (data) {
        commit(types.GET_DASHBOARD_TMPL, data);
        if (callback) callback(data);
      }
    },
    /**
     * 模板详情
     */
    async getDashboardTmplDetail({ commit }, { params, callback }) {
      const { data } = await axios.get('/dashboard/tmpl/search', { params });
      if (data) {
        commit(types.GET_DASHBOARD_TMPL_DETAIL, data);
        if (callback) callback(data);
      }
    },
    /**
     * 增加模板
     */
    async postDashboardTmplDetail(context, { params, callback }) {
      const { data } = await axios.post('/dashboard/tmpl/new', params);
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
     * 更新模板
     */
    async putDashboardTmplDetail(context, { params, callback }) {
      const { data } = await axios.put('/dashboard/tmpl/update', params);
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
     * 删除模板
     */
    async deleteDashboardTmplDetail(context, { params, callback }) {
      const { data } = await axios.delete(`/dashboard/tmpl/${params.id}`);
      if (data) {
        if (callback) callback(data);
      }
    },
  },
};

export default Template;
