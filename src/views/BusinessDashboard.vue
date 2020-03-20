<template>
  <div>
    <Slider />
    <Dashboard moduleName="business_stats" />
  </div>
</template>

<script>
import Slider from '../components/Slider.vue';
import Dashboard from '../components/CommonDashboard.vue';

export default {
  name: 'BusinessDashboard',
  data() {
    return {
    };
  },
  components: {
    Slider,
    Dashboard,
  },
};
</script>
