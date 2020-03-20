<template>
  <div>
    <div class="safe-content">
      <ul class="left-list">
        <li>病毒检测：
          <span>{{ status.virusDetection }}</span>
        </li>
        <li>网络防护：
          <span>{{ status.networkDefense }}</span>
        </li>
        <li>功能服务：
          <span>{{ status.functionalService }}</span>
        </li>
        <li>证书授权：
          <span>{{ status.certificateAuthorities }}</span>
        </li>
        <li>加解密机制：
          <span>{{ status.enciphermentMechanisms }}</span>
        </li>
        <li>操作员：
          <span>{{ status.opUser }}</span>
        </li>
        <li>扫描时间：
          <span>{{ status.scanTime }}</span>
        </li>
      </ul>
      <div class="right-safe-logo"></div>
      <el-button type="primary"
        class="scan-btn"
        @click="openFullScreen">
        开始扫描
      </el-button>
      <el-button type="primary"
        class="scan-btn"
        @click="goHome">
        返回首页
      </el-button>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from 'vuex';

export default {
  name: 'Safe',
  data() {
    return {
      status: {},
    };
  },
  computed: {
    ...mapGetters(['get_latest_security_status']),
  },
  methods: {
    ...mapMutations({ saveScanStatus: 'PUT_SECURITY_SCAN_STATUS' }),
    ...mapActions(['getLatestSecurityStatus']),
    goHome() {
      this.$router.push({ path: '/' });
    },
    openFullScreen() {
      const status = {
        virusDetection: '正在扫描...',
        networkDefense: '正在扫描...',
        functionalService: '正在扫描...',
        certificateAuthorities: '正在扫描...',
        enciphermentMechanisms: '正在扫描...',
        opUser: '加载中...',
        scanTime: '加载中...',
      };
      const loading = this.$loading({
        lock: true,
        text: '正在扫描...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.8)',
      });
      this.status = status;
      this.getLatestSecurityStatus({
        params: '',
        callback: () => {
          setTimeout(() => {
            this.status = this.get_latest_security_status;
            loading.close();
            this.saveScanStatus(1);
            this.goHome();
          }, 10000);
          // this.status.opUser = res.data.opUser;
          // this.status.scanTime = res.data.scanTime;
        },
      });
    },
  },
  mounted() {
    this.getLatestSecurityStatus({
      params: '',
      callback: (res) => {
        this.status = res.data;
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
  background: url("../assets/loadding.svg") no-repeat center;
  background-size: 100%;
  vertical-align: middle;
  margin-left: 5px;
}
.safe-content {
  position: relative;
  padding-top: 20vh;
  color: #fff;
  width: 30vw;
  margin: 0 auto;
  .left-list {
    font-size: 14px;
    margin-top: 5px;
    li {
      line-height: 30px;
    }
  }
  .right-safe-logo {
    position: absolute;
    right: 50px;
    top: 30vh;
    width: 70px;
    height: 70px;
    background: url("../assets/safe.png") no-repeat center;
    background-size: 100% 100%;
  }
}
.scan-btn {
  display: inline;
  margin: 20px auto 0 auto;
}
</style>
