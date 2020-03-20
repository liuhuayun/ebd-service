<template>
  <div class="charts">
    <div id="map"
      class="map"></div>
    <div class="map-logo"></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import echarts from 'echarts/lib/echarts';
import bmap from 'echarts/extension/bmap/bmap';
import 'echarts/lib/chart/scatter';
import 'echarts/lib/chart/effectScatter';
import 'echarts/lib/component/title';
import 'echarts/lib/component/tooltip';

const locationData0 = [];
const geoCoordMap0 = {};

function convertData(data, geoCoordMap) {
  const res = [];
  for (let i = 0; i < data.length; i++) {
    const geoCoord = geoCoordMap[data[i].name];
    if (geoCoord) {
      res.push({
        name: data[i].name,
        value: geoCoord.concat(data[i].value),
      });
    }
  }
  return res;
}
const blackStyle = [
  {
    featureType: 'water',
    elementType: 'all',
    stylers: {
      color: '#021019',
    },
  },
  {
    featureType: 'highway',
    elementType: 'geometry.fill',
    stylers: {
      color: '#000000',
    },
  },
  {
    featureType: 'highway',
    elementType: 'geometry.stroke',
    stylers: {
      color: '#147a92',
    },
  },
  {
    featureType: 'arterial',
    elementType: 'geometry.fill',
    stylers: {
      color: '#000000',
    },
  },
  {
    featureType: 'arterial',
    elementType: 'geometry.stroke',
    stylers: {
      color: '#0b3d51',
    },
  },
  {
    featureType: 'local',
    elementType: 'geometry',
    stylers: {
      color: '#000000',
    },
  },
  {
    featureType: 'land',
    elementType: 'all',
    stylers: {
      color: '#08304b',
    },
  },
  {
    featureType: 'railway',
    elementType: 'geometry.fill',
    stylers: {
      color: '#000000',
    },
  },
  {
    featureType: 'railway',
    elementType: 'geometry.stroke',
    stylers: {
      color: '#08304b',
    },
  },
  {
    featureType: 'subway',
    elementType: 'geometry',
    stylers: {
      lightness: -70,
    },
  },
  {
    featureType: 'building',
    elementType: 'geometry.fill',
    stylers: {
      color: '#000000',
    },
  },
  {
    featureType: 'all',
    elementType: 'labels.text.fill',
    stylers: {
      color: '#857f7f',
    },
  },
  {
    featureType: 'all',
    elementType: 'labels.text.stroke',
    stylers: {
      color: '#000000',
    },
  },
  {
    featureType: 'building',
    elementType: 'geometry',
    stylers: {
      color: '#022338',
    },
  },
  {
    featureType: 'green',
    elementType: 'geometry',
    stylers: {
      color: '#062032',
    },
  },
  {
    featureType: 'boundary',
    elementType: 'all',
    stylers: {
      color: '#1e1c1c',
    },
  },
  {
    featureType: 'manmade',
    elementType: 'geometry',
    stylers: {
      color: '#022338',
    },
  },
  {
    featureType: 'poi',
    elementType: 'all',
    stylers: {
      visibility: 'off',
    },
  },
  {
    featureType: 'all',
    elementType: 'labels.icon',
    stylers: {
      visibility: 'off',
    },
  },
  {
    featureType: 'all',
    elementType: 'geometry',
    stylers: {
      color: '#000000ff',
      hue: '#444444',
      visibility: 'on',
    },
  },
];

function getOption(mapConfig) {
  return {
    title: {
      text: '', // mapConfig.title, // '设备分布',
      subtext: '',
      sublink: 'http://www.pm25.in',
      left: 'center',
      textStyle: {
        color: '#fff',
      },
    },
    tooltip: {
      trigger: 'item',
    },
    bmap: {
      center: mapConfig.center, // [104.114129, 37.550339],
      zoom: mapConfig.zoom, // 5,
      roam: true,
      mapStyle: {
        // style: 'dark',
        styleJson: blackStyle,
      },
    },
    series: [{
      name: '',
      type: 'scatter',
      coordinateSystem: 'bmap',
      data: convertData(mapConfig.locationData, mapConfig.geoCoordMap),
      symbolSize(val) {
        return val[2] / 20;
      },
      label: {
        normal: {
          formatter: '{b}',
          position: 'right',
          show: false,
        },
        emphasis: {
          show: true,
        },
      },
      itemStyle: {
        normal: {
          color: 'green',
        },
      },
    },
    {
      name: mapConfig.activeItemTitle, // '正在播放',
      type: 'effectScatter',
      coordinateSystem: 'bmap',
      data: convertData(mapConfig.locationData.filter(a => a.value >= 200), mapConfig.geoCoordMap),
      symbolSize(val) {
        return val[2] / 40;
      },
      showEffectOn: 'render',
      rippleEffect: {
        brushType: 'stroke',
      },
      hoverAnimation: true,
      label: {
        normal: {
          formatter: '{b}',
          position: 'right',
          show: true,
        },
      },
      itemStyle: {
        normal: {
          color: 'yellow',
          shadowBlur: 40,
          shadowColor: '#333',
        },
      },
      zlevel: 1,
    },
    ],
  };
}

