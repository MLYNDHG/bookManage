<template>
  <div>
    <!-- 搜索栏 -->
    <div>
      <div class="usernameSearchbar">
        <!-- 搜索框 -->
        <div class="wordEnquiries">Borrowing Management</div>
        <el-input
          class="usernameEnquiries"
          placeholder="Please Search for a Borrowing Management"
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
      </div>

      <!-- 空格 -->
      <div>&nbsp;</div>

      <div>
        <!-- 列表 -->
        <el-table :data="tableData" stripe style="width: 100%, "  height="618px">
          <el-table-column type="index"> </el-table-column>
          <el-table-column prop="bid" label="bookId"> </el-table-column>
          <el-table-column prop="username" label="userName"> </el-table-column>
          <el-table-column prop="name" label="bookName"> </el-table-column>
          <el-table-column prop="startTime" label="startTime">
          </el-table-column>
          <el-table-column prop="endTime" label="endTime"> </el-table-column>
        </el-table>
      </div>
    </div>

    <!-- 分页 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageCondition.pageNo"
      :page-sizes="[1, 2, 3, 4]"
      :page-size="pageCondition.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>




<script>
import { selectBookLogList } from "@/services/BookLogController";
import requestPageData from "@/model/RequestPageData.js";
import BookLogVO from "@/model/BookLogVO";

export default {
  data() {
    return {
      //不写这个就打不了字，这个是和搜索框v-model="username"双向绑定的
      username:"",

      BookLogVO: new BookLogVO(),
      requestPageData: new requestPageData(),

      input: "",
      tableData: [],


      pageCondition: {
        pageNo: 1,
        pageSize: 5,
      },
    };
  },

  created() {
    this.getlist();
  },
  methods: {
    //获取借阅记录列表
    getlist() {
      this.requestPageData.condition = this.BookLogVO;
      this.requestPageData.pageCondition = this.pageCondition;

      selectBookLogList(this.requestPageData).then((res) => {
        console.log(res);
        this.tableData = res.data.resultPages;
        this.total = res.data.totalRows;
      });
    },
    //查询按钮
    buttonsearch() {
      this.BookLogVO.username = this.username;
      this.getlist();
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
  },
};
</script>



<style lang="less" scoped>

.usernameSearchbar {
  display: flex;
  /* width: 598px; */
  width: 54%;
}
.wordEnquiries {
  font-family: "Open Sans Condensed", sans-serif;
  padding: 0 10px;
  line-height: 45px;
  font-weight: 100;
  inline-size: 178px;
      // width: 380px !important;
}
.usernameEnquiries {
  height: 46px !important;
  width: 351px !important;
  /deep/ .el-input__inner {
    height: 46px !important;
  }
}
.buttonSearch {
  margin-left: 10px !important;
}


</style>