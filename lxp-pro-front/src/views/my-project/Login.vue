<template>
  <body id="form">
    <el-form class="login_form">
      <el-form-item>
        <h3 class="login_title">欢迎登陆</h3>
      </el-form-item>
      <el-form-item>
        <el-input v-model="loginForm.username" type="text" placeholder="用户名" />
      </el-form-item>
      <el-form-item>
        <el-input v-model="loginForm.password" type="password" placeholder="密码" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="medium" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>

export default {
  name: 'Login',
  components: {},
  data() {
    return {
      loginForm: {
        username: 'admin',
        password: '123456'

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

<style>
  body{
    margin: 0px;
  }
  #form{
    background-image: url("../../../public/login_bg.jpg");
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  .login_form{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 200px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 10px auto;
    text-align: center;
    color: #505458;
  }
</style>
