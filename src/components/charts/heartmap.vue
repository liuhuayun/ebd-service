<template>
  <div class="charts">
    <div id="map"
      class="map"></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import echarts from 'echarts/lib/echarts';
import bmap from 'echarts/extension/bmap/bmap';
import 'echarts/lib/component/visualMap';
import 'echarts/lib/component/title';
import 'echarts/lib/component/tooltip';

const jwdata = [
  [{ coord: [120.1545085354, 30.227019270792], elevation: 27 }, { coord: [120.15524806301, 30.227181779429], elevation: 30 }, { coord: [120.15633197285, 30.228036952337], elevation: 36 }, { coord: [120.15879155382, 30.228106704804], elevation: 30 }, { coord: [120.15894121612, 30.227231924073], elevation: 21 }, { coord: [120.1592190179, 30.227367562527], elevation: 17 }, { coord: [120.15966312929, 30.227132775659], elevation: 16 }, { coord: [120.1600813446, 30.226395224653], elevation: 17 }, { coord: [120.16013850914, 30.226775627078], elevation: 25 }, { coord: [120.15981461782, 30.227055360819], elevation: 11 }, { coord: [120.15972958614, 30.226558814407], elevation: 12 }, { coord: [120.16008665744, 30.226131960124], elevation: 18 }, { coord: [120.15980701003, 30.224970253134], elevation: 30 }, { coord: [120.1598988232, 30.224901000861], elevation: 13 }, { coord: [120.15896025671, 30.223799314886], elevation: 51 }, { coord: [120.15919548693, 30.223162545559], elevation: 86 }, { coord: [120.15898030865, 30.222778600031], elevation: 92 }, { coord: [120.15744979924, 30.222387264663], elevation: 101 }, { coord: [120.15645721989, 30.221748835756], elevation: 102 }, { coord: [120.15590913316, 30.221827494303], elevation: 102 }, { coord: [120.15582786937, 30.221687406896], elevation: 103 }, { coord: [120.15558039294, 30.221700190262], elevation: 120 }, { coord: [120.15455700413, 30.22088162289], elevation: 139 }, { coord: [120.15391649516, 30.220790754017], elevation: 149 }, { coord: [120.15356508544, 30.220362960631], elevation: 158 }, { coord: [120.15319771395, 30.220412634317], elevation: 163 }, { coord: [120.15262288441, 30.220160371456], elevation: 183 }, { coord: [120.15215664368, 30.220397623134], elevation: 200 }, { coord: [120.15219100566, 30.220501980828], elevation: 205 }, { coord: [120.15182508513, 30.220815491889], elevation: 208 }, { coord: [120.15179181327, 30.22071518031], elevation: 229 }, { coord: [120.15132938067, 30.220810936841], elevation: 239 }, { coord: [120.15136178056, 30.220693154629], elevation: 241 }, { coord: [120.15164057408, 30.220745876581], elevation: 246 }, { coord: [120.15215441056, 30.221276358255], elevation: 240 }, { coord: [120.15271915511, 30.220462165065], elevation: 231 }, { coord: [120.15257899408, 30.22009932659], elevation: 229 }, { coord: [120.15310068353, 30.220380247755], elevation: 228 }, { coord: [120.15339991146, 30.220298204494], elevation: 227 }, { coord: [120.15396684427, 30.220494387096], elevation: 218 }, { coord: [120.15406194423, 30.220415299447], elevation: 214 }, { coord: [120.15431278167, 30.220807511321], elevation: 194 }, { coord: [120.15464720104, 30.220814014298], elevation: 179 }, { coord: [120.15471212853, 30.221048819096], elevation: 172 }, { coord: [120.15522764887, 30.22149926966], elevation: 159 }, { coord: [120.15520642813, 30.221631513678], elevation: 155 }, { coord: [120.15498563522, 30.221545853599], elevation: 151 }, { coord: [120.15486548534, 30.221701381235], elevation: 144 }, { coord: [120.15522814837, 30.222240695245], elevation: 138 }, { coord: [120.15528721, 30.22280116292], elevation: 131 }, { coord: [120.15532778712, 30.222705720887], elevation: 124 }, { coord: [120.15556929973, 30.222788547339], elevation: 120 }, { coord: [120.15596951568, 30.223077287845], elevation: 110 }, { coord: [120.15613327857, 30.222957827387], elevation: 110 }, { coord: [120.15655811546, 30.223473989213], elevation: 102 }, { coord: [120.15697562064, 30.223339452799], elevation: 94 }, { coord: [120.15718703762, 30.223108952244], elevation: 92 }, { coord: [120.15762737829, 30.223538561087], elevation: 95 }, { coord: [120.158787524, 30.223950934187], elevation: 84 }, { coord: [120.15868263107, 30.224042690616], elevation: 78 }, { coord: [120.15884028131, 30.224930697486], elevation: 76 }, { coord: [120.15871467118, 30.225576568962], elevation: 68 }, { coord: [120.15887671948, 30.225887174113], elevation: 65 }, { coord: [120.15878450931, 30.226244845426], elevation: 62 }, { coord: [120.15894238915, 30.226892224315], elevation: 58 }, { coord: [120.1588685477, 30.227877367832], elevation: 49 }, { coord: [120.15665952392, 30.227929872006], elevation: 24 }, { coord: [120.15558896596, 30.227547621922], elevation: 24 }, { coord: [120.15487866568, 30.227026999323], elevation: 24 }],
];

function convertData(data) {
  return [].concat.apply([], data.map(track => track.map(seg => seg.coord.concat([1]))));
}

const points = convertData(jwdata);

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
    animation: false,
    bmap: {
      center: [120.13066322374, 30.240018034923],
      zoom: 14,
      roam: true,
      mapStyle: {
        styleJson: blackStyle,
      },
    },
    visualMap: {
      show: false,
      top: 'top',
      min: 0,
      max: 3,
      seriesIndex: 0,
      calculable: true,
      inRange: {
        color: ['green', 'yellow', 'red'],
      },
    },
    series: [{
      type: 'heatmap',
      coordinateSystem: 'bmap',
      data: points,
      pointSize: 5,
      blurSize: 6,
    }],
  };
}

export default {
  name: 'heart-map',
  props: ['size', 'orgId', 'chartConfig', 'chartData', 'id'],
  data() {
    return {
      chart: '',
      bmap: '',
      ply: '',
      locName: '长沙市开福区',
    };
  },
  // watch: {
  //   size() {
  //     this.chart.resize();
  //   },
  //   get_location_name(val) {
  //     this.locName = val;
  //     this.getBoundary(val);
  //   },
  //   chartData: {
  //     deep: true,
  //     handler(val) {
  //       const _this = this;
  //       this.createMapConfig();
  //       this.chart.setOption(getOption(_this.mapConfig));
  //       this.getBoundary(this.locName);
  //     },
  //   },
  // },
  computed: {
    ...mapGetters(['get_location_name']),
  },
  methods: {
    init() {
      const map = document.getElementById('map');
      this.chart = echarts.init(map);
      this.chart.setOption(getOption());

      this.bmap = this.chart.getModel().getComponent('bmap').getBMap();
      this.getBoundary('杭州市西湖区');
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
  },
  mounted() {
    this.init();
  },
};
</script>

<style lang="less" scoped>
.map {
  height: 100vh;
}
</style>
