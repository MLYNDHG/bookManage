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
          <el-button class="logout" type="primary" round size="mini"
            >退出</el-button
          >
        </div>
      </el-header>

      <el-container>
        <el-aside width="200px">
          <!-- 侧边栏菜单 -->
          <el-menu
            default-active="2"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>导航一</span>
              </template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },

  methods: {},
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
        .el-submenu {
          padding-right: 0px !important;
        };
      }
    }

    .el-main {
      background-color: #e9eef3;
      color: #333;
      text-align: center;
    }
  }
}
</style>
