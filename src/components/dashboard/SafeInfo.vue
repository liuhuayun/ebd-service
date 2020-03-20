<template>
  <div class="safe-content">
    <ul class="left-list">
      <li>病毒检测：
        <span>{{ status.virusDetection }}</span>
        <span v-show="loadding"
          class="loadding-container"></span>
      </li>
      <li>网络防护：
        <span>{{ status.networkDefense }}</span>
        <span v-show="loadding"
          class="loadding-container"></span>
      </li>
      <li>功能服务：
        <span>{{ status.functionalService }}</span>
        <span v-show="loadding"
          class="loadding-container"></span>
      </li>
      <li>证书授权：
        <span>{{ status.certificateAuthorities }}</span>
        <span v-show="loadding"
          class="loadding-container"></span>
      </li>
      <li>加解密机制：
        <span>{{ status.enciphermentMechanisms }}</span>
        <span v-show="loadding"
          class="loadding-container"></span>
      </li>
      <li>操作员：
        <span>{{ status.opUser }}</span>
      </li>
      <li>扫描时间：
        <span>{{ status.scanTime }}</span>
      </li>
    </ul>
    <div class="right-safe-logo"></div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from 'vuex';

export default {
  name: 'SafeInfo',
  data() {
    return {
      loadding: false,
      status: {
        virusDetection: '正在扫描...',
        networkDefense: '正在扫描...',
        functionalService: '正在扫描...',
        certificateAuthorities: '正在扫描...',
        enciphermentMechanisms: '正在扫描...',
        opUser: '加载中...',
        scanTime: '加载中...',
      },
    };
  },
  computed: {
    ...mapGetters(['get_latest_security_status', 'get_security_scan_status']),
  },
  methods: {
    ...mapMutations({ saveScanStatus: 'PUT_SECURITY_SCAN_STATUS' }),
    ...mapActions(['getLatestSecurityStatus']),
    stopLoadding() {
      const _this = this;
      setTimeout(() => {
        _this.loadding = false;
        _this.status = _this.get_latest_security_status;
      }, 10000);
    },
  },
  mounted() {
    const _this = this;

    this.getLatestSecurityStatus({
      params: '',
      callback() {
        // _this.status.opUser = res.data.opUser;
        // _this.status.scanTime = res.data.scanTime;
        if (_this.get_security_scan_status) {
          _this.saveScanStatus(0);
          // 播放动画
          _this.loadding = true;
          _this.stopLoadding();
        } else {
          _this.status = _this.get_latest_security_status;
        }
      },
    });
  },
};
</script>

<style lang="less" scoped>
.loadding-container {
  display: inline-block;
  width: 15px;
  height: 15px;
  background: url("../../assets/loadding.svg") no-repeat center;
  background-size: 100%;
  vertical-align: middle;
  margin-left: 5px;
}
.safe-content {
  position: relative;
  padding: 20px;
  .left-list {
    font-size: 12px;
    margin-top: 5px;
  }
  .right-safe-logo {
    position: absolute;
    right: 40px;
    top: 40px;
    width: 70px;
    height: 70px;
    background: url("../../assets/safe.png") no-repeat center;
    background-size: 100% 100%;
  }
}
</style>
