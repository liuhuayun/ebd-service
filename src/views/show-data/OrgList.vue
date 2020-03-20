<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>区域列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false"
      class="search-card">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="区域名称">
          <el-input v-model="form.orgName"
            placeholder="区域名称"></el-input>
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
    <el-table :data="get_org_list.list"
      border
      style="width: 100%">
      <el-table-column prop="orgId"
        label="orgId"
        align="center">
      </el-table-column>
      <el-table-column prop="orgName"
        label="区域名称"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="orgCode"
        label="区域编码"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="parentId"
        label="上级区域ID"
        align="center">
      </el-table-column>
      <el-table-column prop="systemId"
        label="所属系统"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="rooted"
        label="是否为根节点"
        width="60"
        align="center">
        <template slot-scope="scope">{{scope.row.rooted?'是':''}}</template>
      </el-table-column>
      <el-table-column prop="leafed"
        label="是否为叶子节点"
        width="60"
        align="center">
        <template slot-scope="scope">{{scope.row.leafed?'是':''}}</template>
      </el-table-column>
      <el-table-column prop="deleted"
        label="是否已删除"
        width="60"
        align="center">
        <template slot-scope="scope">{{scope.row.deleted?'是':''}}</template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_org_list.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'OrgList',
  data() {
    return {
      id: 100,
      visible: false,
      form: {
        orgName: '',
      },
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_org_list']),
  },
  methods: {
    ...mapActions(['getOrgList']),
    handleCurrentChange(val) {
      this.getOrgList({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    searchOrg() {
      this.getOrgList({
        params: {
          page: 1,
          size: 10,
          orgName: this.form.orgName,
        },
      });
    },
  },
  mounted() {
    this.getOrgList({
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
