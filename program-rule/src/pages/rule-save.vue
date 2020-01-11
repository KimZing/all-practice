<template>
  <div>
    <el-form ref="ruleInfo" :model="ruleInfo" label-width="80px">
      <el-form-item label="规则类型">
        <el-select v-bind:disabled="!isSave" v-model="ruleInfo.type" placeholder="请选择规则">
          <el-option
            v-for="item in params.RULE_TYPE.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item
        v-if="ruleInfo.type == 'APP' || ruleInfo.type == 'SDK' || ruleInfo.type == 'USER_ID'"
        label="项目名称"
      >
        <el-select v-bind:disabled="!isSave" v-model="ruleInfo.param.projectId" placeholder="请选择项目">
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
      <el-form-item
        v-if="ruleInfo.type == 'APP' || ruleInfo.type == 'SDK' || ruleInfo.type == 'USER_ID'"
        label="应用名称"
      >
        <el-select v-bind:disabled="!isSave" v-model="ruleInfo.param.appId" placeholder="请选择应用">
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
      <el-form-item v-if="ruleInfo.type == 'SDK'" label="平台类型">
        <el-select v-bind:disabled="!isSave" v-model="ruleInfo.param.platform" placeholder="请选择平台">
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
      <el-form-item v-if="ruleInfo.type == 'SDK'" label="终端版本">
        <el-select
          v-bind:disabled="!isSave"
          v-model="ruleInfo.param.sdkVersion"
          placeholder="请选择版本"
        >
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
      <el-form-item v-if="ruleInfo.type == 'DEVICE_ID'" label="设备号">
        <el-input
          v-bind:disabled="!isSave"
          v-model="ruleInfo.param.deviceId"
          placeholder="QG123456"
        ></el-input>
      </el-form-item>
      <el-form-item v-if="ruleInfo.type == 'PDSN'" label="车机号">
        <el-input v-bind:disabled="!isSave" v-model="ruleInfo.param.pdsn" placeholder="P1000111"></el-input>
      </el-form-item>
      <el-form-item v-if="ruleInfo.type == 'USER_ID'" label="用户ID">
        <el-input v-bind:disabled="!isSave" v-model="ruleInfo.param.userId" placeholder="0"></el-input>
      </el-form-item>
      <el-form-item label="TTS">
        <el-select v-model="ruleInfo.connection.tts" placeholder="语音服务">
          <el-option
            v-for="item in params.TTS.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="TRACK">
        <el-select v-model="ruleInfo.connection.track" placeholder="数据上报地址">
          <el-option
            v-for="item in params.TRACK.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="QINGAI">
        <el-select v-model="ruleInfo.connection.qingai" placeholder="网关地址">
          <el-option
            v-for="item in params.QINGAI.values"
            :key="item.value + item.tag"
            :label="item.tag"
            :value="item.value"
          >
            <span style="float: left">{{ item.value }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="规则说明">
        <el-input type="textarea" :rows="3" placeholder="添加规则说明" v-model="ruleInfo.remark"></el-input>
      </el-form-item>
      <el-collapse v-if="ruleInfo.type == 'APP'" accordion>
        <el-collapse-item>
          <template slot="title">灰度策略配置</template>
          <el-form-item label="灰度策略">
            <el-select v-model="ruleInfo.grayPolicy" placeholder="请选择灰度类型">
              <el-option
                v-for="item in params.GRAY_POLICY.values"
                :key="item.value + item.tag"
                :label="item.tag"
                :value="item.value"
              >
                <span style="float: left">{{ item.value }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="灰度值">
            <el-input v-model="ruleInfo.grayValue" placeholder="根据不同灰度策略进行定义"></el-input>
          </el-form-item>
          <el-form-item label="灰度TTS">
            <el-select v-model="ruleInfo.grayConnection.grayTts" placeholder="灰度语音服务地址">
              <el-option
                v-for="item in params.TTS.values"
                :key="item.value + item.tag"
                :label="item.tag"
                :value="item.value"
              >
                <span style="float: left">{{ item.value }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="灰度TRACK">
            <el-select v-model="ruleInfo.grayConnection.grayTrack" placeholder="灰度数据上报地址">
              <el-option
                v-for="item in params.TRACK.values"
                :key="item.value + item.tag"
                :label="item.tag"
                :value="item.value"
              >
                <span style="float: left">{{ item.value }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="灰度QINGAI">
            <el-select v-model="ruleInfo.grayConnection.grayQingai" placeholder="灰度网关地址">
              <el-option
                v-for="item in params.QINGAI.values"
                :key="item.value + item.tag"
                :label="item.tag"
                :value="item.value"
              >
                <span style="float: left">{{ item.value }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.tag }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-collapse-item>
      </el-collapse>
      <el-form-item>
        <el-button v-if="isSave" type="primary" @click="onSave">添加规则</el-button>
        <el-button v-if="!isSave" type="primary" @click="onUpdate">更新规则</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "rule-save",
  data() {
    return {
      ruleInfo: {
        id: this.$route.params.id,
        type: this.$route.params.type,
        param: {
          projectId: this.$route.params.projectId,
          appId: this.$route.params.appId,
          platform: this.$route.params.platform,
          sdkVersion: this.$route.params.sdkVersion,
          deviceId: this.$route.params.deviceId,
          pdsn: this.$route.params.pdsn,
          userId: this.$route.params.userId
        },
        connection: {
          tts: this.$route.params.tts,
          track: this.$route.params.track,
          qingai: this.$route.params.qingai
        },
        grayPolicy: this.$route.params.grayPolicy,
        grayValue: this.$route.params.grayValue,
        grayConnection: {
          grayTts: this.$route.params.grayTts,
          grayTrack: this.$route.params.grayTrack,
          grayQingai: this.$route.params.grayQingai
        },
        remark: this.$route.params.remark
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
      }
    };
  },
  computed: {
    isSave() {
      return this.$route.params.id == null;
    }
  },
  created() {
    this.init();
  },
  methods: {
    onSave() {
      this.ruleInfo.ts = +new Date();
      this.axios.put("/manager/rule", this.ruleInfo).then(response => {
        let res = response.data;
        if (res.code == 0) {
          this.$notify({
            title: "添加成功",
            message: "规则添加成功",
            type: "success"
          });
        } else {
          this.$notify.error({
            title: "获取失败",
            message: res.msg
          });
        }
      });
    },
    onUpdate() {
      this.ruleInfo.ts = +new Date();
      this.axios
        .put(`/manager/rule/${this.ruleInfo.id}`, this.ruleInfo)
        .then(response => {
          let res = response.data;
          if (res.code == 0) {
            this.$notify({
              title: "更新成功",
              message: "更新规则成功",
              type: "success"
            });
          } else {
            this.$notify.error({
              title: "更新",
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