<template>
  <div>
    <Slider />
    <Dashboard moduleName="home" />
  </div>
</template>

<script>
import Slider from '../components/Slider.vue';
import Dashboard from '../components/CommonDashboard.vue';
import instance from '../utils/axios';

export default {
  name: 'HomeDashboard',
  data() {
    return {
    };
  },
  components: {
    Slider,
    Dashboard,
  },
  created() {
    instance.defaults.headers.Authorization = `Bearer ${window.localStorage.getItem('accessToken')}`;
  },
};
</script>
