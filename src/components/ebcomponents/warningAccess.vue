<template>
  <div class="warning-access">
    <ul class="trends" ref="rollup">
      <li v-for="(item, idx) in items" @click="itemliClick(idx)" ref="itemli" :idx="idx" :class="idx == itemIdx ? 'high-light':''">
        <span class="msg-icon"></span>
        <div class="msg-one">
          <div class="msg-time">
            {{item.createtime}}
          </div>
          <div class="msg-container">
            <span class="msg-title">{{item.msgtitle}}：</span>
            <span class="msg-desc">{{item.msgdesc}}</span>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {
  mapGetters,
  mapActions,
  mapMutations,
} from 'vuex';

export default {
  name: 'eb-rolling',
  data() {
    return {
      animate: false,
      items: [],
      itemIdx: 0,
      isFirst: true,
    };
  },
  destroyed() {
    clearInterval(this.time);
  },
  computed: {
    ...mapGetters(['get_warning_access', 'get_mouse_state', 'get_next_access']),
  },
  watch: {
    // 监听鼠标事件
    get_mouse_state(val) {
      // 状态为0停止轮播
      if (val == 1 && this.get_next_access == 1) {
        this.scroll();
        this.setNextAccess(0);
      }
    },
    // 监听地图是否轮播完毕，轮播完毕则更换下一条消息
    get_next_access(val) {
      // 切换状态为1并且鼠标监听事件为轮播状态则进行轮播
      if (val == 1 && this.get_mouse_state == 1) {
        this.scroll();
      }
      // 不管是否进行切换都将切换状态置0
      this.setNextAccess(0);
    },
  },
  methods: {
    ...mapMutations({ setNextAccess: 'GET_NEXT_ACCESS' }),
    ...mapActions(['getWarningAccessInfo', 'getEbmInfo']),
    // 设置当前轮播索引
    setIndex(itemli) {
      if (this.itemIdx < itemli.length - 1) {
        this.itemIdx++;
        return true;
      }
      return false;
    },
    // 轮播方法
    scroll() {
      const _this = this;
      const { rollup } = this.$refs;
      rollup.style.transition = 'all 0.3s ease';
      const { itemli } = this.$refs;
      if (itemli == null || itemli == undefined || itemli.length < 1) {
        return;
      }
      itemli[this.itemIdx].attributes.class = 'high-light';
      if (this.isFirst) {
        this.isFirst = false;
        const ebmId = _this.items[_this.itemIdx].ebmid;
        _this.getEbmInfo({
          params: {
            ebmId,
          },
        });
      } else {
        setTimeout(() => {
          rollup.style.transition = 'none';
          const result = _this.setIndex(itemli);
          if (!result) {
            this.getWarningAccessInfo({
              params: {
                limit: 5,
              },
              callback: (data) => {
                this.items = data.data;
                this.itemIdx = 0;
                const ebmId = _this.items[_this.itemIdx].ebmid;
                _this.getEbmInfo({
                  params: {
                    ebmId,
                  },
                });
              },
            });
          } else {
            const ebmId = _this.items[_this.itemIdx].ebmid;
            _this.getEbmInfo({
              params: {
                ebmId,
              },
            });
          }
        }, 300);
      }
    },
    // 应急消息点击事件
    itemliClick(idx) {
      this.setNextAccess(0);
      this.itemIdx = idx;
      const ebmId = this.items[idx].ebmid;
      this.getEbmInfo({
        params: {
          ebmId,
        },
      });
    },
  },
  mounted() {
    const _this = this;
    this.getWarningAccessInfo({
      params: {
        limit: 5,
      },
      callback: (data) => {
        _this.items = data.data;
        _this.itemIdx = 0;
        // $(".warning-access").parents(".card-container").css({border: "none"});
        // $(".warning-access").parents(".card-container").find(".horn").css({display: "none"});
        setTimeout(() => {
          _this.scroll();
        }, 1000);
      },
    });
  },
};
</script>

<style lang="less" scoped>
  .warning-access {
    font-size: 12px;
    height: 100%;
    width: 100%;
    overflow-y: hidden;
    box-sizing: border-box;
    padding: 0;
    padding-left: 18px;
    padding-right: 22px;
  }

  .trends {
    position: relative;
    width: 100%;
    height: 100%;
    flex-direction: column;
  }

  .trends li {
    list-style: none;
    position: relative;
    margin-top: 10px;
    margin-bottom: 10px;
    width: 100%;
    height: calc(20% - 20px);
  }

  .trends li:hover .msg-time {
    color: #f5ff66;
    border: 1px solid #f5ff66;
  }

  .msg-icon {
    display: inline-block;
    background-color: #f5ff66;
    width: 16px;
    height: 16px;
    border-radius: 50%;
    position: absolute;
    left: 0;
    top: 20px;
    margin-top: -8px;
  }

  .msg-one {
    margin-left: 26px;
    min-width: 250px;
    width: calc(100% - 26px);
    height: 100%;
    background-color: rgba(0, 255, 255, 0.1);
    border-radius: 8px;
  }

  .msg-time {
    border-radius: 8px;
    box-sizing: border-box;
    padding: 0px 10px;
    font-size: 18px;
    line-height: 40px;
    color: #66ffb5;
    border: 1px solid #66ffb5;
    background-color: #0c242b;
    width: calc(100% -10px);
  }

  .msg-container {
    height: calc(100% - 60px);
    white-space: pre-wrap;
    word-wrap:break-word;
    word-break:break-all;
    display: -webkit-box;
    -webkit-line-clamp: 5;
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
    padding: 0 5px;
    padding-top: 10px;
  }

  .msg-title {
    color: #ff6666;
    line-height: 18px;
  }

  .msg-desc {
    color: #ffffff;
    line-height: 18px;
  }

  .high-light .msg-time {
    color: #f5ff66;
    border: 1px solid #f5ff66;
  }
</style>
