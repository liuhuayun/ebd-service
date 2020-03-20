<template>
  <div>
    <el-card shadow="hover">
      <el-form :inline="true"
        :model="tmpl"
        :rules="rules"
        ref="tmpl">
        <el-form-item label="模板名称"
          prop="tmplName">
          <el-input v-model="tmpl.tmplName"
            size="small"
            autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="查询按钮位置"
          prop="searchPosition">
          <el-input v-model="tmpl.searchPosition"
            size="small"
            autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图表数量"
          prop="blockNum">
          <el-input v-model="tmpl.blockNum"
            size="small"
            :disabled="true">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            @click="visible = true">修改</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            size="small"
            @click="doSave('tmpl')">保存模板</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <grid-layout :layout="layout"
      :col-num="48"
      :row-height="38"
      :is-draggable="true"
      :is-resizable="true"
      :vertical-compact="true"
      :margin="[10, 10]"
      :use-css-transforms="true"
      class="dashboard-layout">
      <grid-item :key="item.i"
        v-for="item in layout"
        :x="item.x"
        :y="item.y"
        :w="item.w"
        :h="item.h"
        :i="item.i"
        drag-allow-from=".vue-draggable-handle"
        class="gridItem">
        <span class="vue-draggable-handle"></span>
        图表{{item.i+1}}
      </grid-item>
    </grid-layout>
    <modal :visible.sync="visible"
      title="模板数量"
      @confirm="renderTmpl">
      <el-form slot="form"
        inline
        class="number-input">
        <el-form-item label="图表数量">
          <el-input-number v-model="tmpl.blockNum"
            :min="1"
            :max="25"
            size="small"
            label="图表数量"></el-input-number>
        </el-form-item>
      </el-form>
    </modal>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import { GridLayout, GridItem } from 'vue-grid-layout';
import Modal from '../../components/Modal.vue';
import { computeLayout } from '../../utils/config';

export default {
  name: 'DashBoardTmplDetail',
  data() {
    return {
      visible: false,
      rules: {
        tmplName: [
          { required: true, message: '请输入模板名称', trigger: 'blur' },
        ],
        blockNum: [
          { required: true, message: '请输入模板数量', trigger: 'change' },
        ],
      },
      tmpl: {
        tmplName: '',
        blockNum: 9,
        searchPosition: 1,
      },
      layout: [],
    };
  },
  components: {
    GridLayout,
    GridItem,
    Modal,
  },
  computed: {
    ...mapGetters(['get_dashboard_tmpl_detail']),
  },
  watch: {
    get_dashboard_tmpl_detail(val) {
      this.layout = val.tmplDetails;
      this.tmpl = val.tmpl;
    },
  },
  methods: {
    ...mapActions(['getDashboardTmplDetail', 'postDashboardTmplDetail', 'putDashboardTmplDetail']),
    init() {
      if (this.$route.params.id === '0') {
        this.layout = computeLayout(3, 4, this.tmpl.blockNum);
      } else {
        this.getDashboardTmplDetail({
          params: {
            tmplId: this.$route.params.id,
          },
        });
      }
    },
    renderTmpl() {
      this.layout = computeLayout(3, 4, this.tmpl.blockNum);
      this.visible = false;
    },
    doSave(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.$route.params.id === '0') {
            this.postDashboardTmplDetail({
              params: {
                tmpl: this.tmpl,
                tmplDetails: this.layout,
              },
              callback: (res) => {
                if (res.code === 200) {
                  this.$message({
                    message: '增加成功！',
                    type: 'success',
                  });
                  this.$router.go(-1);
                }
              },
            });
          } else {
            this.putDashboardTmplDetail({
              params: {
                tmpl: this.tmpl,
                tmplDetails: this.layout,
              },
              callback: (res) => {
                if (res.code === 200) {
                  this.$message({
                    message: '更新成功！',
                    type: 'success',
                  });
                  this.$router.go(-1);
                }
              },
            });
          }
          return true;
        }
        // console.log('error submit!!');
        return false;
      });
    },
  },
  mounted() {
    this.init();
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
.number-input {
  padding: 20px 0;
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
