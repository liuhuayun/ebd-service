<template>
  <el-aside class="slider-menu"
    width="220px">
    <div class="logo">
      <router-link :to="`/`"
        title="回首页">
        <img src="../assets/logo.png" />
      </router-link>
    </div>
    <el-menu :default-active="activeIndex"
      background-color="#00101a"
      text-color="#fff"
      unique-opened="true"
      active-text-color="#fff"
      :router="true">
      <el-menu-item index="/admin/tmpl/list">
        <span slot="title">Dashboard模板</span>
      </el-menu-item>
      <el-submenu index="/admin/dashboard">
        <span slot="title">Dashboard</span>
        <el-menu-item index="/admin/dashboard/list">
          <span slot="title">Dashboard</span>
        </el-menu-item>
        <el-menu-item index="/admin/dashboard/config/list">
          <span slot="title">Dashboard图表配置</span>
        </el-menu-item>
      </el-submenu>
      <el-menu-item index="/admin/charts/list">
        <span slot="title">图表查看</span>
      </el-menu-item>
      <el-menu-item index="/admin/charts/heartmap">
        <span slot="title">热力图</span>
      </el-menu-item>
      <el-submenu index="/admin/data">
        <span slot="title">数据查看</span>
        <el-menu-item index="/admin/data/org/list">
          <span slot="title">区域数据</span>
        </el-menu-item>
        <el-menu-item index="/admin/stats/biz/abstract/latest">
          <span slot="title">业务动态</span>
        </el-menu-item>
        <el-menu-item index="/admin/stats/user/list">
          <span slot="title">用户登录数据</span>
        </el-menu-item>
        <el-menu-item index="/admin/stats/user/summary">
          <span slot="title">用户活跃度统计数据</span>
        </el-menu-item>
        <el-menu-item index="/admin/data/user/list">
          <span slot="title">用户数据</span>
        </el-menu-item>
        <el-menu-item index="/admin/data/bus/list">
          <span slot="title">业务统计信息</span>
        </el-menu-item>
        <el-menu-item index="/admin/data/dev/list">
          <span slot="title">设备数据</span>
        </el-menu-item>
        <el-menu-item index="/admin/stats/biz/summary/summary/list">
          <span slot="title">业务统计汇总</span>
        </el-menu-item>
        <el-menu-item index="/admin/data/dev/total">
          <span slot="title">设备总数查看</span>
        </el-menu-item>
        <el-menu-item index="/admin/data/dev/summary">
          <span slot="title">设备统计</span>
        </el-menu-item>
        <el-menu-item index="/admin/data/user/summary">
          <span slot="title">用户统计</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </el-aside>
</template>
<script>
export default {
  name: 'AdminSlider',
  data() {
    return {
      activeIndex: '/admin/tmpl/list',
    };
  },
  watch: {
    $route(to) {
      this.getMenuKey(to);
    },
  },
  methods: {
    getMenuKey(to) {
      const key = to.path.split('/')[2];
      console.log(key);
      this.activeIndex = key;
    },
  },
  mounted() {
    this.getMenuKey(this.$route);
    console.log(this.$route);
  },
};
</script>
<style lang="less">
.slider-menu {
  position: fixed !important;
  left: 0;
  top: 0;
  bottom: 0;
  color: #fff;
  background-color: #00101a;
  border-right: 1px solid #1a96d5;
  .logo {
    position: relative;
    color: #fff;
    padding: 10px;
    img {
      width: 100px;
      display: block;
      margin: 0 auto;
      cursor: pointer;
    }
  }
  .el-menu {
    border-right: none;
  }
  .is-active {
    background-color: #01253d !important;
  }
}
</style>
