<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>用户统计</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false">
       <el-select :default-active="$route.path" @change="chickvalue" v-model="searchValue" filterable placeholder="请选择汇总维度" class="h-m-select">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            v-model="item.value">
          </el-option>
        </el-select>
        <el-button type="primary" size="small" @click.native='Count'>统计
        </el-button>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_user_summary.list"
      border
      style="width: 100%">
      <el-table-column prop="count"
        label="统计"
        width="420"
        align="center">
      </el-table-column>
       <el-table-column prop="time"
        label="时间"
        width="500"
        align="center">
      </el-table-column>
       <el-table-column prop="total"
        label="总计"
        width="420"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_user_summary.total">
      </el-pagination>
    </div>
  </div>
</template>


<script>
import { mapGetters, mapActions, Commit } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'UserSummary',
  data() {
    return {
      options: [{
        value: 'time',
        label: '时间',
      }],
      searchValue: '',
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_user_summary']),
  },
  methods: {
    ...mapActions(['getUserSummary']),
    handleCurrentChange(val) {
      this.getUserSummary({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    Count() {
      this.getUserSummary({
        params: {
          page: 1,
          size: 10,
          sumType: this.searchValue,
        },
      });
    },
  },
  mounted() {
    this.getUserSummary({
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
