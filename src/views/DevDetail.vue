<template>
  <div>
    <Slider />
    <Dashboard moduleName="dev_status" />
  </div>
</template>

<script>
import Slider from '../components/Slider.vue';
import Dashboard from '../components/CommonDashboard.vue';

export default {
  name: 'DevDashboard',
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
