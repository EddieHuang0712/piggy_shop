<script setup>

// tab列表
import {onMounted, ref} from "vue";
import {getHistoryListApi, getOrderListApi} from "@/apis/consumer";
import {useConsumerStore} from "@/stores/consumerStore";
import {timeFormat} from "@/utils/timeUtil";
// 订单列表
const orderList = ref([])

const productList = ref([])

const consumerStore = useConsumerStore()

const getOrderList = async () => {
  const consumerId = consumerStore.consumerInfo?.consumerId
  const res = await getHistoryListApi({consumerId})
  orderList.value = res.data
  // 提取orderList中的product信息并合并数组，product不能为null
  orderList.value.forEach(item => {
    if (item.product) {
      productList.value.push(item.product)
    }
  })
  console.log(productList.value)
}
onMounted(() => getOrderList())

</script>

<template>
  <div class="order-container">
    <div class="main-container">
      <div class="holder-container" v-if="orderList.length === 0">
        <el-empty description="暂无订单数据" />
      </div>
      <div class="order-container" v-else>
        <!-- 订单列表 -->
        <div class="order-item" v-for="(order, i) in productList" :key="order.id">
          <div class="head">
            <span>下单时间：{{ timeFormat(orderList[i].createTime) }}</span>
            <span>订单编号：{{ orderList[i].salesId }}</span>
          </div>
          <div class="body">
            <div class="column product">
              <RouterLink :to="`detail/` + order.productId">
                <img class="image" :src="order.coverUrl" alt="" />
              </RouterLink>
              <div class="info">
                <p class="name ellipsis-2">
                  {{ order.name }}
                </p>
                <p class="attr ellipsis">
                  <span>{{ order.description }}</span>
                </p>
              </div>
              <div class="price">¥{{ order.price }}</div>
              <div class="count">x{{ orderList[i].count }}</div>
            </div>
            <div class="column amount">
              <p class="red">¥{{ orderList[i].sum }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.order-container {
  padding: 10px 20px;

  .pagination-container {
    display: flex;
    justify-content: center;
  }

  .main-container {
    height: 400px ;
    overflow-y: auto;

    .holder-container {
      min-height: 500px;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .order-container {
      overflow-y: scroll;
    }
  }
}

.order-item {
  margin-bottom: 20px;
  border: 1px solid #f5f5f5;

  .head {
    height: 50px;
    line-height: 50px;
    background: #f5f5f5;
    padding: 0 20px;
    overflow: hidden;

    span {
      margin-right: 20px;

      &.down-time {
        margin-right: 0;
        float: right;

        i {
          vertical-align: middle;
          margin-right: 3px;
        }

        b {
          vertical-align: middle;
          font-weight: normal;
        }
      }
    }

    .del {
      margin-right: 0;
      float: right;
      color: #999;
    }
  }

  .body {
    display: flex;
    align-items: stretch;

    .column {
      border-left: 1px solid #f5f5f5;
      text-align: center;
      padding: 20px;

      >p {
        padding-top: 10px;
      }

      &:first-child {
        border-left: none;
      }

      &.product {
        flex: 1;
        //padding: 0;
        align-self: center;
        border-bottom: 1px solid #f5f5f5;
        padding: 10px;
        display: flex;

        .image {
          width: 70px;
          height: 70px;
          border: 1px solid #f5f5f5;
        }

        .info {
          width: 220px;
          text-align: left;
          padding: 0 10px;

          p {
            margin-bottom: 5px;

            &.name {
              height: 38px;
            }

            &.attr {
              color: #999;
              font-size: 12px;

              span {
                margin-right: 5px;
              }
            }
          }
        }

        .price {
          width: 100px;
        }

        .count {
          width: 80px;
        }
      }

      &.state {
        width: 120px;

        .green {
          color: $theme-color-4;
        }
      }

      &.amount {
        width: 200px;

        .red {
          color: $priceColor;
        }
      }

      &.action {
        width: 140px;

        a {
          display: block;

          &:hover {
            color: $theme-color-4;
          }
        }
      }
    }
  }
}
</style>
