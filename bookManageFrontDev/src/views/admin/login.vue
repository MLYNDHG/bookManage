<template>
  <div class="login">
    <!-- 字体 -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300;700&display=swap"
      rel="stylesheet"
    />

    <!-- 封面图片 -->
    <div class="container">
      <div class="left">
        <img src="../../assets/img/9.jpg" alt="" class="img-1" />
      </div>

      <!-- 右边的块 -->
      <div class="right">
        <div class="right-div">
          <div class="word-login">L O G I N</div>
          <div class="word-login-1">L O G I N</div>

          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="账号" prop="name">
              <el-input
                v-model="ruleForm.name"
                placeholder="Enter your Name"
              ></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input
                v-model="ruleForm.password"
                placeholder="Enter your Password"
              ></el-input>
            </el-form-item>

            <!-- button按钮 -->
            <el-row>
              <el-button size="medium" @click="submitForm('ruleForm')"
                >LOGIN</el-button
              >
            </el-row>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>




<script>

import  {login}  from "@/services/UserController.js"; //services写接口//JS6 解构赋值{login}，login是方法所以需要解构使用
import User from "@/model/User.js"; //modle里写数据

export default {
  data() {
    return {

      User: new User(),

      ruleForm: {
        name: "",
        password: "",
      },

      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 5, message: "长度在 1 到 5 个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 1, max: 5, message: "长度在 1 到 5 个字符", trigger: "blur" },
        ],
      },
    };
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //get不用this,get只请求一两个参数；
          //如果是post会有很多参数，而User是一个对象，所以要用this.调用
          this.User.username = this.ruleForm.name;         
          this.User.password = this.ruleForm.password;

          console.log(this.User);
          // debugger
          login(this.User).then((res) => {
            let token = res.data;
            console.log(token);
            this.userToken = token;

            //会话存储
            sessionStorage.setItem("user", this.ruleForm.name);
            sessionStorage.setItem("userToken", token);
            
            //将用户名放入vuex中  actiond调用
            this.$store.dispatch("setUser", this.ruleForm.name);
            this.$store.dispatch("setToken", token);

            // 将用户token保存到vuex中
            // this.changeLogin({ Authorization: token });
            this.$store.dispatch("setLogin", token);

            //打印login状态
            console.log(this.$store.state.isLogin);
            this.$message({
              message: "登录成功",
              type: "success",
            });

            this.$router.push("/home");
          });
        } else {
          this.$message.error("登陆失败");
          return false;
        }
      });
    },
  },
};
</script>



<style scoped>
.login {
  /* background-image:url(../../assets/img/1.gif) ; */

  background-color: rgb(240, 240, 240);

  width: 100%;
  height: 100%;

  /* 字体 */
  font-family: "Open Sans Condensed", sans-serif;

  text-align: center;

  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  display: flex;
  width: 1400px;
  height: 750px !important;
  flex-direction: row;
  margin: 0 auto;
}

.word-login {
  width: 438px;
  position: absolute;
  left: 62px;
  top: 45px;
  z-index: 1;
}

.word-login-1 {
  font-size: 111px;
  font-weight: 111;
  padding-bottom: 25px;
}

.right-div {
  width: 86%;
  padding: 0px 54px !important;
}

/* 右边背景 */
.right {
  background-color: rgb(255, 255, 255);
  width: 67% !important;
  height: 100%;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: space-around;
}

/* 左边背景 */
.left {
  background-color: rgb(42, 99, 144);
  width: 33% !important;
  height: 100%;
  position: relative;
}

.img-1 {
  width: 438px;
  position: absolute;
  left: 66px;
  top: 44px;
  z-index: 1;
}

/* 登录按钮 */
.el-button {
  width: 490.94px;
  margin-left: 99px;
  font-family: "Open Sans Condensed", sans-serif;
  width: 709px;
  margin-left: 100px !important;
  background: #2a6390 !important;
  color: #ecf5ff !important;
}

/* .right-dev{
 
} */
</style>