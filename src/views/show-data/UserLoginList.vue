<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>用户登录信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false"
      class="search-card">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="用户ID">
          <el-input v-model="form.userId"
            placeholder="用户ID"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            icon="el-icon-search"
            @click="searchUser">搜索</el-button>
        </el-form-item>
        <el-form-item label="记录条数" style="margin-left: 5%">
          <el-input v-model="form.count"
            placeholder="插入的数目">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            @click="batchAddUser">批量添加</el-button>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_user_login_info.list"
      border
      style="width: 100%">
      <el-table-column prop="userId"
        label="用户ID"
        align="center">
      </el-table-column>
      <el-table-column prop="loginDate"
        label="登录时间"
        width="420"
        align="center">
      </el-table-column>
      <el-table-column prop="clientType"
        label="设备类型"
        width="220"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_user_login_info.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'UserLoginList',
  data() {
    return {
      id: 100,
      visible: false,
      form: {
        count: 1,
      },
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_user_login_info']),
  },
  methods: {
    ...mapActions(['getUserLoginInfo']),
    ...mapActions(['postBatchAddUserLogin']),
    handleCurrentChange(val) {
      this.getUserLoginInfo({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    searchUser() {
      this.getUserLoginInfo({
        params: {
          page: 1,
          size: 10,
          // userId: this.form.userId,
        },
      });
    },
    batchAddUser() {
      this.postBatchAddUserLogin({
        params: {
          count: this.form.count,
        },
        callback: (res) => {
          if (res.code === 200) {
            this.$message({
              message: `成功插入了${this.form.count}条记录！`,
              type: 'success',
            });
            this.getUserLoginInfo({
              params: {
                page: 1,
                size: 10,
              },
            });
          }
        },
      });
    },
  },
  mounted() {
    this.getUserLoginInfo({
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
