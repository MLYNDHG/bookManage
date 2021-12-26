<template>
  <div class="topic3">
    <div class="head">
      <div class="head-title">流控规则配置管理</div>
      <div class="head-button">
        <el-button
          type="primary"
          round
          size="small"
          icon="el-icon-plus"
          @click="addFlowRule()"
          >新增流控制规则</el-button
        >
      </div>
    </div>
    <div class="topic3-table">
      <el-table :data="tableData" height="475" border style="width: 100%">
        <el-table-column
          prop="name"
          label="规则名称"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="flowpattern"
          label="流控模式"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="thresholdtype"
          label="阈值类型"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="threshold"
          label="阈值"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="thresholdpattern"
          label="阈值模式"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="floweffect" label="流控效果" align="center">
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editFlowRule(scope.row)"
              type="primary"
              icon="el-icon-edit"
              circle
            ></el-button>
            <el-button
              @click="delFlowRule(scope.row)"
              type="danger"
              icon="el-icon-delete"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      class="dialog"
      width="55%"
      top="2vh"
      :visible.sync="dialogVisible"
    >
      <div slot="title" class="header-title">
        <span class="title-name"><i class="el-icon-question"></i></span>
        <span class="title-age">{{ title }}流控规则</span>
      </div>

      <el-form
        :model="FlowRuleVO"
        :rules="rules"
        ref="ruleForm"
        label-position="left"
        label-width="80px"
      >
        <el-row>
          <el-col :span="24">
            <el-form-item label="选择资源">
              <el-transfer
                v-model="transferValue"
                :data="transferData"
                :titles="['源列表', '目的列表']"
              ></el-transfer>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="请求来源" prop="requestsource">
              <el-select
                v-model="FlowRuleVO.requestsource"
                placeholder="请选择请求来源"
              >
                <el-option
                  v-for="item in requestsourceOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row class="adjust-input">
          <el-col :span="12">
            <el-form-item label="规则名称" prop="name">
              <el-input
                v-model="FlowRuleVO.name"
                placeholder="请输入规则名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="阈值类型" prop="thresholdtype">
              <el-select
                v-model="FlowRuleVO.thresholdtype"
                placeholder="请选择阈值类型"
              >
                <el-option
                  v-for="item in thresholdtypeOptions"
                  :key="item.label"
                  :label="item.label"
                  :value="item.label"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row class="adjust-input">
          <el-col :span="8">
            <el-form-item label="单机阈值" prop="threshold">
              <el-input
                v-model.number="FlowRuleVO.threshold"
                placeholder="请输入单机阈值"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="流控模式" prop="flowpatternOptions">
              <el-select
                v-model="FlowRuleVO.flowpattern"
                placeholder="请选择流控模式"
              >
                <el-option
                  v-for="item in flowpatternOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="流控效果" prop="floweffect">
              <el-select
                v-model="FlowRuleVO.floweffect"
                placeholder="请选择流控效果"
              >
                <el-option
                  v-for="item in floweffectOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('ruleForm')">{{
          title
        }}</el-button
        ><el-button @click="dialogVisible = false">取 消</el-button>
        <el-button @click="resetForm('ruleForm')">重 置</el-button>
      </div>
    </el-dialog>
    <div class="pageCondition">
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageCondition.pageNo"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="pageCondition.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
import {
  deleteFlowRule,
  saveFlowRule,
  selectFlowRuleList,
} from "@/services/FlowRuleController.js";
import { selectSourcelist } from "@/services/SourceListController.js";
import requestPageData from "@/model/RequestPageData.js";
import FlowRuleVO from "@/model/FlowRuleVO.js";
import Flowrule from "@/model/Flowrule.js";
export default {
  data() {
    return {
      FlowRuleVO: new FlowRuleVO(),
      Flowrule: new Flowrule(),
      requestPageData: new requestPageData(),
      rules: {
        name: [{ required: true, message: "请输入规则名称", trigger: "blur" }],
        thresholdpattern: [
          {
            required: true,
            message: "请至少选择一个阈值类型",
            trigger: ["blur", "change"],
          },
        ],
        threshold: [
          {
            required: true,
            message: "请输入单机阈值",
            trigger: ["blur", "change"],
          },
          { type: "number", message: "单机阈值必须为数字值" },
        ],
      },
      requestsourceOptions: [
        {
          value: "xxx系统",
          label: "xxx系统",
        },
        {
          value: "xxx系统",
          label: "yyy系统",
        },
        {
          value: "xxx系统",
          label: "zzz系统",
        },
      ],
      thresholdtypeOptions: [
        {
          value: "QPS",
          label: "QPS",
        },
        {
          value: "并发线程数",
          label: "并发线程数",
        },
      ],
      flowpatternOptions: [
        {
          value: "直接",
          label: "直接",
        },
        {
          value: "关联",
          label: "关联",
        },
        {
          value: "链路",
          label: "链路",
        },
      ],
      flowpatternOptions: [
        {
          value: "直接",
          label: "直接",
        },
        {
          value: "关联",
          label: "关联",
        },
        {
          value: "链路",
          label: "链路",
        },
      ],
      floweffectOptions: [
        {
          value: "快速失败",
          label: "快速失败",
        },
        {
          value: "排队等候",
          label: "排队等候",
        },
        {
          value: "Warm Up",
          label: "Warm Up",
        },
      ],
      value: "",
      input: "",
      transferData: [],
      transferValue: [],
      dialogVisible: false,
      total: 0,
      pageCondition: {
        pageNo: 1,
        pageSize: 3,
      },
      tableData: [],
      title: "",
    };
  },
  created() {
    this.getList();
    this.getFlowSources();
  },
  methods: {
    getList() {
      this.requestPageData.condition = this.Flowrule;
      this.requestPageData.pageCondition = this.pageCondition;
      selectFlowRuleList(this.requestPageData).then((res) => {
        this.tableData = res.data.resultPages;
        this.total = res.data.totalRows;
      });
    },
    addFlowRule() {
      this.dialogVisible = true;
      this.title = "新增";
    },
    editFlowRule(val) {
      this.title = "编辑";
      this.dialogVisible = true;
      this.FlowRuleVO = val;
      this.transferData = [];
      this.transferValue = [];
      this.getFlowSources();
      for (let index = 0; index < this.FlowRuleVO.flowSources.length; index++) {
        const element = this.FlowRuleVO.flowSources[index];
        this.transferValue.push(element.id);
      }
    },
    delFlowRule(val) {
      deleteFlowRule(val.id).then(() => {
        this.$message({
          message: "删除成功",
          type: "success",
        });
        this.Flowrule = new Flowrule();
        this.getList();
      });
    },
    getFlowSources() {
      selectSourcelist().then((res) => {
        for (let index = 0; index < res.data.length; index++) {
          const element = res.data[index];
          this.transferData.push({
            key: element.id,
            label: element.name,
          });
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.FlowRuleVO.sids = this.transferValue;
          this.FlowRuleVO.flowSources = [];
          this.FlowRuleVO.flowNotSources = [];
          this.FlowRuleVO.thresholdpattern = "单机";
          saveFlowRule(this.FlowRuleVO).then(() => {
            this.$message({
              message: this.title + "成功",
              type: "success",
            });
            this.Flowrule = new Flowrule();
            this.getList();
            this.dialogVisible = false;
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 分页，前往第几页
    handleCurrentChange(val) {
      this.pageCondition.pageNo = val;
      this.getList();
    },

    //共几页
    handleSizeChange(val) {
      this.pageCondition.pageSize = val;
      this.getList();
    },
  },
};
</script>
<style lang="less" scoped>

.topic3 {
  background-color: #6984a4;
  border-radius: 10px;
  .head {
    color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
  }
  .topic3-table{
      height: calc(100% - 84px) !important;
  }
  .dialog {
    /deep/.el-dialog__body {
      text-align: left !important;
      padding: 15px 25px !important;
    }
    /deep/.el-dialog__header {
      text-align: left !important;
    }
    .title-name {
      font-size: 20px;
      color: #ed8e00;
      margin-right: 10px;
    }
    .el-select {
      width: 100%;
    }
  }

  .adjust-input {
    .el-input,
    .el-select {
      width: 90%;
    }
  }
}
</style>