<template>
  <div class="floating-login-container">
    <div class="floating-login">
      <!-- 左侧视觉区域 -->
      <div class="visual-side">
        <div class="visual-content">
          <div class="app-logo">
            <div class="icon">
              <el-icon><User /></el-icon>
            </div>
            <h1>创意登录系统</h1>
          </div>

          <ul class="feature-list">
            <li>
              <span class="check"><el-icon><Check /></el-icon></span>
              安全可靠的账号保护
            </li>
            <li>
              <span class="check"><el-icon><Check /></el-icon></span>
              多平台同步支持
            </li>
            <li>
              <span class="check"><el-icon><Check /></el-icon></span>
              个性化界面定制
            </li>
          </ul>
        </div>

        <div class="decoration"></div>
        <div class="decoration decoration-2"></div>
      </div>

      <!-- 右侧表单区域 -->
      <div class="form-side">
        <div class="form-container">
          <div class="form-header">
            <h2>欢迎回来</h2>
            <p>请输入您的账号信息登录系统</p>
          </div>

          <el-form
              ref="formRef"
              :model="form"
              :rules="rules"
              class="login-form"
          >
            <el-form-item prop="username" class="form-item">
              <el-input
                  v-model="form.username"
                  placeholder="用户名或邮箱"
                  size="large"
                  :prefix-icon="User"
              />
            </el-form-item>

            <el-form-item prop="password" class="form-item">
              <el-input
                  v-model="form.password"
                  type="password"
                  placeholder="密码"
                  size="large"
                  :prefix-icon="Lock"
                  show-password
              />
            </el-form-item>

            <el-form-item>
              <el-button
                  :loading="loading"
                  type="primary"
                  size="large"
                  class="login-btn"
                  @click="handleLogin"
              >
                登录
              </el-button>
            </el-form-item>
          </el-form>

          <div class="alternative-login">
            <div class="divider">
              <span>或使用其他方式登录</span>
            </div>

            <div class="social-login">
              <div class="social-btn google" @click="handleSocialLogin('Google')">
                <el-icon><Star /></el-icon>
              </div>
              <div class="social-btn facebook" @click="handleSocialLogin('Facebook')">
                <el-icon><ChatDotRound /></el-icon>
              </div>
              <div class="social-btn github" @click="handleSocialLogin('GitHub')">
                <el-icon><Iphone /></el-icon>
              </div>
            </div>
          </div>

          <div class="form-footer">
            <p>没有账号? <a href="#">立即注册</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onUnmounted } from 'vue'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { User, Lock, ChatDotRound, Iphone, Star, Check } from '@element-plus/icons-vue'

// 表单数据
const form = reactive({
  username: '',
  password: '',
  remember: false
})

// 表单引用
const formRef = ref<FormInstance>()

// 表单规则
const rules = reactive<FormRules>({
  username: [
    { required: true, message: '请输入用户名或邮箱', trigger: 'blur' },
    { min: 3, message: '用户名长度不能少于3个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能少于6个字符', trigger: 'blur' }
  ]
})

// 登录加载状态
const loading = ref(false)

// 登录方法
const handleLogin = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      loading.value = true
      // 模拟登录请求
      setTimeout(() => {
        loading.value = false
        ElMessage.success('登录成功！')
        // 这里通常会进行路由跳转
      }, 1500)
    } else {
      ElMessage.error('请正确填写表单')
      return false
    }
  })
}

// 第三方登录
const handleSocialLogin = (provider: string) => {
  ElMessage.info(`正在使用${provider}登录`)
}

// 监听回车键
const onKeyUp = (e: KeyboardEvent) => {
  if (e.key === 'Enter') {
    handleLogin()
  }
}

// 添加键盘监听
onMounted(() => {
  window.addEventListener('keyup', onKeyUp)
})

// 移除键盘监听
onUnmounted(() => {
  window.removeEventListener('keyup', onKeyUp)
})
</script>

