<template>
  <div class="adminHome">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap"
      rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300;700&display=swap"
      rel="stylesheet"
    />
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <div class="headLeft">Book Manage</div>
        <div class="headRight">
          <div class="avatar">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:9090/uploadImage"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </div>
          {{nickname}}
          <el-button
            class="logout"
            type="primary"
            round
            size="mini"
            @click="tiaoshi"
            >退出</el-button
          >
        </div>
      </el-header>

      <el-container>
        <el-aside width="200px">
          <!-- 侧边栏菜单 -->
          <el-menu
            default-active="activePath"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <el-menu-item index="/home" @click="home('/home')">
              <span slot="title">HOME</span>
            </el-menu-item>

            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>BookManage</span>
              </template>
              <el-menu-item
                index="/home/bookmanage"
                @click="saveNavState('/home/bookmanage', menuName1)"
                >{{ menuName1 }}</el-menu-item
              >
            </el-submenu>

            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>MemberManage</span>
              </template>
              <el-menu-item
                index="/home/userlist"
                @click="saveNavState('/home/userlist', menuName2)"
                >{{ menuName2 }}</el-menu-item
              >
            </el-submenu>

            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>BorrowManage</span>
              </template>
              <el-menu-item index="1-1">选项1</el-menu-item>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>XinChen</span>
              </template>
              <el-menu-item index="/home/topic1" @click="saveNavState('/home/topic1', menuName4_1)">{{menuName4_1}}</el-menu-item>
              <el-menu-item index="/home/topic2" @click="saveNavState('/home/topic2', menuName4_2)">{{menuName4_2}}</el-menu-item>
              <el-menu-item index="/home/topic3" @click="saveNavState('/home/topic3', menuName4_3)">{{menuName4_3}}</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-main>
          <el-tabs
            v-model="editableTabsValue"
            type="card"
            @tab-click="tabclick"
            @tab-remove="removeTab"
          >
            <el-tab-pane
              v-for="item in editableTabs"
              :key="item.name"
              :label="item.title"
              :name="item.name"
              :closable="item.close"
            >
            </el-tab-pane>

            <router-view></router-view>
          </el-tabs>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { saveUser,selectUserList } from "@/services/UserController.js";
