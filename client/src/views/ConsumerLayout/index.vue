<script setup>
import LayoutHeader from './components/LayoutHeader.vue'
import LayoutFooter from './components/LayoutFooter.vue'
import {ElMessage} from "element-plus";
import {useConsumerStore} from "@/stores/consumerStore";
import {useRouter} from "vue-router";
import {onMounted} from "vue";

const router = useRouter()

onMounted(()=>{
  const consumerStore = useConsumerStore()
  if (!consumerStore.consumerInfo || !Object.keys(consumerStore.consumerInfo).length) {
    ElMessage.warning('请先登录')
    router.push('/login')
  }
})

</script>

<template>
  <div class="consumer-container">
    <LayoutHeader />
    <RouterView />
    <LayoutFooter />
  </div>
</template>

<style scoped lang='scss'>
.consumer-container {
  min-height: 100vh;
  background: $theme-color-1;
  overflow: auto;
}
</style>
