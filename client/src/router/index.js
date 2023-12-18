import { createRouter, createWebHistory } from 'vue-router'
import ConsumerLayout from '@/views/ConsumerLayout/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Consumer',
      component: ConsumerLayout,
      children: [{
          path: '',
          component: () => import('@/views/Home/index.vue'),
          name: 'Home'
      }, {
          path: 'detail/:id',
          component: () => import('@/views/Detail/index.vue'),
          name: 'Detail'
      }, {
          path: 'pay',
          component: () => import('@/views/Pay/index.vue'),
          name: 'Pay'
      }, {
          path: 'user',
          component: () => import('@/views/User/index.vue'),
          name: 'User',
          children: [{
              path: '',
              component: () => import('@/views/User/components/UserInfo.vue'),
              name: 'Info'
          },{
              path: 'order',
              component: () => import('@/views/User/components/UserOrder.vue'),
              name: 'Order'
          }]
      }
      ]
    },
    {
      path: '/merchant',
      name: 'Merchant',
      component: () => import('@/views/MerchantLayout/index.vue'),
      children: [{
          path: '',
          component: () => import('@/views/Sales/index.vue'),
          name: 'Sales'
      },{
          path: 'product',
          component: () => import('@/views/Product/index.vue'),
          name: 'Product'
      }]
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Login/index.vue')
    },
  ]
})

export default router
