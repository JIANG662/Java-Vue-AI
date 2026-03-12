<!--<template>-->
<!--  <div class="container">-->
<!--    <div class="login-main">-->
<!--      &lt;!&ndash; 左侧品牌和信息区 &ndash;&gt;-->
<!--      <div class="left-panel">-->
<!--        <div class="brand">企业后台管理系统</div>-->
<!--        <p class="welcome-text">欢迎登录后台管理系统，请填写您的账号信息登录系统</p>-->
<!--        <ul class="feature-list">-->
<!--          <li>-->
<!--            <span class="icon"><el-icon><User /></el-icon></span>-->
<!--            基于 Vue 3 + TypeScript 开发-->
<!--          </li>-->
<!--          <li>-->
<!--            <span class="icon"><el-icon><Lock /></el-icon></span>-->
<!--            安全的账号认证与权限管理-->
<!--          </li>-->
<!--          <li>-->
<!--            <span class="icon"><el-icon><Setting /></el-icon></span>-->
<!--            丰富的组件和功能模板-->
<!--          </li>-->
<!--        </ul>-->
<!--        <div class="decoration"></div>-->
<!--      </div>-->

<!--      &lt;!&ndash; 右侧登录表单区 &ndash;&gt;-->
<!--      <div class="right-panel">-->
<!--        <h2 class="login-title">账号登录</h2>-->

<!--        <el-form-->
<!--            ref="formRef"-->
<!--            :model="form"-->
<!--            :rules="rules"-->
<!--            class="login-form"-->
<!--        >-->
<!--          <el-form-item prop="username" class="form-item">-->
<!--            <el-input-->
<!--                v-model="form.username"-->
<!--                placeholder="请输入用户名"-->
<!--                size="large"-->
<!--                :prefix-icon="User"-->
<!--            />-->
<!--          </el-form-item>-->

<!--          <el-form-item prop="password" class="form-item">-->
<!--            <el-input-->
<!--                v-model="form.password"-->
<!--                type="password"-->
<!--                placeholder="请输入密码"-->
<!--                size="large"-->
<!--                :prefix-icon="Lock"-->
<!--                show-password-->
<!--            />-->
<!--          </el-form-item>-->

<!--          <el-form-item class="form-item">-->
<!--            <el-checkbox v-model="form.remember">记住我</el-checkbox>-->
<!--            <el-link type="primary" :underline="false" style="float: right;">忘记密码?</el-link>-->
<!--          </el-form-item>-->

<!--          <el-form-item>-->
<!--            <el-button-->
<!--                :loading="loading"-->
<!--                type="primary"-->
<!--                size="large"-->
<!--                class="login-btn"-->
<!--                @click="handleLogin"-->
<!--            >-->
<!--              登录-->
<!--            </el-button>-->
<!--          </el-form-item>-->
<!--        </el-form>-->

<!--        <div class="third-login">-->
<!--          <div class="third-login-title">其他登录方式</div>-->
<!--          <div class="third-login-options">-->
<!--            <div class="third-login-icon" @click="handleThirdLogin('微信')">-->
<!--              <el-icon><ChatDotRound /></el-icon>-->
<!--            </div>-->
<!--            <div class="third-login-icon" @click="handleThirdLogin('QQ')">-->
<!--              <el-icon><Iphone /></el-icon>-->
<!--            </div>-->
<!--            <div class="third-login-icon" @click="handleThirdLogin('GitHub')">-->
<!--              <el-icon><Star /></el-icon>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->

<!--        <div class="copyright">-->
<!--          Copyright © 2023 企业管理系统 | 版权所有-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script setup lang="ts">-->
<!--import { ref, reactive, onUnmounted } from 'vue';-->
<!--import { ElMessage, FormInstance, FormRules } from 'element-plus';-->
<!--import { User, Lock, ChatDotRound, Iphone, Star } from '@element-plus/icons-vue';-->

<!--// 表单数据-->
<!--const form = reactive({-->
<!--  username: '',-->
<!--  password: '',-->
<!--  remember: false-->
<!--});-->

<!--// 表单引用-->
<!--const formRef = ref<FormInstance>();-->

<!--// 表单规则-->
<!--const rules = reactive<FormRules>({-->
<!--  username: [-->
<!--    { required: true, message: '请输入用户名', trigger: 'blur' },-->
<!--    { min: 3, message: '用户名长度不能少于3个字符', trigger: 'blur' }-->
<!--  ],-->
<!--  password: [-->
<!--    { required: true, message: '请输入密码', trigger: 'blur' },-->
<!--    { min: 6, message: '密码长度不能少于6个字符', trigger: 'blur' }-->
<!--  ]-->
<!--});-->

<!--// 登录加载状态-->
<!--const loading = ref(false);-->

<!--// 登录方法-->
<!--const handleLogin = () => {-->
<!--  formRef.value?.validate((valid) => {-->
<!--    if (valid) {-->
<!--      loading.value = true;-->
<!--      // 模拟登录请求-->
<!--      setTimeout(() => {-->
<!--        loading.value = false;-->
<!--        ElMessage.success('登录成功！');-->
<!--        // 这里通常会进行路由跳转-->
<!--      }, 1500);-->
<!--    } else {-->
<!--      ElMessage.error('请正确填写表单');-->
<!--      return false;-->
<!--    }-->
<!--  });-->
<!--};-->

