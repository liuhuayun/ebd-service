<template>
  <div class="box" ref="box">
    <audio class="audio" id="audio" :src="mscsrc" controls autoplay loop crossOrigin="anonymous"></audio>
    <canvas class="audio-flash" id="canvas" style="border:0px solid #f00"></canvas>
  </div>
</template>

<script>
  import {
    mapGetters,
    mapActions,
    mapMutations,
  } from 'vuex';

  export default {
    name: 'PlayingChart',
    data() {
      return {
        mscsrc: 'http://192.168.111.112:9012/EBDR_123123.mp3',
        voicec1: ['#0f0', '#f00', '#f0f'],
      };
    },
    computed: {
      ...mapGetters(['get_ebm_info']),
    },
    watch: {
      get_ebm_info(val) {
        if (val.data == null || val.data == undefined) {
          this.mscsrc = "";
          return;
        }
        if (val.data.audiopath == null || val.data.audiopath == undefined || val.data.audiopath == "") {
          this.mscsrc = "";
          return;
        }
        this.mscsrc = '/res' + val.data.audiopath;
        // this.mscsrc = 'http://192.168.111.100:9012' + val.data.audiopath;
      },
    },
    methods: {
      play() {
        const canvas = $('#canvas')[0];
        const audio = $('#audio')[0];

        // audio.src = this.mscsrc;
        const ctx = canvas.getContext('2d');
        const actx = new AudioContext();
        const color = ctx.createLinearGradient(canvas.width * 0.5, 0, canvas.width * 0.5, canvas.height);
        color.addColorStop(0, this.voicec1[0]);
        color.addColorStop(0.5, this.voicec1[1]);
        color.addColorStop(1, this.voicec1[2]);
        const colort = ctx.createLinearGradient(canvas.width * 0.5, canvas.height * 0.5, canvas.width * 0.5, canvas.height *
          3);
        colort.addColorStop(0, 'rgba(125,225,133,0.7)');
        colort.addColorStop(0.5, 'rgba(225,225,0,0.1)');
        colort.addColorStop(1, 'rgba(125,0,133,0)');
        const {
          box
        } = this.$refs;
        canvas.width = box.clientWidth;
        canvas.height = box.clientHeight;

        const analyser = actx.createAnalyser();
        const audioSrc = actx.createMediaElementSource(audio);
        audioSrc.connect(analyser);
        analyser.connect(actx.destination);
        const num = 20;

        function draw() {
          const voicehigh = new Uint8Array(analyser.frequencyBinCount);
          analyser.getByteFrequencyData(voicehigh);
          const step = Math.round(voicehigh.length / num);
          ctx.clearRect(0, 0, canvas.width, canvas.height);
          ctx.beginPath();
          for (let i = 1; i < num; i++) {
            const value = voicehigh[step * i] / 3;
            ctx.fillStyle = color;
            ctx.fillRect(i * 10 + canvas.width * 0.5, canvas.height * 0.5, 7, -value + 1);
            ctx.fillRect(canvas.width * 0.5 - (i - 1) * 10, canvas.height * 0.5, 7, -value + 1);
            ctx.fill();
            ctx.fillStyle = colort;
            ctx.fillRect(i * 10 + canvas.width * 0.5, canvas.height * 0.5, 7, value + 1);
            ctx.fillRect(canvas.width * 0.5 - (i - 1) * 10, canvas.height * 0.5, 7, value + 1);
          }
          requestAnimationFrame(draw);
        }
        draw();
      },
      run() {
        const audio = document.getElementById('audio');
        const imgcss = document.getElementById('img');
        if (audio.paused) {
          audio.play();
          imgcss.className = 'img';
        } else {
          audio.pause();
          imgcss.className = '';
        }
      },
    },
    mounted() {
      window.AudioContext = window.AudioContext || window.webkitAudioContext || window.mozAudioContext;
      const _this = this;
      setTimeout(() => {
        _this.play();
        const audio = document.getElementById('audio');
      }, 2000);
    },
  };
</script>

<style lang="less" scoped>
  .box {
    width: 100%;
    height: 100%;
    position: relative;
    overflow: hidden;
  }

  .audio {
    display: none;
    width: 100%;
    position: absolute;
    bottom: calc(50% - 27px);
    z-index: 1000;
  }

  .box:hover .audio {
    display: block;
  }

  .audio-flash {
    position: absolute;
    top: 10%;
  }
</style>
