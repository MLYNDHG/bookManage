<template>
  <div class="classall">
    <el-container>
      <!--         左     边     区      域      -->
      <el-aside
        width="33%"
        height="323%"
        style="background-color: rgb(105, 132, 164); border-radius: 10px"
      >
        <!--     左      上     -->
        <el-header>
          <div class="leftup">
            <div style="padding: 9px; color: #f2f6fc">系统分组</div>
            <!-- 左上按钮 -->
            <template>
              <el-button-group>
                <el-button
                  plain
                  icon="el-icon-folder-add"
                  round
                  @click="buttonNewsystem = true"
                  >新增</el-button
                >
                <el-button plain icon="el-icon-download" round>导入</el-button>
              </el-button-group>
            </template>

            <!-- 新增系统表单 -->
            <el-dialog title="新增系统表单" :visible.sync="buttonNewsystem">
              <el-form :model="newSystemForm">
                <el-form-item label="系统名称" label-width="80px">
                  <el-input
                    v-model="newSystemForm.systemName"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="系统代码" label-width="80px">
                  <el-input
                    v-model="newSystemForm.systemId"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
              </el-form>
              <div>
                <el-button @click="buttonNewsystem = false">Cancel </el-button>
                <el-button type="primary" @click="newlistLeft()">Ok</el-button>
              </div>
            </el-dialog>
          </div>
        </el-header>
        <!--        左       下      -->
        <el-main class="leftdown">
          <!-- 左下列表 -->
          <el-table :data="leftData" border style="width: 100%" height="100%">
            <el-table-column prop="systemname" label="系统名称">
            </el-table-column>
            <el-table-column prop="systemcode" label="系统编码">
            </el-table-column> </el-table
        ></el-main>
        <!-- 左下分页 -->
        <div>
          <el-pagination
            @size-change="lefthandleSizeChange"
            @current-change="lefthandleCurrentChange"
            :current-page="pageConditionLeft.pageNo"
            :page-sizes="[1, 2, 3, 4]"
            :page-size="pageConditionLeft.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalLeft"
          >
          </el-pagination>
        </div>
      </el-aside>

      <!-- 右   边   区   域 -->

      <el-main
        height="88px"
        style="
          margin-left: 5px;
          background-color: rgb(105, 132, 164);
          border-radius: 10px;
        "
      >
        <!-- 右上 -->
        <el-header>
          <div class="rightup">
            <div style="padding: 9px; color: #f2f6fc">渠道整合平台系统</div>
            <!-- 右上按钮 -->

            <el-button-group>
              <el-button
                plain
                icon="el-icon-folder-add"
                round
                @click="buttonNewreturnId = true"
                >新增</el-button
              >
              <el-button plain icon="el-icon-close">删除</el-button>
              <el-button plain icon="el-icon-download">导入</el-button>
              <el-button plain icon="el-icon-upload2" round>导出</el-button>
            </el-button-group>

            <!-- 增加返回编码表单 -->
            <el-dialog title="新增返回编码" :visible.sync="buttonNewreturnId">
              <el-form :model="returnidForm">
                <el-form-item label="编码" label-width="80px">
                  <el-input
                    v-model="returnidForm.encoding"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="中文注释" label-width="80px">
                  <el-input
                    v-model="returnidForm.chinesenote"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="英文注释" label-width="80px">
                  <el-input
                    v-model="returnidForm.englishnote"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="繁体注释" label-width="80px">
                  <el-input
                    v-model="returnidForm.traditionalchinesenote"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
              </el-form>

              <div slot="footer" class="dialog-footer">
                <el-button @click="buttonNewreturnId = false"
                  >Cancel
                </el-button>
                <el-button type="primary" @click="buttonEditencodingOk()"
                  >Ok</el-button
                >
              </div>
            </el-dialog>
          </div>
        </el-header>
        <!--       右      下      -->
        <el-main style="background-color: rgb(233, 238, 243)" class="rightdown">
          <!-- 右下表格 -->
          <el-table
            ref="multipleTable"
            :data="rightData"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange"
            height="100%"
          >
            <el-table-column type="wid" width="55"> </el-table-column>

            <el-table-column prop="code" label="编码"> </el-table-column>
            <el-table-column
              prop="chinese"
              label="中文注释"
              show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column prop="english" label="英文注释"> </el-table-column>
            <el-table-column
              prop="fanti"
              label="繁体注释"
              show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
              prop="infodate"
              label="创建日期"
              show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)"
                  >编辑</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-main>

        <!-- 右下分页 -->
        <div>
          <el-pagination
            @size-change="righthandleSizeChange"
            @current-change="righthandleCurrentChange"
            :current-page="pageConditionRight.pageNo"
            :page-sizes="[1, 2, 3, 4]"
            :page-size="pageConditionRight.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalLeft"
          >
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </div>
</template>


