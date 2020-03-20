<template>
  <div :id="id"
    class="charts"></div>
</template>

<script>
import echarts from 'echarts/lib/echarts';
import 'echarts/lib/chart/line';
import 'echarts/lib/component/title';
import 'echarts/lib/component/grid';
import 'echarts/lib/component/tooltip';
import { getTypeArray } from '../../utils/config';

function getOption(config, legend, data) {
  return {
    title: {
      text: '',
      textStyle: {
        color: '#f7a537',
        fontSize: 12,
      },
      left: 20,
    },
    tooltip: {
      trigger: 'axis',
    },
    toolbox: {
      feature: {
        saveAsImage: {},
      },
    },
    legend: {
      data: legend.legend2,
      type: 'scroll',
      orient: 'vertical',
      top: 20,
      itemWidth: 12,
      itemHeight: 8,
      textStyle: {
        fontSize: 10,
        color: '#fff',
      },
      pageTextStyle: {
        color: '#fff',
      },
      x: 'left',
    },
    grid: {
      left: '13%',
      right: '5%',
      bottom: '3%',
      containLabel: true,
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: legend.legend1,
      axisLine: {
        show: true,
        lineStyle: {
          color: '#ffffff',
          width: 1,
          type: 'solid',
        },
      },
    },
    yAxis: {
      type: 'value',
      axisLine: {
        show: true,
        lineStyle: {
          color: '#ffffff',
          width: 1,
          type: 'solid',
        },
      },
    },
    series: data,
  };
}

export default {
  name: 'mline-chart',
  props: ['chartConfig', 'chartData', 'size', 'id'],
  data() {
    return {
      chart: '',
    };
  },
  watch: {
    size() {
      this.chart.resize();
    },
    chartData: {
      handler(val) {
        const mockData = this.createMockData();
        const lineData = this.getMLineDataByKey(val, mockData);
        this.chart.setOption(getOption(this.chartConfig, lineData.legend, lineData.data));
      },
      deep: true,
    },
  },
  methods: {
    init() {
      const mockData = this.createMockData();
      const lineData = this.getMLineDataByKey(mockData, this.chartConfig);
      this.chart.setOption(getOption(this.chartConfig, lineData.legend, lineData.data));
    },
    createMockData() {
      const hours = 24;
      const devNum = 10000;
      const devData = [];
      const statuses = ['online', 'offline', 'idle', 'play', 'fault'];
      for (let i = 0; i < hours; i++) {
        let onlineNum = Math.ceil(Math.random() * devNum);
        onlineNum = onlineNum < devNum * 0.6 ? Math.ceil(devNum * 0.8) : devNum;
        const idleNum = Math.ceil(Math.random() * onlineNum * 0.75);
        const faultNum = Math.ceil(Math.random() * onlineNum * 0.2);
        const offlineNum = devNum - onlineNum;
        const playNum = onlineNum - idleNum - faultNum;
        const nums = [onlineNum, offlineNum, idleNum, playNum, faultNum];
        for (let j = 0; j < nums.length; j++) {
          devData.push({
            hour: i,
            status: statuses[j],
            total: nums[j],
          });
        }
      }
      return devData;
    },
    getMLineDataByKey(list, config) {
      const key = config.dimension;
      const value = config.perfIndex;
      const keyArray = key.split(',');
      // keyArray有两个元素, legend1作为x轴，legend2作为value
      const legend1 = getTypeArray(list, keyArray[0]);
      const legend2 = getTypeArray(list, keyArray[1]);
      // data 直接保存多折线图中的series
      const data = [];
      legend2.forEach((item2) => {
        const data1 = [];
        legend1.forEach((item1) => {
          let itemVal = 0;
          list.forEach((element) => {
            if (element[keyArray[0]] === item1 && element[keyArray[1]] === item2) {
              itemVal += element[value];
            }
          });
          data1.push(itemVal);
        });
        const serie = {
          name: item2,
          type: 'line',
          data: data1,
        };
        if (config.chartType === 'StackLine') {
          serie.stack = '总量';
          serie.areaStyle = {};
        }
        data.push(serie);
      });
      const legend = {
        legend1,
        legend2,
      };
      return { data, legend };
    },
  },
  mounted() {
    const line = document.getElementById(this.id);
    this.chart = echarts.init(line);
    this.init();
  },
};
</script>

<style lang="less" scoped>
</style>
