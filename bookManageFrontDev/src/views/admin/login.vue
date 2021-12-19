<template>
  <div class="login">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans+Condensed:wght@300;700&display=swap"
      rel="stylesheet"
    />

    <div class="container">
      <div class="left">
        <img src="../../assets/img/6.jpg" alt="" class="img-1" />
      </div>

      <div class="right">
        <div class="right-dev">
          <div class="word-login">L O G I N</div>
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

.word-lorgin {
  margin-bottom: 7px;
  font-size: 21px;
  font-weight: 650;
}

.right {
  background-color: rgb(255, 255, 255);
  width: 67% !important;
  height: 100%;

  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: space-around;
}
.left {
  background-color: rgb(42, 99, 144);
  width: 33% !important;
  height: 100%;
  position: relative;
}

.img-1 {
  width: 438px;
  position: absolute;
  left: -23px;
  top: 48px;
  z-index: 1;
}

/* .right-dev{
 
} */
</style>