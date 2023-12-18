<script setup>
import {ref,watch} from "vue";
import {ElMessage} from 'element-plus'
import 'element-plus/theme-chalk/el-message.css'
import {useRouter} from "vue-router";
import {useConsumerStore} from "@/stores/consumerStore";
import {useMerchantStore} from "@/stores/merchantStore";

const isGX = ref(false);
const isTXR = ref(false);
const isTXL = ref(false);
const isZ = ref(false);
const isSwitchC1 = ref(true);

const changeForm = () => {
  // 修改类名
  isGX.value = true;
  setTimeout(() => {
    isGX.value = false;
  }, 1500)
  isTXR.value = !isTXR.value;
  isSwitchC1.value = !isSwitchC1.value;
  isTXL.value = !isTXL.value;
  isZ.value = !isZ.value;
}

const registerForm = ref({
  shopName: '',
  username: '',
  email: '',
  password: '',
})
//准备校验规则对象
const registerRules = {
  shopName: [
    {required: true, message: '请输入店铺名称', trigger: 'blur'}
  ],
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  email: [
    {required: true, message: '请输入邮箱', trigger: 'blur'}
  ],
  phone: [
    {required: true, message: '请输入手机号', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 14, message: '密码长度在6至14个字符之间', trigger: 'blur'}
  ]
}

const loginForm = ref({
  phone: '',
  password: '',
})

//准备校验规则对象
const loginRules = {
  phone: [
    {required: true, message: '请输入手机号', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 14, message: '密码长度在6至14个字符之间', trigger: 'blur'}
  ]
}

//获取表单示例做统一检验
const loginFormRef=ref(null);
const registerFormRef=ref(null);
const router=useRouter()

// 判断是否是顾客注册
const isCustomerRegister = ref(true)

// 判断是否是顾客登录
const isCustomerLogin = ref(true)

const consumerStore = useConsumerStore()
const merchantStore = useMerchantStore()
const doLogin=()=>{
  const {phone,password}=loginForm.value
  loginFormRef.value.validate(async (valid)=>{
    //当所有项都校验通过才为true
    if (valid){
      console.log(isCustomerLogin)
      if(isCustomerLogin.value) {
        await consumerStore.consumerLogin({phone,password})
        ElMessage({type:'success',message:'顾客登录成功'});
        //跳转首页
        await router.replace({path: '/'})
      }else {
        await merchantStore.merchantLogin({phone,password})
        ElMessage({type:'success',message:'商家登录成功'});
        //跳转首页
        await router.replace({path: '/merchant'})
      }
    }
  })
}

// 注册
const doRegister=()=>{
  const {shopName,username,phone,email,password}=registerForm.value
  registerFormRef.value.validate(async (valid)=>{
    //当所有项都校验通过才为true
    if (valid){
      if(isCustomerRegister.value) {
        await consumerStore.consumerRegister({username,email,phone,password})
        ElMessage({type:'success',message:'顾客注册成功'});
        //跳转首页
        await router.replace({path: '/'})
      }else {
        await merchantStore.merchantRegister({shopName,phone,password})
        ElMessage({type:'success',message:'商家注册成功'});
        //跳转首页
        await router.replace({path: '/merchant'})
      }
    }
  })
}

// 监听isConsumerRegister变化，变化时清空表单
watch(isCustomerRegister,()=>{
  registerForm.value = {
    shopName: '',
    username: '',
    email: '',
    password: '',
  }
})