import user from "@/model/User.js";
import requestPageData from "@/model/RequestPageData.js"
export default {
  
  data() {
    return {
      show: true,
      editableTabsValue: "2",
      editableTabs: this.$store.state.list,
      tabIndex: 2,

      //被激活的链接地址
      activePath: "",

      //菜单名
      menuName1: "图书管理",
      menuName2: "图书信息",
      menuName3: "",
      menuName4_1: "新晨考核1",
      menuName4_2: "新晨考核2",
      menuName4_3: "新晨考核3",

      //管理员名字
      nickname: sessionStorage.getItem('user'),

      route: {
        name: "",
        title: "",
        path: "",
        close: true,
      },

      //头像地址
      imageUrl: "",

      //用户信息
      userLog: new(user)
    };
  },
  created() {
    this.activePath = sessionStorage.getItem("activePath");
    window.addEventListener("beforeunload", () => {
      sessionStorage.setItem("storeState", JSON.stringify(this.$store.state));
    });
    //在页面加载时读取sessionStorage里的状态信息
    if (sessionStorage.getItem("storeState")) {
      // repLaceState，替换store的根状态
      this.$store.replaceState(
        Object.assign(
          {},
          this.$store.state,
          JSON.parse(sessionStorage.getItem("storeState"))
        )
      );
    }
    //console.log(this.editableTabs);
    this.editableTabs = this.$store.state.list;

    //查询用户信息
    this.getUser()
  },
  methods: {
    //删除标签
    removeTab(targetName) {
      console.log(targetName);
      let tabs = this.editableTabs;
      this.editableTabs = tabs.filter((item) => item.name !== targetName);

      this.$store.dispatch("outList", targetName);
      this.$router.go(-1);
      //console.log(this.editableTabs);
    },

    //保存激活的链接地址
    saveNavState(path, name) {
      //将当前连接地址存到sessionStorage
      sessionStorage.setItem("activePath", path);
      this.activePath = path;
      //将路由信息给数据模型
      this.route.name = path;
      this.route.title = name;
      this.route.path = path;
      this.route.close = true;
      //将路由信息存到Vuex
      this.$store.dispatch("setList", this.route);
      //跳转
      this.$router.push(path);
      //再将Vuex里面的的所有路由信息给data里面要做循环渲染的数据模型
      this.editableTabs = this.$store.state.list;
      //console.log(this.$store.state.list);
    },

    tiaoshi() {
      //console.log(this.$route)
      //console.log(this.$store.state.list);
    },

    tabclick(tab) {
      //console.log(tab)
      this.activePath = tab.name;
      for (var i = 0; i < this.$store.state.list.length; i++) {
        if (tab.label == this.$store.state.list[i].title) {
          this.$router.push(this.$store.state.list[i].path);
        }
      }
    },

    home(path) {
      this.activePath = path;
      this.$router.push(path);
    },

    //头像上传
    handleAvatarSuccess(res) {
        console.log(res)
        this.userLog.head = res.data
        this.userLog.id = 1
        this.userLog.username = 'root'
        //console.log(this.userLog)
        saveUser(this.userLog).then(res=>{
          console.log(res)
          //console.log(this.imageUrl)
        })
          this.imageUrl = "http://127.0.0.1:9090"+res.data
        // console.log(this.imageUrl)
        // this.imageUrl = "127.0.0.1:9090"
    },

    //页面渲染时查用户数据
    getUser() {
      const userModel = new requestPageData()
      this.userLog.id = 1
      userModel.condition = this.userLog
      userModel.pageCondition.pageNo = 1
      userModel.pageCondition.pageSize = 1

      //console.log(userModel)
      selectUserList(userModel).then(res=>{
        console.log(res)
        this.imageUrl = "http://127.0.0.1:9090"+res.data.resultPages[0].head
      })
    }
  },
};
</script>
<style lang="less" scoped>
.adminHome {
  height: 100%;
  .el-container {
    height: 100% !important;
    .el-header {
      background-color: rgb(56 82 108);
      color: #fff7f7db;
      text-align: center;
      display: flex;
      justify-content: space-between;

      .headLeft {
        //background-color: aqua;
        line-height: 60px;
        font-size: 3rem;
        font-family: "Ubuntu", sans-serif;
      }

      .headRight {
        display: flex;
        line-height: 60px;
        background-color: rgb(56 82 108);
        width: 300px;
        font-family: "Ubuntu", sans-serif;
        .avatar {
          background-color: rgb(56 82 108);
          width: 50px;
          height: 50px;
          line-height: 98px;
          border-radius: 50%;
          margin-right: 30px;
        }

        .logout {
          width: 100px;
          padding: 0px;
          margin-left: 30px;
        }

        .el-button--primary {
          color: #fff;
          background-color: #38526c;
          border-color: #38526c;
        }
      }
    }

    .el-aside {
      background-color: #d3dce6;
      color: #333;
      text-align: center;
      height: 100% !important;
      .el-menu {
        height: 100%;
        border-right: solid 0px;
        background-color: #0c356a87 !important;
        .el-menu-item {
          background-color: #6984a4 !important;
        }
        // /deep/.el-submenu {
        //   padding-right: 0px !important;
        // }
        /deep/.el-submenu__title {
          background-color: #6984a4 !important;
          font-size: 15px;
          font-family: "Open Sans Condensed", sans-serif;
          padding-right: 55px !important;
        }
      }
    }

    .el-main {
      background-color: #e9eef3;
      color: #333;
      text-align: center;
      /deep/.el-tabs__item {
        color: white;
        background-color: #4d77a3 !important;
        border-radius: 20% 10% 0 0 !important;
      }
      // /deep/.el-tabs__item:hover {
      //   color: white;
      // }
    }
  }
}
</style>
