<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="用户名称">
          <el-input v-model="form.orgName"
            placeholder="用户名称"></el-input>
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
    <el-table :data="get_user_list.list"
      border
      style="width: 100%">
      <el-table-column prop="username"
        label="用户名"
        align="center">
      </el-table-column>
      <el-table-column prop="realName"
        label="真实姓名"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="phone"
        label="手机号"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column prop="email"
        label="邮箱"
        align="center">
      </el-table-column>
      <el-table-column prop="orgId"
        label="orgId"
        width="120"
        align="center">
      </el-table-column><el-table-column prop="systemId"
        label="systemId"
        width="120"
        align="center">
      </el-table-column>

    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_user_list.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'UserList',
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
    ...mapGetters(['get_user_list']),
  },
  methods: {
    ...mapActions(['getUserList']),
    handleCurrentChange(val) {
      this.getUserList({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    searchOrg() {
      this.getUserList({
        params: {
          page: 1,
          size: 10,
          orgName: this.form.orgName,
        },
      });
    },
  },
  mounted() {
    this.getUserList({
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
