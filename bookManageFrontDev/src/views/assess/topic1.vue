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
                 <el-button plain icon="el-icon-download" 
                ><a href="#" :download="downFileOne">下载模板</a></el-button
              >
                
                <el-button
                  style="display: flex"
                  class="upploadButton"
                  plain
                  icon="el-icon-right"
                  round
                >
                  <el-upload
                    class="upload-demo"
                    :action="uploadLeftUrl"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    >导入
                  </el-upload></el-button
                >
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
          <el-table
            :data="leftData"
            border
            style="width: 100%"
            height="100%"
            @row-click="rowclickleft"
          >
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
            :page-sizes="[5, 10, 15, 20]"
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
            <div style="padding: 9px; color: #f2f6fc">{{ titleName }}</div>
            <!-- 右上按钮 -->

            <el-button-group>
              <el-button
                plain
                icon="el-icon-folder-add"
                round
                @click="buttonNewreturnId = true"
                >新增</el-button
              >
              <el-button plain icon="el-icon-close" @click="delRightTable"
                >删除</el-button
              >
              <el-button plain icon="el-icon-download" 
                ><a href="#" :download="downFileSecond">下载模板</a></el-button
              >
                
              <el-button plain icon="el-icon-right" style="display: flex">
                <el-upload
                  class="upload-demo"
                  :action="uploadRightUrl + mid"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  >导入
                </el-upload></el-button
              >
              <el-button plain icon="el-icon-upload2" round>导出</el-button>
            </el-button-group>

            <!-- 新增返回编码表单 -->
            <el-dialog title="新增返回编码" :visible.sync="buttonNewreturnId">
              <el-form :model="firstitemsysteminfo">
                <el-form-item label="编码" label-width="80px">
                  <el-input
                    v-model="firstitemsysteminfo.code"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="中文注释" label-width="80px">
                  <el-input
                    v-model="firstitemsysteminfo.chinese"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="英文注释" label-width="80px">
                  <el-input
                    v-model="firstitemsysteminfo.english"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="繁体注释" label-width="80px">
                  <el-input
                    v-model="firstitemsysteminfo.fanti"
                    auto-complete="off"
                  ></el-input>
                </el-form-item>
              </el-form>

              <div slot="footer" class="dialog-footer">
                <el-button @click="buttonAddencodingCancel()"
                  >Cancel
                </el-button>
                <el-button type="primary" @click="buttonAddencodingOk()"
                  >Ok</el-button
                >
              </div>\
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
            <el-table-column type="selection" width="55"> </el-table-column>

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
                <el-button size="mini" @click="handleEdit(scope.row)"
                  >编辑</el-button
                >
              </template>
            </el-table-column>
          </el-table>
          
          <!-- 编辑返回编码表单 -->
          <el-dialog title="编辑返回编码" :visible.sync="buttonEditreturnId">
            <el-form :model="firstitemsysteminfo">
              <el-form-item label="编码" label-width="80px">
                <el-input
                  v-model="firstitemsysteminfo.code"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="中文注释" label-width="80px">
                <el-input
                  v-model="firstitemsysteminfo.chinese"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="英文注释" label-width="80px">
                <el-input
                  v-model="firstitemsysteminfo.english"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item label="繁体注释" label-width="80px">
                <el-input
                  v-model="firstitemsysteminfo.fanti"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="buttonEditencodingCancel()">Cancel </el-button>
              <el-button type="primary" @click="buttonEditencodingOk()"
                >Ok</el-button
              >
            </div>
          </el-dialog>
        </el-main>

        <!-- 右下分页 -->
        <div>
          <el-pagination
            @size-change="righthandleSizeChange"
            @current-change="righthandleCurrentChange"
            :current-page="pageConditionRight.pageNo"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="pageConditionRight.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalRight"
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
import {
  selectSystemInfoList,
  deleteSystemInfoOfList,
  updateSystemInfo,
  insertSystemInfo,
} from "@/services/FirstItemSystemInfoController.js";
import request from "@/utils/request";
import requestPageData from "@/model/RequestPageData.js";
import firstitemsystem from "@/model/firstitemsystem.js";
//后端类model-class里写的是什么大小写 这里引入的就是同样的大小写
import Firstitemsysteminfo from "@/model/Firstitemsysteminfo.js";
// import file from '';
export default {
  data() {
    return {
      downFileOne:'',
      downFileSecond:'',
      uploadLeftUrl: "",
      uploadRightUrl: "",
      // 默认隐藏左上新增用户的表单
      buttonNewsystem: false,
      // 默认隐藏右上新增用户的表单
      buttonNewreturnId: false,
      // 默认隐藏左下编辑的表单
      buttonEditreturnId: false,

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
        pageSize: 5,
      },
      pageConditionRight: {
        pageNo: 1,
        pageSize: 5,
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

      mid: "",
      titleName: "",
      wid: "",
      multipleSelection: [],
      widList: [],
    };
  },
  created() {
    // created()：生命周期
    this.getleftlist();

    //
  },
  mounted() {
    this.uploadLeftUrl = request.baseUrl + "/insertSystemOfExcel";
    this.uploadRightUrl = request.baseUrl + "/insertSystemInfoOfExcel?mid=";
    this.downFileOne=request.baseUrl+"/static/系统分组模板.xlsx";
      this.downFileSecond=request.baseUrl+"/static/系统分组信息表.xlsx";
  },
  methods: {
    //分组上传
    handleAvatarSuccess(res) {
      this.$message({
        message: "上传成功",
        type: "success",
      });
      this.getleftlist();
    },
    //右下弹出的编辑表单上的Cancel按钮点击事件
    buttonEditencodingCancel() {
      this.buttonEditreturnId = false;
      this.firstitemsysteminfo = new Firstitemsysteminfo();
    },
    //右上弹出的新增表单里的Cancel按钮点击事件
    buttonAddencodingCancel() {
      this.buttonNewreturnId = false;
      this.firstitemsysteminfo = new Firstitemsysteminfo();
    },
    //右上弹出的新增表单里的ok按钮点击事件
    buttonAddencodingOk() {
      this.firstitemsysteminfo.mid = this.mid;
      insertSystemInfo(this.firstitemsysteminfo).then((res) => {
        this.buttonNewreturnId = false;
        this.$message({
          message: "新增成功",
          type: "success",
        });
        //表单也要绑定firstitemsysteminfo的，再new就可以把这个空出来了
        this.firstitemsysteminfo = new Firstitemsysteminfo();
        this.getrightlist(this.mid);
      });
    },
    //右下表格删除方法
    delRightTable() {
      this.widList = [];
      for (let index = 0; index < this.multipleSelection.length; index++) {
        const element = this.multipleSelection[index];
        this.widList.push(element.wid);
      }
      deleteSystemInfoOfList(this.widList).then((res) => {
        this.$message({
          message: "删除成功",
          type: "success",
        });
        //重新获得
        this.getrightlist(this.mid);
      });
    },
    //右上删除按钮：点击删除多项列表
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //左下 当某一行被点击时会触发该事件
    rowclickleft(row) {
      //console.log(row["id"]) 跟下面效果一样
      console.log(row.mid); //获取各行id的值
      this.mid = row.mid;
      this.getrightlist(this.mid);
      this.titleName = row.systemname;
    },

    //点击编辑按钮展示表单信息
    handleEdit(row) {
      this.buttonEditreturnId = true;
      //因为firstitemsysteminfo这个的modle数据new了，就不用在data return 里再写一遍了
      //scope.row拿出来一整行的数据，但是作用域只在这个点击事件里可以使用，
      //所以this.firstitemsysteminfo = row;
      this.firstitemsysteminfo = row;
    },
    //点击编辑表单的ok按钮，编辑表单信息
    buttonEditencodingOk() {
      updateSystemInfo(this.firstitemsysteminfo).then((res) => {
        this.buttonEditreturnId = false;
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.firstitemsysteminfo = new Firstitemsysteminfo();
        this.getrightlist(this.mid);
      });
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
      this.getrightlist(this.mid);
    },

    //右共几页
    righthandleSizeChange(val) {
      this.pageConditionRight.pageSize = val;
      this.getrightlist(this.mid);
    },
    // 获取列表显示左下
    getleftlist() {
      this.requestPageData.condition = this.firstitemsystem;
      this.requestPageData.pageCondition = this.pageConditionLeft;

      selectSystemList(this.requestPageData).then((res) => {
        this.leftData = res.data.resultPages;

        this.getrightlist(this.leftData[0].mid);
        this.titleName = this.leftData[0].systemname;
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
    getrightlist(mid) {
      this.mid = mid;
      this.firstitemsysteminfo.mid = mid;
      this.requestPageData.condition = this.firstitemsysteminfo;
      this.requestPageData.pageCondition = this.pageConditionRight;

      selectSystemInfoList(this.requestPageData).then((res) => {
        this.rightData = res.data.resultPages;
        this.totalRight = res.data.totalRows;
      });
    },
  },
};
</script>


<style lang="less" scoped>
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