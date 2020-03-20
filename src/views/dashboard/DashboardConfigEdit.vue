<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>Dashboard</el-breadcrumb-item>
      <el-breadcrumb-item>Dashboard配置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow="hover"
      class="perconfig">
      <el-form ref="form"
        :model="form"
        label-width="80px">
        <el-form-item label="dashboard ID">
          <el-input v-model="form.dashboardId"></el-input>
        </el-form-item>
        <el-form-item label="块索引">
          <el-input v-model="form.blockIndex"></el-input>
        </el-form-item>
        <el-form-item label="图表ID">
          <el-input v-model="form.configId"></el-input>
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
  name: 'DashboardConfigEdit',
  data() {
    return {
      form: {
        blockIndex: '',
        configId: '',
      },
      dashConfigId: '',
      config: {},
    };
  },
  methods: {
    ...mapActions(['getDashboardConfig', 'putDashboardConfig', 'postDashboardConfig']),
    init() {
      this.dashConfigId = this.$route.params.id;
      if (this.dashConfigId !== 0) {
        this.getDashboardConfig({
          params: {
            id: this.dashConfigId,
          },
          callback: (res) => {
            this.config = res.data;
            this.form.dashboardId = this.config.dashboardId;
            this.form.blockIndex = this.config.blockIndex;
            this.form.configId = this.config.configId;
          },
        });
      }
    },
    onSubmit() {
      if (this.dashConfigId !== '0') {
        this.config.dashboardId = this.form.dashboardId;
        this.config.blockIndex = this.form.blockIndex;
        this.config.configId = this.form.configId;
        this.putDashboardConfig({
          params: this.config,
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
      } else {
        this.postDashboardConfig({
          params: this.form,
          callback: (res) => {
            if (res.code === 200) {
              this.$message({
                message: '添加成功！',
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
.perconfig {
  .el-form-item {
    margin-bottom: 10px !important;
    width: 400px;
    /deep/ .el-select {
      width: 100%;
    }
  }
}
</style>
