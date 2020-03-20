<template>
  <div>
    <Slider />
    <Dashboard moduleName="user_info" />
  </div>
</template>

<script>
import Slider from '../components/Slider.vue';
import Dashboard from '../components/CommonDashboard.vue';

export default {
  name: 'UserDashboard',
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
