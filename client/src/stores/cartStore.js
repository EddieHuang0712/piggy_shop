//购物车逻辑
import {defineStore} from "pinia";
import {computed, ref} from "vue";
import {useConsumerStore} from "@/stores/consumerStore";
import {addCartApi, getCartListApi} from "@/apis/consumer";

export const useCartStore = defineStore('cart', () => {
    const consumerStore=useConsumerStore();
    //isLogin是一个布尔值
    const isLogin=computed(()=>consumerStore.consumerInfo)
    //定义购物车列表
    const cartList = ref([]);
    //获取最新购物车数据
    const updateCart=async ()=>{
        const res=await getCartListApi({consumerId:consumerStore.consumerInfo.consumerId});
        clearCart();
        res.data.forEach((item) => {
            console.log(item)
            const product = cartList.value.find((product) => product.productId === item.product.productId);
            if (product) {
                product.count += item.count;
            } else {
                cartList.value.push(item);
            }
        });
        console.log(cartList)
    }
    const addCart =async (product) => {
        if (isLogin.value){
            const {consumerId}=consumerStore.consumerInfo;
            const {productId, count, price}=product;
            const sum = count * price;
            await addCartApi({productId, consumerId, count, sum});
            await updateCart()
        }else {
            const item = cartList.value.find((item) => product.productId === item.productId);
            if (item) {
                //在购物车中找到相同的，只需要数量加1
                item.count++;
            } else {
                //没找到，新增
                cartList.value.push(product);
            }
        }

    };

    //清空购物车
    const clearCart=()=>{
        cartList.value=[]
    }

    // 1. 总的数量 所有项的count之和
    const allCount = computed(() => cartList.value.reduce((a, c) => a + c.count, 0))
    // 2. 总价 所有项的count*price之和
    const allPrice = computed(() => cartList.value.reduce((a, c) => a + c.sum, 0))

    return {
        cartList,
        addCart,
        allCount,
        allPrice,
        clearCart,
        updateCart,
    }
}, {
    //数据存储在浏览器存储，保证刷新不丢失
    persist: true
})
