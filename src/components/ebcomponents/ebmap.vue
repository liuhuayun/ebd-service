<template>
  <div class="eb-map">
    <div :class="messages == null ? 'no-caption' : 'show-caption'" ref="msgCaption">
      <span class="msg-show" ref="msgShow">{{messages}}</span>
    </div>
    <div id='map'></div>
    <div class="img-bar">
      <ul>
        <li>
          <img src="../../assets/msg-source.png" class="msg-icon" />
          <span class="msg-desc">信息源</span>
        </li>
        <li>
          <img src="../../assets/online-icon.png" class="msg-icon" />
          <span class="msg-desc">在线资源</span>
        </li>
        <li>
          <img src="../../assets/response-icon.png" class="msg-icon" />
          <span class="msg-desc">响应资源</span>
        </li>
        <li>
          <img src="../../assets/offline-icon.png" class="msg-icon" />
          <span class="msg-desc">离线资源</span>
        </li>
        <li>
          <span class="msg-icon plan-res"></span>
          <span class="msg-desc">方案资源</span>
        </li>
        <li>
          <span class="msg-icon msg-res"></span>
          <span class="msg-desc">消息发布源</span>
        </li>
        <li>
          <img src="../../assets/msg-dis.png" class="msg-icon" />
          <span class="msg-desc">消息路径</span>
        </li>
        <li>
          <img src="../../assets/msg-fberror.png" class="msg-icon" />
          <span class="msg-desc">反馈路径（异常）</span>
        </li>
        <li>
          <img src="../../assets/msg-fbsuccess.png" class="msg-icon" />
          <span class="msg-desc">反馈路径（正常）</span>
        </li>
      </ul>
    </div>
    <div class="eb-flow">
      <ul ref="flow">
        <li @click="itemShow(0)" idx="0" ref="flowli0" :class="[0 == itemli ? 'high-light':'', 'flow-btn']">
          <span :class="[0 == itemli ? 'flow-black':'', 'flow-now']" v-if="flowState(0)"></span>预警接入
        </li>
        <li class="next">〉〉</li>
        <li @click="itemShow(1)" idx="1" ref="flowli1" :class="[1 == itemli ? 'high-light':'', 'flow-btn']">
          <span :class="[1 == itemli ? 'flow-black':'', 'flow-now']" v-if="flowState(1)"></span>方案调度
        </li>
        <li class="next">〉〉</li>
        <li @click="itemShow(2)" idx="2" ref="flowli2" :class="[2 == itemli ? 'high-light':'', 'flow-btn']">
          <span :class="[2 == itemli ? 'flow-black':'', 'flow-now']" v-if="flowState(2)"></span>消息分发
        </li>
        <li class="next">〉〉</li>
        <li @click="itemShow(3)" idx="3" ref="flowli3" :class="[3 == itemli ? 'high-light':'', 'flow-btn']">
          <span :class="[3 == itemli ? 'flow-black':'', 'flow-now']" v-if="flowState(3)"></span>效果反馈
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {
  mapGetters,
  mapActions,
  mapMutations,
} from 'vuex';

