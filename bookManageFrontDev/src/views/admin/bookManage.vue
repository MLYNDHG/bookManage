<template>
  <div>
    <div class="filter-container">
      <el-input
        placeholder="图书名称"
        style="width: 200px"
        class="filter-item"
        v-model="book.name"
      ></el-input>
      <el-input
        placeholder="图书类型"
        style="width: 200px"
        class="filter-item"
        v-model="book.type"
      ></el-input>
      <el-input
        placeholder="图书描述"
        style="width: 200px"
        class="filter-item"
        v-model="book.des"
      ></el-input>
      <el-button class="dalfBut" >查询</el-button>
      <el-button type="primary" class="butT" 
        >添加图书</el-button
      >
    </div>
  </div>
</template>
<script>
export default {
    data(){
        return{
            book:{
                name:'',
                tyoe:'',
                des:''
            }
        }
    }
};
</script>
<style lang="less">
.filter-container {
    border-style: solid;
    height: 450px;
}
</style>
