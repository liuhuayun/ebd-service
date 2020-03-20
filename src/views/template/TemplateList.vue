<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb>
      <el-breadcrumb-item>Dashboard模板</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索条件 -->
    <user-card :expand="false"
      class="search-card">
      <el-form :inline="true"
        :model="form"
        size='small'>
        <el-form-item label="图表数量">
          <el-input v-model="form.blockNum"
            placeholder="图表数量"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            icon="el-icon-search"
            @click="searchTmplList">搜索</el-button>
        </el-form-item>
        <el-form-item>
          <router-link to='edit/0'
            class="link">
            <el-button type="primary"
              size="small">新增模板</el-button>
          </router-link>
        </el-form-item>
      </el-form>
    </user-card>
    <!-- 表格 -->
    <el-table :data="get_dashboard_tmpl_list.list"
      border
      style="width: 100%">
      <el-table-column prop="id"
        label="id"
        align="center">
      </el-table-column>
      <el-table-column prop="tmplName"
        label="模板名称"
        align="center"
        width="350">
      </el-table-column>
      <el-table-column prop="blockNum"
        label="图表数量"
        align="center">
      </el-table-column>
      <el-table-column prop="searchPosition"
        label="查询按钮位置"
        align="center">
      </el-table-column>
      <el-table-column label="操作"
        width="200"
        align="center">
        <template slot-scope="scope"
          v-if="scope.row.id!==100">
          <router-link :to="`edit/${scope.row.id}`"
            class="link"
            title="编辑">
            <i class="el-icon-edit"></i>
          </router-link>
          <i class="el-icon-delete"
            title="删除"
            @click="deleteTemplate(scope.row.id)"></i>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import UserCard from '../../components/UserCard.vue';

export default {
  name: 'DashBoardTmpl',
  data() {
    return {
      id: 100,
      visible: false,
      form: {
        blockNum: '',
      },
      dialogform: {
        name: '',
        num: 1,
      },
    };
  },
  components: {
    UserCard,
  },
  computed: {
    ...mapGetters(['get_dashboard_tmpl_list']),
  },
  methods: {
    ...mapActions(['getDashboardTmplList', 'deleteDashboardTmplDetail']),
    renderTmpl() {
      this.$router.push({ path: 'edit/0', query: { name: this.dialogform.name, num: this.dialogform.num } });
    },
    deleteTemplate(id) {
      this.deleteDashboardTmplDetail({
        params: {
          id,
        },
        callback: (res) => {
          if (res.code === 200) {
            this.$message({
              message: '删除成功！',
              type: 'success',
            });
            this.getDashboardTmplList({
              params: {
                page: 1,
                size: 10,
              },
            });
          }
        },
      });
    },
    searchTmplList() {
      this.getDashboardTmplList({
        params: {
          page: 1,
          size: 10,
          blockNum: this.form.blockNum,
        },
      });
    },
  },
  mounted() {
    this.getDashboardTmplList({
      params: {
        page: 1,
        size: 10,
      },
    });
  },
};
</script>
<style lang="less" scoped>
.dashboard-layout {
  background: -webkit-radial-gradient(#0d89a0 0%, #184d5f 25%, #194760 100%);
}
.gridItem {
  background: #0c3144;
  color: #fff;
  text-align: center;
}
.vue-draggable-handle {
  display: block;
  position: absolute;
  top: 6px;
  left: 6px;
  height: 10px;
  width: 10px;
  border-radius: 50%;
  background-color: #ffffff;
  z-index: 9;
}
.el-card {
  margin: 10px 0;
  .el-form-item {
    margin-bottom: 0 !important;
    display: inline-block !important;
    .el-form-item__content {
      > .el-select {
        width: 100%;
      }
    }
  }
  .el-button {
    background: #042c46;
    border: 1px solid #042c46;
  }
}
</style>
