<script setup>
import {useConsumerStore} from '@/stores/consumerStore'
import {onMounted, ref} from "vue";

const consumerStore = useConsumerStore()

const userInfo = ref({})

onMounted(() => {
  userInfo.value = consumerStore.consumerInfo
})
</script>
<template>
  <div class="container">
    <div class="xtx-member-aside">
      <div class="avatar">
        <img src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201911%2F29%2F20191129201059_hgbvh.thumb.700_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1705239589&t=323b154652fccdb20dd2f777c172a029" />
      </div>
      <h3>{{userInfo.username}}</h3>
      <h4>({{userInfo.email}})</h4>
    </div>
    <div class="article">
      <div class="user-manage">
        <RouterLink to="/user">浏览历史</RouterLink>
        <RouterLink to="/user/order">我的订单</RouterLink>
      </div>
      <!-- 三级路由的挂载点 -->
       <RouterView />
    </div>
  </div>
</template>

<style scoped lang="scss">
.container {
  display: flex;
  padding-top: 3vw;
  padding-bottom: 2vw;

  .xtx-member-aside {
    width: 220px;
    min-height: calc(100vh - 172px - 5vw);
    margin-right: 20px;
    border-radius: 10px;
    background-color: #fff;

    .avatar {
      margin-top: 30px;
    }

    h3 {
      text-align: center;
      font-size: 24px;
      margin-top: 20px;
    }

    h4 {
      text-align: center;
      color: #666;
    }
  }

  .article {
    width: 1000px;
    background-color: #fff;

    .user-manage {
      display: flex;
      background-color: #fff;
      gap: 10px;
      padding: 10px 30px;

      h4 {
        font-size: 24px;
        font-weight: 400;
        padding: 20px 52px 5px;
        border-top: 1px solid #f6f6f6;
      }

      .links {
        padding: 0 52px 10px;
      }

      a {
        display: block;
        line-height: 1;
        padding: 15px 0;
        font-size: 24px;
        color: #666;
        position: relative;
        border-bottom: 1px solid #f6f6f6;

        &:hover {
          color: $theme-color-3;
        }

        &.active,
        &.router-link-exact-active {
          color: $theme-color-3;

          &:before {
            display: block;
          }
        }
      }
    }
  }
}
</style>
