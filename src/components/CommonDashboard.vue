<template>
  <div class="container" @mousemove="stopScroll()">
    <WebSocketClient />
    <grid-layout :layout="layout"
      :col-num="48"
      :row-height="40"
      :is-draggable="false"
      :is-resizable="false"
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
        @resized="resizedEvent(item.ref)"
        class="gridItem">
        <p v-if="index==searchPosition"
          class="all-query"
          @click="QueryVisible=true">综合查询</p>
        <user-card :name="item.name"
          :border="item.border"
          :url="item.url"
          :home="moduleName != 'home'"
          :expand="item.url != ''">
          <component :is="item.component"
            :id="item.id"
            @doclick="devFilterStatus"
            :size="{w:item.w,h:item.h}"
            :chartConfig="item.chartConfig"
            :chartData="item.chartData"></component>
        </user-card>
      </grid-item>
    </grid-layout>
    <!-- <modal :visible.sync="
            QueryVisible"
            title="综合查询"
            @confirm="refreshDashboard">
            <el-form :model="form"
              slot="form"
              label-width="60px"
              size='small'
              class="dashboard-form">
              <el-form-item label="时间">
                <el-date-picker v-model="form.time"
                  value-format="yyyy-MM-dd"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </el-form-item>
            </el-form>
            </modal> -->
  </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex';
import { GridLayout, GridItem } from 'vue-grid-layout';
import ELine from './charts/line.vue';
import MLine from './charts/mline.vue';
import Pie from './charts/pie.vue';
import Bar from './charts/bar.vue';
import Map from './charts/map.vue';
import Doughnut from './charts/doughnut.vue';
import Rolling from './dashboard/Rolling.vue';
import SafeInfo from './dashboard/SafeInfo.vue';
import Number from './dashboard/Number.vue';
import Table from './dashboard/Table.vue';
import Modal from './Modal.vue';
import UserCard from './UserCard.vue';
import WebSocketClient from './WebSocketClient.vue';
import VideoChart from './ebcomponents/VideoChart.vue';
import PlayingChart from './ebcomponents/PlayingChart.vue';
import EbrCount from './ebcomponents/EbrCount.vue';
import EbRolling from './ebcomponents/warningAccess.vue';
import EbMap from './ebcomponents/ebmap.vue';
import {
  getChartSettings, getRangeDate, getRangeWeek, getRangeMonth, getDayFromDatetime,
} from '../utils/config';

