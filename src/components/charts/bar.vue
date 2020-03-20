<template>
  <div id="bar"
    class="charts"></div>
</template>

<script>
import { mapActions } from 'vuex';
import echarts from 'echarts/lib/echarts';
import 'echarts/lib/chart/bar';
import 'echarts/lib/component/title';
import 'echarts/lib/component/grid';

function getOption(barData) {
  const maxData = Math.max(...Object.values(barData));
  return {
    grid: {
      left: 5,
      right: 10,
      bottom: 0,
      top: 25,
      containLabel: true,
    },
    xAxis: {
      type: 'value',
      min: 0,
      max: maxData + maxData / 2,
      axisLine: {
        show: false,
      },
      splitLine: {
        show: false,
      },
      axisTick: {
        show: false,
      },
      axisLabel: {
        show: false,
      },
    },
    yAxis: {
      type: 'category',
      data: ['其他', '订阅', '点播', '推送', '直播'],
      axisLine: {
        show: false,
      },
      splitLine: {
        show: false,
      },
      axisTick: {
        show: false,
      },
      axisLabel: {
        show: true,
        margin: 10,
        inside: false,
        textStyle: {
          color: '#fff',
        },
      },
    },
    series: [{
      type: 'bar',
      barWidth: 14,
      itemStyle: {
        emphasis: {
          barBorderRadius: 7,
        },
        normal: {
          barBorderRadius: 7,
        },
      },
      label: {
        normal: {
          show: true,
          position: 'right',
          color: '#fff',
          formatter: '{c}次',
        },
      },
      data: [{
        value: 0,
        itemStyle: {
          color: '#af604f',
        },
      }, {
        value: 0,
        itemStyle: {
          color: '#035a90',
        },
      }, {
        value: 0,
        itemStyle: {
          color: '#bdae0b',
        },
      }, {
        value: barData.pushNum,
        itemStyle: {
          color: '#c1bc88',
        },
      }, {
        value: barData.liveNum,
        itemStyle: {
          color: '#08b2a0',
        },
      }],
    }],
  };
}

export default {
  name: 'bar',
  props: ['size', 'orgId', 'id'],
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
    ...mapActions(['getBusinessStats']),
    init() {
      const _this = this;
      const bar = document.getElementById('bar');
      this.chart = echarts.init(bar);
      this.getBusinessStats({
        params: {
          orgId: this.orgId,
        },
        callback(res) {
          _this.chart.setOption(getOption(res));
        },
      });
      // this.chart.setOption(getOption(_this.get_business_stats));
    },
  },
  mounted() {
    setTimeout(() => {
      this.init();
    }, 500);
  },
};
</script>

<style lang="less" scoped>
</style>
