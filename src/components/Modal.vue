<template>
  <el-dialog :title="title"
    :visible.sync="visible"
    :top="top"
    class="modal">
    <slot name="form"></slot>
    <div slot="footer"
      class="dialog-footer">
      <el-button size="small"
        @click="ispop=false">取 消</el-button>
      <el-button type="primary"
        size="small"
        @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
  name: 'Modal',
  props: {
    visible: {
      type: Boolean,
    },
    title: {
      type: String,
    },
    top: {
      default: '15vh',
    },
  },
  data() {
    return {
      ispop: false,
    };
  },
  watch: {
    visible(val) {
      this.ispop = val;
    },
    ispop(val) {
      this.$emit('update:visible', val);
    },
  },
  methods: {
    submit() {
      this.$emit('confirm');
    },
  },
  mounted() {

  },
};
</script>
<style lang="less" scoped>
.modal {
  /deep/ .el-dialog__body {
    max-height: 72vh;
    overflow-y: auto;
    padding: 5px 10px;
    text-align: center;
  }
  /deep/ .el-dialog__header {
    padding: 10px;
    border-bottom: 1px solid #efefef;
  }
  /deep/ .el-dialog__headerbtn {
    top: 15px;
  }
  /deep/ .el-dialog__footer {
    border-top: 1px solid #efefef;
    padding: 10px;
  }
}
</style>
