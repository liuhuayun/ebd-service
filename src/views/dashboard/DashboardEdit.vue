<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>Dashboard</el-breadcrumb-item>
      <el-breadcrumb-item>编辑Dashboard</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow="hover"
      class="perdashboard">
      <el-form ref="form"
        :model="form"
        label-width="80px">
        <el-form-item label="Dashboard名称">
          <el-input v-model="form.dashboardName"></el-input>
        </el-form-item>
        <el-form-item label="模块名称">
          <el-input v-model="form.moduleName"></el-input>
        </el-form-item>
        <el-form-item label="Dashboard模板ID">
          <el-input v-model="form.tmplId"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            @click="onSubmit">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { mapActions } from 'vuex';

export default {
  name: 'DashboardEdit',
  data() {
    return {
      form: {
        dashboardName: '',
        moduleName: '',
        tmplId: '',
      },
      dashboardId: 0,
      dashboard: {},
    };
  },
  methods: {
    ...mapActions(['getDashboardDetail', 'putDashboard']),
    init() {
      this.dashboardId = this.$route.params.id;
      if (this.dashboardId !== 0) {
        this.getDashboardDetail({
          params: {
            id: this.dashboardId,
          },
          callback: (res) => {
            this.dashboard = res.data;
            this.form.dashboardName = this.dashboard.dashboardName;
            this.form.moduleName = this.dashboard.moduleName;
            this.form.tmplId = this.dashboard.tmplId;
          },
        });
      }
    },
    onSubmit() {
      if (this.dashboardId !== '0') {
        this.dashboard.dashboardName = this.form.dashboardName;
        this.dashboard.moduleName = this.form.moduleName;
        this.dashboard.tmplId = this.form.tmplId;
        this.putDashboard({
          params: this.dashboard,
          callback: (res) => {
            if (res.code === 200) {
              this.$message({
                message: '更新成功！',
                type: 'success',
              });
              this.$router.go(-1);
            }
          },
        });
      }
    },
  },
  mounted() {
    this.init();
  },
};
</script>
<style lang="less" scoped>
.perdashboard {
  .el-form-item {
    margin-bottom: 10px !important;
    width: 400px;
    /deep/ .el-select {
      width: 100%;
    }
  }
}
</style>
