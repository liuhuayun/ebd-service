<template>
  <div class="box">
    <ul class="th">
      <li>
        <div class="ebr-type">
          <span class="ebr-type-name">资源类型</span>
          <span class="ebr-type-data"><span class="online">在线</span>离线/总数</span>
        </div>
      </li>
    </ul>
    <ul>
      <li :key="item.ebrTypeName" v-for="item in ebrCountInfo">
        <div class="ebr-type">
          <span class="ebr-type-name">{{item.ebrTypeName}}</span>
          <span class="ebr-type-data"><span class="online">{{item.runCount}}</span>/{{item.totalCount-item.runCount}}/{{item.totalCount}}</span>
        </div>
        <div class="ebr-dto" v-if="item.resType=='0314'">
          <span class="ebr-dto-name">大喇叭终端</span>
          <span class="ebr-dto-data">
            <span class="online">{{item.ebrtypeDto.runCount}}</span>/{{item.ebrtypeDto.totalCount-item.ebrtypeDto.runCount}}/{{item.ebrtypeDto.totalCount}}
          </span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {
  mapGetters,
  mapActions,
} from 'vuex';

export default {
  name: 'EbrCount',
  data() {
    return {
      loadding: false,
      ebrCountInfo: [],
    };
  },
  computed: {
    ...mapGetters(['get_ebrStatistic_info', 'get_ebrStatistic_info']),
  },
  methods: {
    // ...mapMutations({ saveScanStatus: 'GET_DYNAMIC_EBM_INFO' }),
    ...mapActions(['getEbrStatisticInfo']),
    /* stopLoadding() {
        let _this = this;
        setTimeout(() => {
          _this.loadding = false;
          _this.ebrCountInfo = _this.get_ebrStatistic_info;
        }, 10000);
      }, */
  },
  mounted() {
    const _this = this;
    this.getEbrStatisticInfo({
      params: '',
      callback(data) {
        _this.ebrCountInfo = data.data;
        /* if (_this.get_dynamic_ebm_Info) {
             _this.saveScanStatus(0);
             // 播放动画
             _this.loadding = true;
             _this.stopLoadding();
           } else {
            // _this.status = _this.get_dynamic_ebm_Info;
           } */
      },
    });
  },
};
</script>

<style lang="less" scoped>
  .box {
    font-size: 14px;
    height: 100%;
    width: 100%;
    overflow-y: hidden;
    box-sizing: border-box;
    padding: 10px 10px 6px 10px;
  }

  ul {
    list-style: none;
    padding: 0px;
    margin: 0px;
    width: 100%;
    position: relative;
    min-width: 200px;
  }

  .th {
    height: 30px;
  }

  ul li {
    display: block;
    width: 100%;
    position: relative;
  }

  .ebr-type {
    position: relative;
    width: 100%;
    height: 30px;
    line-height: 30px;
  }

  .ebr-type:before {
    content: "";
    background-color: #fff;
    width: 10px;
    height: 10px;
    border-radius: 50%;
    position: absolute;
    left: 0;
    top: 15px;
    margin-top: -5px;
  }

  .ebr-type-name {
    display: inline-block;
    float: left;
    margin-left: 20px;
  }

  .ebr-type-data {
    display: inline-block;
    float: right;
    text-align: right;
  }

  .online {
    color: #66ffb5;
  }

  .ebr-dto {
    position: relative;
    margin-left: 5%;
    width: 100%;
    line-height: 30px;
  }

  .ebr-dto:before {
    content: "";
    background-color: #fff;
    width: 10px;
    height: 10px;
    border-radius: 50%;
    position: absolute;
    left: 0;
    top: 15px;
    margin-top: -5px;
  }

  .ebr-dto-name {
    display: inline-block;
    margin-left: 20px;
    width: 40%;
  }

  .ebr-dto-data {
    display: inline-block;
    width: 40%;
    float: right;
    text-align: right;
    margin-right: 5%;
  }
</style>
