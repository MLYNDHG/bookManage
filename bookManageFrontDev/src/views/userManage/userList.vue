

<template>
  <div>
    <!--                     用  户  搜  索  栏             -->
    <div class="usernameSearchbar">
      <!-- 搜索框 -->
      <div class="wordEnquiries">Username Enquiries</div>
      <el-input
        class="usernameEnquiries"
        placeholder="Please Search for a Username"
        v-model="input1"
        prefix-icon="el-icon-search"
        clearable
      >
      </el-input>
      <!-- 搜索按钮 -->
      <el-button icon="el-icon-search" class="buttonSearch">Search</el-button>
      <!-- 增加新用户按钮 -->
      <el-button plain icon="el-icon-edit" @click="dialogFormVisible = true"
        >add</el-button
      >
      <!-- 增加新用户表单 -->
      <el-dialog title="New User" :visible.sync="dialogFormVisible">
        <el-form :model="addForm">
          <el-form-item label="username" label-width="80px">
            <el-input v-model="addForm.username" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="tel" label-width="80px">
            <el-input v-model="addForm.tel" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="email" label-width="80px">
            <el-input v-model="addForm.email" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false"
            >Cancel
          </el-button>
          <el-button type="primary" @click="buttonFormVisibleok">Ok</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 空格 -->
    <div>&nbsp;</div>
    <!--            列  表           -->
    <div>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="id" label="id"> </el-table-column>
        <el-table-column prop="username" label="username"> </el-table-column>
        <el-table-column prop="tel" label="tel"> </el-table-column>
        <el-table-column prop="email" label="email"> </el-table-column>
        <el-table-column prop="operation" label="operation">
          <!-- 用户编辑按钮 -->
          <el-button plain @click="buttonEdit = true">Edit</el-button>
        </el-table-column>
      </el-table>

       <!-- 用户编辑表单 -->
          <el-dialog title="User Message" :visible.sync="buttonEdit">
            <el-form :model="editForm">
                <el-form-item label="id" label-width="80px">
                <el-input
                  v-model="editForm.id"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="username" label-width="80px">
                <el-input
                  v-model="editForm.username"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="tel" label-width="80px">
                <el-input
                  v-model="editForm.tel"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="email" label-width="80px">
                <el-input
                  v-model="editForm.email"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer1" class="dialog-footer1">
              <el-button @click="buttonEdit = false"
                >Cancel
              </el-button>
              <el-button type="primary" @click="buttonFormVisibleok"
                >Ok</el-button
              >
            </div>
          </el-dialog>

    </div>
  </div>
</template>



<script>
import { selectUserList } from "@/services/UserController.js";
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

      input1: "",
      tableData: [
        {
          uid: "1",
           name: "王小虎",
          tel: "1233955466",
          email: "1931878873@qq.com",
        },
      ],

      editForm: {
        username: "",
        tel: "",
        email: "",
          id:"",
      },

      addForm: {
          username: "",
        tel: "",
        email: "",
      },
    };
  },

  created() {
    this.getlist();
  }, //生命周期
  methods: {
  buttonFormVisibleok(){
alert("调用接口");
  },


    getlist() {
      this.User.status = false;
      this.User.isSuper = false;
      this.requestPageData.condition = this.User;

      selectUserList(this.requestPageData).then((res) => {
        this.tableData = res.data.resultPages;
        console.log(res);
      });
    },
  },
};
</script>



<style scoped>
.usernameSearchbar {
  display: flex;
  width: 598px;
}

.wordEnquiries {
  font-family: "Open Sans Condensed", sans-serif;
  width: 86%;
  line-height: 45px;
  font-weight: 100;
}
.usernameEnquiries {
  width: 50px;
  height: 46px;
}
.buttonSearch {
  margin-left: 10px !important;
}

.el-input__inner {
  height: 50px !important;
}
</style>