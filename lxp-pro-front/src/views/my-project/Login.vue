<template>
  <div>
    <el-input v-model="loginForm.username" placeholder="请输入用户名" />
    <br><br>
    <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" />
    <br><br>
    <el-button type="primary">登录</el-button>
  </div>
</template>

<script>

export default {
  name: 'Login',
  components: {},
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login() {
      this.$axios
        .post('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$router.replace({ path: '/appindex' })
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