export default {
  name: 'map-chart',
  props: ['size', 'orgId', 'chartConfig', 'chartData', 'id'],
  data() {
    return {
      chart: '',
      bmap: '',
      ply: '',
      mapConfig: {
        title: '设备分布',
        activeItemTitle: '正在播放...',
        locationData: [],
        geoCoordMap: [],
        center: [104.114129, 37.550339],
        zoom: 5,
      },
      locName: '长沙市开福区',
    };
  },
  watch: {
    size() {
      this.chart.resize();
    },
    get_location_name(val) {
      this.locName = val;
      this.getBoundary(val);
    },
    chartData: {
      deep: true,
      handler(val) {
        const _this = this;
        this.createMapConfig();
        this.chart.setOption(getOption(_this.mapConfig));
        this.getBoundary(this.locName);
      },
    },
  },
  computed: {
    ...mapGetters(['get_location_name']),
  },
  methods: {
    init() {
      const _this = this;
      const map = document.getElementById('map');
      this.chart = echarts.init(map);
      this.createMapConfig();
      this.chart.setOption(getOption(_this.mapConfig));

      this.bmap = this.chart.getModel().getComponent('bmap').getBMap();
      this.getBoundary(_this.locName);
    },
    getBoundary(name) {
      const _this = this;
      const bdary = new BMap.Boundary();
      bdary.get(name, (rs) => { // 获取行政区域
        _this.bmap.removeOverlay(_this.ply); // 清除地图覆盖物
        const count = rs.boundaries.length; // 行政区域的点有多少个
        // 默认要画的行政区域点
        let drawIndex = 0;
        if (count > 1) {
          // 点最大值的索引
          const countLengthArray = rs.boundaries.map(item => item.length);
          const maxCount = Math.max(...countLengthArray);
          drawIndex = countLengthArray.indexOf(maxCount);
        }
        _this.ply = new BMap.Polygon(rs.boundaries[drawIndex], {
          strokeColor: '#019eff', strokeWeight: 2, strokeOpacity: 0.0, fillOpacity: 0.1, fillColor: '#019eff',
        }); // 建立多边形覆盖物
        _this.bmap.addOverlay(_this.ply); // 添加覆盖物
        _this.bmap.setViewport(_this.ply.getPath()); // 调整视野
      });
    },
    createMapConfig() {
      if (this.chartConfig.moduleName === 'dev') {
        const geo = this.createDevGeoData();
        this.mapConfig.locationData = geo.locationData;
        this.mapConfig.geoCoordMap = geo.geoCoordMap;
        this.mapConfig.center = geo.center;
        this.mapConfig.zoom = geo.zoom;
      }
    },
    isEmpty(str) {
      return str === null || str === '';
    },
    createDevGeoData() {
      const locationData = [];
      const geoCoordMap = {};

      let locNum = 0;
      let longTotal = 0.0;
      let latiTotal = 0.0;
      let minLong = 180.0;
      let minLati = 90.0;
      let maxLong = 0.0;
      let maxLati = 0.0;
      this.chartData.forEach((element) => {
        if (this.isEmpty(element.longitude) || this.isEmpty(element.latitude)) {
          // 将缺少经纬度的设备定位到公司总部
          element.latitude = '28.200717';
          element.longitude = '113.065051';
        }
        const long = parseFloat(element.longitude);
        const lati = parseFloat(element.latitude);
        const coord = [];
        coord.push(long);
        coord.push(lati);
        geoCoordMap[element.devId] = coord;
        // 空闲：100； 播放：200；升级：300
        locationData.push({
          name: element.devId,
          value: element.bizStatus * 100,
        });
        locNum += 1;
        longTotal += long;
        latiTotal += lati;
        if (minLong > long) {
          minLong = long;
        }
        if (minLati > lati) {
          minLati = lati;
        }
        if (maxLong < long) {
          maxLong = long;
        }
        if (maxLati < lati) {
          maxLati = lati;
        }
      });
      const center = [];
      center.push(longTotal / locNum);
      center.push(latiTotal / locNum);

      // const zoom = this.getZoomLevel(minLong, minLati, maxLong, maxLati);
      const zoom = 15;

      return {
        locationData,
        geoCoordMap,
        center,
        zoom,
      };
    },
    getZoomLevel(minLong, minLati, maxLong, maxLati) {
      // 缩放级别
      const zoomLevel = [20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3];
      // 跟缩放级别对应的距离，单位（米）
      const zoomDis = [10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000];
      const map = new bmap.Map('container');
      const point1 = new bmap.Point(minLong, minLati);
      const point2 = new bmap.Point(maxLong, maxLati);
      const distance = map.getDistance(point1, point2);

      let zoom = zoomLevel[0];
      for (let i = 0; i < zoomLevel.length; i++) {
        if (distance < zoomDis[i]) {
          zoom = zoomLevel[i];
          break;
        }
      }
      return zoom;
    },
  },
  mounted() {
    this.init();
  },
};
</script>

<style lang="less" scoped>
.map {
  height: 100%;
}
.map-logo {
  position: absolute;
  width: 90px;
  height: 65px;
  bottom: 25px;
  left: 45%;
  background: url("../../assets/logo_blue.png") no-repeat center;
  background-size: 100%;
}
</style>
