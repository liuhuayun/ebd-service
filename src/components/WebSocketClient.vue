<template>
  <div>
     <h1>{{ message }}</h1>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      message: '',
      websocket: null,
    };
  },
  created() {
    this.initWebSocket();
  },
  destroyed() {
    this.websocketclose();
  },
  computed: {
    ...mapGetters(['get_dev_list']),
  },
  methods: {
    initWebSocket() {
      const wsuri = 'ws://localhost:8688/websocket/123';
      this.websocket = new WebSocket(wsuri);
      this.websocket.onopen = this.websocketonopen;
      this.websocket.onerror = this.websocketonerror;
      this.websocket.onmessage = this.websocketonmessage;
      this.websocket.onclose = this.websocketclose;
    },

    websocketonopen() {
      console.log('WebSocket连接成功');
    },
    websocketonerror(e) {
      console.log(`WebSocket连接发生错误: ${e.code}`);
    },
    websocketonmessage(e) { // 数据接收
      const redata = JSON.parse(e.data);
      console.log(redata.type);
      console.log(redata.value);
      // this.message = redata.type;
      switch (redata.type) {
        case 'dev':
          // 使用web socket发过来的数据更新dev list
          this.updateDevList(redata.value);
          break;
        case 'news':
          // 更新动态列表
          this.updateDynamicInfo(redata.value);
          break;
        default:
          break;
      }
    },
    updateDynamicInfo(news) {

    },
    updateDevList(dev) {
      let found = false;
      const { list } = this.get_dev_list;
      list.forEach((element) => {
        if (element.devId === dev.devId) {
          element.onlineStatus = dev.onlineStatus;
          element.bizStatus = dev.bizStatus;
          found = true;
        }
      });
      if (!found) {
        this.get_dev_list.list.push(dev);
      }
    },
    websocketsend(agentData) { // 数据发送
      this.websock.send(agentData);
    },
    websocketclose(e) {
      console.log(`connection closed (${e.code})`);
    },
  },
};
</script>
<style lang="less" scoped>
h1 {
  font-size: 18px;
  line-height: 18px;
  color: #fff;
}
</style>
