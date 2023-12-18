<script setup>
import {useCartStore} from "@/stores/cartStore";
import {onMounted,computed,ref} from "vue";
import {useRouter} from "vue-router";
import {useConsumerStore} from "@/stores/consumerStore";

const router = useRouter()

const consumerStore = useConsumerStore()

const handleConfirm = () => {
  router.push('/login')
  consumerStore.clearConsumerInfo()
}

const cartStore=useCartStore()

const cartList = ref([])
const username = ref('')

onMounted(()=>{
  cartStore.updateCart()
  cartList.value = cartStore.cartList
  console.log(cartList.value)
  username.value = consumerStore.consumerInfo.username
})

</script>

<template>
  <header class='app-header'>
    <div class="container">
      <h1 class="logo">
        <RouterLink to="/">
<!--          <img src="@/assets/piggy_logo.png" alt="logo" />-->
          <span>PiggyShop</span>
        </RouterLink>
      </h1>
      <ul>
        <div class="cart">
          <a class="curr" href="javascript:;">
            <i class="iconfont icon-cart"></i><em>{{ cartStore.allCount }}</em>
          </a>
          <div class="layer">
            <div class="list">
              <div class="item" v-for="i in cartStore.cartList" :key="i">
                <RouterLink :to="'/detail/' + i.product.productId">
                  <img :src="i.product.coverUrl" alt="" />
                  <div class="center">
                    <p class="name ellipsis-2">
                      {{ i.product.name }}
                    </p>
                  </div>
                  <div class="right">
                    <p class="price">&yen;{{ i.product.price }}</p>
                    <p class="count">x{{ i.count }}</p>
                  </div>
                </RouterLink>
              </div>
            </div>
            <div class="foot">
              <div class="total">
                <p>共 {{cartStore.allCount}} 件商品</p>
                <p>&yen; {{ cartStore.allPrice }} </p>
              </div>
              <el-button size="large" type="primary" @click="$router.push('/pay')">去购物车结算</el-button>
            </div>
          </div>
        </div>
        <template v-if="true">
          <li><a href="javascript:;"><i class="iconfont icon-user"></i>{{username}}</a></li>
          <li>
            <el-popconfirm
                title="确认退出吗?"
                confirm-button-text="确认"
                cancel-button-text="取消"
                @confirm="handleConfirm"
            >
              <template #reference>
                <a href="javascript:;">退出登录</a>
              </template>
            </el-popconfirm>
          </li>
          <li>
            <RouterLink to="/user">
              个人中心
            </RouterLink>
          </li>
        </template>
      </ul>
    </div>
  </header>
</template>


<style scoped lang='scss'>
.app-header {
  background: #fff;
  border-bottom: solid 2px $theme-color-2;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

  .container {
    display: flex;
    align-items: center;
    justify-content: space-between;

    .logo {
      display: flex;
      align-items: center;
      height: 70px;

      img {
        height: 70px;
      }
    }

    .cart {
      width: 50px;
      position: relative;
      z-index: 600;
      margin-right: 10px;

      .curr {
        height: 32px;
        line-height: 32px;
        text-align: center;
        position: relative;
        display: block;

        .icon-cart {
          font-size: 20px;
        }

        em {
          font-style: normal;
          position: absolute;
          right: 0;
          top: 0;
          padding: 1px 5px;
          line-height: 1;
          background: $helpColor;
          color: #fff;
          font-size: 12px;
          border-radius: 10px;
          font-family: Arial;
        }
      }

      &:hover {
        .layer {
          opacity: 1;
          transform: none;
        }
      }

      .layer {
        opacity: 0;
        transition: all 0.4s 0.2s;
        transform: translateY(-200px) scale(1, 0);
        width: 400px;
        height: 400px;
        position: absolute;
        top: 50px;
        right: 0;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        background: #fff;
        border-radius: 4px;
        padding-top: 10px;

        &::before {
          content: "";
          position: absolute;
          right: 14px;
          top: -10px;
          width: 20px;
          height: 20px;
          background: #fff;
          transform: scale(0.6, 1) rotate(45deg);
          box-shadow: -3px -3px 5px rgba(0, 0, 0, 0.1);
        }

        .foot {
          position: absolute;
          left: 0;
          bottom: 0;
          height: 70px;
          width: 100%;
          padding: 10px;
          display: flex;
          justify-content: space-between;
          background: #f8f8f8;
          align-items: center;

          .total {
            padding-left: 10px;
            color: #999;

            p {
              &:last-child {
                font-size: 18px;
                color: $priceColor;
              }
            }
          }
        }
      }

      .list {
        height: 310px;
        overflow: auto;
        padding: 0 10px;

        &::-webkit-scrollbar {
          width: 10px;
          height: 10px;
        }

        &::-webkit-scrollbar-track {
          background: #f8f8f8;
          border-radius: 2px;
        }

        &::-webkit-scrollbar-thumb {
          background: #eee;
          border-radius: 10px;
        }

        &::-webkit-scrollbar-thumb:hover {
          background: #ccc;
        }

        .item {
          border-bottom: 1px solid #f5f5f5;
          padding: 10px 0;
          position: relative;

          a {
            display: flex;
            align-items: center;

            img {
              height: 80px;
              width: 80px;
            }

            .center {
              padding: 0 10px;
              width: 200px;

              .name {
                font-size: 16px;
              }

              .attr {
                color: #999;
                padding-top: 5px;
              }
            }

            .right {
              width: 100px;
              padding-right: 20px;
              text-align: center;

              .price {
                font-size: 16px;
                color: $priceColor;
              }

              .count {
                color: #999;
                margin-top: 5px;
                font-size: 16px;
              }
            }
          }
        }
      }
    }

    ul {
      display: flex;
      height: 53px;
      justify-content: flex-end;
      align-items: center;
      li {
        a {
          padding: 0 15px;
          color: #3d3b3b;
          line-height: 1;
          display: inline-block;

          i {
            font-size: 14px;
            margin-right: 2px;
          }

          &:hover {
            color: $theme-color-3;
          }
        }

        ~li {
          a {
            border-left: 2px solid #666;
          }
        }
      }
    }
  }
}
</style>
