<template>
  <div class="adminLogin">
    <h1>logo</h1>
    <div class="userInfo">
      <el-form :model="user" ref="user" label-width="100px">
        <el-form-item
          prop="name"
          label="用户名"
          :rules="{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur',
          }"
        >
          <el-input placeholder="请输入用户名" v-model="user.name" clearable>
          </el-input>
        </el-form-item>
        <el-form-item
          prop="password"
          label="密码"
          :rules="{
            required: true,
            message: '密码不能为空',
            trigger: 'blur',
          }"
        >
          <el-input
            placeholder="请输入密码"
            v-model="user.password"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item class="buttonItem">
          <el-button type="primary" @click="login('user')" class="loginButton"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
import { login } from "@/services/UserInfoController.js";
export default {
  data() {
    return {
      dialogFormVisible: false,
      userInfo: [{ name: "root", password: "123456" }],
      user: {
        name: "",
        password: "",
      },
      registerUser: {
        name: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapMutations(["changeLogin"]),
    login(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          login(this.user.name, this.user.password).then((res) => {
            let token = res.data;
            console.log(token);
            this.userToken = token;

            sessionStorage.setItem("user", this.user.name);
            sessionStorage.setItem("userToken", token);
            //将用户名放入vuex中  actiond调用
            this.$store.dispatch("setUser", this.user.name);
            this.$store.dispatch("setToken", token);

            // 将用户token保存到vuex中
            // this.changeLogin({ Authorization: token });
            this.changeLogin(token);
            //打印login状态
            console.log(this.$store.state.isLogin);
            this.$router.push("/login");
            this.success("登录成功");
          });
        } else {
          this.fail("账号密码错误");
          return false;
        }
      });
    },
    success(msg) {
      this.$message({
        message: msg,
        type: "success",
      });
    },
    fail(msg) {
      this.$message({
        message: msg,
        type: "warning",
      });
    },
  },
};
</script>

<style lang="less" scoped>
.container {
  width: 100%;
  .logo {
    width: 200px !important;
    height: 200px !important;
    margin-bottom: 50px;
  }

  .userInfo {
    margin: 0 auto;
    width: 30%;
    .buttonItem {
      /deep/ .el-form-item__content {
        display: flex !important;
        justify-content: space-between !important;
      }
      .loginButton {
        background-color: #c03854;
      }
    }
  }
}
</style>