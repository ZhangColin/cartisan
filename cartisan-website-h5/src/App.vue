<template>
  <div id="app">
    <v-header :seller="seller"></v-header>
    <div class="tab border-top-1px border-bottom-1px">
      <div class="tab-item">
        <router-link to="/goods">商品</router-link>
      </div>
      <div class="tab-item">
        <router-link to="/ratings">评论</router-link>
      </div>
      <div class="tab-item">
        <router-link to="/seller">商家</router-link>
      </div>
    </div>
    <keep-alive>
      <router-view :seller="seller"></router-view>
    </keep-alive>
  </div>
</template>

<script>
  import {urlParse} from '@/assets/js/util';
  import vheader from '@/components/header/header';

  export default {
    name: 'App',
    data() {
      return {
        seller: {
          id: (() => {
            let queryParam = urlParse();
            return queryParam.id;
          })()
        }
      };
    },
    created() {
      this.axios.get('/api/seller')
        .then((response) => {
          this.seller = Object.assign({}, this.seller, response.data.data);
        });
    },
    components: {'v-header': vheader}
  };
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "assets/stylus/mixin.styl"
  #app
    .tab
      display: flex
      width: 100%
      height: 40px
      line-height: 40px
      border-top-1px(rgba(7, 17, 27, 0.1))
      border-bottom-1px(rgba(7, 17, 27, 0.1))
      .tab-item
        flex: 1
        text-align: center
        & > a
          display: block
          font-size: 14px
          color: rgb(77, 85, 93)
          &.active
            color: rgb(240, 20, 20)
</style>
≥
