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
        <img src="../../assets/img/6.jpg" alt="" class="img-1" />
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
                v-model="ruleForm.name"
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
export default {
  data() {
    return {
      ruleForm: {
        name: "",
        password: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>



<style>
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