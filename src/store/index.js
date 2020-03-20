import Vue from 'vue';
import Vuex from 'vuex';

import App from './app';
import Template from './template';
import Dashboard from './dashboard';
import ChartInfo from './charts/chart-info';
import ChartConfig from './charts/chart-config';


Vue.use(Vuex);

// 导出需要的模块
export default new Vuex.Store({
  modules: {
    App,
    Template,
    Dashboard,
    ChartInfo,
    ChartConfig,
  },
});
