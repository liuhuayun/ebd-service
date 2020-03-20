/*
 * Copyright 2018
 * Created by janz (zhangzhi93@hotmail.com)
 */

import * as types from '../mutation-types';
import axios from '../../utils/axios';

const devStatus = {
  state: {
    devList: {},
    devTotal: {},
    devSummary: {
      online: 0,
      offline: 0,
      idle: 0,
      play: 0,
      fault: 0,
    },
    businessStats: {
      pushNum: 0, // 推送数量
      liveNum: 0, // 直播数量
    },
    businessStatsList: {},
  },
  // 提供页面用到的参数
  getters: {
    // 获取datasource里面的特定参数
    get_dev_list: state => state.devList,
    // 获取设备统计数据
    get_dev_total: state => state.devTotal,
    //
    get_dev_summary: state => state.devSummary,
    // 获取业务统计信息
    get_business_stats: state => state.businessStats,
    // 获取业务统计信息列表

    get_business_stats_list: state => state.businessStatsList,
  },
  // 同步处理 actions操作state必须通过mutations
  mutations: {
    [types.GET_DEV_LIST](state, data) {
      state.devList = data.data;
    },
    [types.GET_DEV_SUMMARY](state, data) {
      state.devSummary = data.data;
    },
    [types.GET_DEV_TOTAL](state, data) {
      state.devTotal = data.data;
    },
    [types.GET_BUSINESS_STATS_LIST](state, data) {
      state.businessStatsList = data.data;
    },
    [types.GET_BUSINESS_STATS](state, data) {
      let pushNum = 0;
      let liveNum = 0;
      data.forEach((item) => {
        pushNum += item.scheduleQuantity;
        liveNum += item.liveQuantity;
      });
      state.businessStats.pushNum = pushNum;
      state.businessStats.liveNum = liveNum;
    },
  },
  // 异步处理
  actions: {
    /**
   * 从后台获取设备列表
   */
    async getDevList({ commit }, { params, callback }) {
      const { data } = await axios.get('/dev', { params });
      if (data) {
        commit(types.GET_DEV_LIST, data);
        if (callback) callback(data);
      }
    },
    /**
     * 从后台获取设备列表
     */
    async getDevSummary({ commit }, { params, callback }) {
      const { data } = await axios.get('/dev/summary', { params });
      if (data.code === 200) {
        commit(types.GET_DEV_SUMMARY, data);
        if (callback) callback(data);
      }
    },
    /**
     * 从后台获取设备总量
     */
    async getDevTotal({ commit }, { params, callback }) {
      const { data } = await axios.get('/dev/total', { params });
      if (data.code === 200) {
        commit(types.GET_DEV_TOTAL, data);
        if (callback) callback(data);
      }
    },
    /**
    * 获取业务统计信息列表
    */
    async getBusinessStatsList({ commit }, { params, callback }) {
      const { data } = await axios.get('/stats/biz/summary', { params });
      if (data.code === 200) {
        commit(types.GET_BUSINESS_STATS_LIST, data);
        if (callback) callback(data);
      }
    },
    /**
    * 获取业务统计信息
    */
    async getBusinessStats({ commit, state }, { params, callback }) {
      const { data } = await axios.get('/stats/biz/summary/summary', { params });
      if (data.code === 200) {
        commit(types.GET_BUSINESS_STATS, data.data.list);
        if (callback) callback(state.businessStats);
      }
    },
  },
};

export default devStatus;
