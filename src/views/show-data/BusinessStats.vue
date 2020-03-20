<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>业务统计信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 统计条件 -->
    <user-card :expand="false"
      class="search-card">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="插入记录条数">
          <el-input v-model="form.count"
            placeholder="条数"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            icon="el-icon-search"
            @click="searchOrg">批量添加</el-button>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_business_stats_list.list"
      border
      style="width: 100%">
      <el-table-column prop="summaryId"
        label="ID"
        width="240"
        align="center">
      </el-table-column>
      <el-table-column prop="bizDate"
        label="统计日期"
        width="240"
        align="center">
      </el-table-column>
      <el-table-column prop="orgId"
        label="区域ID"
        width="500"
        align="center">
      </el-table-column>
      <el-table-column prop="scheduleQuantity"
        label="播单次数"
        align="center">
      </el-table-column>
      <el-table-column prop="liveQuantity"
        label="喊话次数"
        width="120"
        align="center">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination background
        layout="total, prev, pager, next"
        @current-change="handleCurrentChange"
        :total="get_business_stats_list.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard';

export default {
  name: 'BusinessStatsList',
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
    ...mapGetters(['get_business_stats_list']),
  },
  methods: {
    ...mapActions(['getBusinessStatsList']),
    handleCurrentChange(val) {
      this.getBusinessStatsList({
        params: {
          page: val,
          size: 10,
        },
      });
    },
    searchBus() {
      this.getBusinessStatsList({
        params: {
          page: 1,
          size: 10,
          orgId: this.form.orgId,
        },
      });
    },
  },
  mounted() {
    this.getBusinessStatsList({
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
