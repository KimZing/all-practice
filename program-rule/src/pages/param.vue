<template>
  <div>
    <el-form ref="param" :model="param" label-width="80px">
      <el-form-item label="参数类型">
        <el-select @change="changeParamType" v-model="param.type" placeholder="请选择参数类型">
          <el-option :label="item.tag" :value="item.type" v-for="item in params" :key="item.type"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div class="table">
      <el-table
        :data="param.values.filter(data => !search || data.tag.toLowerCase().includes(search.toLowerCase())|| data.value.toLowerCase().includes(search.toLowerCase()))"
        ref="table"
        tooltip-effect="dark"
        border
        stripe
        style="width: 95%"
      >
        <el-table-column label="序号" type="index" width="60" align="center"></el-table-column>
        <el-table-column label="Value" align="center">
          <template slot-scope="scope">
            <el-input v-model="scope.row.value"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="Tag">
          <template slot-scope="scope">
            <el-input v-model="scope.row.tag"></el-input>
          </template>
        </el-table-column>
        <el-table-column align="right">
          <template slot="header" slot-scope="scope">
            <el-input v-model="search" size="middle" placeholder="输入关键字搜索" />
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="delData(scope.row)"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-button size="middle" type="success" @click.prevent="addRow()">新增数据</el-button>
    <el-button size="middle" type="primary" @click="saveOrUpdateData()">保存更新</el-button>
  </div>
</template>

<script>
export default {
  name: "manager-param",
  data() {
    return {
      params: {},
      param: {
        ts: {},
        id: -1,
        type: "PROJECT_ID",
        values: []
      },
      search: ""
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    saveOrUpdateData() {
      this.param.ts = +new Date();
      //添加
      if (this.param.id == -1) {
        this.axios.post("/manager/param", this.param).then(response => {
          let res = response.data;
          if (res.code == 0) {
            this.init();
            this.$notify({
              title: "添加成功",
              message: "参数值添加成功",
              type: "success"
            });
          } else {
            this.$notify.error({
              title: "添加失败",
              message: res.msg
            });
          }
        });
        return;
      }

      // 更新
      this.axios
        .put(`/manager/param/${this.param.id}`, this.param)
        .then(response => {
          let res = response.data;
          if (res.code == 0) {
            this.init();
            this.$notify({
              title: "更新成功",
              message: "参数值更新成功",
              type: "success"
            });
          } else {
            this.$notify.error({
              title: "更新失败",
              message: res.msg
            });
          }
        });
    },
    // 增加行
    addRow() {
      var list = {
        value: "",
        tag: ""
      };
      this.param.values.unshift(list);
    },
    // 删除选中行
    delData(row) {
      this.param.values.forEach((v, i) => {
        if (v.value == row.value && v.tag == row.tag) {
          this.param.values.splice(i, 1);
        }
      });
    },
    changeParamType(chooseType) {
      let paramValue = this.params[`${chooseType}`];
      this.param.values = paramValue.values;
      this.param.id = paramValue.id;
    },
    init() {
      this.axios.get(`/manager/param/list`).then(response => {
        let res = response.data;
        if (res.code == 0) {
          this.params = res.data;
          this.changeParamType("PROJECT_ID");
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