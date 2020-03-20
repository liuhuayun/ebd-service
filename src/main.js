import Vue from 'vue';
import ElementUI from 'element-ui';
import App from './App.vue';
import router from './router';
import store from './store/index';
import 'element-ui/lib/theme-chalk/index.css';
import './plugins/element';
import './theme.less';
import VideoPlayer from 'vue-video-player';

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(VideoPlayer);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
