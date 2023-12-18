import axios from 'axios'
import {ElMessage} from 'element-plus'
import 'element-plus/theme-chalk/el-message.css'
import router from "@/router";
// 创建axios实例
const httpInstance = axios.create({
    // baseURL: 'http://pcapi-xiaotuxian-front-devtest.itheima.net',
    baseURL: 'http://8.134.180.139/api',
    timeout: 5000
})
// axios请求拦截器(对发出的数据进行处理)
httpInstance.interceptors.request.use(config => {
    return config
}, e => Promise.reject(e))

// axios响应式拦截器(对返回的数据进行处理)
httpInstance.interceptors.response.use(res => {
    // //从pinia获取token数据
    if (!(res.data.code===200 || res.data.code===0)){
        ElMessage({
            type:'error',
            message:res.data.msg
        })
        return Promise.reject(res.data.msg)
    }
    return res.data
}, e => {
    return Promise.reject(e)
})


export default httpInstance
