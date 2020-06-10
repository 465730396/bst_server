<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :rules="rules" :inline="true" label-width="68px">
      <el-form-item label="车牌号" prop="lsprefix">
        <el-select v-model="queryParams.lsprefix" placeholder="车牌前缀" style="width: 60px;" size="small">
          <el-option
            v-for="dict in provinceOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item prop="lsnum">
        <el-input
          v-model="queryParams.lsnum"
          placeholder="车牌号"
          clearable
          size="small"
          style="width: 150px;"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车架号" prop="frameno">
        <el-input
          v-model="queryParams.frameno"
          placeholder="请输入车架号"
          style="width: 150px;"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发动机号" prop="engineno">
        <el-input
          v-model="queryParams.engineno"
          placeholder="请输入发动机号"
          style="width: 150px;"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="postList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="前缀" align="center" width="55" prop="lsprefix" />
      <el-table-column label="车牌号" align="center" width="160" prop="lsnum" />
      <el-table-column label="发动机号" align="center" width="160" prop="engineno" />
      <el-table-column label="车架号" align="center" width="160" prop="frameno" />
      <el-table-column label="登记时间" align="center" width="160" prop="createTime" :formatter="dateFormat" />
      <el-table-column label="最后一次查询时间" align="center" width="160" prop="lastQueryTime" :formatter="dateFormat" />
      <el-table-column label="手机号码" align="left" width="130" prop="mobile" />
      <el-table-column label="查询次数" align="left" width="50" prop="queryCount" />
    </el-table>

  </div>
</template>

<script>
import { listCar,completeIllega,backMony } from "@/api/car/illega/post";

export default {
  name: "Post",
  data() {
    return {
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 岗位表格数据
      postList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      // 状态数据字典
      provinceOptions: [{"dictLabel":"陕","dictValue":"陕"},{"dictLabel":"粤","dictValue":"粤"}],
      // 查询参数
      queryParams: {
        lsprefix: null,
        pageNum: 1,
        pageSize: 10,
        postCode: undefined,
        postName: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        //lsprefix: [
        //  { required: true, message: "请选择省份", trigger: "blur" }
        //],
        //lsnum: [
        //  { required: true, message: "车牌号不能为空", trigger: "blur" }
        //],
        //frameno: [
        //  { required: true, message: "车架号不能为空", trigger: "blur" }
        //],
        //engineno: [
        //  { required: true, message: "发动机号不能为空", trigger: "blur" }
        //]
      }
    };
  },
  created() {
    //this.getDicts("car_province_list").then(response => {
    //  this.provinceOptions = response.data;
    //});
  },
  methods: {
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      listCar(this.queryParams).then(response => {
        this.postList = response.data;
        this.total = response.data.length;
        this.loading = false;
      }).catch(function (error){
          console.log(error)
          this.loading = false;
      });
    },
    formatDate(date, fmt) {
        if (/(y+)/.test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
        }
        let o = {
          'M+': date.getMonth() + 1,
          'd+': date.getDate(),
          'h+': date.getHours(),
          'm+': date.getMinutes(),
          's+': date.getSeconds()
        }
        for (let k in o) {
          if (new RegExp(`(${k})`).test(fmt)) {
            let str = o[k] + ''
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : this.padLeftZero(str))
          }
        }
        return fmt
      },
      padLeftZero(str) {
        return ('00' + str).substr(str.length)
      },
    // 岗位状态字典翻译
    dateFormat(row, column) {
      var str = "待付款";
      if(row.createTime){
        let date = new Date(row.createTime)
        return this.formatDate(date, 'yyyy-MM-dd hh:mm:ss')
      }
      //return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        postId: undefined,
        postCode: undefined,
        postName: undefined,
        postSort: 0,
        status: "0",
        remark: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
        this.postList = [];
        this.total = 0;
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.postId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加岗位";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      completeIllega({illegaNumbers:[row.number]}).then(response => {
        if(response.status ==1){
          this.msgSuccess("状态已修改");
        }else{
          this.msgSuccess(response.msg);
        }
      });
    },
    /** 修改按钮操作 */
    backMonyUpdate(row) {
      backMony({illegaNumbers:[row.number]}).then(response => {
        if(response.status ==1){
          this.msgSuccess("状态已修改");
        }else{
          this.msgSuccess(response.msg);
        }
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.postId != undefined) {
            updatePost(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addPost(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const postIds = row.postId || this.ids;
      this.$confirm('是否确认删除岗位编号为"' + postIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPost(postIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有岗位数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPost(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
