<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>用户活跃度统计</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false"
    class="search-card">
    <el-form :inline="true"
        :model="form"
        size='small'>
       <el-select :default-active="$route.path" @change="chickvalue"
        v-model="searchValue" filterable placeholder="请选择汇总维度" >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            v-model="item.value">
          </el-option>
        </el-select>
        <el-date-picker v-model="startTime" type="date"
          placeholder="开始日期"
          style="margin-left: 2%"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd">
        </el-date-picker>
        <el-date-picker v-model="endTime" type="date"
          placeholder="结束日期"
          style="margin-left: 2%"
          value-format="yyyy-MM-dd"
          format="yyyy-MM-dd">
        </el-date-picker>
        <el-button type="primary" size="small" @click.native='Count'
         style="margin-left: 2%">统计
        </el-button>
        </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_user_login_summary.list"
      border
      style="width: 100%">
      <el-table-column prop="count"
        label="统计"
        width="190"
        align="center">
      </el-table-column>
      <el-table-column prop="day"
        label="日"
        width="190"
        align="center">
      </el-table-column>
      <el-table-column prop="week"
        label="周"
        width="190"
        align="center">
      </el-table-column>
      <el-table-column prop="month"
        label="月"
        width="190"
        align="center">
      </el-table-column>
      <el-table-column prop="year"
        label="年"
        width="190"
        align="center">
      </el-table-column>
      <el-table-column prop="clientType"
        label="客户端类型"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_user_login_summary.total">
      </el-pagination>
    </div>
  </div>
</template>


<script>
import { mapGetters, mapActions, Commit } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'UserLoginSummary',
  data() {
    return {
      options: [{
        value: 'day',
        label: '每日',
      }, {
        value: 'week',
        label: '每周',
      }, {
        value: 'month',
        label: '每月',
      }, {
        value: 'year',
        label: '每年',
      }, {
        value: 'clientType',
        label: '客户端类型',
      }],
      searchValue: '',
      startTime: '',
      endTime: '',
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_user_login_summary']),
  },
  methods: {
    ...mapActions(['getUserLoginSummary']),
    handleCurrentChange(val) {
      this.getUserLoginSummary({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    Count() {
      this.getUserLoginSummary({
        params: {
          page: 1,
          size: 10,
          sumType: this.searchValue,
          beginDate: this.startTime,
          endDate: this.endTime,
        },
      });
    },
  },
  mounted() {
    this.getUserLoginSummary({
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
