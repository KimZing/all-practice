<template>
  <div class="login-container">
    <el-form
      :model="loginForm"
      :rules="rules"
      status-icon
      ref="loginForm"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-page"
    >
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox v-model="checked" class="rememberme">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="login" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  name: "manager-login",
  data() {
    return {
      logining: false,
      loginForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          {
            required: true,
            message: "please enter your account",
            trigger: "blur"
          }
        ],
        password: [
          { required: true, message: "enter your password", trigger: "blur" }
        ]
      },
      checked: false
    };
  },
  methods: {
    login() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.logining = true;

          this.axios.post("/login", this.loginForm).then(response => {
            let res = response.data;
            if (res.code == 0) {
              this.logining = false;
              sessionStorage.setItem("username", res.data.username);
              sessionStorage.setItem("role", res.data.role);
              sessionStorage.setItem("token", res.data.token);
              this.$router.push({ path: "/rule/query" });
            } else {
              this.logining = false;
              this.$alert("username or password wrong!", "info", {
                confirmButtonText: "ok"
              });
            }
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>