<template>
  <div>
    <el-table :data="ruleList" stripe border fit style="width: 100%">
      <el-table-column fixed prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="type" label="类型" width="120"></el-table-column>
      <el-table-column prop="projectId" label="项目名称" width="80"></el-table-column>
      <el-table-column prop="appId" label="应用名称" width="80"></el-table-column>
      <el-table-column prop="status" label="升级状态" width="80"></el-table-column>
      <el-table-column prop="nlg" label="升级话术" width="200"></el-table-column>
      <el-table-column label="当前连接" width="100">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>TTS: {{ scope.row.tts }}</p>
            <p>TRACK: {{ scope.row.track }}</p>
            <p>QINGAI: {{ scope.row.qingai }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">当前地址</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="灰度连接" width="100">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>TTS: {{ scope.row.grayTts }}</p>
            <p>TRACK: {{ scope.row.grayTrack }}</p>
            <p>QINGAI: {{ scope.row.grayQingai }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">灰度地址</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            v-if="scope.row.status=='NORMAL'"
            @click="preUpdate(scope.row)"
          >标记升级</el-button>
          <el-button
            size="mini"
            v-if="scope.row.status=='UPGRADE'"
            type="danger"
            @click="unupgrade(scope.$index, scope.row)"
          >标记正常</el-button>
          <el-dialog v-bind:modal="false" title="提示" :visible.sync="dialogVisible" width="30%">
            <el-input v-model="nlg" placeholder="请输入升级话术"></el-input>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="upgrade()">确 定</el-button>
            </span>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "upgrade",
  props: {},
  data() {
    return {
      dialogVisible: false,
      pageNum: 1,
      pageSize: 100,
      total: 0,
      ruleList: [],
      nlg: "",
      typeId: ""
    };
  },
  mounted() {
    this.axios
      .post(
        `/manager/rule/list?pageNum=${this.pageNum}&pageSize=${this.pageSize}`,
        { type: "APP" }
      )
      .then(response => {
        let result = response.data;
        this.pageNum = result.data.pageNum;
        this.pageSize = result.data.pageSize;
        this.total = result.data.total;
        this.ruleList = result.data.data;
      });
  },
  methods: {
    unupgrade(index, row) {
      this.axios
        .put(`/manager/rule/${row.typeId}/NORMAL/${row.nlg}`)
        .then(response => {
          if (response.data.code == 0) {
            this.$router.go(0);
            this.$router.push({
              path: "/upgrade"
            });

            this.$notify({
              title: "取消升级成功",
              message: "取消升级成功",
              type: "success"
            });
          } else {
            this.$notify.error({
              title: "取消升级失败",
              message: response.data.msg
            });
          }
        });
    },
    preUpdate(row) {
      this.dialogVisible = true;
      this.typeId = row.typeId;
    },
    upgrade() {
      this.axios
        .put(`/manager/rule/${this.typeId}/UPGRADE/${this.nlg}`)
        .then(response => {
          if (response.data.code == 0) {
            this.$router.go(0);
            this.$router.push({
              path: "/upgrade"
            });

            this.$notify({
              title: "标记升级成功",
              message: "标记升级成功",
              type: "success"
            });
          } else {
            this.$notify.error({
              title: "标记升级失败",
              message: response.data.msg
            });
          }
        });
    }
  }
};
</script>