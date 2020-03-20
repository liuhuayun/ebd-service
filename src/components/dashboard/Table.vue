<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>查看数据</el-breadcrumb-item>
      <el-breadcrumb-item>图表配置</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false"
      class="search-card">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="图表名称">
          <el-input v-model="form.configName"
            placeholder="图表名称"></el-input>
        </el-form-item>
        <el-form-item label="模块名称">
          <el-input v-model="form.moduleName"
            placeholder="模块名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            icon="el-icon-search"
            @click="searchChart">搜索</el-button>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_chart_config_list.list"
      border
      style="width: 100%">
      <el-table-column prop="id"
        label="id"
        align="center">
      </el-table-column>
      <el-table-column prop="configName"
        label="图表名称"
        align="center"
        width="250">
      </el-table-column>
      <el-table-column prop="moduleName"
        label="模块名称"
        width="100"
        align="center">
      </el-table-column>
      <el-table-column prop="apiName"
        label="接口名称"
        align="center">
      </el-table-column>
      <el-table-column prop="chartType"
        label="图表类型"
        align="center">
      </el-table-column>
      <el-table-column prop="chartName"
        label="类型名称"
        align="center">
      </el-table-column>
      <el-table-column prop="dimension"
        label="维度"
        align="center">
      </el-table-column>
      <el-table-column prop="dimName"
        label="维度名称"
        align="center">
      </el-table-column>
      <el-table-column prop="perfIndex"
        label="指标"
        align="center">
      </el-table-column>
      <el-table-column prop="indexName"
        label="指标名称"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_chart_config_list.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../UserCard.vue';

export default {
  name: 'ChartConfigListTable',
  components: {
    UserCard,
  },
  data() {
    return {
      id: 100,
      visible: false,
      form: {
        configName: '',
        moduleName: '',
      },
    };
  },
  computed: {
    ...mapGetters(['get_chart_config_list']),
  },
  methods: {
    ...mapActions(['getChartConfigList']),
    searchChart() {
      this.getChartConfigList({
        params: {
          page: 1,
          size: 10,
          configName: this.form.configName,
          moduleName: this.form.moduleName,
        },
      });
    },
    handleCurrentChange(val) {
      this.getChartConfigList({
        params: {
          page: val,
          size: 10,
        },
      });
    },
  },
  mounted() {
    this.getChartConfigList({
      params: {
        page: 1,
        size: 10,
      },
    });
  },
};
</script>
<style lang="less" scoped>
.dashboard-layout {
  background: -webkit-radial-gradient(#0d89a0 0%, #184d5f 25%, #194760 100%);
}
.gridItem {
  background: #0c3144;
  color: #fff;
  text-align: center;
}
.vue-draggable-handle {
  display: block;
  position: absolute;
  top: 6px;
  left: 6px;
  height: 10px;
  width: 10px;
  border-radius: 50%;
  background-color: #ffffff;
  z-index: 9;
}
</style>
