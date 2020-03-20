<template>
  <div id="pie"
    class="charts"></div>
</template>

<script>
import echarts from 'echarts/lib/echarts';
import 'echarts/lib/chart/pie';
import 'echarts/lib/component/title';
import 'echarts/lib/component/grid';
import { getTypeArray } from '../../utils/config';

function getOption(chartConfig, legendArray, dataArray) {
  return {
    title: {
      text: '', // chartConfig.configName,
      subtext: '',
      x: 'center',
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)',
    },
    legend: {
      type: 'scroll',
      orient: 'vertical',
      right: 10,
      top: 20,
      bottom: 20,
      data: legendArray,
      // selected: chartInfo.selected
    },
    series: [
      {
        name: '', // chartConfig.dimName,
        type: 'pie',
        radius: '55%',
        center: ['40%', '50%'],
        data: dataArray,
        itemStyle: {
          emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)',
          },
        },
      },
    ],
  };
}

export default {
  name: 'pie-chart',
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
  },
  methods: {
    getPieDataByKey(list, key, value) {
      const legend = getTypeArray(list, key);
      const data = [];
      legend.forEach((item) => {
        let itemVal = 0;
        list.forEach((element) => {
          if (element[key] === item) {
            itemVal += element[value];
          }
        });
        data.push({
          name: item,
          value: itemVal,
        });
      });
      return { data, legend };
    },
    init() {
      const pie = document.getElementById('pie');
      this.chart = echarts.init(pie);
      const pieData = this.getPieDataByKey(this.chartData, this.chartConfig.dimension, this.chartConfig.perfIndex);
      this.chart.setOption(getOption(this.chartConfig, pieData.legend, pieData.data));
    },
  },
  mounted() {
    this.init();
  },
};
</script>

<style lang="less" scoped>
</style>
