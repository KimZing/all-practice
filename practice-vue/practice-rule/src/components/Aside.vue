<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    @select="handleSelect"
    background-color="#20222A"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <el-submenu v-if="username" index="1">
      <template v-slot:title>
        <i class="el-icon-setting"></i>
        <span>规则管理</span>
      </template>
      <el-menu-item index="查询规则">查询规则</el-menu-item>
      <el-menu-item index="添加规则">添加规则</el-menu-item>
    </el-submenu>
    <el-menu-item v-if="username" index="升级管理">
      <i class="el-icon-upload"></i>
      <span slot="title">升级管理</span>
    </el-menu-item>
    <el-menu-item v-if="username" index="参数配置">
      <i class="el-icon-menu"></i>
      <span slot="title">参数配置</span>
    </el-menu-item>
    <el-menu-item index="设备模拟">
      <i class="el-icon-help"></i>
      <span slot="title">设备模拟</span>
    </el-menu-item>
  </el-menu>
</template>
<script>
export default {
  name: "manager-aside",
  props: {},
  data() {
    return {
      username: sessionStorage.getItem("username")
    };
  },
  methods: {
    handleSelect(index) {
      if (index == "查询规则") {
        this.$router
          .push({
            path: "/rule/query"
          })
          .catch(err => {
            err;
          });
      }
      if (index == "添加规则") {
        this.$router
          .push({
            path: "/rule/save"
          })
          .catch(err => {
            err;
          });
      }

      if (index == "升级管理") {
        this.$router
          .push({
            path: "/upgrade"
          })
          .catch(err => {
            err;
          });
      }
      if (index == "参数配置") {
        this.$router
          .push({
            path: "/param"
          })
          .catch(err => {
            err;
          });
      }
      if (index == "设备模拟") {
        this.$router
          .push({
            path: "/device"
          })
          .catch(err => {
            err;
          });
      }
    }
  }
};
</script>