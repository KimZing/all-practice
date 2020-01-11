<template>
  <div>
    <el-form ref="info" :model="info" label-width="80px">
      <el-form-item label="项目名称">
        <el-select v-model="info.projectId" placeholder="请选择项目">
          <el-option
            v-for="item in params.PROJECT_ID.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="应用名称">
        <el-select v-model="info.appId" placeholder="请选择应用">
          <el-option
            v-for="item in params.APP_ID.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="平台类型">
        <el-select v-model="info.platform" placeholder="请选择平台">
          <el-option
            v-for="item in params.PLATFORM.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="终端版本">
        <el-select v-model="info.sdkVersion" placeholder="请选择版本">
          <el-option
            v-for="item in params.SDK_VERSION.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备号">
        <el-input v-model="info.deviceId" placeholder="QG123456"></el-input>
      </el-form-item>
      <el-form-item label="车机号">
        <el-input v-model="info.pdsn" placeholder="P1000111"></el-input>
      </el-form-item>
      <el-form-item label="用户ID">
        <el-input v-model="info.userId" placeholder="0"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">获取连接</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="提示" :visible.sync="centerDialogVisible" center class="connection">
      <table class="table">
        <tr v-for="(v,k) in connectionInfo" :key="k">
          <td>{{k}}</td>
          <td>{{v}}</td>
        </tr>
      </table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "manager-device",
  props: {},
  data() {
    return {
      info: {
        projectId: "h6n",
        appId: "h6n",
        platform: "IOS",
        sdkVersion: "1.0",
        deviceId: "QG123456",
        pdsn: "P1000111",
        userId: "0"
      },
      params: {
        TRACK: {},
        APP_ID: {},
        PROJECT_ID: {},
        GRAY_POLICY: {},
        RULE_TYPE: {},
        SDK_VERSION: {},
        TTS: {},
        QINGAI: {},
        PLATFORM: {}
      },
      connectionInfo: {},
      sign: "pateo1234567890abcdef",
      centerDialogVisible: false
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    onSubmit() {
      let connectionInfo = {
        info: this.info,
        sign: this.sign,
        ts: +new Date()
      };
      this.axios.post("/connection/info", connectionInfo).then(response => {
        let res = response.data;
        if (res.code == 0) {
          this.connectionInfo = res.data;
          this.$notify({
            title: "获取成功",
            message: "获取模拟设备连接成功",
            type: "success"
          });
          this.centerDialogVisible = true;
        } else {
          this.$notify.error({
            title: "获取失败",
            message: res.msg
          });
        }
      });
    },
    init() {
      this.axios.get(`/manager/param/list`).then(response => {
        let res = response.data;
        if (res.code == 0) {
          this.params = res.data;
        } else {
          this.$notify.error({
            title: "系统异常",
            message: "获取参数信息失败"
          });
        }
      });
    }
  }
};
</script>
<style>
.connection .table {
  width: 100%;
  font-size: 16px;
  line-height: 35px;
  border-collapse: collapse;
}

.connection .table > tr {
  border-bottom: 1px solid #ebeef5;
}

.connection .table tr td:nth-child(2) {
  padding: 5px;
  padding-left: 10px;
}
</style>