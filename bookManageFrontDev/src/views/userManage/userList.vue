<template>
  <div style="height: 100%">
    <!-- 空格 -->
    <!-- <div>&nbsp;</div> -->
    <!--            列  表           -->
    <div style="height: calc(100% - 50px)">
      <!--                     用  户  搜  索  栏             -->
      <div class="usernameSearchbar">
        <!-- 搜索框 -->
        <div class="wordEnquiries">Username Enquiries</div>
        <el-input
          class="usernameEnquiries"
          placeholder="Please Search for a Username"
          v-model="username"
          prefix-icon="el-icon-search"
          clearable
        >
        </el-input>
        <!-- 搜索按钮 -->
        <el-button
          icon="el-icon-search"
          class="buttonSearch"
          @click="buttonsearch"
          >Search</el-button
        >
        <!-- 增加新用户按钮 -->
        <el-button plain icon="el-icon-edit" @click="dialogFormVisible = true"
          >add</el-button
        >
        <!-- 增加新用户表单 -->
        <el-dialog title="New User" :visible.sync="dialogFormVisible">
          <el-form :model="addForm" ref="addForm">
            <el-form-item label="username" label-width="80px" prop="username">
              <el-input
                v-model="addForm.username"
                auto-complete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="tel" label-width="80px" prop="tel">
              <el-input v-model="addForm.tel" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="email" label-width="80px" prop="email">
              <el-input v-model="addForm.email" auto-complete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="resetForm('addForm')">Cancel </el-button>
            <el-button type="primary" @click="newlist()">Ok</el-button>
          </div>
        </el-dialog>
      </div>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column type="index"> </el-table-column>
        <el-table-column prop="id" label="id"> </el-table-column>
        <el-table-column prop="username" label="username"> </el-table-column>
        <el-table-column prop="tel" label="tel"> </el-table-column>
        <el-table-column prop="email" label="email"> </el-table-column>
        <el-table-column prop="operation" label="operation">
          <!-- 用户编辑按钮 -->
          <template slot-scope="scope">
            <!-- 点击Edit button  里面的点击事件绑定的 方法buttonEdituser 会：把用户编辑表单打开 -->
            <el-button plain @click="buttonEdituser(scope.row)">Edit</el-button>
            <el-button @click="del(scope.row)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 用户编辑表单 -->
      <el-dialog title="User Message" :visible.sync="buttonEdit">
        <el-form :model="editForm">
          <el-form-item label="username" label-width="80px">
            <el-input
              v-model="editForm.username"
              auto-complete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="tel" label-width="80px">
            <el-input v-model="editForm.tel" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="email" label-width="80px">
            <el-input v-model="editForm.email" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div>
          <el-button @click="buttonEdit = false">Cancel </el-button>
          <el-button type="primary" @click="buttonEdiitOk()">Ok</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 分页 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageCondition.pageNo"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="pageCondition.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>



<script>
import { selectUserList, saveUser } from "@/services/UserController.js";
import User from "@/model/User.js";
import requestPageData from "@/model/RequestPageData.js";

export default {
  data() {
    return {
      // 默认隐藏新增用户的表单
      dialogFormVisible: false,
      buttonEdit: false,

      User: new User(),
      requestPageData: new requestPageData(),

      total: 0,

      username: "",
      tableData: [],

      //增加
      addForm: {
        username: "",
        tel: "",
        email: "",
      },

      //编辑
      editForm: {
        id: "",
        username: "",
        tel: "",
        email: "",
      },

      pageCondition: {
        pageNo: 1,
        pageSize: 5,
      },
    };
  },
  //打开页面一进来就看得到列表，所以做请求getlist()
  created() {
    // created()：生命周期
    this.getlist();
  },
  methods: {
    resetForm(formName) {
      this.dialogFormVisible = false;
      this.dialogFormVisible = false;
      this.$nextTick(() => {
        this.$refs[formName].resetFields();
      });
    },
    //获取用户列表
    getlist() {
      this.requestPageData.condition = this.User;
      this.requestPageData.pageCondition = this.pageCondition;

      selectUserList(this.requestPageData).then((res) => {
        this.tableData = res.data.resultPages;
        this.total = res.data.totalRows;
      });
    },
    //新增用户
    newlist() {
      this.User.username = this.addForm.username;
      this.User.tel = this.addForm.tel;
      this.User.email = this.addForm.email;

      saveUser(this.User).then(() => {
        this.dialogFormVisible = false;
        this.$message({
          message: "新增成功",
          type: "success",
        });
        this.User = new User();
        this.getlist(); //调用全部数据的方法getlist()，为了刷新
      });
    },

    // 分页，前往第几页
    handleCurrentChange(val) {
      this.pageCondition.pageNo = val;
      this.getlist();
    },

    //共几页
    handleSizeChange(val) {
      this.pageCondition.pageSize = val;
      this.getlist();
    },

    del(val) {
      this.buttonEdit = false;
      this.User.id = val.id;
      this.User.status = 1;
      saveUser(this.User).then((res) => {
        this.buttonEdit = false;
        this.$message({
          message: "删除成功",
          type: "success",
        });
        this.getlist(); //调用全部数据的方法getlist()，为了刷新
      });
    },
    //打开编辑模态框
    buttonEdituser(val) {
      this.buttonEdit = true;
      this.editForm.id = val.id;
      this.editForm.username = val.username;
      this.editForm.tel = val.tel;
      this.editForm.email = val.email;
    },
    //编辑用户
    buttonEdiitOk() {
      this.User.id = this.editForm.id;
      this.User.username = this.editForm.username;
      this.User.tel = this.editForm.tel;
      this.User.email = this.editForm.email;

      saveUser(this.User).then((res) => {
        this.buttonEdit = false;
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.User = new User();
        this.getlist(); //调用全部数据的方法getlist()，为了刷新
      });
    },
    //查询按钮
    buttonsearch() {
      this.User.username = this.username;
      this.getlist();
    },
  },
};
</script>



<style lang="less" scoped>
.usernameSearchbar {
  display: flex;
  /* width: 598px; */
  width: 54%;
  margin-bottom: 15px;
}

.wordEnquiries {
  font-family: "Open Sans Condensed", sans-serif;
  padding: 0 10px;
  line-height: 45px;
  font-weight: 100;
  inline-size: 178px;
}
.usernameEnquiries {
  height: 46px !important;
  width: 274px !important;
  /deep/ .el-input__inner {
    height: 46px !important;
  }
}
.buttonSearch {
  margin-left: 10px !important;
}
</style>