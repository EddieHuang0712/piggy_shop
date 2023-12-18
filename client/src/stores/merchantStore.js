//管理商家相关数据
import {defineStore} from "pinia";
import {ref} from "vue";
import {merchantLoginApi, merchantRegisterApi} from "@/apis/merchant";

export const useMerchantStore=defineStore('merchant',()=>{
    const merchantInfo=ref({});

    // 用户注册
    const merchantRegister=async ({shopName,phone,password})=>{
        try {
            const res=await merchantRegisterApi({name:shopName,phone,password});
            if (res.code===200 || res.code === 0) {
                merchantInfo.value = {
                    ...res.data,
                    name:shopName,
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
    const merchantLogin=async ({phone,password})=>{
        try {
            const res=await merchantLoginApi({phone,password});
            if(res.code === 200 || res.code === 0){
                merchantInfo.value=res.data
            } else {
                throw new Error(res.msg)
            }
        } catch (e) {
            console.log(e)
            return Promise.reject(e)
        }
    }
    //退出登录函数,清除用户信息
    const clearMerchantInfo=()=>{
        merchantInfo.value={};
    }
    return{
        merchantInfo,
        merchantRegister,
        merchantLogin,
        clearMerchantInfo
    }
},{
    //数据持久化
    persist:true
})
