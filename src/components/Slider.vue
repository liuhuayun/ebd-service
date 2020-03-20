<template>
  <div class="Slider"
    :class="{'active': type==2}">
    <div class="SliderTree">
      <el-input placeholder="输入关键字进行过滤"
        v-model="filterText">
      </el-input>
      <el-tree class="filter-tree"
        :data="data2"
        :props="defaultProps"
        default-expand-all
        :filter-node-method="filterNode"
        node-key="id"
        @node-click="handleNodeClick"
        ref="tree2">
      </el-tree>
    </div>
    <div class="set"
      :class="{'active': type==2}"
      @click="shownext">
      <router-link to="admin/tmpl/list"
        title="设置">
        <img src="../assets/set.png">
        <span>设置</span>
      </router-link>
    </div>
    <div class="arrow"
      :class="{'bactive': type==2}"
      @click="show"></div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from 'vuex';

export default {
  name: 'Slider',
  data() {
    return {
      type: 2,
      filterText: '',
      data2: [],
      defaultProps: {
        children: 'children',
        label: 'label',
      },
    };
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val);
    },
  },
  computed: {
    ...mapGetters(['get_org_list']),
  },
  methods: {
    ...mapMutations({ saveLocation: 'PUT_LOCATION_NAME' }),
    ...mapActions(['getOrgList']),
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    handleNodeClick(obj, data) {
      console.log(`obj=>${JSON.stringify(obj)}`);
      if (data.level <= 3) {
        this.saveLocation((data.parent.data.label || '') + obj.label);
      }
      const orgId = obj.id;
      if (obj.children.length === 0) {
        this.getOrgList({
          params: {
            parentId: orgId,
          },
          callback: (res) => {
            const { list } = res.data;
            list.forEach((item) => {
              const org = {};
              org.id = item.orgId;
              org.label = item.orgName;
              if (!item.leafed) {
                org.children = [];
              }
              obj.children.splice(obj.children.length, 0, org);
            });
          },
        });
      }
    },
    show() {
      if (this.type === 1) {
        this.type = 2;
      } else {
        this.type = 1;
        setTimeout(() => {
          this.type = 2;
        }, 30000);
      }
    },
    shownext() {
      console.log('打开新的页面');
    },
    init() {
      this.getOrgList({
        params: {
          rooted: 1,
        },
        callback: (res) => {
          const { list } = res.data;
          this.data2 = [];
          list.forEach((item) => {
            const org = {};
            org.id = item.orgId;
            org.label = item.orgName;
            if (!item.leafed) {
              org.children = [];
            }
            this.data2.splice(this.data2.length, 0, org);
          });
        },
      });
    },
  },
  mounted() {
    this.init();
  },

};
</script>

<style lang="less">
.Slider {
  position: fixed;
  width: 220px;
  height: 96vh;
  top: 2vh;
  left: 10px;
  z-index: 9;
  background-color: #00101a;
  opacity: 0.8;
  box-shadow: #1a96d5 0px 0px 10px 5px inset;
  border-radius: 5px;
  box-sizing: border-box;
  transition: width 0.5s;
}
.SliderTree {
  height: 96vh;
}
.Slider {
  .el-tree {
    color: #ffffff;
    background: transparent;
    padding: 0 10px;
    height: 82vh;
    overflow: auto;
  }
  .el-input,
  .el-input__inner {
    background: transparent;
    border: none;
    box-shadow: #1a96d5 0px 0px 3px 2px inset;
    color: #ffffff;
    border-radius: 5px 5px 0 0;
  }
  .el-tree-node__content:hover {
    background-color: #01253d;
  }
  .el-tree-node:focus > .el-tree-node__content {
    background-color: #01253d;
  }
  .tree-icon {
    display: inline-block;
  }
  .group-class {
    background-image: url(../assets/icon.png);
    width: 10px;
    height: 10px;
  }
  .el-tree-node__label {
    font-size: 14px;
  }
  .el-tree-node__expand-icon {
    background-image: url(../assets/icon.png);
    width: 4px;
    height: 4px;
    background-size: 100% 100%;
    background-repeat: no-repeat;
    margin-right: 5px;
  }
  .el-tree-node__expand-icon.expanded {
    transform: rotate(0deg);
  }
  .el-tree-node__children {
    .el-tree-node__expand-icon {
      background-image: url(../assets/dian.png);
      width: 4px;
      height: 4px;
      background-size: 100% 100%;
      background-repeat: no-repeat;
    }
    .el-tree-node__expand-icon.expanded {
      transform: rotate(0deg);
    }
    .el-tree-node__content {
      > .el-tree-node__expand-icon {
        padding: 1px;
      }
    }
  }
  .el-icon-caret-right:before {
    content: "";
  }
}
.arrow {
  position: fixed;
  width: 32px;
  height: 32px;
  left: 240px;
  bottom: 5vh;
  cursor: pointer;
  transition: left 0.5s, transform 0.5s;
  background: url("../assets/left.png") no-repeat center #282828;
}
.set {
  position: fixed;
  left: 30px;
  bottom: 5vh;
  cursor: pointer;
  img {
    width: 22px;
    height: 22px;
    vertical-align: middle;
    margin-right: 5px;
  }
  span{
    color:#fff;
    text-decoration: none;
    line-height: 22px;
    vertical-align: middle;
  }
}
.active {
  width: 0;
  overflow: hidden;
}
.bactive {
  left: 20px;
  transform: rotate(180deg);
}
</style>