export default {
  name: 'CommonDashboard',
  props: ['moduleName', 'dashboardId'],
  data() {
    return {
      QueryVisible: false,
      orgId: '',
      // configId: "",
      // tmplId: 100,
      form: {
        time: '',
        location: '',
        type: '',
      },
      startTime: '',
      endTime: '',
      dashInfo: {},
      chartConfigList: [],
      searchPosition: 100,
      // blockIndex: 0,
      layout: [], /* [{
        x: 0, y: 0, w: 9, h: 4, i: 0, component: 'Doughnut', name: '设备状态', url: 'dev',
      }, {
        x: 9, y: 0, w: 3, h: 4, i: 1, component: 'ELine', name: '活跃度统计', url: '',
      }, {
        x: 0, y: 4, w: 9, h: 12, i: 2, component: 'Map', name: '设备状态', url: '',
      }, {
        x: 9, y: 4, w: 3, h: 4, i: 3, component: 'Bar', name: '业务统计', url: '',
      }, {
        x: 9, y: 8, w: 3, h: 4, i: 4, component: 'Rolling', name: '动态展示', url: '',
      }, {
        x: 9, y: 12, w: 3, h: 4, i: 5, component: 'SafeInfo', name: '安全状态', url: '',
      }], */
      startIdx: 0, // 监听鼠标事件的定时器
    };
  },
  components: {
    GridLayout,
    GridItem,
    ELine,
    MLine,
    Pie,
    Bar,
    Map,
    Rolling,
    SafeInfo,
    Number,
    Table,
    Doughnut,
    Modal,
    UserCard,
    WebSocketClient,
    EbrCount,
    PlayingChart,
    VideoChart,
    EbRolling,
    EbMap,
  },
  watch: {
    // 处理websocket更新
    get_dev_list: {
      deep: true,
      handler(val) {
        this.updateDevList(val.list);
      },
    },
    startIdx(val) {
      if (this.get_mouse_state === 0 && val >= 10) {
        this.setMouseState(1);
      }
    },
  },
  computed: {
    ...mapGetters(['get_dashboard_data', 'get_dev_list', 'get_mouse_state']),
  },
  methods: {
    ...mapMutations({ setMouseState: 'GET_MOUSE_STATE' }),
    ...mapActions(['getDashboardData', 'getDevList']),
    updateDevList(list) {
      for (let i = 0; i < this.layout.length; i++) {
        const item = this.layout[i];
        if (item.component === 'Map' && this.moduleName === 'home') {
          item.chartData = list;
        }
      }
    },
    getDevStatus(rawData) {
      const status = {
        online: 0,
        offline: 0,
        idle: 0,
        play: 0,
        fault: 0,
      };
      rawData.forEach((element) => {
        status[element.onlineStatus] += element.count;
        if (element.onlineStatus === 'online') {
          if (element.bizStatus === 1) {
            status.idle += element.count;
          } else if (element.bizStatus === 2) {
            status.play += element.count;
          }
        }
      });
      return [{
        status: 'online',
        count: status.online,
      }, {
        status: 'offline',
        count: status.offline,
      }, {
        status: 'idle',
        count: status.idle,
      }, {
        status: 'play',
        count: status.play,
      }, {
        status: 'fault',
        count: status.fault,
      }];
    },
    // 按设备状态对设备进行过滤，并在地图上显示
    devFilterStatus(status) {
      let onlineStatus = 'online';
      let bizStatus = ''; // 1 - 空闲； 2 - 忙碌； 3 - 升级; 不关心则用空字符串
      switch (status) {
        case '在线':
          onlineStatus = 'online';
          break;
        case '离线':
          onlineStatus = 'offline';
          break;
        case '空闲':
          onlineStatus = 'online';
          bizStatus = '1';
          break;
        case '忙碌':
          onlineStatus = 'online';
          bizStatus = '2';
          break;
        case '故障':
          onlineStatus = 'online';
          bizStatus = '4';
          break;
        default:
          break;
      }
      // 找到设备分布地图对应的chartConfig
      let chartConfig = {};
      this.chartConfigList.forEach((element) => {
        if (element.moduleName === 'dev' && element.chartType === 'Map') {
          chartConfig = element;
        }
      });
      // 根据设备状态重新获取设备列表
      this.getDevList({
        params: {
          onlineStatus,
          bizStatus,
        },
        callback: (res) => {
          const chartData = res.data.list;
          // const mapLayout = {};
          // const mapIndex = 0;
          this.layout.forEach((element) => {
            const idPrefix = chartConfig.chartType + this.dashInfo.id + chartConfig.id;
            if (element.component === 'Map' && element.id.indexOf(idPrefix) !== -1) {
              // mapLayout = element;
              // mapIndex = index;
              element.chartConfig = chartConfig;
              element.chartData = chartData;
            }
          });
        },
      });
    },
    getContinuousDataForLine(chartConfig, l) {
      const dim = chartConfig.dimension;
      const len = l.length;
      if (dim !== 'bindDate' && dim !== 'day' && dim !== 'time' && dim !== 'bizDate'
        && dim !== 'week' && dim !== 'month') {
        return l;
      }
      if (len === 0) {
        return l;
      }
      const { perfIndex } = chartConfig;
      const minX = l[0][dim];
      const maxX = l[len - 1][dim];
      let xArray = [];
      if (dim === 'bindDate' || dim === 'day' || dim === 'time' || dim === 'bizDate') {
        xArray = getRangeDate(minX, maxX);
        l.forEach((el) => {
          el[dim] = getDayFromDatetime(el[dim]);
        });
      } else if (dim === 'week') {
        xArray = getRangeWeek(minX, maxX);
      } else if (dim === 'month') {
        xArray = getRangeMonth(minX, maxX);
      }
      const xValue = {};
      l.forEach((el) => {
        xValue[el[dim]] = el[perfIndex];
      });
      const newList = [];
      xArray.forEach((el) => {
        const item = {};
        item[dim] = el;
        if (!xValue[el]) {
          item[perfIndex] = 0;
        } else {
          item[perfIndex] = xValue[el];
        }
        newList.push(item);
      });
      return newList;
    },
    setDashboardData(allData) {
      // 这里要注意，当YH切换模板之后，模板的块数跟图表个数有可能不一致。
      // 如果模板块数 < 图表个数，则只显示前几个图表
      // 如果模板块数 > 图表个数，则后面几个小块没有图表
      this.dashInfo = allData.dashboard;
      // list: 按blockIndex从小到大排列，但不一定连贯
      const dashConfigList = allData.dashboardConfig;
      // this.dashConfigList = dashConfigList;
      // list: dashConfigList 和 charts的长度是一样的，但是跟tmplDetails的长度可能不一致
      // const charts = allData.charts;
      const { charts } = allData;
      this.chartConfigList = charts;

      const { tmpl } = allData;
      this.searchPosition = tmpl.searchPosition;
      // list
      // const tmplDetails = allData.tmplDetails;
      const { tmplDetails } = allData;

      let idx = 0;
      const template = [];
      for (let i = 0; i < tmplDetails.length; i++) {
        const block = {};
        block.x = tmplDetails[i].x;
        block.y = tmplDetails[i].y;
        block.w = tmplDetails[i].w;
        block.h = tmplDetails[i].h;
        block.i = tmplDetails[i].i;
        block.component = '';
        block.name = '';
        block.id = '';
        block.url = '';

        if (idx < charts.length && dashConfigList[idx].blockIndex === i) {
          const { chartConfig } = charts[idx]; // .chartConfig;
          let list = charts[idx].chartData;
          idx++;
          block.component = getChartSettings(chartConfig);
          block.id = chartConfig.chartType + this.dashInfo.id + chartConfig.id + i;
          block.chartConfig = chartConfig;
          if (chartConfig.moduleName === 'dev' && chartConfig.dimension === 'status') {
            const l = list;
            list = this.getDevStatus(l);
          }
          if (chartConfig.chartType === 'Line') {
            const l = list;
            list = this.getContinuousDataForLine(chartConfig, l);
          }
          block.chartData = list;
          block.name = chartConfig.configName;
          if (this.moduleName === 'home') {
            if (chartConfig.chartType === 'Doughnut') {
              block.url = 'dev';
              block.border = false;
            } else if (chartConfig.chartType === 'Line') {
              block.url = 'user';
              block.border = true;
            } else if (chartConfig.chartType === 'Rolling') {
              block.url = 'news';
              block.border = true;
            } else if (chartConfig.chartType === 'Bar') {
              block.url = 'business';
              block.border = true;
            } else if (chartConfig.chartType === 'Map') {
              block.border = false;
              block.name = '';
            } else if (chartConfig.chartType === 'SafeInfo') {
              block.url = 'safeinfo';
              block.border = true;
            }
          }
        }
        template.push(block);
      }
      this.layout = template;
    },
    init() {
      this.getDashboardData({
        params: {
          id: this.dashboardId,
          moduleName: this.moduleName,
          startTime: this.startTime,
          endTime: this.endTime,
        },
        callback: (res) => {
          if (res.code === 200) {
            this.setDashboardData(res.data);
            if (this.QueryVisible) {
              this.$message({
                message: '查询成功！',
                type: 'success',
              });
            }
            this.QueryVisible = false;
          }
        },
      });
      this.getDevList({
        params: {
          orgId: this.orgId,
        },
      });
    },
    refreshDashboard() {
      [this.startTime, this.endTime] = this.form.time;
      this.init();
    },
    resizedEvent(ref) {
      this.$refs[ref][0].resize();
    },
    stopScroll() {
      if (this.get_mouse_state === 1) {
        this.setMouseState(0);
      }
      this.startIdx = 0;
    },
  },
  mounted() {
    this.init();
    const _this = this;
    setInterval(() => {
      _this.startIdx++;
    }, 1000);
  },
};
</script>
<style lang="less" scoped>
.container {
  :global {
    .el-row {
      margin-bottom: 20px;
      &:last-child {
        margin-bottom: 0;
      }
    }
  }
  .option {
    position: absolute;
    width: 15px;
    height: auto;
    right: 0;
    top: 10px;
    font-size: 12px;
    color: #c5cad0;
    cursor: pointer;
    z-index: 10;
  }
}
.all-query {
  position: absolute;
  width: 16px;
  height: auto;
  right: 0;
  top: 55px;
  font-size: 12px;
  color: #c5cad0;
  text-align: center;
  line-height: 14px;
  cursor: pointer;
  z-index: 10;
}
.col-item {
  > div {
    background-color: #fff;
    border: 1px solid #efefef;
  }
}
.select {
  width: 80%;
  margin-left: 10%;
  padding: 20px 0;
}
.dashboard-form {
  .el-form-item {
    width: 410px;
  }
}
.top-menu {
  display: block;
  position: relative;
}
.template-btn {
  display: block;
  position: absolute;
  right: 120px;
  top: 1px;
}
</style>
