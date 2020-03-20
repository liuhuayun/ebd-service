/*
 * Copyright 2018
 * Created by janz (zhangzhi93@hotmail.com)
 */

/**
 * app.js 为入口和全局性的状态提供
 * 其他功能的状态管理需新建文件
 */

import * as types from '../mutation-types';
import axios from '../../utils/axios';

const chartConfig = {
  state: {
    configList: {},
  },
  // 提供页面用到的参数
  getters: {
    // 获取datasource里面的特定参数
    get_chart_config_list: state => state.configList,
  },
  // 同步处理 actions操作state必须通过mutations
  mutations: {
    [types.GET_CHART_CONFIG](state, data) {
      state.configList = data.data;
    },
  },
  // 异步处理
  actions: {
    /**
   * 数据源信息
   */
    async getChartConfigList({ commit }, { params, callback }) {
      const { data } = await axios.get('chart/config', { params });
      if (data) {
        commit(types.GET_CHART_CONFIG, data);
        if (callback) callback(data);
      }
    },
    /**
     * 删除条目
     */
    async deleteChartConfigItem(context, { params, callback }) {
      const { data } = await axios.delete(`chart/config/${params.id}`);
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
     * 获取条目
     */
    async getChartConfigItem(context, { params, callback }) {
      const { data } = await axios.get(`chart/config/${params.id}`);
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
     * 添加算法
     */
    async postChartConfig(context, { params, callback }) {
      const { data } = await axios.post('chart/config', params);
      if (data) {
        if (callback) callback(data);
      }
    },
    /**
     * 更新dashboard中某一小块的算法
     */
    async putChartConfig(context, { params, callback }) {
      const { data } = await axios.put('chart/config', params);
      if (data) {
        if (callback) callback(data);
      }
    },
  },
};

export default chartConfig;
