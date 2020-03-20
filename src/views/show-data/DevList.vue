<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>设备列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label=  "设备状态">
        <el-input v-model="form.bizStatus"
            placeholder="设备状态"></el-input>
        </el-form-item>
        <el-form-item label="区域ID">
          <el-input v-model="form.orgId"
            placeholder="区域ID"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            icon="el-icon-search"
            @click="searchDev">搜索</el-button>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_dev_list.list"
      border
      style="width: 100%">
      <el-table-column prop="devName"
        label="设备名称"
        align="center">
      </el-table-column>
      <el-table-column prop="devType"
        label="设备维度"
        align="center">
      </el-table-column>
      <el-table-column prop="orgId"
        label="orgId"
        align="center">
      </el-table-column>
      <el-table-column prop="bizStatus"
        label="在线状态类型"
        align="center">
      </el-table-column>
      <el-table-column prop="onlineStatus"
        label="是否在线"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="systemId"
        label="所属系统"
        width="120"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_dev_list.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'DevList',
  data() {
    return {
      id: 100,
      visible: false,
      form: {
        bizStatus: '',
        orgId: '',
      },
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_dev_list']),
  },
  methods: {
    ...mapActions(['getDevList']),
    handleCurrentChange(val) {
      this.getDevList({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    searchDev() {
      this.getDevList({
        params: {
          page: 1,
          size: 10,
          bizStatus: this.form.bizStatus,
          orgId: this.form.orgId,
        },
      });
    },
  },
  mounted() {
    this.getDevList({
      params: {
        page: 1,
        size: 10,
      },
    });
  },
};
</script>
<style lang="less" scoped>
</style>
