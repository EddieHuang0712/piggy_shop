//管理用户相关数据
import {defineStore} from "pinia";
import {ref} from "vue";
// import {useCartStore} from "@/stores/cartStore";
import {consumerLoginApi, consumerRegisterApi} from "@/apis/consumer";
import {useCartStore} from "@/stores/cartStore";

export const useConsumerStore=defineStore('consumer',()=>{
    const consumerInfo=ref({});

    // 用户注册
    const consumerRegister=async ({username,email,phone,password})=>{
        try {
            const res=await consumerRegisterApi({username,email,phone,password});
            if(res.code===200 || res.code === 0) {
                consumerInfo.value = {
                    ...res.data,
                    username,
                    email,
                    phone,
                    password
                }
            }else {
                throw new Error(res.msg)
            }
        } catch (e) {
            console.log(e)
            return Promise.reject(e)
        }
    }
    const consumerLogin=async ({phone,password})=>{
        try {
            const res=await consumerLoginApi({phone,password});
            if(res.code === 200 || res.code === 0){
                consumerInfo.value=res.data
            } else {
                throw new Error(res.msg)
            }
        } catch (e) {
            console.log(e)
            return Promise.reject(e)
        }
    }
    //退出登录函数,清除用户信息
    const clearConsumerInfo=()=>{
        consumerInfo.value={};
        useCartStore().clearCart();
    }
    return{
        consumerInfo,
        consumerRegister,
        consumerLogin,
        clearConsumerInfo
    }
},{
    //数据持久化
    persist:true
})
