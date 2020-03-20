<template>
  <div class="doughnut">
    <canvas id="doughnut_1" @click="tapEvent('在线')"></canvas>
    <canvas id="doughnut_2" @click="tapEvent('离线')"></canvas>
    <canvas id="doughnut_3" @click="tapEvent('空闲')"></canvas>
    <canvas id="doughnut_4" @click="tapEvent('忙碌')"></canvas>
    <canvas id="doughnut_5" @click="tapEvent('故障')"></canvas>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { getTypeArray } from '../../utils/config';

function getOption(piedata) {
  return {
    type: 'gauge', // pie 环形图|| gauge 计量图
    title: {
      show: true,
      text: piedata.name,
      textStyle: {
        fontSize: '18px',
        lineHeight: '30px',
        color: '#fff',
        fontWeight: 'normal',
      },
      x: '50%',
      y: '50%',
    },
    tooltip: {
      show: false,
    },
    x: '50%',
    y: '50%',
    radius: '28%',
    lineWidth: '8%',
    max: piedata.total, // 映射总值
    data: [{
      name: piedata.name,
      value: piedata.value, // 映射计量值
    }],
    label: {
      show: true,
      fontStyle: 'normal',
      fontVariant: 'normal',
      fontWeight: 'normal',
      fontSize: '20px',
      lineHeight: '20px',
      fontFamily: 'Microsoft YaHei',
      color: '#fff',
      formatter(param) {
        const str = `{b|${param.value}}`;
        return str;
      },
    },
    capType: 'round',
    color: [piedata.color],
    labelCoverTitle: true,
    backgroundArc: '#1c1c1c',
  };
}

export default {
  name: 'doughnut',
  props: ['size', 'orgId', 'chartData', 'chartConfig', 'id'],
  data() {
    return {
      chart: '',
    };
  },
  computed: {
    ...mapGetters(['get_dev_summary']),
  },
  watch: {
    // chartData: {
    //   handler(val) {
    //     const oData = this.getStatusData(val, this.chartConfig.dimension, this.chartConfig.perfIndex);
    //     this.chart.setOption(getOption(oData));
    //   },
    //   deep: true,
    // },
    size() {
      this.chart.resize();
    },
  },
  methods: {
    ...mapActions(['getDevSummary']),
    getStatusData(list, key, value) {
      const legend = getTypeArray(list, key);
      const data = [];
      legend.forEach((item) => {
        let itemVal = 0;
        list.forEach((element) => {
          if (element[key] === item) {
            itemVal += element[value];
          }
        });
        data[item] = itemVal;
      });
      return data;
    },
    init() {
      const array = [{
        name: '在线',
        value: 153,
        total: 985,
        color: '#08b2a0',
      }, {
        name: '离线',
        value: 453,
        total: 985,
        color: '#c1bc88',
      }, {
        name: '空闲',
        value: 631,
        total: 985,
        color: '#035a90',
      }, {
        name: '忙碌',
        value: 53,
        total: 985,
        color: '#bdae0b',
      }, {
        name: '故障',
        value: 113,
        total: 985,
        color: '#af604f',
      }];
      // 填入真实数据
      let oData = [];
      if (this.chartConfig.dimension) {
        oData = this.getStatusData(this.chartData, this.chartConfig.dimension, this.chartConfig.perfIndex);
        const statuses = ['online', 'offline', 'idle', 'play', 'fault'];
        const total = oData.online + oData.offline;
        for (let i = 0; i < 5; i++) {
          array[i].value = oData[statuses[i]];
          array[i].total = total;
        }
      }
      const container = document.querySelector('.doughnut');
      const canvasW = container.clientWidth / 5;
      const canvasH = container.clientHeight;
      for (let i = 1; i <= 5; i++) {
        const device = document.getElementById(`doughnut_${i}`);
        device.width = canvasW;
        device.height = canvasH;
        const deviceCharts = new DonutChart(`doughnut_${i}`, getOption(array[i - 1]));
        deviceCharts.init((result) => {
          console.log(result);
        });
      }
    },
    tapEvent(name) {
      this.$emit('doclick', name);
    },
  },
  mounted() {
    // const device = document.getElementById(this.id);
    // this.chart = echarts.init(device);
    setTimeout(() => {
      this.init();
    }, 1000);
  },
};
</script>

<style lang="less" scoped>
.doughnut {
  overflow: hidden;
  height: 100%;
  > canvas {
    width: 20%;
    height: 100%;
    float: left;
  }
}
</style>
