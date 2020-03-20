<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>查看图表</el-breadcrumb-item>
      <el-breadcrumb-item>编辑图表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow="hover"
      class="perconfig">
      <el-form ref="form"
        :model="form"
        label-width="80px">
        <el-form-item label="图表名称">
          <el-input v-model="form.configName"></el-input>
        </el-form-item>
        <el-form-item label="模块名称">
          <el-input v-model="form.moduleName"></el-input>
        </el-form-item>
        <el-form-item label="接口名称">
          <el-input v-model="form.apiName"></el-input>
        </el-form-item>
        <el-form-item label="图表类型">
          <el-input v-model="form.chartType"></el-input>
        </el-form-item>
        <el-form-item label="类型名称">
          <el-input v-model="form.chartName"></el-input>
        </el-form-item>
        <el-form-item label="维度">
          <el-input v-model="form.dimension"></el-input>
        </el-form-item>
        <el-form-item label="维度名称">
          <el-input v-model="form.dimName"></el-input>
        </el-form-item>
        <el-form-item label="指标">
          <el-input v-model="form.perfIndex"></el-input>
        </el-form-item>
        <el-form-item label="指标名称">
          <el-input v-model="form.indexName"></el-input>
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
  name: 'ChartConfigEdit',
  data() {
    return {
      form: {
        configName: '',
        moduleName: '',
        apiName: '',
        chartType: '',
        chartName: '',
        dimension: '',
        dimName: '',
        perfIndex: '',
        indexName: '',
      },
      configId: 0,
      config: {},
    };
  },
  methods: {
    ...mapActions(['getChartConfigItem', 'putChartConfig', 'postChartConfig']),
    init() {
      this.configId = this.$route.params.id;
      if (this.configId !== 0) {
        this.getChartConfigItem({
          params: {
            id: this.configId,
          },
          callback: (res) => {
            this.config = res.data;
            this.form.configName = this.config.configName;
            this.form.moduleName = this.config.moduleName;
            this.form.apiName = this.config.apiName;
            this.form.chartType = this.config.chartType;
            this.form.chartName = this.config.chartName;
            this.form.dimension = this.config.dimension;
            this.form.dimName = this.config.dimName;
            this.form.perfIndex = this.config.perfIndex;
            this.form.indexName = this.config.indexName;
          },
        });
      }
    },
    onSubmit() {
      if (this.configId !== '0') {
        this.config.configName = this.form.configName;
        this.config.moduleName = this.form.moduleName;
        this.config.apiName = this.form.apiName;
        this.config.chartType = this.form.chartType;
        this.config.chartName = this.form.chartName;
        this.config.dimension = this.form.dimension;
        this.config.dimName = this.form.dimName;
        this.config.perfIndex = this.form.perfIndex;
        this.config.indexName = this.form.indexName;
        this.putChartConfig({
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
        this.postChartConfig({
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
