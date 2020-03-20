<template>
  <div class="showNum">
    <!-- <h2>{{number}}</h2> -->
    <p>{{number}}</p>
  </div>
</template>

<script>

export default {
  name: 'Number',
  props: ['chartConfig', 'chartData'],
  data() {
    return {
      number: 88888888,
    };
  },
  mounted() {
    if (this.chartConfig.perfIndex) {
      this.number = this.chartData[0][this.chartConfig.perfIndex];
    }
  },
};
</script>

<style lang="less" scoped>
h2 {
  font-size: 12px;
  color: #f7a537;
  text-align: left;
  padding: 5px 0 0 20px;
}
.showNum {
  text-align: center;
  color: #ffffff;
  font-size: 2.6rem;
  p {
    height: 50px;
    line-height: 50px;
    position: absolute;
    top: 50%;
    margin-top: -25px;
    width: 100%;
  }
}
</style>
