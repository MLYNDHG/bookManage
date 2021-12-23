<template>
  <div class="vueContent">
    <el-card
      shadow="hover"
      :body-style="{ padding: '0px' }"
      v-for="s in cardContent"
      :key="s"
    >
      {{ s.mm }}
      <div class="cardImage">图片</div>

      <div class="log">描述</div>
    </el-card>
  </div>
</template>
<script>
import { selectResourceBasicList } from "@/services/topicOneController.js";
import resourcesLog from '@/model/resourcesLogModel.js'
export default {
  data() {
    return {
      currentDate: new Date(),

      //卡片内容
      cardContent: [
        {
          name: "ds",
          mm: "1",
        },
        {
          name: "ddd",
          mm: "2",
        },
        {
          name: "dddfff",
          mm: "3",
        },
        {
          name: "dddfff",
          mm: "3",
        },
        {
          name: "dddfff",
          mm: "3",
        },
        {
          name: "dddfff",
          mm: "3",
        },
      ],
    };
  },

  created() {
      this.getAll()
  },

  methods: {
      getAll() {
          const log = new resourcesLog()
          selectResourceBasicList(log).then(res=>{
              console.log(res)
          })
      }
  },
};
</script>
<style lang="less">
.vueContent {
  display: flex;
  flex-wrap: wrap;
  .el-card {
    margin: 10px;
    width: 320px;
    height: 100px;
    cursor: pointer;
    display: flex !important;
    .cardImage {
      background-color: violet;
    }

    .el-card__body {
      display: flex;
    }

    .log {
      width: 300px;
      background-color: yellowgreen;
    }
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
