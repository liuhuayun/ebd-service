<template>
  <div class="container">
    <AdminSlider />
    <el-main class="content">
      <router-view />
    </el-main>
  </div>
</template>
<script>
import AdminSlider from '../components/AdminSlider.vue';

export default {
  name: 'admin',
  components: {
    AdminSlider,
  },
};
</script>
<style lang="less" scoped>
.container {
  min-height: 100vh;
}
.content {
  width: calc(~"100vw - 220px");
  margin-left: 220px;
  min-height: 100vh;
  padding: 10px;
  background-color: #000;
}
</style>
