<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>设备统计</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false">
        <el-form :inline="true"
        :model="form"
        size='small'>
       <el-select :default-active="$route.path" @change="chickvalue" v-model="searchValue" filterable placeholder="请选择汇总维度" >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            v-model="item.value">
          </el-option>
        </el-select>
        <el-form-item>
        <el-button type="primary"
        size="small"
        @click.native='Count'> 统计
        </el-button>
        </el-form-item>
        </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_dev_summary.list"
      border
      style="width: 100%">
      <el-table-column prop="count"
        label="统计"
        width="426"
        align="center">
      </el-table-column>
      <el-table-column prop="address"
        label="位置"
        width="426"
        align="center">
      </el-table-column>
      <el-table-column prop="devName"
        label="设备名称"
        width="426"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_dev_summary.total">
      </el-pagination>
    </div>
  </div>
</template>


<script>
import { mapGetters, mapActions, Commit } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'devSummary',
  data() {
    return {
      options: [{
        value: 'devType',
        label: 'Type',
      }, {
        value: 'bizStatus',
        label: 'Status',
      }, {
        value: 'bindDate',
        label: 'Date',
      }],
      searchValue: '',
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_dev_summary']),
  },
  methods: {
    ...mapActions(['getDevSummary']),
    handleCurrentChange(val) {
      this.getDevSummary({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    Count() {
      this.getDevSummary({
        params: {
          page: 1,
          size: 10,
          sumType: this.searchValue,
        },
      });
    },
  },
  mounted() {
    this.getDevSummary({
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
