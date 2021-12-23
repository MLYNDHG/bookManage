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
          <el-button type="text" @click="sss(s.id)">基本信息</el-button>
          <el-button type="text" @click="deploy(s.sid,id)">配置信息</el-button>
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
      @close="addDialogColse"
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
        <el-button>取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>

    <!-- Redis配置对话框 -->
    <el-dialog title="Redis配置" :visible.sync="RedisDialog">
      <el-form :inline="true" ref="insertResourcesRef" :model="redisForm">
        <el-form-item label="集群主机地址" label-width="100px">
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
        <el-button>取 消</el-button>
        <el-button type="primary" @click="addRedis">确 定</el-button>
      </div>
    </el-dialog>

    <!-- Redis配置对话框 -->
    <el-dialog title="Redis配置" :visible.sync="RocketMqDialog">
      <el-form ref="insertResourcesRef" :model="addForm">
        <el-form-item label="nameServer地址" label-width="100px">
          <el-input autocomplete="off" v-model="addForm.englishname"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button>取 消</el-button>
        <el-button type="primary">确 定</el-button>
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
import resourcesLog from "@/model/resourcesLogModel.js";
import request from "@/utils/request";
import addModel from "@/model/addResourcesModel.js";
import redisModel from "@/model/redisModel.js";
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

      RedisDialog: false,

      RocketMqDialog: false,
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
        console.log(res);
        this.cardContent = res.data;
      });
    },

    //获取当前卡片id
    sss(a) {
      console.log(a);
    },

    //点击新增资源
    addResources() {
      this.addResourcesDialog = true;
    },

    //获取二级联动菜单
    getMenu() {
      selectTypeList().then((res) => {
        console.log(res.data);
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
        //console.log(this.options)
      });
    },

    //点击增加资源
    add() {
      //console.log(this.addForm)
      const addForm = new addModel();
      addForm.englishname = this.addForm.englishname;
      addForm.chinesename = this.addForm.chinesename;
      addForm.sid = this.addForm.sid[1];
      addForm.description = this.addForm.description;
      //console.log(addForm)
      saveResourcesBasic(addForm).then((res) => {
        console.log(res)
        this.addResourcesDialog = false;
        this.getAll();
      });
    },

    //监听新增对话框关闭事件
    addDialogColse() {
      this.$refs.insertResourcesRef.resetFields();
    },

    //删除资源卡片
    deletee(id) {
      console.log(id);
      this.$confirm("确定删除此资源吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteResources(id)
            .then((res) => {
              console.log(res);

              this.getAll();
            })
            .catch((res) => {
              console.log(res);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    //Redis配置
    deploy(sid) {
      if (sid == 10) {
          selectRedisByResourcesId(sid).then(res=>{
              console.log(res)
          })
        this.RedisDialog = true;
      } else {
        this.RocketMqDialog = true;
      }
    },

    //添加redis配置信息
    addRedis() {
      const model = new redisModel();
      model.cluteraddress = this.redisForm.cluteraddress;
      model.maxjumps = this.redisForm.maxjumps;
      model.password = this.redisForm.password;
      model.timeout = this.redisForm.timeout;
      model.maxthreadactivities = this.redisForm.maxthreadactivities;
      model.maxthreadidles = this.redisForm.maxthreadidles;
      model.minthreadidles = this.redisForm.minthreadidles;
      model.maxthreadwaits = this.redisForm.maxthreadwaits;

      saveRedis(model).then(res=>{
          console.log(res)
      })
    },
  },
};
</script>
<style lang="less">
.vueContent {
  display: flex;
  flex-wrap: wrap;

  .title {
    width: 1000px;
    margin: 5px;
    display: flex;
    justify-content: space-between;
    p {
      margin: 3px;
    }
  }
  .el-card {
    transition: all 0.4s;
    margin: 10px 3px;
    width: 335px;
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
</style>
