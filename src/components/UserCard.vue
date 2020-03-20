<template>
  <div :class="[url == 'dev'? 'card-container' : (border ? 'card-container' : 'no-border-container')]">
    <div class="card-title"
      v-if="name">{{name}}</div>
    <div class="card-content">
      <slot></slot>
    </div>
    <span v-if="expand"
      @click="doExpand"
      class="expand-icon"></span>
    <span v-if="home"
      @click="goHome"
      class="home-icon"></span>
    <span class="horn leftTop"></span>
    <span class="horn rightTop"></span>
    <span class="horn leftBottom"></span>
    <span class="horn rightBottom"></span>
  </div>
</template>

<script>
export default {
  name: 'UserCard',
  props: {
    name: String,
    expand: {
      type: Boolean,
      default: true,
    },
    home: {
      type: Boolean,
      default: true,
    },
    url: String,
    border: {
      type: Boolean,
      default: true,
    },
  },
  methods: {
    goHome() {
      this.$router.push({ path: '/' });
    },
    doExpand() {
      const _this = this;
      if (this.url) {
        this.$router.push({ path: _this.url });
      }
    },
  },
};
</script>

<style lang="less" scoped>
@baseWidth: 3px;
@basePosition: -2px;
.horn {
  width: 12px;
  height: 12px;
  position: absolute;
}
.leftTop {
  top: @basePosition;
  left: @basePosition;
  border-left: @baseWidth solid #42f9f7;
  border-top: @baseWidth solid #42f9f7;
}
.rightTop {
  top: @basePosition;
  right: @basePosition;
  border-right: @baseWidth solid #42f9f7;
  border-top: @baseWidth solid #42f9f7;
}
.leftBottom {
  bottom: @basePosition;
  left: @basePosition;
  border-left: @baseWidth solid #42f9f7;
  border-bottom: @baseWidth solid #42f9f7;
}
.rightBottom {
  bottom: @basePosition;
  right: @basePosition;
  border-right: @baseWidth solid #42f9f7;
  border-bottom: @baseWidth solid #42f9f7;
}
.no-border-container {
  box-sizing: border-box;
  padding: 5px;
  color: #fff;
  position: relative;
  height: 100%;
  width: 100%;
}
.l-container {
  box-sizing: border-box;
  border: 1px solid #041f30;
  border-radius: 3px;
  padding: 5px;
  color: #fff;
  position: relative;
  height: 100%;
  width: 100%;
}
.card-container {
  box-sizing: border-box;
  border: 1px solid #536f8f;
  border-radius: 3px;
  padding: 0;
  color: #fff;
  // background: #0c1624;
  // box-shadow: inset 0px 0px 15px #019eff;
  position: relative;
  height: 100%;
  width: 100%;
}
.card-title {
  border-bottom: 2px solid;
  border-image: linear-gradient(
      to right,
      #0c1624,
      #001f2d,
      #347582,
      #001f2d,
      #0c1624
    )
    30 30;
  // background: linear-gradient(
  //   to right,
  //   #0c1624,
  //   #001f2d,
  //   #062c3b,
  //   #001f2d,
  //   #0c1624
  // );
  padding: 8px 0;
  color: #41f8f6;
  font-size: 12px;
  text-align: center;
  font-weight: normal;
}
.card-content {
  height: calc(100% - 34px);
}
.expand-icon {
  position: absolute;
  width: 16px;
  height: 16px;
  z-index: 10;
  right: 6px;
  top: 6px;
  background-image: url(../assets/expand.png);
  background-size: 100%;
  cursor: pointer;
}
.home-icon {
  position: absolute;
  width: 16px;
  height: 16px;
  z-index: 10;
  right: 6px;
  top: 6px;
  background-image: url(../assets/icon.png);
  background-size: 100%;
  cursor: pointer;
}
</style>
