<template>
  <div @mouseover="stop"
    @mouseout="start"
    class="box">
    <div>
      <ul class="trends"
        ref="rollup">
        <li v-for="item in items"
          :key="item.id">{{item.name}}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'Rolling',
  data() {
    return {
      animate: false,
      time: '',
      items: [],
    };
  },
  destroyed() {
    clearInterval(this.time);
  },
  computed: {
    ...mapGetters(['get_dynamic_info']),
  },
  watch: {
    get_dynamic_info(val) {
      this.createItems(val.list);
    },
  },
  methods: {
    ...mapActions(['getDynamicInfo']),
    createItems(list) {
      this.items = list.map(item => ({
        name: `${item.orgName} 正在播放 ${item.bizAbstract}`,
        id: item.id,
      }));
    },
    scroll() {
      const _this = this;
      const { rollup } = this.$refs;
      rollup.style.transition = 'all 0.3s ease';
      rollup.style.marginTop = '-22px';
      setTimeout(() => {
        const deleteElement = this.items.shift();
        this.items.push(deleteElement);
        rollup.style.transition = 'none';
        rollup.style.marginTop = '0px';
      }, 300);
    },
    start() {
      this.time = setInterval(this.scroll, 3000);
    },
    stop() {
      clearInterval(this.time);
    },
  },
  mounted() {
    this.time = setInterval(this.scroll, 3000);
    this.getDynamicInfo({
      params: {
        page: 0,
        size: 10,
      },
    });
  },
};
</script>

<style lang="less" scoped>
.box {
  font-size: 12px;
  height: 100%;
  width: 100%;
  overflow: hidden;
  box-sizing: border-box;
  padding: 20px 10px 6px 10px;
  overflow: hidden;
  > div {
    height: 100%;
    overflow: hidden;
  }
}
.trends {
  li {
    list-style: none;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    vertical-align: middle;
    position: relative;
    padding-left: 10px;
    height: 22px;
    line-height: 22px;
  }
  li:before {
    content: " ";
    background-color: #fff;
    width: 4px;
    height: 4px;
    border-radius: 50%;
    margin-right: 2px;
    position: absolute;
    left: 0;
    top: 50%;
    margin-top: -2px;
  }
}
</style>
