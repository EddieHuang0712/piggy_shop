<script lang="ts" setup>
import { RouterView } from 'vue-router'
import LayoutNav from "@/views/MerchantLayout/components/LayoutNav.vue";
import SideBar from "@/views/MerchantLayout/components/Sidebar.vue";

</script>
<template>
  <div class="container">
    <SideBar />
    <div class="main">
      <LayoutNav />
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
/* Add any custom styles here */
.container {
  width: 100%;
  height: calc(100vh - 80px);
  display: flex;
  margin: 0;
  background: #ffffff;
}

.main {
  width: 90vw;
}
</style>
