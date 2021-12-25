<template>
  <div class="adminHome">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap"
      rel="stylesheet"
    />
    <!-- 字体2 -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300;700&display=swap"
      rel="stylesheet"
    />

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap"
      rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&family=Ubuntu:wght@500&display=swap"
      rel="stylesheet"
    />
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <div class="headLeft">
          <transition name="slide-fade">
            <span v-show="Bb">B</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="a">o</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="b">o</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="c">k</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="d">M</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="e">a</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="f">n</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="g">a</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="h">g</span>
          </transition>
          <transition name="slide-fade">
            <span v-show="i">e</span>
          </transition>
        </div>
        <div class="headRight">
          <div class="avatar">
            <el-upload
              class="avatar-uploader"
              :action="upImageUrl"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
            >
              <el-tooltip
                class="item"
                effect="dark"
                content="点击上传或更新头像"
                placement="bottom"
              >
                <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-tooltip>
            </el-upload>
          </div>
          {{ nickname }}
          <el-button
            class="logout"
            type="primary"
            round
            size="mini"
            @click="logOut"
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
            <transition name="slide-fade">
              <el-menu-item index="/home" @click="home('/home')" v-show="show1">
                <span slot="title">HOME</span>
              </el-menu-item>
            </transition>
            <transition name="slide-fade">
              <el-submenu index="1" v-show="show2">
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
            </transition>
            <transition name="slide-fade">
              <el-submenu index="2" v-show="show3">
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
            </transition>
            <transition name="slide-fade">
              <el-submenu index="3" v-show="show4">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>BorrowManage</span>
                </template>
                <el-menu-item
                  index="/home/booklist"
                  @click="saveNavState('/home/booklist', name3)"
                  >{{ name3 }}</el-menu-item
                >
              </el-submenu>
            </transition>
            <transition name="slide-fade">
              <el-submenu index="4" v-show="show5">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>XinChen</span>
                </template>
                <el-menu-item
                  index="/home/topic1"
                  @click="saveNavState('/home/topic1', menuName4_1)"
                  >{{ menuName4_1 }}</el-menu-item
                >
                <el-menu-item
                  index="/home/topic2"
                  @click="saveNavState('/home/topic2', menuName4_2)"
                  >{{ menuName4_2 }}</el-menu-item
                >
                <el-menu-item
                  index="/home/topic3"
                  @click="saveNavState('/home/topic3', menuName4_3)"
                  >{{ menuName4_3 }}</el-menu-item
                >
              </el-submenu>
            </transition>
          </el-menu>
        </el-aside>

        <el-main>
          <transition name="slide-fade">
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
          </transition>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { saveUser, selectUserList } from "@/services/UserController.js";
import user from "@/model/User.js";
import requestPageData from "@/model/RequestPageData.js";
import request from "@/utils/request";
export default {
  data() {
    return {
      show: true,
      editableTabsValue: "2",
      editableTabs: this.$store.state.list,
      tabIndex: 2,

      //被激活的链接地址
      activePath: "",
      //upImage Url
      upImageUrl:'',      
      //菜单名
      menuName1: "图书管理",
      menuName2: "用户信息",
      menuName3: "借阅管理",
      menuName4_1: "新晨考核1",
      menuName4_2: "新晨考核2",
      menuName4_3: "新晨考核3",
      //管理员名字
      nickname: sessionStorage.getItem("user"),
      name3: "Borrowing Management",
      route: {
        name: "",
        title: "",
        path: "",
        close: true,
      },
      //头像地址
      actionn: request.baseUrl + '/uploadImage',
      imageUrl: "",

      //用户信息
      userLog: new user(),

      //标题动画
      Bb: false,
      a: false,
      b: false,
      c: false,
      d: false,
      e: false,
      f: false,
      g: false,
      h: false,
      i: false,
      //菜单动画
      show1: false,
      show2: false,
      show3: false,
      show4: false,
      show5: false,
    };
  },
  created() {
    this.menuAni();
    this.headAni();
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
    this.editableTabs = this.$store.state.list;
    //查询用户信息
    this.getUser();
  },mounted() {
    this.upImageUrl=request.baseUrl+'/uploadImage';
  },
  methods: {
    //标题动画
    headAni() {
      setTimeout(() => {
        this.Bb = true;
      }, 200);
      setTimeout(() => {
        this.a = true;
      }, 300);
      setTimeout(() => {
        this.b = true;
      }, 400);
      setTimeout(() => {
        this.c = true;
      }, 500);
      setTimeout(() => {
        this.d = true;
      }, 600);
      setTimeout(() => {
        this.e = true;
      }, 700);
      setTimeout(() => {
        this.f = true;
      }, 800);
      setTimeout(() => {
        this.g = true;
      }, 900);
      setTimeout(() => {
        this.h = true;
      }, 1000);
      setTimeout(() => {
        this.i = true;
      }, 1100);
    },
    //菜单动画
    menuAni() {
      setTimeout(() => {
        this.show1 = true;
      }, 200);
      setTimeout(() => {
        this.show2 = true;
      }, 300);
      setTimeout(() => {
        this.show3 = true;
      }, 400);
      setTimeout(() => {
        this.show4 = true;
      }, 500);
      setTimeout(() => {
        this.show5 = true;
      }, 600);
    },

    //删除标签
    removeTab(targetName) {
      let tabs = this.editableTabs;
      this.editableTabs = tabs.filter((item) => item.name !== targetName);

      this.$store.dispatch("outList", targetName);
      this.$router.go(-1);
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
    },

    logOut() {

      //退出时清空sessionStorage存的东西
      sessionStorage.clear();
      this.$store.dispatch("clearStore");
      this.$router.push("/");
    },

    tabclick(tab) {
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
      this.userLog.head = res.data;
      this.userLog.id = 1;
      this.userLog.username = "root";
      saveUser(this.userLog).then();
      this.imageUrl = request.baseUrl+ res.data;
    },

    //页面渲染时查用户数据
    getUser() {
      const userModel = new requestPageData();
      this.userLog.id = 1;
      userModel.condition = this.userLog;
      userModel.pageCondition.pageNo = 1;
      userModel.pageCondition.pageSize = 1;
      selectUserList(userModel).then((res) => {
        this.imageUrl = request.baseUrl + res.data.resultPages[0].head;
      });
    },
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
          font-family: "Ubuntu", sans-serif;
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
      .el-tabs {
        height: 100%;

        /deep/.el-tabs__content {
          height: calc(100% - 43.4px) !important;
        }
      }
    }

    .slide-fade-enter-active {
      transition: opacity 1.8s;
    }
    .slide-fade-leave-active {
      transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
    }
    .slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active for below version 2.1.8 */ {
      /* transform: translateY(-100px); */
      opacity: 0;
    }
  }
}
</style>
