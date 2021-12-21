

<template>
  <div>
    <!-- 用户搜索栏 -->
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
      <el-button icon="el-icon-search" class="buttonSearch">Search</el-button>
    </div>
    <div>&nbsp;</div>
    <!-- 列表 -->
    <div>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="uid" label="uid" > </el-table-column>
        <el-table-column prop="name" label="name" >
        </el-table-column>
        <el-table-column prop="tel" label="tel" > </el-table-column>
        <el-table-column prop="email" label="email" > </el-table-column>
      </el-table>
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
    };
  },

  created() {
    this.getlist();
  }, //生命周期
  methods: {
    getlist() {
        this.User.status = false;
         this.User.isSuper = false;
        this.requestPageData.condition=this.User;
        
      selectUserList(this.requestPageData).then((res) => {

          console.log(res);
      });
    },
  },
};
</script>



<style>
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