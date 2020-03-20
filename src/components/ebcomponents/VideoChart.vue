<template>
  <div class="box">
    <div class="video">
      <video-player class="video-player vjs-custom-skin" ref="videoPlayer" :playsinline="true" :options="playerOptions">
      </video-player>
    </div>
  </div>
</template>

<script>
import {
  mapGetters,
  mapActions,
  mapMutations,
} from 'vuex';
// 引入video样式
import {
  videoPlayer,
} from 'vue-video-player';

// 引入hls.js
import 'videojs-contrib-hls.js/src/videojs.hlsjs';
// require('video.js/dist/video-js.css')
require('vue-video-player/node_modules/video.js/dist/video-js.css');
require('vue-video-player/src/custom-theme.css');

export default {
  name: 'VideoChart',
  data() {
    return {
      loadding: false,
      ebrCountInfo: [],
      playerOptions: {},
    };
  },
  computed: {
    ...mapGetters(['get_ebrStatistic_info', 'get_ebrStatistic_info']),
  },
  methods: {
    ...mapActions(['getEbrStatisticInfo']),
  },
  computed: {
    player() {
      return this.$refs.videoPlayer.player;
    },
  },
  components: {
    videoPlayer,
  },
  mounted() {
    this.playerOptions = {
      // playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
      autoplay: false, // 如果true,浏览器准备好时开始回放。
      controls: true, // 控制条
      preload: 'auto', // 视频预加载
      muted: true, // 默认情况下将会消除任何音频。
      loop: true, // 导致视频一结束就重新开始。
      language: 'zh-CN',
      aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
      fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
      sources: [{
        type: 'video/mp4', // application/x-mpegURL
        src: 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Soldier76_Hero.mp4',
      }],
      // poster: "http://static.smartisanos.cn/pr/img/video/video_03_cc87ce5bdb.jpg", //你的封面地址
      width: document.documentElement.clientWidth,
      notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
    };
  },
};
</script>

<style lang="less" scoped>
  .box {
    font-size: 12px;
    height: 100%;
    width: 100%;
    overflow-y: hidden;
    box-sizing: border-box;
    position: relative;
  }

  .video {
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    overflow: hidden;
  }
</style>
