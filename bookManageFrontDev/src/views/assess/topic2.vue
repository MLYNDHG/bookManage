<template>
  <div class="vueContent">
    <div class="title">
      <p>应用资源配置管理</p>

      <el-button type="primary" round size="mini">+ 新建资源</el-button>
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
            <el-button type="text">配置信息</el-button>
            <el-button type="text" class="delete">删除</el-button>
          
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
import { selectResourceBasicList } from "@/services/topicOneController.js";
import resourcesLog from "@/model/resourcesLogModel.js";
import request from "@/utils/request";
export default {
  data() {
    return {
      currentDate: new Date(),

      //服务器地址
      imageUrl: request.baseUrl,

      //卡片内容
      cardContent: [],
    };
  },

  created() {
    this.getAll();
  },

  methods: {
    getAll() {
      const log = new resourcesLog();
      selectResourceBasicList(log).then((res) => {
        console.log(res);
        this.cardContent = res.data;
      });
    },

    sss(a) {
        console.log(a)
    }
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
