<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import instance from './utils/axios';

export default {
  name: 'app',
  created() {
    instance.defaults.headers.Authorization = `Bearer ${window.localStorage.getItem('accessToken')}`;
  },
};
</script>

<style lang="less">

</style>
