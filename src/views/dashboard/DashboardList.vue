<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>Dashboard</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 表格 -->
    <el-table :data="get_dashboard_list.list"
      border
      style="width: 100%">
      <el-table-column prop="id"
        label="id"
        align="center">
      </el-table-column>
      <el-table-column prop="dashboardName"
        label="Dashboard名称"
        align="center"
        width="350">
      </el-table-column>
      <el-table-column prop="moduleName"
        label="模块名称"
        width="200"
        align="center">
      </el-table-column>
      <el-table-column prop="tmplId"
        label="Dashboard模板ID"
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
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'DashBoardList',
  data() {
    return {
      id: 100,
      visible: false,
    };
  },
  computed: {
    ...mapGetters(['get_dashboard_list']),
  },
  methods: {
    ...mapActions(['getDashboardList']),
  },
  mounted() {
    this.getDashboardList({
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
