<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>业务动态</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="区域编号">
          <el-input v-model="form.orgId"
            placeholder="orgId"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            icon="el-icon-search"
            @click="searchOrg">搜索</el-button>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_dynamic_info.list"
      border
      style="width: 100%">
      <el-table-column prop="bizAbstract"
        label="bizAbstract"
        align="center">
      </el-table-column>
      <el-table-column prop="bizTime"
        label="bizTime"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="bizType"
        label="bizType"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="id"
        label="id"
        align="center">
      </el-table-column>
      <el-table-column prop="opUser"
        label="opUser"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="orgId"
        label="orgId"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="orgName"
        label="orgName"
        width="120"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_dynamic_info.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'DynamicInfo',
  data() {
    return {
      id: 100,
      visible: false,
      form: {
        orgId: '',
      },
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_dynamic_info']),
  },
  methods: {
    ...mapActions(['getDynamicInfo']),
    handleCurrentChange(val) {
      this.getDynamicInfo({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    searchOrg() {
      this.getDynamicInfo({
        params: {
          page: 1,
          size: 10,
          orgId: this.form.orgId,
        },
      });
    },
  },
  mounted() {
    this.getDynamicInfo({
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
