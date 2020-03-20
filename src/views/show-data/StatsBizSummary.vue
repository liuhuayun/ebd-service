<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>业务统计信息汇总</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 下拉菜单 -->
    <user-card :expand="false"
      class="search-card">
      <el-form :inline="true"
        :model="form"
        size='small'>

        <el-select v-model="selectedValue" placeholder="请选择维度" @change="getValue"
          class="el-select">
          <el-option v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            class="el-option">
          </el-option>
        </el-select>
            <el-form-item style="margin-left: 3%">
          <el-button type="primary"
          class="el-button"
            size="small" @click="bizSummary" >统计</el-button>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_stats_biz_summary.list"
      border
      style="width: 100%">
      <el-table-column prop="orgId"
        label="区域ID"
        align="center">
      </el-table-column>
      <el-table-column prop="bizDate"
        label="统计日期"
        align="center">
      </el-table-column>
      <el-table-column prop="scheduleQuantity"
        label="播单次数"
        align="center">
      </el-table-column>
      <el-table-column prop="liveQuantity"
        label="喊话次数"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_stats_biz_summary.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard';

let selectedValue;

export default {
  name: 'StatsBizSummary',
  data() {
    return {
      options: [{
        label: '日期',
        value: 'date',
      }],
      selectedValue: '',
    };
  },

  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_stats_biz_summary']),
  },
  methods: {
    ...mapActions(['getStatsBizSummary']),
    handleCurrentChange(val) {
      this.getStatsBizSummary({
        params: {
          page: val,
          size: 10,
          sumType: selectedValue,
        },
      });
    },
    bizSummary() {
      this.getStatsBizSummary({
        params: {
          page: 1,
          size: 10,
          sumType: selectedValue,
        },
      });
    },
    // 获取下拉菜单的值
    getValue(value) {
      selectedValue = value;
    },
  },
  mounted() {
    this.getStatsBizSummary({
      params: {
        page: 1,
        size: 10,
        // 默认按date显示
        sumType: 'date',
      },
    });
  },
};
</script>
<style lang="less" scoped>
.el-option:hover {
  cursor: pointer;
   color: #409EFF;
}
.el-button {
    vertical-align: sub;
}
</style>
