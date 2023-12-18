<script setup>
import {
  Document,
  Menu as IconMenu,
} from "@element-plus/icons-vue";

const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}


</script>

<template>
  <div class="sidebar">
    <div class="logo">
      猪头商城<br/>后台系统
    </div>
    <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
    >
      <el-menu-item index="1" @click="$router.push(`/merchant`)">
        <el-icon><document /></el-icon>
        <a>销售数据</a>
      </el-menu-item>
      <el-menu-item index="2" @click="$router.push(`/merchant/product`)">
        <el-icon><icon-menu /></el-icon>
        <a>商品详情</a>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped lang="scss">
.sidebar {
  width: 10vw;
  height: 100vh;
  background: $theme-color-3;

  .logo {
    height: 12vh;
    color: white;
    font-size: 3vh;
    font-weight: bold;
    padding: 1rem;
    text-align: center;
    letter-spacing: 2px;
  }

  .el-menu-vertical-demo {
    height: 88vh;
    background: $theme-color-3;

    .el-menu-item {
      color: white;
      font-size: 16px;
      font-weight: bold;
      padding: 1rem;
      text-align: center;

      &.is-active {
        background: $theme-color-4;
      }

      a {
        color: white;
      }

      &:hover {
        background: $theme-color-4;
      }
    }
  }
}
</style>