</script>
<template>
  <div class="login-container">
    <div class="shell">
      <div :class="{'container a-container': true, 'is-txl': isTXL}" id="a-container">
        <el-form ref="registerFormRef" :model="registerForm" :rules="registerRules" class="form" id="a-form">
          <h2 class="form_title title">注册账号</h2>
          <nav>
            <el-button
                :class="{'active': isCustomerRegister}"
                @click="isCustomerRegister = true"
            >顾客注册</el-button>
            <el-button
                :class="{'active': !isCustomerRegister}"
                @click="isCustomerRegister = false"
            >商家注册</el-button>
          </nav>
          <el-form-item prop="shopName" v-if="!isCustomerRegister">
            <el-input type="text" class="form_input"
                      v-model="registerForm.shopName"
                      placeholder="店铺名称"
                      maxlength="8"
            />
          </el-form-item>
          <el-form-item prop="username" v-if="isCustomerRegister">
            <el-input type="text" class="form_input"
                      v-model="registerForm.username"
                      placeholder="用户名"
                      maxlength="8"
            />
          </el-form-item>
          <el-form-item prop="email" v-if="isCustomerRegister">
            <el-input type="text" class="form_input"
                      v-model="registerForm.email"
                      placeholder="邮箱"
                      maxlength="20"
            />
          </el-form-item>
          <el-form-item prop="phone">
            <el-input type="text" class="form_input"
                      v-model="registerForm.phone"
                      placeholder="手机号"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" class="form_input"
                      v-model="registerForm.password"
                      placeholder="密码"
                      maxlength="14"
            />
          </el-form-item>
<!--          <span class="form_span">填写用户信息和密码进行注册</span>-->
          <el-button class="form_button button submit" @click="doRegister">SIGN UP</el-button>
        </el-form>
      </div>

      <div :class="{'container b-container': true, 'is-txl': isTXL, 'is-z': isZ}" id="b-container">
        <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="form" id="b-form">
          <h2 class="form_title title">登录账号</h2>
          <nav>
            <el-button
                :class="{'active': isCustomerLogin}"
                @click="isCustomerLogin = true"
            >顾客登录</el-button>
            <el-button
                :class="{'active': !isCustomerLogin}"
                @click="isCustomerLogin = false"
            >商家登录</el-button>
          </nav>
          <el-form-item prop="phone">
            <el-input type="text" class="form_input"
                      v-model="loginForm.phone"
                      placeholder="手机号"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" class="form_input"
                      v-model="loginForm.password"
                      placeholder="密码"
            />
          </el-form-item>
<!--          <span class="form_span" >填写手机号和密码进行登录</span>-->
          <el-button class="form_button button submit" @click="doLogin">SIGN IN</el-button>
        </el-form>
      </div>

      <div class="switch" :class="{'is-gx': isGX, 'is-txr': isTXR}">
        <div class="switch_circle" :class="{'is-txr': isTXR}"></div>
        <div class="switch_circle switch_circle-t" :class="{'is-txr': isTXR}"></div>
        <div class="switch_container" :class="{'is-hidden': !isSwitchC1}" id="switch-c1">
          <h2 class="switch_title title" style="letter-spacing: 0;">Welcome Back！</h2>
          <p class="switch_description description">欢迎回来！请登录您的账号，即可畅享海量商品内容/进入后台管理系统。</p>
          <button class="switch_button button switch-btn" @click.prevent="changeForm">SIGN IN</button>
        </div>

        <div class="switch_container" :class="{'is-hidden': isSwitchC1}" id="switch-c2">
          <h2 class="switch_title title" style="letter-spacing: 0;">Hello Newbie！</h2>
          <p class="switch_description description">您还没有账号？快速注册，即可畅享海量商品/成为商家！</p>
          <button class="switch_button button switch-btn" @click.prevent="changeForm">SIGN UP</button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped lang='scss'>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  /* 字体无法选中 */
  user-select: none;
}

.el-button {
  background: none;
  border: none;
}

:deep(.el-input__wrapper) {
  border: none;
  background: none;
  box-shadow: none;
  padding: 0 0 0 1.5vw;
}

:deep(.el-form-item__error) {
  top: 90%;
}

.login-container {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 0.72vw;
  background-color: rgba(238, 241, 255,0.75);
  color: #a0a5a8;
}

.shell {
  position: relative;
  width: 60vw;
  height: 36vw;
  padding: 1.5vw;
  background-color: $theme-color-1;
  box-shadow: 0 0 20px 0 $theme-color-2;
  border-radius: 12px;
  overflow: hidden;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 36vw;
  height: 100%;
  padding: 1.5vw;
  background-color: $theme-color-1;
  transition: 1.25s;
}