export default {
  name: 'eb-map',
  data() {
    return {
      geo: {}, // 区域边界图层对象
      map: null, // 地图对象
      itemli: -1, // 当前预警阶段
      time: null, // 定时器对象
      dsLine: null, // 消息下发飞线图层对象
      fbLine: null, // 消息反馈飞线图层对象
      msgCenter: null, // 消息源图层对象
      resources: null, // 资源点图层对象集合
      sendResource: null, // 消息发布源
      planResources: null, // 方案资源
      respResources: null, // 响应资源
      maxLi: 0, // 轮播最大值
      newLayers: null, // 原始的图层，用于重置地图状态
      messages: null, // 轮播字幕
      msgTimer: null, // 字幕轮播定时器
    };
  },
  computed: {
    ...mapGetters(['get_geo_json', 'get_ebm_info', 'get_mouse_state', 'get_resource_info', 'get_next_access']),
  },
  methods: {
    ...mapMutations({ setNextAccess: 'GET_NEXT_ACCESS' }),
    ...mapActions(['getGeoJson', 'getResourceInfo']),
    // 添加信息源
    addMsgCenter(centerData) {
      // 判断现有资源中是否为信息源
      if (this.isNull(centerData) || this.isNull(this.resources)) {
        return;
      }
      let place = null;
      let ebrName = '';
      const resource = this.resources.get(centerData.ebrId);
      if (!this.isNull(resource)) {
        place = resource.resourcePoint.getLatLng();
        ebrName = resource.ebrName;
        resource.resourcePoint.remove();
      }
      // 添加信息源第一个参数为纬度（latitude），第二个参数为经度（longitude）
      const myIcon = BM.divIcon({
        className: 'my-div-icon',
        html: '<span class="center-icon"></span>',
      });
      if (place == null) {
        place = [];
        place.push(centerData.latitude);
        place.push(centerData.longitude);
      }
      const center = BM.marker(place, {
        icon: myIcon,
      }).addTo(this.map);
      const popHtml = `<div class='msg-title'><span class='title'>${centerData.msgtitle
      }</span><span class='time'>${centerData.createtime
      }</span></div><div class='center-desc'>${centerData.msgdesc == null ? '' : centerData.msgdesc
      }</div>`;
      center.bindTooltip(popHtml, {
        permanent: true,
        offset: [0, -5],
        direction: 'top',
        className: 'center-tooltip',
      });
      this.msgCenter = {
        ebrId: centerData.ebrId,
        ebrName,
        resourcePoint: center,
      };
    },
    // 重置消息源状态
    resetMsgCenter() {
      if (this.msgCenter == null || this.isNull(this.resources)) {
        return;
      }
      // 重置资源点的状态
      const resource = this.resources.get(this.msgCenter.ebrId);
      if (!this.isNull(resource)) {
        resource.resourcePoint.addTo(this.map);
      }
      this.msgCenter.resourcePoint.remove();
      this.msgCenter = null;
    },
    // 添加消息发布源
    addSendResource(ebmInfo) {
      // 判断现有资源中是否为信息源
      if (this.isNull(ebmInfo) || this.isNull(this.resources)) {
        return;
      }
      let place = null;
      let ebrName = '';
      const resource = this.resources.get(ebmInfo.ebrId);
      if (!this.isNull(resource)) {
        place = resource.resourcePoint.getLatLng();
        ebrName = resource.ebrName;
        resource.resourcePoint.remove();
      }
      // 添加信息源第一个参数为纬度（latitude），第二个参数为经度（longitude）
      const myIcon = BM.divIcon({
        className: 'my-div-icon',
        html: '<span class="send-resource-icon"></span>',
      });
      if (place == null) {
        place = [];
        place.push(ebmInfo.latitude);
        place.push(ebmInfo.longitude);
      }
      const sendResource = BM.marker(place, {
        icon: myIcon,
      }).addTo(this.map);
      sendResource.bindTooltip(ebrName, {
        permanent: true,
        offset: [0, -5],
        direction: 'top',
        className: 'send-resource-tooltip',
      });
      this.sendResource = {
        ebrId: ebmInfo.ebrId,
        ebrName,
        resourcePoint: sendResource,
      };
    },
    // 重置消息发布源
    resetSendResource() {
      if (this.sendResource == null || this.isNull(this.resources)) {
        return;
      }
      // 重置资源点的状态
      const resource = this.resources.get(this.sendResource.ebrId);
      if (!this.isNull(resource)) {
        resource.resourcePoint.addTo(this.map);
      }
      this.sendResource.resourcePoint.remove();
      this.sendResource = null;
    },
    // 添加方案资源
    addPlanResources(ebmInfo) {
      const { ebrDtoList } = ebmInfo;
      // 方案资源为空不处理
      if (this.isNull(ebrDtoList) || this.isNull(this.resources)) {
        return;
      }
      const planResources = [];
      for (const ebr of ebrDtoList) {
        // 判断现有资源中是否为信息源
        const resource = this.resources.get(ebr.ebrId);
        if (this.isNull(resource)) {
          continue;
        }
        const place = resource.resourcePoint.getLatLng();
        const { ebrName } = resource;
        resource.resourcePoint.remove();
        // 添加信息源第一个参数为纬度（latitude），第二个参数为经度（longitude）
        const myIcon = BM.divIcon({
          className: 'my-div-icon',
          html: '<span class="plan-icon"></span>',
        });
        const planResource = BM.marker(place, {
          icon: myIcon,
        }).addTo(this.map);
        planResource.bindTooltip(ebrName, {
          permanent: true,
          offset: [0, -5],
          direction: 'top',
          className: 'resource-tooltip',
        });
        planResources.push({
          ebrId: ebr.ebrId,
          ebrName,
          resourcePoint: planResource,
        });
      }
      this.planResources = planResources;
    },
    // 重置方案资源
    resetPlanResources() {
      if (this.isNull(this.planResources) || this.planResources.length < 1 || this.isNull(this.resources)) {
        return;
      }
      // 遍历方案资源集合对全部方案资源进行移除并且恢复原有资源样式
      for (const planResource of this.planResources) {
        const resource = this.resources.get(planResource.ebrId);
        planResource.resourcePoint.remove();
        if (this.isNull(resource)) {
          continue;
        }
        resource.resourcePoint.addTo(this.map);
      }
      this.planResources = null;
    },
    // 添加响应资源
    addRespResources(ebmInfo) {
      const { ebrDtoList } = ebmInfo;
      // 方案资源为空不处理
      if (this.isNull(ebrDtoList) || this.isNull(this.resources)) {
        return;
      }
      const respResources = [];
      for (const ebr of ebrDtoList) {
        // 判断现有资源中是否为信息源
        const resource = this.resources.get(ebr.ebrId);
        if (this.isNull(resource)) {
          continue;
        }
        const place = resource.resourcePoint.getLatLng();
        const { ebrName } = resource;
        resource.resourcePoint.remove();
        // 添加信息源第一个参数为纬度（latitude），第二个参数为经度（longitude）
        const myIcon = BM.divIcon({
          className: 'my-div-icon',
          html: '<span class="resp-icon"></span>',
        });
        const respResource = BM.marker(place, {
          icon: myIcon,
        }).addTo(this.map);
        respResource.bindTooltip(ebr.ebrName, {
          permanent: true,
          offset: [0, -5],
          direction: 'top',
          className: 'resp-tooltip',
        });
        respResources.push({
          ebrId: ebr.ebrId,
          ebrName,
          resourcePoint: respResource,
        });
      }
      this.respResources = respResources;
    },
    // 重置响应资源
    resetRespResources() {
      if (this.isNull(this.respResources) || this.respResources.length < 1 || this.isNull(this.resources)) {
        return;
      }
      // 遍历方案资源集合对全部响应资源进行移除并且恢复原有资源样式
      for (const respResource of this.respResources) {
        const resource = this.resources.get(respResource.ebrId);
        respResource.resourcePoint.remove();
        if (this.isNull(resource)) {
          continue;
        }
        resource.resourcePoint.addTo(this.map);
      }
      this.respResources = null;
    },
    /**
       * @param {Object} ebmInfo
       * 添加消息分发飞线数据
       */
    msgDisLine(ebmInfo) {
      // 组装地图数据
      if (this.isNull(ebmInfo) || this.isNull(ebmInfo.ebrDtoList) || ebmInfo.ebrDtoList.length < 1) {
        return;
      }
      const converData = [];
      for (const ebr of ebmInfo.ebrDtoList) {
        // 飞线起止信息
        converData.push({
          fromName: ebmInfo.sendname,
          toName: ebr.ebrName,
          coords: [
            [ebmInfo.longitude, ebmInfo.latitude],
            [ebr.longitude, ebr.latitude],
          ],
        });
      }
      // echarts配置信息
      const series = [{
        name: ebmInfo.msgtitle,
        type: 'lines',
        coordinateSystem: 'leaflet',
        zlevel: 1,
        effect: {
          show: true,
          period: 6, // 箭头指向速度，值越小速度越快
          trailLength: 0.2, // 特效尾迹长度[0,1]值越大，尾迹越长重
          color: '#00ffff',
          symbol: 'arrow', // 箭头图标
          symbolSize: 7, // 图标大小
        },
        lineStyle: {
          normal: {
            color: '#00ffff',
            width: 1, // 尾迹线条宽度
            opacity: 1, // 尾迹线条透明度
            curveness: 0.3, // 尾迹线条曲直度
          },
        },
        data: converData,
      }];

      const option = {
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(12, 36, 43, 0.9)',
          borderColor: '#00ffff',
          showDelay: 0,
          hideDelay: 0,
          enterable: true,
          transitionDuration: 0,
          extraCssText: 'z-index:100',
          formatter(params, ticket, callback) {
            // 根据业务自己拓展要显示的内容
            let res = '';
            const name = params.seriesName;
            res = `<span style='color:#00ffff;'>${name}</span>`;
            return res;
          },
        },
        series,
      };
      this.dsLine = BM.supermap.echartsLayer(option).addTo(this.map);
    },
    // 重置消息下发飞线
    resetMsgDisLine() {
      if (this.isNull(this.dsLine)) {
        return;
      }
      this.dsLine.remove();
      this.dsLine = null;
    },
    // 添加消息反馈飞线数据
    msgFbLine(ebmInfo) {
      if (this.isNull(ebmInfo) || this.isNull(ebmInfo.ebrDtoList) || ebmInfo.ebrDtoList.length < 1) {
        return;
      }
      // 组装地图数据
      const converSuccessData = [];
      const converFailedData = [];
      // 飞线起止信息
      for (const ebr of ebmInfo.ebrDtoList) {
        // 如果反馈成功
        if (ebr.dispatchState == 1) {
          converSuccessData.push({
            fromName: ebr.ebrName,
            toName: ebmInfo.sendname,
            coords: [
              [ebr.longitude, ebr.latitude],
              [ebmInfo.longitude, ebmInfo.latitude],
            ],
          });
        } else {
          converFailedData.push({
            fromName: ebr.ebrName,
            toName: ebmInfo.sendname,
            coords: [
              [ebr.longitude, ebr.latitude],
              [ebmInfo.longitude, ebmInfo.latitude],
            ],
          });
        }
      }
      // echarts配置信息，成功反馈的配置
      const successSeries = [{
        name: ebmInfo.msgtitle,
        type: 'lines',
        coordinateSystem: 'leaflet',
        zlevel: 1,
        effect: {
          show: true,
          period: 6, // 箭头指向速度，值越小速度越快
          trailLength: 0.2, // 特效尾迹长度[0,1]值越大，尾迹越长重
          color: '#00ff84',
          symbol: 'arrow', // 箭头图标
          symbolSize: 7, // 图标大小
        },
        lineStyle: {
          normal: {
            color: '#00ff84',
            width: 1, // 尾迹线条宽度
            opacity: 1, // 尾迹线条透明度
            curveness: 0.3, // 尾迹线条曲直度
          },
        },
        data: converSuccessData,
      }];
        // 失败反馈的配置
      const failedSeries = [{
        name: ebmInfo.msgtitle,
        type: 'lines',
        coordinateSystem: 'leaflet',
        zlevel: 1,
        effect: {
          show: true,
          period: 6, // 箭头指向速度，值越小速度越快
          trailLength: 0.2, // 特效尾迹长度[0,1]值越大，尾迹越长重
          color: '#ff0000',
          symbol: 'arrow', // 箭头图标
          symbolSize: 7, // 图标大小
        },
        lineStyle: {
          normal: {
            color: '#ff0000',
            width: 1, // 尾迹线条宽度
            opacity: 1, // 尾迹线条透明度
            curveness: 0.3, // 尾迹线条曲直度
          },
        },
        data: converFailedData,
      }];
        // 成功反馈配置项
      const successOption = {
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(12, 36, 43, 0.9)',
          borderColor: '#00ff84',
          showDelay: 0,
          hideDelay: 0,
          enterable: true,
          transitionDuration: 0,
          extraCssText: 'z-index:100',
          formatter(params, ticket, callback) {
            // 根据业务自己拓展要显示的内容
            let res = '';
            const name = params.seriesName;
            res = `<span style='color:#00ff84;'>${name}</span>`;
            return res;
          },
        },
        series: successSeries,
      };
        // 失败反馈配置项
      const failedOption = {
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(12, 36, 43, 0.9)',
          borderColor: '#ff0000',
          showDelay: 0,
          hideDelay: 0,
          enterable: true,
          transitionDuration: 0,
          extraCssText: 'z-index:100',
          formatter(params, ticket, callback) {
            // 根据业务自己拓展要显示的内容
            let res = '';
            const name = params.seriesName;
            res = `<span style='color:#ff0000;'>${name}</span>`;
            return res;
          },
        },
        series: failedSeries,
      };
      const lines = [];
      lines.push(BM.supermap.echartsLayer(successOption).addTo(this.map));
      lines.push(BM.supermap.echartsLayer(failedOption).addTo(this.map));
      this.fbLine = lines;
    },
    resetFbLine() {
      if (this.isNull(this.fbLine) || this.fbLine.length < 1) {
        return;
      }
      for (const line of this.fbLine) {
        line.remove();
      }
      this.fbLine = null;
    },
    // 设置当前轮播索引
    setIndex() {
      if (this.itemli < this.maxLi) {
        this.itemli++;
      } else {
        // this.itemli = 0;
        this.setNextAccess(1);
        this.stop();
      }
    },
    // 定时器执行
    scroll() {
      const _this = this;
      const { flow } = this.$refs;
      flow.style.transition = 'all 0.3s ease';
      const flowli = this.$refs[`flowli${_this.itemli}`];
      flowli.attributes.class = 'high-light';

      setTimeout(() => {
        flow.style.transition = 'none';
        _this.setIndex();
      }, 300);
    },
    // 开启定时器
    start() {
      if (this.itemli != 0) {
        this.itemli = 0;
      } else {
        this.callFlow(0);
      }
      this.time = setInterval(this.scroll, 5000);
    },
    // 关闭定时器
    stop() {
      clearInterval(this.time);
    },
    // 每个li点击事件
    itemShow(idx) {
      this.stop();
      this.itemli = idx;
    },
    // 根据乡镇名称高亮区域
    coverTown(ebmInfo) {
      if (this.isNull(ebmInfo) || this.isNull(ebmInfo.areaNames) || ebmInfo.areaNames.length < 1) {
        return;
      }
      for (const areaName of ebmInfo.areaNames) {
        for (const idx in this.geo._layers) {
          const layer = this.geo._layers[idx];
          if (layer.feature.properties.name == areaName) {
            const newLayer = layer;
            this.geo.removeLayer(layer);
            newLayer._bigemap_id = '';
            newLayer.setStyle({
              color: '#019eff',
              fillOpacity: 0.8,
              fillColor: "#ff6a7b"
            });
            this.geo.addLayer(newLayer);
          }
        }
      }
    },
    // 重置乡镇覆盖区域
    resetTown() {
      if (this.isNull(this.geo)) {
        return;
      }
      if (this.isNull(this.newLayers) || this.newLayers.length < 1) {
        return;
      }
      // 从图层组中删除所有图层
      const _geo = this.geo;
      _geo.clearLayers();
      for (const layer of this.newLayers) {
        // 重置图层样式
        _geo.resetStyle(layer);
        // 重新添加图层
        _geo.addLayer(layer);
      }
    },
    // 判断是否显示点
    flowState(idx) {
      if (this.get_ebm_info.data == undefined) {
        return false;
      }
      const flowState = this.get_ebm_info.data.flowstate;
      // 预警接入
      if (flowState < 20 && idx == 0) {
        this.maxLi = 0;
        return true;
      }
      // 预警处理
      if (flowState > 20 && flowState < 30 && idx == 1) {
        this.maxLi = 1;
        return true;
      }
      // 消息分发
      if (flowState > 30 && flowState < 50 && idx == 2) {
        this.maxLi = 2;
        return true;
      }
      // 效果反馈
      if (flowState > 50 && idx == 3) {
        this.maxLi = 3;
        return true;
      }
      return false;
    },
    resetAll() {
      this.resetMsgCenter(); // 重置信息源状态
      this.resetTown(); // 重置覆盖区域状态
      this.resetSendResource(); // 重置消息发送源
      this.resetPlanResources(); // 重置方案资源
      this.resetRespResources(); // 重置反馈资源
      this.resetMsgDisLine(); // 重置消息下发飞线
      this.resetFbLine(); // 重置消息反馈飞线
    },
    // 调用不同阶段事件
    callFlow(idx) {
      const _ebmInfo = this.get_ebm_info.data;
      if (this.isNull(_ebmInfo)) {
        return;
      }
      switch (idx) {
        case 0:
          // 预警接入
          this.resetAll();
          // 添加新的信息源
          this.addMsgCenter(_ebmInfo);
          // 重新设置覆盖区域
          this.coverTown(_ebmInfo);
          break;
        case 1:
          // 方案调度
          this.resetAll();
          // 添加方案资源
          this.addPlanResources(_ebmInfo);
          break;
        case 2:
          // 消息分发
          this.resetAll();
          // 添加方案资源
          this.addPlanResources(_ebmInfo);
          // 添加消息发送源
          this.addSendResource(_ebmInfo);
          // 添加消息下发飞线
          this.msgDisLine(_ebmInfo);
          break;
        case 3:
          // 效果反馈
          this.resetAll();
          // 添加消息发送源
          this.addSendResource(_ebmInfo);
          // 添加反馈资源
          this.addRespResources(_ebmInfo);
          // 添加反馈飞线
          this.msgFbLine(_ebmInfo);
          break;
        default:
          break;
      }
    },
    // 设置资源信息
    setResources(val) {
      const resourcePoints = val.data;
      if (this.isNull(resourcePoints) || resourcePoints.length < 1) {
        return;
      }
      if (this.isNull(this.map)) {
        return;
      }
      const resourceIcon = BM.divIcon({
        className: 'my-div-icon',
        html: '<span class="resource-icon"></span>',
      });
      const offlineIcon = BM.divIcon({
        className: 'my-div-icon',
        html: '<span class="offline-icon"></span>',
      });
      const resourceMap = new Map();
      for (const point of resourcePoints) {
        if (this.isNull(point.latitude) || this.isNull(point.longitude)) {
          continue;
        }
        if (point.ebrState == '1') {
          const resourcePoint = BM.marker([point.latitude, point.longitude], {
            icon: resourceIcon,
          }).bindTooltip(point.ebrName, {
            permanent: true,
            offset: [0, -5],
            direction: 'top',
            className: 'resource-tooltip',
          }).addTo(this.map);
          resourceMap.set(point.ebrId, {
            ebrName: point.ebrName,
            resourcePoint: resourcePoint,
          });
        } else {
          const resourcePoint = BM.marker([point.latitude, point.longitude], {
            icon: offlineIcon,
          }).bindTooltip(point.ebrName, {
            permanent: true,
            offset: [0, -5],
            direction: 'top',
            className: 'offline-tooltip',
          }).addTo(this.map);
        }
      }
      this.resources = resourceMap;
    },
    // 重置资源信息
    resetResources() {
      if (this.isNull(this.resources)) {
        return;
      }
      for (const idx in this.resources) {
        const resource = resouces[idx];
        resource.resourcePoint.remove();
      }
      this.resources = null;
    },
    isNull(val) {
      if (val == null || val == undefined) {
        return true;
      }
      return false;
    },
    // 字幕滚动开启
    startCaptions() {
      const _this = this;
      const { msgCaption } = _this.$refs;
      const scrollWidth = msgCaption.clientWidth;
      const { msgShow } = _this.$refs;
      msgShow.style.left = '100%';
      clearInterval(_this.msgTimer);
      const textWidth = msgShow.clientWidth;
      let i = scrollWidth;
      _this.msgTimer = setInterval(() => {
        i--;
        if (i < -textWidth) {
          _this.messages = null;
          msgShow.style.left = '100%';
          clearInterval(_this.msgTimer);
        }
        msgShow.style.left = `${i}px`;
      }, 20);
    },
  },
  created() {
    // 初始化地图资源点
    this.getResourceInfo({
      params: {},
    });
  },
  watch: {
    // 监听当前轮播索引的事件
    itemli(val) {
      this.callFlow(val);
    },
    // 监听ebminfo是否更新
    get_ebm_info(val) {
      // 先关闭当前轮播定时器
      if (this.time) {
        this.stop();
      }
      // 如果鼠标状态为微操作则开启轮播，否则只显示第一个步骤
      if (this.get_mouse_state == 1) {
        this.start();
      } else if (this.itemli != 0) {
        this.itemli = 0;
      } else {
        this.callFlow(0);
      }
      // 设置字幕轮播内容并开启轮播
      this.messages = `${val.data.msgtitle}：${val.data.msgdesc == null ? '' : val.data.msgdesc}`;
      const _this = this;
      this.$nextTick(() => {
        _this.startCaptions();
      });
    },
    // 监听鼠标事件
    get_mouse_state(val) {
      if (val == 0) {
        this.stop();
      } else {
        this.start();
      }
    },
    // 关闭旧的定时器
    time(val, oldVal) {
      clearInterval(oldVal);
    },
    // 监听资源点数据
    get_resource_info(val) {
      this.resetResources();
      this.setResources(val);
    },
  },
  updated() {

  },
  mounted() {
    this.getGeoJson({
      params: {},
      callback: (data) => {
        if (data == null || data == undefined) {
          return;
        }

        BM.Config.HTTP_URL = 'http://localhost:9000';
        const map = BM.map('map', 'bigemap.bh39o3gf', {
          center: [28.290536880493164, 113.21981811523438],
          zoom: 10,
          zoomControl: true,
        });
          // 设置最小缩放级别
        map.setMinZoom(10);
        // 设置地图移动边界，第一个参数为限制区域的左上角坐标，第二个为限制区域的右下角坐标
        map.setMaxBounds([
          [28.66796417, 112.88926998],
          [27.81579855, 113.59868047],
        ]);

        this.map = map;
        const _this = this;
        const geo = BM.geoJSON(data.data, {
          // 设置导入的地图边界颜色(包含地图背景)
          style(feature) {
            /* 带背景地图
            return {
              // fillOpacity: 0.3,//填充透明度
              color: '#0076FF',
              weight: 2,
              // fillColor: "#019eff"
            };*/
            //不带背景地图
            return {
              fillOpacity: 0,//填充透明度
              // color: '#0c242b',
              color: '#019eff',
              weight: 1,
              // fillColor: "#019eff"
            };
          },
        }).addTo(map);
        /*
        .bindPopup((layer) => {
          _this.resetTown();
          const newLayer = layer;
          geo.removeLayer(layer);
          newLayer._bigemap_id = '';
          //带地图背景
          // newLayer.setStyle({
          //   color: 'red',
          //   fillOpacity: 0.3,
          // });
          //不带地图背景
          newLayer.setStyle({
            color: '#019eff',
            fillOpacity: 0.8,
            fillColor: "#ff6a7b"
          });
          geo.addLayer(newLayer);
          return layer.feature.properties.name;
        })
        */
        this.geo = geo;
        const newLayers = [];
        for (const idx in geo._layers) {
          newLayers.push(geo._layers[idx]);
        }
        this.newLayers = newLayers;
        this.setResources(this.get_resource_info);
        $(".eb-map").parents(".card-container").css({border: "none"});
        $(".eb-map").parents(".card-container").find(".horn").css({display: "none"});
      },
    });
  },
  destroyed() {
    clearInterval(this.time);
  },
};
</script>

