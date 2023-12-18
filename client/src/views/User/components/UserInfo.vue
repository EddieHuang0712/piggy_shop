<script setup>
import {onMounted, ref} from 'vue';
import {getLogListApi} from '@/apis/consumer';
import {useConsumerStore} from "@/stores/consumerStore";
import {timeFormat} from "@/utils/timeUtil";

const consumerStore = useConsumerStore()
// 获取浏览历史列表
const historyList = ref([])

const productList = ref([])
const getHistoryList = async () => {
  const consumerId = consumerStore.consumerInfo?.consumerId
  let type = 0
  const res = await getLogListApi({consumerId, type})
  historyList.value = res.data
  console.log(historyList.value)
  // 提取historyList中的product信息并合并数组，product不能为null
  historyList.value.forEach(item => {
    item.createTime = timeFormat(item.createTime);
    if (item.product) {
      productList.value.push(item.product)
    }
  })
  productList.value.reverse()
}
onMounted(() => getHistoryList())
</script>

<template>
  <div class="like-container">
    <div class="home-panel">
      <div class="box">
        <div class="product-list">
          <div class="product-item" v-for="(product,i) in productList" :key="product?.productId">
            <RouterLink :to="`/detail/` + product.productId">
              <img :src="product.coverUrl"/>
            </RouterLink>
            <div class="product-info">
              <p class="name ellipsis-2">{{ product.name }}</p>
              <p class="time">{{product.description}}</p>
              <p class="price">¥{{ product.price }}</p>
              <p class="time">{{ historyList[i].createTime }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.like-container {
  margin-top: 20px;
  border-radius: 4px;
  background-color: #fff;
}

.home-panel {
  background-color: #fff;
  padding: 0 20px;
  margin-top: 20px;
  height: 400px;

  .header {
    height: 66px;
    border-bottom: 1px solid #f5f5f5;
    padding: 18px 0;
    display: flex;
    justify-content: space-between;
    align-items: baseline;

    h4 {
      font-size: 22px;
      font-weight: 400;
    }

  }

  .box {
    width: 100%;
    height: 100%;
    overflow-y: scroll;
    margin-top: 20px;

    .product-list {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-around;

      .product-item {
        width: 300px;
        height: 150px;
        margin-bottom: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        border-radius: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        transition: all 0.5s;
        cursor: pointer;

        &:hover {
          transform: translate3d(0, -3px, 0);
          box-shadow: 0 3px 8px rgb(0 0 0 / 20%);
        }

        a {
          width: 100px;
          height: 100px;

          img {
            width: 100%;
            height: 100%;
          }
        }

        .product-info {
          width: 70%;
          padding: 10px 10px;

          .name {
            font-size: 14px;
            color: #333;
            margin-bottom: 5px;
          }

          .price {
            font-size: 16px;
            color: $theme-color-2;
          }

          .time {
            font-size: 12px;
            color: #999;
          }
        }
      }
    }
  }
}
</style>
