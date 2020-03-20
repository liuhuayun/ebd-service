<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>Dashboard</el-breadcrumb-item>
      <el-breadcrumb-item>Dashboard配置</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false"
      class="search-card">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="Dashboard ID">
          <el-input v-model="form.dashboardId"
            placeholder="Dashboard ID"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            icon="el-icon-search"
            @click="searchChart">搜索</el-button>
        </el-form-item>
        <el-form-item>
          <router-link to='edit/0'
            class="link">
            <el-button type="primary"
              size="small">新增配置</el-button>
          </router-link>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_dashboard_config_list.list"
      border
      style="width: 100%">
      <el-table-column prop="id"
        label="id"
        align="center">
      </el-table-column>
      <el-table-column prop="dashboardId"
        label="Dashboard ID"
        align="center">
      </el-table-column>
      <el-table-column prop="blockIndex"
        label="块索引"
        align="center">
      </el-table-column>
      <el-table-column prop="configId"
        label="图表ID"
        align="center">
      </el-table-column>
      <el-table-column label="操作"
        align="center">
        <template slot-scope="scope">
          <router-link :to="`edit/${scope.row.id}`"
            class="link"
            title="编辑">
            <i class="el-icon-edit"></i>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_dashboard_config_list.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard.vue';

export default {
  name: 'DashboardConfigList',
  components: {
    UserCard,
  },
  data() {
    return {
      id: 100,
      visible: false,
      form: {
        dashboardId: '',
      },
    };
  },
  computed: {
    ...mapGetters(['get_dashboard_config_list']),
  },
  methods: {
    ...mapActions(['getDashboardConfigList']),
    searchChart() {
      this.getDashboardConfigList({
        params: {
          page: 1,
          size: 10,
          dashboardId: this.form.dashboardId,
        },
      });
    },
    handleCurrentChange(val) {
      this.getDashboardConfigList({
        params: {
          page: val,
          size: 10,
        },
      });
    },
  },
  mounted() {
    this.getDashboardConfigList({
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