<style>
  @import url("http://localhost:9000/bigemap.js/v2.1.0/bigemap.css");

  .eb-map {
    font-size: 12px;
    height: 100%;
    width: 100%;
    overflow: hidden;
    box-sizing: border-box;
    position: relative;
    padding: 0;
    margin: 0;
  }

  #map {
    position: absolute;
    top: 0;
    bottom: 0;
    width: 100%;
    /* background: rgba(1, 158, 255, 0.05); */
    background: rgba(1, 158, 255, 0);
  }

  /* 添加工具栏样式 */
  .eb-map ul {
    list-style-type: none;
    padding: 0;
  }

  .eb-map .img-bar {
    position: absolute;
    width: 180px;
    right: 0;
    bottom: 118px;
    margin-bottom: 30px;
  }

  .eb-map .img-bar li {
    display: block;
    position: relative;
    width: 100%;
  }

  .eb-map .img-bar .msg-icon {
    position: absolute;
    top: 50%;
    margin-top: -7px;
  }

  .eb-map .img-bar .msg-desc {
    display: inline-block;
    margin-left: 34px;
    font-size: 12px;
    line-height: 25px;
  }

  .eb-map .plan-res {
    display: inline-block;
    width: 14px;
    height: 14px;
    background-image: url(../../assets/online-icon.png);
  }

  .eb-map .plan-res:after {
    content: "";
    box-shadow: 0 0 6px 2px #00ff84;
    animation: pulsate 2s linear;
    animation-iteration-count: infinite;
    animation-delay: -2s;
    /* -webkit-border-radius: 100%; */
    border-radius: 100%;
    height: 300%;
    width: 300%;
    position: absolute;
    margin: -100% 0 0 -100%;
  }

  .eb-map .msg-res {
    display: inline-block;
    width: 14px;
    height: 14px;
    background-image: url(../../assets/red.png);
  }

  .eb-map .msg-res:after {
    content: "";
    box-shadow: 0 0 6px 2px #ff0000;
    animation: pulsate 2s linear;
    animation-iteration-count: infinite;
    animation-delay: -2s;
    /* -webkit-border-radius: 100%; */
    border-radius: 100%;
    height: 300%;
    width: 300%;
    position: absolute;
    margin: -100% 0 0 -100%;
  }

  /* 轮播组样式 */
  .eb-map .eb-flow {
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 118px;
    /* border-top: 1px solid #00ffff; */
    box-sizing: border-box;
  }

  .eb-map .eb-flow ul {
    position: relative;
    height: 60px;
    width: 982px;
    top: calc(50% - 30px);
    margin: 0 auto;
  }

  .eb-map .eb-flow li {
    display: inline-block;
    height: 60px;
    box-sizing: border-box;
  }

  .eb-map .eb-flow .flow-btn {
    display: inline-block;
    line-height: 60px;
    text-align: center;
    font-size: 18px;
    width: 160px;
    color: #00ffff;
    background-color: rgba(0, 0, 0, 0.5);
  }

  .eb-map .eb-flow .next {
    display: inline-block;
    color: #00ffff;
    text-align: center;
    line-height: 60px;
    font-size: 18px;
    width: 114px;
  }

  .eb-map .eb-flow .flow-btn:hover {
    background-color: #00ffff;
    color: #000000;
  }

  .eb-map .eb-flow .flow-btn:hover .flow-now {
    background-color: #000000;
  }

  .eb-map .eb-flow .high-light {
    background-color: #00ffff;
    color: #000000;
  }

  .eb-map .flow-now {
    display: inline-block;
    border-radius: 50%;
    width: 14px;
    height: 14px;
    background-color: #00ffff;
    margin-right: 10px;
  }

  .eb-map .flow-black {
    background-color: #000000;
  }

  /* 地图logo去除 */
  .eb-map .iclient-leaflet-logo {
    display: none;
  }

  .eb-map .bigemap-control-attribution {
    display: none;
  }

  .eb-map .bigemap-logo-true {
    display: none !important;
  }

  .eb-map .bigemap-bar {
    background-color: #037c80;
  }

  .eb-map .bigemap-bar a:hover {
    background-color: #00ffff;
  }

  /* 公用动画光环效果 */
  @keyframes pulsate {
    0% {
      transform: scale(0.1, 0.1);
      opacity: 0;
      /* -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)"; */
      filter: alpha(opacity=0);
    }

    50% {
      opacity: 1;
      /* -ms-filter: none; */
      filter: none;
    }

    100% {
      transform: scale(1.2, 1.2);
      opacity: 0;
      /* -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)"; */
      filter: alpha(opacity=0);
    }
  }

  /* 消息源样式 */
  .eb-map .center-icon {
    width: 60px;
    height: 60px;
    position: absolute;
    background-image: url(../../assets/large.png);
    bottom: 0;
    left: calc(50% - 30px);
  }

  .eb-map .center-tooltip {
    background-color: rgba(12, 36, 43, 0.9);
    border-radius: 8px;
    padding: 0 14px;
    width: 300px;
    height: 164px;
    border: none;
    box-sizing: border-box;
    z-index: 1000;
    margin-top: -60px;
    white-space: pre-wrap;
  }

  .eb-map .center-tooltip:before {
    border-top-color: rgba(12, 36, 43, 0.9);
  }

  .eb-map .msg-title {
    width: 100%;
    margin-top: 16px;
    margin-bottom: 12px;
    color: #ff6666;
    font-size: 14px;
  }

  .eb-map .title {
    display: inline-block;
  }

  .eb-map .time {
    display: inline-block;
    margin-left: 10px;
  }

  .eb-map .center-desc {
    line-height: 18px;
    font-size: 12px;
    height: 110px;
    color: #fff;
    white-space: pre-wrap;
    word-wrap: break-word;
    word-break: break-all;
    display: -webkit-box;
    -webkit-line-clamp: 6;
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  /* 添加资源点样式 */
  .eb-map .resource-icon {
    background-color: #00ff84;
    display: inline-block;
    position: relative;
    width: 14px;
    height: 14px;
    border-radius: 50%;
  }

  .resource-tooltip {
    color: #00ff84;
    background-color: rgba(12, 36, 43, 0.9);
    border: 1px solid #00ff84;
    box-sizing: border-box;
    margin-top: -6px;
    line-height: 30px;
    padding: 0 5px;
  }

  .resource-tooltip:before {
    display: none;
  }

  /* 方案资源*/
  .eb-map .plan-icon {
    background-color: #00ff84;
    display: inline-block;
    position: relative;
    width: 14px;
    height: 14px;
    border-radius: 50%;
  }

  .eb-map .plan-icon:after {
    content: "";
    box-shadow: 0 0 6px 2px #00ff84;
    animation: pulsate 2s linear;
    animation-iteration-count: infinite;
    animation-delay: -2s;
    -webkit-border-radius: 100%;
    border-radius: 100%;
    height: 300%;
    width: 300%;
    position: absolute;
    margin: -100% 0 0 -100%;
  }

  /* 离线资源*/
  .eb-map .offline-icon {
    background-color: #999999;
    display: inline-block;
    position: relative;
    width: 14px;
    height: 14px;
    border-radius: 50%;
  }

  .eb-map .offline-tooltip {
    color: #999999;
    background-color: rgba(12, 36, 43, 0.9);
    border: 1px solid #999999;
    box-sizing: border-box;
    margin-top: -6px;
    line-height: 30px;
    padding: 0 5px;
  }

  .eb-map .offline-tooltip:before {
    display: none;
  }

  /* 消息发布源样式*/
  .eb-map .send-resource-icon {
    display: inline-block;
    width: 14px;
    height: 14px;
    border-radius: 50%;
    background-color: #ff0000;
    position: relative;
  }

  .eb-map .send-resource-icon:after {
    content: "";
    box-shadow: 0 0 6px 2px #FF0000;
    animation: pulsate 2s linear;
    animation-iteration-count: infinite;
    animation-delay: -2s;
    -webkit-border-radius: 100%;
    border-radius: 100%;
    height: 300%;
    width: 300%;
    position: absolute;
    margin: -100% 0 0 -100%;
  }

  .eb-map .send-resource-tooltip {
    color: #ff6666;
    background-color: rgba(12, 36, 43, 0.9);
    border: 1px solid #ff6666;
    box-sizing: border-box;
    margin-top: -6px;
    line-height: 30px;
    padding: 0 5px;
  }

  .eb-map .send-resource-tooltip:before {
    display: none;
  }

  /* 响应资源样式*/
  .eb-map .resp-icon {
    display: inline-block;
    width: 14px;
    height: 14px;
    border-radius: 50%;
    background-color: #f5ff66;
    position: relative;
  }

  .eb-map .resp-tooltip {
    color: #f5ff66;
    background-color: rgba(12, 36, 43, 0.9);
    border: 1px solid #f5ff66;
    box-sizing: border-box;
    margin-top: -6px;
    line-height: 30px;
    padding: 0 5px;
  }

  .eb-map .resp-tooltip:before {
    display: none;
  }

  /* 添加字幕样式*/
  .eb-map .no-caption {
    display: none;
  }

  .eb-map .show-caption {
    display: block;
    position: relative;
    top: 0;
    left: 0;
    width: 100%;
    height: 50px;
    /* background-color: #a60408; */
    overflow: hidden;
    z-index: 1000;
  }

  .eb-map .msg-show {
    position: absolute;
    line-height: 50px;
    font-size: 20px;
    /* color: #fff; */
    color: #a60408;
    font-weight: bolder;
    top: 0;
    left: 100%;
    white-space: nowrap;
  }

</style>
