import Vue from 'vue';
import Router from 'vue-router';

import Home from './views/Home.vue';
import Login from './views/Login.vue';
import DevDashboard from './views/DevDetail.vue';
import UserDashboard from './views/UserDashboard.vue';
import NewsDashboard from './views/NewsDashboard.vue';
import BusinessDashboard from './views/BusinessDashboard.vue';
import AdminHome from './views/AdminHome.vue';
import DashboardTmpl from './views/template/TemplateList.vue';
import DashboardTmplEdit from './views/template/TemplateEdit.vue';
// 大屏配置
import DashboardList from './views/dashboard/DashboardList.vue';
import DashboardEdit from './views/dashboard/DashboardEdit.vue';
// 大屏图表配置
import DashboardConfigList from './views/dashboard/DashboardConfigList.vue';
import DashboardConfigEdit from './views/dashboard/DashboardConfigEdit.vue';
// 数据查看
import DevTotal from './views/show-data/DevTotal.vue';
// 区域数据
import OrgList from './views/show-data/OrgList.vue';
import DynamicInfo from './views/show-data/DynamicInfo.vue';

// 用户登录数据
import UserLoginList from './views/show-data/UserLoginList.vue';

// 用户活跃度统计信息
import UserLoginSummary from './views/show-data/UserLoginSummary.vue';
import UserList from './views/show-data/UserList.vue';
import BusinessStats from './views/show-data/BusinessStats.vue';
import StatsBizSummary from './views/show-data/StatsBizSummary.vue';
import DevList from './views/show-data/DevList.vue';
//
import DevSummary from './views/show-data/DevSummary.vue';
import UserSummary from './views/show-data/UserSummary.vue';
// 大屏模板
import ChartConfigList from './views/charts/ChartConfigList.vue';
import ChartConfigEdit from './views/charts/ChartConfigEdit.vue';

//
import SafeInfo from './views/SafeInfo.vue';
// 热力图
import HeartMap from './components/charts/heartmap.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/dev',
      name: 'dev',
      component: DevDashboard,
    },
    {
      path: '/user',
      name: 'user',
      component: UserDashboard,
    },
    {
      path: '/news',
      name: 'news',
      component: NewsDashboard,
    },
    {
      path: '/business',
      name: 'business',
      component: BusinessDashboard,
    },
    {
      path: '/safeinfo',
      name: 'safeinfo',
      component: SafeInfo,
    },
    {
      path: '/admin',
      component: AdminHome,
      children: [{
        path: 'tmpl/list',
        component: DashboardTmpl,
      }, {
        path: 'tmpl/edit/:id',
        component: DashboardTmplEdit,
      }, {
        path: 'charts/list',
        component: ChartConfigList,
      }, {
        path: 'charts/edit/:id',
        component: ChartConfigEdit,
      }, {
        path: 'dashboard/list',
        component: DashboardList,
      }, {
        path: 'dashboard/edit/:id',
        component: DashboardEdit,
      }, {
        path: 'dashboard/config/list',
        component: DashboardConfigList,
      }, {
        path: 'dashboard/config/edit/:id',
        component: DashboardConfigEdit,
      }, {
        path: 'data/bus/list',
        component: BusinessStats,
      }, {
        path: 'data/org/list',
        component: OrgList,
      }, {
        path: 'data/user/summary',
        component: UserSummary,
      }, {
        path: 'stats/biz/abstract/latest',
        component: DynamicInfo,
      }, {
        path: 'stats/user/list',
        component: UserLoginList,
      }, {
        path: 'data/user/list',
        component: UserList,
      }, {
        path: 'data/dev/list',
        component: DevList,
      }, {
        path: 'stats/biz/summary/summary/list',
        component: StatsBizSummary,
      }, {
        path: 'charts/heartmap',
        component: HeartMap,
      }, {
        path: 'data/dev/total',
        component: DevTotal,
      }, {
        path: 'stats/user/summary',
        component: UserLoginSummary,
      }, {
        path: 'data/dev/summary',
        component: DevSummary,
      }],
    },
    // {
    //   path: '/admin/template/list',
    //   name: 'list',
    //   component: DashboardTmpl,
    // },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/Home.vue'),
    },
  ],
});
