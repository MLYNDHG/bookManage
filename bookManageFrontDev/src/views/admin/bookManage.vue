<template>
  <div class="vueContainer">
    <div class="filter-container">
      <el-input
        placeholder="图书名称"
        style="width: 200px"
        class="filter-item"
        v-model="queryInfo.condition.name"
      ></el-input>
      <el-input
        placeholder="图书作者"
        style="width: 200px"
        class="filter-item"
        v-model="queryInfo.condition.author"
      ></el-input>
      <el-input
        placeholder="图书描述"
        style="width: 200px"
        class="filter-item"
        v-model="queryInfo.condition.description"
      ></el-input>
      <el-button class="dalfBut">查询</el-button>
      <el-button type="danger" class="butT">添加图书</el-button>
    </div>

    <!-- table区域 -->
    <el-table :data="bookList" stripe height="358px">
      <el-table-column type="index"></el-table-column>
      <el-table-column label="BID" prop="id"></el-table-column>
      <el-table-column label="图书名" prop="name"></el-table-column>
      <el-table-column label="作者" prop="author"></el-table-column>
      <el-table-column label="描述" prop="description"></el-table-column>
      <el-table-column
        ><el-button size="small" type="primary"
          >借阅</el-button
        ></el-table-column
      >
    </el-table>

    <!-- 分页 -->
    <el-pagination
      @current-change="currentChange"
      :current-page="queryInfo.pageCondition.pageNo"
      :page-sizes="[3, 6, 9]"
      :page-size="queryInfo.pageCondition.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>
<script>
import { selectBookList } from "@/services/bookcontroller.js";
import requestPageData from "@/model/RequestPageData.js";
export default {
  data() {
    return {
      requestPageData: new requestPageData(),
      //获取图书列表的参数对象
      queryInfo: {
        condition: {
          author: "",
          date: "",
          description: "",
          id: "",
          log: "",
          name: "",
          status: "",
          time: "",
          timeFrom: "",
          timeTo: "",
          uid: "",
        },
        pageCondition: {
          pageNo: 1,
          pageSize: 3,
        },
      },

      //全部页数
      total: 0,

      //图书信息列表
      bookList: [],
    };
  },

  created() {
    this.getAll();
  },

  methods: {
    //查询
    getAll() {
      this.requestPageData.condition = this.queryInfo.condition;
      this.requestPageData.pageCondition = this.queryInfo.pageCondition;

      selectBookList(this.requestPageData)
        .then((res) => {
          console.log(res);
          this.bookList = res.data.resultPages;
          this.total = res.data.totalRows
        })
        .catch((res) => {
          console.log(res);
        });
    },

    //监听页码改变事件
    currentChange(page) {
      this.queryInfo.pageCondition.pageNo = page;

      this.getAll()
    },
  },
};
</script>
<style lang="less">
.vueContainer {
  .filter-container {
    height: 50px;
  }

  .filter-item {
    margin: 5px 8px;
  }


  .el-pagination {
      margin-top: 8px;
    margin-right: 25px;
  }
}
      .el-tabs__nav-scroll {
        overflow: hidden;
        border-bottom: solid;
      }
</style>
