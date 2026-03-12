<template>
  <div class="card-login">
    <div class="cards-container">
      <div class="card main-card">
        <div class="card-header">
          <h2>登录您的账户</h2>
          <p>输入您的凭据以访问您的帐户</p>
        </div>

        <el-form :model="form" :rules="rules" ref="formRef" class="login-form">
          <el-form-item prop="username">
            <el-input
                v-model="form.username"
                placeholder="用户名或邮箱"
                size="large"
                :prefix-icon="User"
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
            />
          </el-form-item>

          <el-form-item>
            <div class="remember-forgot">
              <el-checkbox v-model="form.remember">记住我</el-checkbox>
              <el-link type="primary" :underline="false">忘记密码?</el-link>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button
                type="primary"
                size="large"
                class="login-btn"
                :loading="loading"
                @click="handleLogin"
            >
              登录
            </el-button>
          </el-form-item>
        </el-form>

        <div class="card-footer">
          <p>还没有账户? <el-link type="primary" :underline="false">创建账户</el-link></p>
        </div>
      </div>

      <div class="card feature-card">
        <div class="feature-content">
          <el-icon size="48"><Setting /></el-icon>
          <h3>完全控制</h3>
          <p>管理您的所有账户和设置</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage, FormInstance, FormRules } from 'element-plus'
import { User, Lock, Setting } from '@element-plus/icons-vue'

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
.card-login {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f7fa;
  padding: 20px;
}

.cards-container {
  display: flex;
  max-width: 900px;
  width: 100%;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
  border-radius: 16px;
  overflow: hidden;
}

.card {
  padding: 40px;
  display: flex;
  flex-direction: column;
}

.main-card {
  flex: 1;
  background: white;
}

.feature-card {
  flex: 0 0 40%;
  background: linear-gradient(135deg, #396afc, #2948ff);
  color: white;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.card-header {
  text-align: center;
  margin-bottom: 30px;
}

.card-header h2 {
  color: #303133;
  margin-bottom: 10px;
  font-weight: 600;
}

.card-header p {
  color: #909399;
  margin: 0;
}

.login-form {
  margin-bottom: 20px;
}

.remember-forgot {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.login-btn {
  width: 100%;
  border-radius: 8px;
  margin-top: 10px;
}

.card-footer {
  text-align: center;
  margin-top: auto;
  padding-top: 20px;
  color: #606266;
}

.feature-content h3 {
  margin: 20px 0 10px;
  font-weight: 500;
}

.feature-content p {
  margin: 0;
  opacity: 0.9;
}

@media (max-width: 768px) {
  .cards-container {
    flex-direction: column;
  }

  .feature-card {
    order: -1;
    padding: 30px;
  }
}
</style>