<style scoped>
.floating-login-container {
  font-family: 'Inter', 'Helvetica Neue', Helvetica, Arial, sans-serif;
  background: linear-gradient(135deg, #6e8efb, #a777e3);
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.floating-login {
  display: flex;
  width: 90%;
  max-width: 1000px;
  background: rgba(255, 255, 255, 0.15);
  backdrop-filter: blur(10px);
  border-radius: 24px;
  box-shadow: 0 8px 32px rgba(31, 38, 135, 0.2);
  border: 1px solid rgba(255, 255, 255, 0.18);
  overflow: hidden;
  position: relative;
  animation: float 8s ease-in-out infinite;
}

.visual-side {
  flex: 1;
  background: linear-gradient(45deg, rgba(110, 142, 251, 0.8), rgba(167, 119, 227, 0.8));
  color: white;
  padding: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  position: relative;
  overflow: hidden;
}

.visual-content {
  position: relative;
  z-index: 2;
}

.app-logo {
  display: flex;
  align-items: center;
  margin-bottom: 30px;
}

.app-logo .icon {
  width: 50px;
  height: 50px;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 15px;
}

.app-logo h1 {
  font-size: 28px;
  font-weight: 700;
}

.feature-list {
  list-style: none;
  margin-top: 40px;
}

.feature-list li {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  font-weight: 500;
}

.feature-list .check {
  width: 24px;
  height: 24px;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 15px;
}

.decoration {
  position: absolute;
  width: 250px;
  height: 250px;
  border-radius: 50%;
  background: linear-gradient(45deg, rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0.05));
  right: -50px;
  bottom: -50px;
}

.decoration-2 {
  width: 150px;
  height: 150px;
  top: -50px;
  left: -50px;
  background: linear-gradient(45deg, rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0.05));
}

.form-side {
  flex: 1;
  background: white;
  padding: 50px 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.form-container {
  max-width: 360px;
  margin: 0 auto;
  width: 100%;
}

.form-header {
  margin-bottom: 35px;
}

.form-header h2 {
  font-size: 24px;
  color: #1a1a1a;
  margin-bottom: 10px;
  font-weight: 700;
}

.form-header p {
  color: #666;
  font-size: 15px;
}

.login-form {
  margin-bottom: 25px;
}

.form-item {
  margin-bottom: 22px;
}

:deep(.el-input__wrapper) {
  border-radius: 12px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
}

:deep(.el-input__wrapper.is-focus) {
  box-shadow: 0 0 0 1px #6e8efb;
}

.login-btn {
  width: 100%;
  border-radius: 12px;
  height: 48px;
  font-weight: 600;
  background: linear-gradient(45deg, #6e8efb, #a777e3);
  border: none;
  margin-top: 10px;
}

.alternative-login {
  text-align: center;
  margin-top: 30px;
}

.divider {
  display: flex;
  align-items: center;
  margin: 25px 0;
  color: #999;
  font-size: 14px;
}

.divider::before,
.divider::after {
  content: "";
  flex: 1;
  height: 1px;
  background: #eee;
}

.divider::before {
  margin-right: 15px;
}

.divider::after {
  margin-left: 15px;
}

.social-login {
  display: flex;
  justify-content: center;
  gap: 15px;
}

.social-btn {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #e0e0e0;
  background: white;
  cursor: pointer;
  transition: all 0.3s;
}

.social-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.social-btn.google {
  color: #DB4437;
}

.social-btn.facebook {
  color: #4267B2;
}

.social-btn.github {
  color: #333;
}

.form-footer {
  text-align: center;
  margin-top: 30px;
  color: #666;
  font-size: 14px;
}

.form-footer a {
  color: #6e8efb;
  text-decoration: none;
  font-weight: 500;
}

/* 响应式设计 */
@media (max-width: 900px) {
  .floating-login {
    flex-direction: column;
  }

  .visual-side {
    display: none;
  }

  .form-side {
    padding: 40px 30px;
  }
}

@media (max-width: 480px) {
  .form-side {
    padding: 30px 20px;
  }

  .form-header h2 {
    font-size: 22px;
  }
}

/* 动画效果 */
@keyframes float {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
}
</style>
