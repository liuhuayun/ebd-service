<template>
  <div>
    <Slider />
    <Dashboard moduleName="dynamic_info" />
  </div>
</template>

<script>
import Slider from '../components/Slider.vue';
import Dashboard from '../components/CommonDashboard.vue';

export default {
  name: 'NewsDashboard',
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
