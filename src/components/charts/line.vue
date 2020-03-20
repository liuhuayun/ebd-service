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
import { getDataByKey } from '../../utils/config';

function getOption(legend, LineArr) {
  return {
    grid: {
      top: 35,
      left: 0,
      right: 10,
      bottom: 0,
      containLabel: true,
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        label: {
          backgroundColor: '#6a7985',
        },
      },
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: legend,
      axisLabel: {
        fontSize: 10,
        textStyle: {
          color: '#fff',
        },
      },
    },
    yAxis: {
      type: 'value',
      name: '',
      nameGap: 5,
      nameLocation: 'end',
      nameTextStyle: {
        color: '#fff',
        fontSize: 10,
      },
      axisLabel: {
        fontSize: 10,
        textStyle: {
          color: '#fff',
        },
      },
    },
    series: [{
      data: LineArr,
      type: 'line',
      areaStyle: {
        color: 'rgba(1,158,255,0.3)',
      },
      itemStyle: {
        color: '#019eff',
      },
      lineStyle: {
        width: 1,
        color: '#019eff',
      },
      smooth: true,
    }],
  };
}

export default {
  name: 'line-chart',
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
        const lineData = getDataByKey(val, this.chartConfig.dimension, this.chartConfig.perfIndex);
        this.chart.setOption(getOption(lineData.legend, lineData.data));
      },
      deep: true,
    },
  },
  methods: {
    init() {
      const lineData = getDataByKey(this.chartData, this.chartConfig.dimension, this.chartConfig.perfIndex);
      this.chart.setOption(getOption(lineData.legend, lineData.data));
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
