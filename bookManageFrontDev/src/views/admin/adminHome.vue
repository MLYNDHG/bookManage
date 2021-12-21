<template>
  <div class="adminHome">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap"
      rel="stylesheet"
    />
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <div class="headLeft">HTTB 图书馆里系统</div>
        <div class="headRight">
          <div class="avatar">头像</div>
          NickName
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
              <span slot="title">首&nbsp;&nbsp;&nbsp;&nbsp;页</span>
            </el-menu-item>
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>书籍管理</span>
              </template>
              <el-menu-item
                index="/home/bookmanage"
                @click="saveNavState('/home/bookmanage', name)"
                >{{ name }}</el-menu-item
              >
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>会员管理</span>
              </template>
              <el-menu-item
                index="/home/bookinfo"
                @click="saveNavState('/home/bookinfo', name2)"
                >{{ name2 }}</el-menu-item
              >
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>借阅管理</span>
              </template>
              <el-menu-item index="1-1">选项1</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>新晨作业</span>
              </template>
              <el-menu-item index="1-1">选项1</el-menu-item>
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
export default {
  data() {
    return {
      show: true,
      editableTabsValue: "2",
      editableTabs: this.$store.state.list,
      tabIndex: 2,

      //被激活的链接地址
      activePath: "",

      name: "图书管理",

      name2: "图书信息",

      route: {
        name: "",
        title: "",
        path: "",
        close: true,
      },
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
      this.activePath=path
      this.$router.push(path)
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
      background-color: #b3c0d1;
      color: #333;
      text-align: center;
      display: flex;
      justify-content: space-between;

      .headLeft {
        background-color: aqua;
        line-height: 60px;
        font-size: 3rem;
        font-family: "Ubuntu", sans-serif;
      }

      .headRight {
        display: flex;
        line-height: 60px;
        background-color: antiquewhite;
        width: 200px;
        font-family: "Ubuntu", sans-serif;
        .avatar {
          background-color: blueviolet;
          width: 1000px;
          border-radius: 50%;
          margin-right: 30px;
        }

        .logout {
          width: 100px;
          padding: 0px;
          margin-left: 30px;
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
        .el-submenu {
          padding-right: 0px !important;
        }
        .el-submenu__title {
          padding-right: 55px !important;
        }
      }
    }

    .el-main {
      background-color: #e9eef3;
      color: #333;
      text-align: center;
      .el-tabs__item {
        color: white;
        background-color: #409eff !important;
        border-radius: 20% 10% 0 0 !important;
      }
      // /deep/.el-tabs__item:hover {
      //   color: white;
      // }
    }
  }
}
</style>
