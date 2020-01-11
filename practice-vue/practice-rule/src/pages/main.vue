<template>
  <el-container>
    <el-header>
      <manager-header></manager-header>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <manager-aside></manager-aside>
      </el-aside>
      <el-container>
        <el-main>
          <router-view />
        </el-main>
        <el-footer>
          <manager-footer></manager-footer>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import ManagerHeader from "../components/Header";
import ManagerFooter from "../components/Footer";
import ManagerAside from "../components/Aside";

export default {
  name: "manager-main",
  components: {
    ManagerHeader,
    ManagerFooter,
    ManagerAside
  }
};
</script>