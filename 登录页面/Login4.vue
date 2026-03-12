<template>
  <div class="dark-login">
    <div class="dark-background">
      <div class="stars"></div>
      <div class="twinkling"></div>
    </div>

    <div class="login-container">
      <div class="dark-card">
        <div class="app-brand">
          <el-icon size="36"><Moon /></el-icon>
          <h1>系统登录</h1>
        </div>

        <el-form :model="form" :rules="rules" ref="formRef" class="login-form">
          <el-form-item prop="username">
            <el-input
                v-model="form.username"
                placeholder="用户名"
                size="large"
                :prefix-icon="User"
                class="dark-input"
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
                class="dark-input"
            />
          </el-form-item>

          <el-form-item>
            <el-button
                type="primary"
                size="large"
                class="login-btn dark-btn"
                :loading="loading"
                @click="handleLogin"
            >
              登录
            </el-button>
          </el-form-item>
        </el-form>

        <div class="dark-footer">
          <el-link type="info" :underline="false">忘记密码</el-link>
          <el-link type="info" :underline="false">注册账号</el-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage, FormInstance, FormRules } from 'element-plus'
import { User, Lock, Moon } from '@element-plus/icons-vue'

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
.dark-login {
  min-height: 100vh;
  position: relative;
  color: #fff;
}

.dark-background {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
  overflow: hidden;
}

.stars, .twinkling {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  width: 100%;
  height: 100%;
  display: block;
}

.stars {
  background: #000 url('https://assets.codepen.io/1231630/stars.png') repeat top center;
  z-index: 0;
}

.twinkling {
  background: transparent url('https://assets.codepen.io/1231630/twinkling.png') repeat top center;
  z-index: 1;
  animation: move-twink-back 200s linear infinite;
}

@keyframes move-twink-back {
  from { background-position: 0 0; }
  to { background-position: -10000px 5000px; }
}

.login-container {
  position: relative;
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}

.dark-card {
  background: rgba(25, 30, 40, 0.85);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  padding: 40px;
  border-radius: 16px;
  width: 100%;
  max-width: 420px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
}

.app-brand {
  text-align: center;
  margin-bottom: 35px;
}

.app-brand h1 {
  margin-top: 15px;
  font-weight: 500;
  font-size: 24px;
}

.login-form {
  margin-bottom: 20px;
}

.dark-input :deep(.el-input__wrapper) {
  background: rgba(255, 255, 255, 0.08);
  box-shadow: 0 0 0 1px rgba(255, 255, 255, 0.1);
}

.dark-input :deep(.el-input__wrapper:hover) {
  box-shadow: 0 0 0 1px rgba(255, 255, 255, 0.2);
}

.dark-input :deep(.el-input__inner) {
  color: #fff;
}

.dark-input :deep(.el-input__prefix-inner .el-icon) {
  color: rgba(255, 255, 255, 0.5);
}

.dark-btn {
  width: 100%;
  background: linear-gradient(45deg, #409eff, #3375e0);
  border: none;
  margin-top: 10px;
}

.dark-footer {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}
</style>
