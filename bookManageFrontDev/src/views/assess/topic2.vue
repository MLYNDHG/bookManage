<template>
  <div class="vueContent">
    <div class="title">
      <p>应用资源配置管理</p>

      <el-button type="primary" round size="mini" @click="addResources"
        >+ 新建资源</el-button
      >
    </div>
    <!-- 卡片区域 -->

    <el-card
      shadow="hover"
      :body-style="{ padding: '0px' }"
      v-for="s in cardContent"
      :key="s.id"
    >
      <!-- 图片区域 -->
      <div class="cardImage">
        <img class="img" :src="imageUrl + s.image" alt="" />
      </div>

      <div class="log">
        <p>{{ s.chinesename }}</p>

        <!-- 标签 -->
        <div>
          <el-tag size="mini" type="success">{{ s.pname }}</el-tag>
          <el-tag size="mini" type="warning">{{ s.sname }}</el-tag>
        </div>

        <!-- 编辑区域 -->
        <div class="edit">
          <el-button type="text" @click="basicMes(s.id)">基本信息</el-button>
          <el-button type="text" @click="deploy(s.sid, s.id)"
            >配置信息</el-button
          >
          <el-button type="text" class="delete" @click="deletee(s.id)"
            >删除</el-button
          >
        </div>
      </div>
    </el-card>

    <!-- 新增对话框 -->
    <el-dialog
      title="新建资源"
      :visible.sync="addResourcesDialog"
      @close="addDialogColse('insertResourcesRef')"
      width="350px"
    >
      <el-form ref="insertResourcesRef" :model="addForm">
        <el-form-item label="资源名称" label-width="100px">
          <el-input autocomplete="off" v-model="addForm.englishname"></el-input>
        </el-form-item>
        <el-form-item label="中文名称" label-width="100px">
          <el-input autocomplete="off" v-model="addForm.chinesename"></el-input>
        </el-form-item>
        <el-form-item label="资源类型" label-width="100px">
          <el-cascader
            v-model="addForm.sid"
            :options="options"
            :props="{ expandTrigger: 'hover' }"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="资源描述" label-width="100px">
          <el-input autocomplete="off" v-model="addForm.description"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addResourcesDialog = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 基本信息对话框 -->
    <el-dialog
      title="新建资源"
      :visible.sync="basicMesDialog"
      @close="basicMesClose"
    >
      <el-form ref="basicMesRef" :model="addForm">
        <el-form-item label="资源名称" label-width="100px">
          <el-input autocomplete="off" v-model="addForm.englishname"></el-input>
        </el-form-item>
        <el-form-item label="中文名称" label-width="100px">
          <el-input autocomplete="off" v-model="addForm.chinesename"></el-input>
        </el-form-item>
        <el-form-item label="资源类型" label-width="100px">
          <el-cascader
            v-model="addForm.sid"
            :options="options"
            :props="{ expandTrigger: 'hover' }"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="资源描述" label-width="100px">
          <el-input autocomplete="off" v-model="addForm.description"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="basicMesDialog = false">取 消</el-button>
        <el-button type="primary" @click="editBasicMes">确 定</el-button>
      </div>
    </el-dialog>

    <!-- Redis配置对话框 -->
    <el-dialog title="Redis配置" :visible.sync="RedisDialog" @close="redisDialogClose" width="700px">
      <el-form :inline="true" ref="insertResourcesRef" :model="redisForm">
        <el-form-item label="集群主机地址">
          <el-input
            autocomplete="off"
            v-model="redisForm.cluteraddress"
          ></el-input>
        </el-form-item>
        <el-form-item label="最大跳转次数" label-width="100px">
          <el-input autocomplete="off" v-model="redisForm.maxjumps"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="100px">
          <el-input autocomplete="off" v-model="redisForm.password"></el-input>
        </el-form-item>
        <el-form-item label="超时时间" label-width="100px">
          <el-input autocomplete="off" v-model="redisForm.timeout"></el-input>
        </el-form-item>
        <el-form-item label="线程最大活动数" label-width="100px">
          <el-input
            autocomplete="off"
            v-model="redisForm.maxthreadactivities"
          ></el-input>
        </el-form-item>
        <el-form-item label="线程最大空闲数" label-width="100px">
          <el-input
            autocomplete="off"
            v-model="redisForm.maxthreadidles"
          ></el-input>
        </el-form-item>
        <el-form-item label="线程最小空闲数" label-width="100px">
          <el-input
            autocomplete="off"
            v-model="redisForm.minthreadidles"
          ></el-input>
        </el-form-item>
        <el-form-item label="线程最大等待" label-width="100px">
          <el-input
            autocomplete="off"
            v-model="redisForm.maxthreadwaits"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="RedisDialog = false">取 消</el-button>
        <el-button type="primary" @click="addRedis">确 定</el-button>
      </div>
    </el-dialog>

    <!-- RocketMQ配置对话框 -->
    <el-dialog
      title="RocketMQ配置"
      :visible.sync="RocketMqDialog"
      @close="rocketDialogClose"
    >
      <label for="ddd">nameServer地址</label>
      <el-input
        class="RocketMQ"
        id="ddd"
        label="sss"
        autocomplete="off"
        v-model="rocketMQForm"
      ></el-input>

      <div slot="footer" class="dialog-footer">
        <el-button @click="RocketMqDialog = false">取 消</el-button>
        <el-button type="primary" @click="addRocketMQ">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {
  selectResourceBasicList,
  selectTypeList,
  saveResourcesBasic,
  deleteResources,
} from "@/services/topicTwoController.js";
import {
  selectRedisByResourcesId,
  saveRedis,
} from "@/services/redisController.js";
import {
  selectRocketmqByResourcesId,
  saveRocketmq,
} from "@/services/rocketMQController.js";
import resourcesLog from "@/model/resourcesLogModel.js";
import request from "@/utils/request";
import addModel from "@/model/addResourcesModel.js";
import redisModel from "@/model/redisModel.js";
import rocketMQModel from "@/model/rocketMQModel.js";
export default {
  data() {
    return {
      currentDate: new Date(),

      //服务器地址
      imageUrl: request.baseUrl,

      //卡片内容
      cardContent: [],

      //新增资源弹框
      addResourcesDialog: false,

      //基本信息弹框
      basicMesDialog: false,

      //新增资源下拉菜单
      options: [],

      //新增资源模型
      addForm: {
        englishname: "",
        chinesename: "",
        sid: [],
        description: "",
      },

      //redis配置模型
      redisForm: {
        cluteraddress: "",
        maxjumps: "",
        password: "",
        timeout: "",
        maxthreadactivities: "",
        maxthreadidles: "",
        minthreadidles: "",
        maxthreadwaits: "",
      },

      //RocketMQ配置模型
      rocketMQForm: "",

      RedisDialog: false,

      RocketMqDialog: false,

      //获取当前弹出框id
      redisId: 0,
      rocketMQID: 0,

      basicMesId: 0,

      //判断查到的信息是否为空
      configLog: {},
    };
  },

  created() {
    this.getAll();
    this.getMenu();
  },

  methods: {
    getAll() {
      const log = new resourcesLog();
      selectResourceBasicList(log).then((res) => {
        this.cardContent = res.data;
      });
    },

    //获取当前卡片id
    basicMes(a) {
      this.basicMesId = a;
      const log = new resourcesLog();
      log.id = a;
      selectResourceBasicList(log).then((res) => {
        this.addForm.chinesename = res.data[0].chinesename;
        this.addForm.englishname = res.data[0].englishname;
        this.addForm.description = res.data[0].description;
        this.addForm.sid[0] = res.data[0].pid;
        this.addForm.sid[1] = res.data[0].sid;
        console.log(this.addForm);
        this.basicMesDialog = true;
      });
    },

    //点击弹出新增资源
    addResources() {
      this.addResourcesDialog = true;
    },

    //获取二级联动菜单
    getMenu() {
      selectTypeList().then((res) => {
        for (var i = 0; i < res.data.length; i++) {
          this.options.push({
            value: res.data[i].id,
            label: res.data[i].name,
            children: [],
          });
          for (var j = 0; j < res.data[i].slist.length; j++) {
            this.options[i].children.push({
              value: res.data[i].slist[j].id,
              label: res.data[i].slist[j].name,
            });
          }
        }
      });
    },

    //点击增加资源
    add() {
      const addForm = new addModel();
      addForm.englishname = this.addForm.englishname;
      addForm.chinesename = this.addForm.chinesename;
      addForm.sid = this.addForm.sid[1];
      addForm.description = this.addForm.description;
      saveResourcesBasic(addForm).then((res) => {
        this.addResourcesDialog = false;
        this.$message({ message: "新增成功", type: "success" });
        this.getAll();
      });
    },

    editBasicMes() {
      this.$confirm("确定编辑此资源吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          const edit = new addModel();
          edit.id = this.basicMesId;
          edit.englishname = this.addForm.englishname;
          edit.chinesename = this.addForm.chinesename;
          edit.sid = this.addForm.sid[1];
          edit.description = this.addForm.description;
          saveResourcesBasic(edit).then((res) => {
            console.log(res);
            this.basicMesDialog = false;
            this.$message({ message: "修改成功", type: "success" });
            this.getAll()
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消编辑",
          });
        });
    },

    //监听新增对话框关闭事件
    addDialogColse(name) {
      this.$refs[name].resetFields();
    },

    //监听rocket配置信息对话框关闭时间
    rocketDialogClose() {
      this.rocketMQForm = "";
    },

    //监听redis关闭事件
    redisDialogClose() {
      this.redisForm.cluteraddress = "";
      this.redisForm.maxjumps = "";
      this.redisForm.password = "";
      this.redisForm.timeout = "";
      this.redisForm.maxthreadactivities = "";
      this.redisForm.maxthreadidles = "";
      this.redisForm.minthreadidles = "";
      this.redisForm.maxthreadwaits = "";
    },

    //删除资源卡片
    deletee(id) {
      this.$confirm("确定删除此资源吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteResources(id)
            .then((res) => {
              this.$message({
                type: "success",
                message: "删除成功",
              });

              this.getAll();
            })
            .catch((res) => {
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    //Redis配置  Rocket配置
    deploy(sid, id) {
      if (sid == 10) {
        selectRedisByResourcesId(id).then((res) => {
          console.log(res);
          if (res.data == null) {
            this.redisId = id;
            this.configLog = res.data;
          } else {
            this.configLog = res.data;
            this.redisId = res.data.id;
            this.redisForm.cluteraddress = res.data.cluteraddress;
            this.redisForm.maxjumps = res.data.maxjumps;
            this.redisForm.password = res.data.password;
            this.redisForm.timeout = res.data.timeout;
            this.redisForm.maxthreadactivities = res.data.maxthreadactivities;
            this.redisForm.maxthreadidles = res.data.maxthreadidles;
            this.redisForm.minthreadidles = res.data.minthreadidles;
            this.redisForm.maxthreadwaits = res.data.maxthreadwaits;
            //console.log(this.redisId)
          }
          this.RedisDialog = true;
        });
      } else if (sid == 16) {
        console.log(id);
        selectRocketmqByResourcesId(id).then((res) => {
          if (res.data == null) {
            this.rocketMQID = id;
            this.configLog = res.data;
          } else {
            this.configLog = res.data;
            this.rocketMQForm = res.data.nameserver;
            this.rocketMQID = res.data.id;
            //console.log(this.rocketMQID)
          }
          this.RocketMqDialog = true;
        });
      }
    },

    //添加redis配置信息
    addRedis() {
      const model = new redisModel();
      if (this.configLog == null) {
        model.rid = this.redisId;
        model.cluteraddress = this.redisForm.cluteraddress;
        model.maxjumps = this.redisForm.maxjumps;
        model.password = this.redisForm.password;
        model.timeout = this.redisForm.timeout;
        model.maxthreadactivities = this.redisForm.maxthreadactivities;
        model.maxthreadidles = this.redisForm.maxthreadidles;
        model.minthreadidles = this.redisForm.minthreadidles;
        model.maxthreadwaits = this.redisForm.maxthreadwaits;
        //console.log(model);
      } else {
        model.id = this.redisId;
        model.cluteraddress = this.redisForm.cluteraddress;
        model.maxjumps = this.redisForm.maxjumps;
        model.password = this.redisForm.password;
        model.timeout = this.redisForm.timeout;
        model.maxthreadactivities = this.redisForm.maxthreadactivities;
        model.maxthreadidles = this.redisForm.maxthreadidles;
        model.minthreadidles = this.redisForm.minthreadidles;
        model.maxthreadwaits = this.redisForm.maxthreadwaits;
      }

      this.$confirm("确定新增或修改配置吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          saveRedis(model).then((res) => {
            this.$message({ message: "修改成功", type: "success" });
            this.RedisDialog = false;
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },

    //新增 Rocket 配置
    addRocketMQ() {
      const rocket = new rocketMQModel();
      if (this.configLog == null) {
        //console.log(this.rocketMQID)
        rocket.rid = this.rocketMQID;
        rocket.nameserver = this.rocketMQForm;
        //console.log(rocket)
      } else {
        rocket.id = this.rocketMQID;
        rocket.nameserver = this.rocketMQForm;
        //console.log(rocket)
      }
      this.$confirm("确定新增或修改配置吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //lconsole.log(rocket);
          saveRocketmq(rocket).then((res) => {
            this.$message({ message: "修改成功", type: "success" });
            this.RocketMqDialog = false;
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },

    //监听基本信息对话框关闭
    basicMesClose() {
      this.$refs.basicMesRef.resetFields();
      this.addForm.englishname = "";
      this.addForm.chinesename = "";
      this.addForm.sid.length = 0;
      this.addForm.description = "";
    },
  },
};
</script>
<style lang="less">
.vueContent {
  display: flex;
  flex-wrap: wrap;

  .title {
    width: 100%;
    margin: 5px;
    display: flex;
    justify-content: space-between;
    p {
      margin: 3px;
    }
  }
  .el-card {
    transition: all 0.4s;
    margin: 0.5px 5px;
    width: 320px;
    height: 100px;
    cursor: pointer;
    border-radius: 3%;
    display: flex !important;

    .edit {
      .el-button {
        color: rgba(255, 255, 255, 0.89);
      }

      .el-button:hover {
        text-decoration: underline;
      }
      .delete {
        color: rgba(233, 31, 31, 0.856);
      }
    }

    .el-tag {
      margin: 1px 3px;
    }

    p {
      margin: 5px;
    }

    .img {
      width: 100px;
      height: 100px;
    }
    .cardImage {
      background-color: violet;
    }

    .el-card__body {
      display: flex;
    }

    .log {
      width: 240px;
      background-color: #a0c2ea;
    }
  }

  .el-card:hover {
    border-style: none;
    box-shadow: 1px 5px 5px rgb(145, 141, 141) !important;
  }
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.RocketMQ {
  width: 500px !important;
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
</style>
