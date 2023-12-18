<script setup>
import { onMounted } from 'vue'
import { ref } from 'vue'
import ProductItem from "@/components/ProductItem/index.vue";
import {getHomeProductsApi} from "@/apis/product";
import {useRouter} from "vue-router";

const router = useRouter()

const productsList = ref([])
const getGoods = async () => {
  let pageNum = 0,pageSize = 40
  const res = await getHomeProductsApi({pageNum,pageSize})
  productsList.value = res.data
}
onMounted( ()=> {
  getGoods()
} )
</script>

<template>
  <div class="home-product">
    <div class="home-panel">
      <div class="container">
        <div class="head">
          <!-- 主标题和副标题 -->
          <h3>
            产品列表
          </h3>
        </div>
        <!-- 主体内容区域 -->
        <div class="box">
          <ul class="goods-list">
            <ProductItem v-for="product in productsList" :key="product.productId" :product="product" />
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang='scss'>
.home-panel {
  width: 1240px;
  min-height: calc(100vh);
  margin: 3vw auto 2vw;
  border-radius: 20px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 0 2vw 2vw;

  .container {
    width: 100%;

    .head {
      position: sticky;
      top: 0;
      padding: 40px 0;
      display: flex;
      align-items: flex-end;

      h3 {
        flex: 1;
        font-size: 32px;
        margin-left: 6px;
        height: 35px;
        line-height: 35px;
        font-weight: bold;

        small {
          font-size: 16px;
          color: #999;
          margin-left: 20px;
        }
      }
    }

    .box {
      width: 100%;
      //height: 60vh;
      overflow: auto;

      .goods-list {
        display: flex;
        flex-wrap: wrap;
      }
    }
  }
}
</style>
