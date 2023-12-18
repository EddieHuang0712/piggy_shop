<script setup>
import { computed } from 'vue'
import { addLogApi } from "@/apis/consumer";
import {useConsumerStore} from "@/stores/consumerStore";

const props = defineProps({
  product: {
    type: Object,
    default: () => ({})
  }
})

const link = computed(() => {
  return {
    path: `/detail/${props.product.productId}`
  }
})

const consumerStore = useConsumerStore()

// 添加浏览历史
const addHistory = () => {
  addLogApi({
    uid: consumerStore.consumerInfo.consumerId,
    pid: props.product.productId,
    type: 0
  })
}
</script>
<template>
  <RouterLink :to=link @click="addHistory" class="goods-item">
    <img v-img-lazy="product.coverUrl" alt="" />
    <p class="name ellipsis">{{ product.name }}</p>
    <p class="desc ellipsis">{{ product.desc?product.desc:product.description }}</p>
    <p class="price">&yen;{{ product.price }}</p>
  </RouterLink>
</template>
<style lang="scss">
.goods-item {
  display: block;
  width: 230px;
  padding: 20px 30px;
  text-align: center;
  transition: all .5s;
  border-radius: 10px;

  &:hover {
    transform: translate3d(0, -3px, 0);
    box-shadow: 0 3px 8px rgb(0 0 0 / 20%);
  }

  img {
    width: 160px;
    height: 160px;
    border-radius: 10px;
  }

  p {
    padding-top: 10px;
  }

  .name {
    font-size: 16px;
  }

  .desc {
    color: #999;
    height: 29px;
  }

  .price {
    color: $priceColor;
    font-size: 20px;
  }
}
</style>