nav {
  width: 60%;
  height: 70px;
  margin-bottom: 0px;
  border-bottom: 1px solid $theme-color-2;
  display: flex;
  justify-content: space-around;
  text-align: right;
  align-items: center;

  button {
    height: 70%;
    flex: 1;
    line-height: 1;
    display: inline-block;
    font-size: 20px;
    font-weight: bold;
    position: relative;
    text-align: center;

    &.active {
      color: $theme-color-4;
    }

    &:hover {
      color: $theme-color-4;
    }

    &:first-child {
      border-right: 2px solid $theme-color-3;
    }
  }
}

.form {
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  padding-top: 20px;
}

.form_input {
  width: 20vw;
  height: 2.6vw;
  margin: 15px 0;
  font-size: 0.8vw;
  border: none;
  outline: none;
  background-color: $theme-color-1;
  transition: 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 2px 2px 4px $theme-color-2, inset -2px -2px 4px #f9f9f9;
}

.form_input:focus {
  box-shadow: inset 4px 4px 4px $theme-color-2, inset -4px -4px 4px #f9f9f9;
}

.form_span {
  color: rgba(169,169,169,0.8);
  margin-top: 1vw;
  font-size: 1vw;
}

.invalid_username {
  background-color: #1f1d2b;
}

.title {
  font-size: 2vw;
  font-weight: 700;
  line-height: 2;
  color: #181818;
  letter-spacing: 0.6vw;
}

.description {
  font-size: 0.85vw;
  letter-spacing: 0.12vw;
  text-align: center;
  line-height: 1.6;
}

.button {
  width: 10.8vw;
  height: 3vw;
  border-radius: 25px;
  margin-top: 1.5vw;
  font-weight: 700;
  font-size: 0.85vw;
  letter-spacing: 0.07vw;
  background-color: $theme-color-4;
  color: #f9f9f9;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border: none;
  outline: none;
  cursor: pointer;
}

.a-container {
  z-index: 100;
  left: calc(100% - 36vw);
}

.b-container {
  left: calc(100% - 36vw);
  z-index: 0;
}

.switch {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 24vw;
  padding: 3vw;
  z-index: 200;
  transition: 1.25s;
  background-color: $theme-color-1;
  overflow: hidden;
  box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #d1d9e6;
}

.switch_circle {
  position: absolute;
  width: 30vw;
  height: 30vw;
  border-radius: 50%;
  background-color: $theme-color-1;
  box-shadow: inset 8px 8px 12px rgb(210, 218, 255), inset -8px -8px 12px $theme-color-1;
  bottom: -60%;
  left: -60%;
  transition: 1.25s;
}

.switch_circle-t {
  top: -30%;
  left: 60%;
  width: 18vw;
  height: 18vw;
}

.switch_container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: absolute;
  width: 24vw;
  padding: 3vw 3.3vw;
  transition: 1.25s;
}

.switch_button {
  cursor: pointer;
}

.switch_button:hover,
.submit:hover {
  box-shadow: 6px 6px 10px #d1d9e6, -6px -6px 10px #f9f9f9;
  transform: scale(0.985);
  transition: 0.25s;
}

.switch_button:active,
.switch_button:focus {
  box-shadow: 2px 2px 6px #d1d9e6, -2px -2px 6px #f9f9f9;
  transform: scale(0.97);
  transition: 0.25s;
}

.is-txr {
  left: calc(100% - 24vw);
  transition: 1.25s;
  transform-origin: left;
}

.is-txl {
  left: 0;
  transition: 1.25s;
  transform-origin: right;
}

.is-z {
  z-index: 200;
  transition: 1.25s;
}

.is-hidden {
  visibility: hidden;
  opacity: 0;
  position: absolute;
  transition: 1.25s;
}

.is-gx {
  animation: is-gx 1.25s;
}

@keyframes is-gx {

  0%,
  10%,
  100% {
    width: 24vw;
  }

  30%,
  50% {
    width: 30vw;
  }
}

@media screen and (max-width:800px) {
  .shell{
    transform: scale(1.2);
  }
}

</style>
