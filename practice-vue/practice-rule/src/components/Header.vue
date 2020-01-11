<template>
  <div class="system-header">
    <label>{{title}}-{{profile}}</label>
    <div class="login-box">
      <i size="middle" class="el-icon-user">{{username == null ? "游客": username}}</i>
      <el-button @click="destory" type="info" v-if="username">
        注销
        <i class="el-icon-upload el-icon--right"></i>
      </el-button>
      <el-button @click="toLogin" type="info" v-if="!username">
        登录
        <i class="el-icon-upload el-icon--right"></i>
      </el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: "manager-header",
  props: {},
  data() {
    return {
      profile: process.env.VUE_APP_PROFILE,
      title: process.env.VUE_APP_SYSTEM_NAME,
      username: sessionStorage.getItem("username")
    };
  },
  methods: {
    toLogin() {
      this.$router.push({ path: "/" });
    },
    destory() {
      sessionStorage.clear();
      this.$notify({
        title: "注销成功",
        message: "切换为游客账户",
        type: "success"
      });
      this.$router.push({ path: "/device" });
      this.$router.go(0);
    }
  }
};
</script>