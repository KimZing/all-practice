<template>
  <div>
    <el-table :data="ruleList" stripe border fit style="width: 100%">
      <!-- <el-table-column fixed prop="id" label="ID"></el-table-column> -->
      <el-table-column prop="type" label="类型"></el-table-column>
      <el-table-column prop="projectId" label="项目名称"></el-table-column>
      <el-table-column prop="appId" label="应用名称"></el-table-column>
      <el-table-column prop="platform" label="平台类型"></el-table-column>
      <el-table-column prop="sdkVersion" label="终端版本"></el-table-column>
      <el-table-column prop="deviceId" label="设备号"></el-table-column>
      <el-table-column prop="pdsn" label="车机号"></el-table-column>
      <el-table-column prop="userId" label="用户ID"></el-table-column>
      <el-table-column label="当前连接">
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
      <el-table-column prop="grayPolicy" label="灰度策略"></el-table-column>
      <el-table-column prop="grayValue" label="灰度值"></el-table-column>
      <el-table-column label="灰度连接">
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
      <el-table-column prop="remark" label="规则说明"></el-table-column>

      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="preDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>确认删除吗</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleDelete">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "rule-qury",
  props: {},
  data() {
    return {
      pageNum: 1,
      pageSize: 10,
      total: 0,
      ruleList: [],
      deleteId: "",
      deleteTypeId: "",
      dialogVisible: false
    };
  },
  mounted() {
    this.axios
      .post(
        `/manager/rule/list?pageNum=${this.pageNum}&pageSize=${this.pageSize}`,
        {}
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
    handleEdit(index, row) {
      // 只能使用name进行params传参
      this.$router.push({
        name: "RuleSavePage",
        params: row
      });
    },
    preDelete(index, row) {
      this.deleteId = row.id;
      this.deleteTypeId = row.typeId;
      this.dialogVisible = true;
    },
    handleDelete() {
      this.ruleList.forEach((element, index) => {
        if (element.id == this.deleteId) {
          this.ruleList.splice(index, 1);
        }
      });
      this.dialogVisible = false;
      this.axios
        .delete(`/manager/rule/id/${this.deleteId}/typeId/${this.deleteTypeId}`)
        .then(response => {
          if (response.data.code == 0) {
            this.$notify({
              title: "删除成功",
              message: "规则已经成功删除",
              type: "success"
            });
          } else {
            this.$notify.error({
              title: "删除失败",
              message: response.data.msg
            });
          }
        });
    }
  }
};
</script>