<!--// 第三方登录-->
<!--const handleThirdLogin = (type: string) => {-->
<!--  ElMessage.info(`尝试使用${type}登录`);-->
<!--};-->

<!--// 监听回车键-->
<!--const onKeyUp = (e: KeyboardEvent) => {-->
<!--  if (e.key === 'Enter') {-->
<!--    handleLogin();-->
<!--  }-->
<!--};-->

<!--// 添加键盘监听-->
<!--window.addEventListener('keyup', onKeyUp);-->

<!--// 组件卸载时移除事件监听-->
<!--onUnmounted(() => {-->
<!--  window.removeEventListener('keyup', onKeyUp);-->
<!--});-->
<!--</script>-->

<!--<style scoped>-->
<!--.container{-->
<!--  display: flex;-->
<!--  justify-content: center;-->
<!--  align-items: center;-->
<!--  height: 100vh;-->
<!--}-->
<!--.login-main{-->
<!--  margin: 0 auto;-->
<!--  display: flex;-->
<!--  width: 80%;-->
<!--  max-width: 1000px;-->
<!--  height: 550px;-->
<!--  background: #fff;-->
<!--  border-radius: 10px;-->
<!--  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);-->
<!--  overflow: hidden;-->
<!--}-->

<!--.left-panel {-->
<!--  flex: 1;-->
<!--  background: linear-gradient(135deg, #396afc, #2948ff);-->
<!--  color: white;-->
<!--  padding: 40px;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  justify-content: center;-->
<!--  position: relative;-->
<!--  overflow: hidden;-->
<!--}-->

<!--.brand {-->
<!--  font-size: 28px;-->
<!--  font-weight: bold;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.welcome-text {-->
<!--  font-size: 18px;-->
<!--  line-height: 1.6;-->
<!--  margin-bottom: 30px;-->
<!--}-->

<!--.feature-list {-->
<!--  list-style: none;-->
<!--}-->

<!--.feature-list li {-->
<!--  margin-bottom: 15px;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--}-->

<!--.feature-list .icon {-->
<!--  margin-right: 10px;-->
<!--  background: rgba(255, 255, 255, 0.2);-->
<!--  width: 30px;-->
<!--  height: 30px;-->
<!--  border-radius: 50%;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--}-->

<!--.decoration {-->
<!--  position: absolute;-->
<!--  bottom: -50px;-->
<!--  right: -50px;-->
<!--  width: 300px;-->
<!--  height: 300px;-->
<!--  border-radius: 50%;-->
<!--  background: rgba(255, 255, 255, 0.1);-->
<!--}-->

<!--.right-panel {-->
<!--  flex: 1;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  justify-content: center;-->
<!--  align-items: center;-->
<!--  padding: 40px;-->
<!--}-->

<!--.login-title {-->
<!--  font-size: 24px;-->
<!--  color: #303133;-->
<!--  margin-bottom: 30px;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.login-form {-->
<!--  width: 100%;-->
<!--  max-width: 350px;-->
<!--}-->

<!--.form-item {-->
<!--  margin-bottom: 22px;-->
<!--}-->

<!--.login-btn {-->
<!--  width: 100%;-->
<!--  margin-top: 10px;-->
<!--}-->

<!--.third-login {-->
<!--  margin-top: 30px;-->
<!--  text-align: center;-->
<!--  width: 100%;-->
<!--}-->

<!--.third-login-title {-->
<!--  position: relative;-->
<!--  font-size: 14px;-->
<!--  color: #909399;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.third-login-title:before,-->
<!--.third-login-title:after {-->
<!--  content: "";-->
<!--  position: absolute;-->
<!--  top: 50%;-->
<!--  width: 30%;-->
<!--  height: 1px;-->
<!--  background: #dcdfe6;-->
<!--}-->

<!--.third-login-title:before {-->
<!--  left: 0;-->
<!--}-->

<!--.third-login-title:after {-->
<!--  right: 0;-->
<!--}-->

<!--.third-login-options {-->
<!--  display: flex;-->
<!--  justify-content: center;-->
<!--  gap: 15px;-->
<!--}-->

<!--.third-login-icon {-->
<!--  width: 40px;-->
<!--  height: 40px;-->
<!--  border-radius: 50%;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--  cursor: pointer;-->
<!--  border: 1px solid #dcdfe6;-->
<!--  color: #606266;-->
<!--  transition: all 0.3s;-->
<!--}-->

<!--.third-login-icon:hover {-->
<!--  background: #f5f7fa;-->
<!--  transform: translateY(-3px);-->
<!--}-->

<!--.copyright {-->
<!--  margin-top: 30px;-->
<!--  font-size: 12px;-->
<!--  color: #909399;-->
<!--  text-align: center;-->
<!--}-->

<!--/* 响应式设计 */-->
<!--@media (max-width: 768px) {-->
<!--  .login-container {-->
<!--    flex-direction: column;-->
<!--    height: auto;-->
<!--    width: 90%;-->
<!--  }-->

<!--  .left-panel {-->
<!--    display: none;-->
<!--  }-->

<!--  .right-panel {-->
<!--    padding: 30px 20px;-->
<!--  }-->
<!--}-->

<!--</style>-->
