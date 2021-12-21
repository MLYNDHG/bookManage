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
      <el-button class="dalfBut" @click="bookSearch">查询</el-button>
      <el-button type="danger" class="butT" @click="addBookBto">添加图书</el-button>
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

      <el-table-column label="操作" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button size="small" @click="showEditDialog(scope.row.id)"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="deleteBook(scope.row.bid)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page="queryInfo.pageCondition.pageNo"
      :page-sizes="[3, 6, 9]"
      :page-size="queryInfo.pageCondition.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>

    <!-- 新建图书对话框 -->
    <el-dialog
      title="新增图书"
      :visible.sync="addBookFormDialog"
      @close="addBookClose"
    >
      <el-form :model="addBookForm" ref="addBookRef">
        <el-form-item label="书籍名称" label-width="100px">
          <el-input v-model="addBookForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="作者" label-width="100px">
          <el-input v-model="addBookForm.author" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" label-width="100px">
          <el-input
            v-model="addBookForm.description"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addBookFormDialog = false">取 消</el-button>
        <el-button type="primary" @click="addBook">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑图书对话框 -->
    <el-dialog title="新建图书" :visible.sync="editBookDialog">
      <el-form :model="editBookList">
        <el-form-item label="书籍名称" label-width="100px">
          <el-input
            v-model="editBookList.name"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="作者" label-width="100px">
          <el-input v-model="editBookList.author" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" label-width="100px">
          <el-input
            v-model="editBookList.description"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editBookDialog = false">取 消</el-button>
        <el-button type="primary" @click="editBook">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { selectBookList,saveBook } from "@/services/bookcontroller.js";
import requestPageData from "@/model/RequestPageData.js";
import addBookForm from "@/model/addBookForm.js";
export default {
  data() {
    return {
      requestPageData: new requestPageData(),
      addBookForm: new addBookForm(),
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

      //新增图书对话框
      addBookFormDialog: false,

      //编辑图书对话框
      editBookDialog: false,

      //编辑图书展示信息
      editBookList: []
    };
  },

  created() {
    this.getAll();
  },

  methods: {
    //条件查询
    getAll() {
      this.requestPageData.condition = this.queryInfo.condition;
      this.requestPageData.pageCondition = this.queryInfo.pageCondition;

      selectBookList(this.requestPageData)
        .then((res) => {
          console.log(res);
          this.bookList = res.data.resultPages;
          this.total = res.data.totalRows;
        })
        .catch((res) => {
          console.log(res);
        });
    },

    //匹配查询
    bookSearch() {
      this.getAll();
    },

    //监听当前页码改变事件
    currentChange(page) {
      this.queryInfo.pageCondition.pageNo = page;

      this.getAll();
    },

    //监听页码大小改变事件
    sizeChange(pageSize) {
      //console.log(a)
      this.queryInfo.pageCondition.pageSize = pageSize;
      this.getAll();
    },

    //新增图书
    addBook() {
        saveBook(this.addBookForm).then(res=>{
            console.log(res)
            this.addBookFormDialog = false
            this.$message({ message: "添加成功", type: "success" });
        }).catch(res=>{
            console.log(res)
        })
    },

    //点击弹出新增对话框
    addBookBto() {
        this.addBookFormDialog = true
    },

    //监听新增对话框关闭
    addBookClose() {
        this.$refs.addBookRef.resetFields()
    },

    //点击编辑展示编辑信息
    showEditDialog(bid) {
        this.requestPageData.condition.id = bid
        selectBookList(this.requestPageData).then(res=>{
            //console.log(res)
            this.editBookList = res.data.resultPages[0];
            this.requestPageData.condition.id = ""
        }).catch(res=>{
            console.log(res)
        })
        this.editBookDialog = true
        console.log(bid)
    },

    //点击修改编辑信息
    editBook() {
        saveBook(this.editBookList).then(res=>{
            console.log(res)
            this.editBookDialog = false
            this.$message({ message: "修改成功", type: "success" });
            this.getAll()
        }).catch(res=>{
            console.log(res)
        })
    }
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
