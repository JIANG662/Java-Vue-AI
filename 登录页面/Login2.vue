<template>
  <div class="gradient-login">
    <div class="login-wrapper">
      <div class="login-content">
        <div class="welcome-section">
          <h1>欢迎回来</h1>
          <p>请输入您的账号信息登录系统</p>
        </div>

        <el-form :model="form" :rules="rules" ref="formRef" class="login-form">
          <el-form-item prop="username">
            <el-input
                v-model="form.username"
                placeholder="用户名"
                size="large"
                :prefix-icon="User"
                class="gradient-input"
            />
          </el-form-item>

          <el-form-item prop="password">
            <el-input
                v-model="form.password"
                type="password"
                placeholder="密码"
                size="large"
                :prefix-icon="Lock"
                show-password
                class="gradient-input"
            />
          </el-form-item>

          <el-form-item>
            <el-button
                type="primary"
                size="large"
                class="login-btn gradient-btn"
                :loading="loading"
                @click="handleLogin"
            >
              登录
            </el-button>
          </el-form-item>
        </el-form>

        <div class="extra-options">
          <el-checkbox v-model="form.remember">记住密码</el-checkbox>
          <el-link type="primary" :underline="false">忘记密码?</el-link>
        </div>

        <div class="social-login-section">
          <p class="divider">或使用社交账号登录</p>
          <div class="social-buttons">
            <el-button circle class="social-btn wechat">
              <el-icon><ChatDotRound /></el-icon>
            </el-button>
            <el-button circle class="social-btn qq">
              <el-icon><Iphone /></el-icon>
            </el-button>
            <el-button circle class="social-btn github">
              <el-icon><Star /></el-icon>
            </el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage, FormInstance, FormRules } from 'element-plus'
import { User, Lock, ChatDotRound, Iphone, Star } from '@element-plus/icons-vue'

const form = reactive({
  username: '',
  password: '',
  remember: false
})

const formRef = ref<FormInstance>()
const loading = ref(false)

const rules = reactive<FormRules>({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
})

const handleLogin = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      loading.value = true
      setTimeout(() => {
        loading.value = false
        ElMessage.success('登录成功')
      }, 1500)
    }
  })
}
</script>

<style scoped>
.gradient-login {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.login-wrapper {
  width: 100%;
  max-width: 480px;
}

.login-content {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
}

.welcome-section {
  text-align: center;
  margin-bottom: 35px;
}

.welcome-section h1 {
  color: #303133;
  margin-bottom: 10px;
  font-weight: 600;
}

.welcome-section p {
  color: #606266;
  margin: 0;
}

.login-form {
  margin-bottom: 20px;
}

.gradient-input :deep(.el-input__wrapper) {
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.gradient-input :deep(.el-input__wrapper.is-focus) {
  box-shadow: 0 0 0 1px #409eff;
}

.gradient-btn {
  width: 100%;
  background: linear-gradient(45deg, #667eea, #764ba2);
  border: none;
  border-radius: 10px;
  margin-top: 10px;
  font-weight: 500;
}

.extra-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.social-login-section {
  text-align: center;
}

.divider {
  position: relative;
  color: #909399;
  margin-bottom: 20px;
}

.divider:before {
  content: "";
  position: absolute;
  left: 0;
  top: 50%;
  width: 100%;
  height: 1px;
  background: #e4e7ed;
}

.divider {
  background: white;
  padding: 0 15px;
  position: relative;
  display: inline-block;
}

.social-buttons {
  display: flex;
  justify-content: center;
  gap: 15px;
}

.social-btn {
  width: 50px;
  height: 50px;
  font-size: 18px;
}

.social-btn.wechat {
  color: #2aae67;
  border-color: #2aae67;
}

.social-btn.qq {
  color: #12b7f5;
  border-color: #12b7f5;
}

.social-btn.github {
  color: #333;
  border-color: #333;
}
</style>