<script>
// ,insertSystemOfExcel
import {
  selectSystemList,
  insertSystem,
} from "@/services/FirstItemSystemController.js";
import {selectSystemInfoList} from "@/services/FirstItemSystemInfoController.js";

import requestPageData from "@/model/RequestPageData.js";
import firstitemsystem from "@/model/firstitemsystem.js";
import Firstitemsysteminfo from "@/model/Firstitemsysteminfo.js";
// import file from '';
export default {
  data() {
    return {
      // 默认隐藏新增用户的表单
      buttonNewsystem: false,
      buttonNewreturnId: false,

      leftData: [],
      rightData: [],
      //点击右上新增按钮 出现的表单
      newSystemForm: {
        systemName: "",
        systemId: "",
      },



      // 左下分页
      totalLeft: 0,
      //右下分页
      totalRight: 0,
      //
      pageConditionLeft: {
        pageNo: 1,
        pageSize: 3,
      },
      pageConditionRight: {
        pageNo: 1,
        pageSize: 3,
      },
      //

      requestPageData: new requestPageData(),
      firstitemsystem: new firstitemsystem(),
      firstitemsysteminfo: new Firstitemsysteminfo(),

      //点击左上新增按钮 出现的表单
      returnidForm: {
        encoding: "",
        chinesenote: "",
        englishnote: "",
        traditionalchinesenote: "",
      },
    };
  },
  created() {
    // created()：生命周期
    this.getleftlist();
    this.getrightlist();
  },
  methods: {
    // 右下
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 左分页，前往第几页
    lefthandleCurrentChange(val) {
      this.pageConditionLeft.pageNo = val;
      this.getleftlist();
    },

    //左共几页
    lefthandleSizeChange(val) {
      this.pageConditionLeft.pageSize = val;
      this.getleftlist();
    },
    // 右分页，前往第几页
    righthandleCurrentChange(val) {
      this.pageConditionRight.pageNo = val;
      this.getrightlist();
    },

    //右共几页
    righthandleSizeChange(val) {
      this.pageConditionRight.pageSize = val;
      this.getrightlist();
    },
    // 获取列表显示左下
    getleftlist() {
      this.requestPageData.condition = this.firstitemsystem;
      this.requestPageData.pageCondition = this.pageConditionLeft;

      selectSystemList(this.requestPageData).then((res) => {
        console.log(res);
        this.leftData = res.data.resultPages;
        this.totalLeft = res.data.totalRows;
      });
    },
    //左新增
    newlistLeft() {
      this.firstitemsystem.systemname = this.newSystemForm.systemName;
      this.firstitemsystem.systemcode = this.newSystemForm.systemId;

      insertSystem(this.firstitemsystem).then(() => {
        this.buttonNewsystem = false;
        this.$message({
          message: "新增成功",
          type: "success",
        });
        this.firstitemsystem = new firstitemsystem();
        this.getleftlist(); //调用全部数据的方法getlist()，为了刷新
      });
    },
    //右下表单显示
    getrightlist() {
      this.requestPageData.condition = this.firstitemsysteminfo;
      this.requestPageData.pageCondition = this.pageConditionRight;

      selectSystemInfoList(this.requestPageData).then((res) => {
        console.log(res);
        this.rightData = res.data.resultPages;
        this.totalRight = res.data.totalRows;
      });
    },
  },
};
</script>


<style lang="less">
.classall {
  height: 100%;
  background-color: #dae1e5;
  padding: 20px;
}
.el-tabs__content {
  height: 100%;
}
.el-container {
  height: 738px;
}
// 右下背景
.el-main {
  padding: 0px !important;
}

// 右边表格
.rightdown {
  height: 86.9%;
}

//左下表格
.leftdown {
  height: 86.9%;
}

.el-table {
  padding: 10px;
}
.el-pagination__total {
  color: white !important;
}
.el-pagination__jump {
  color: white !important;
}
//左上模块
.leftup {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 10px 5px;
}

//右上模块
.rightup {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 10px 5px;
}

.el-tabs__header {
  margin: 0px !important;
}
</style>