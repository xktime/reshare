<template>
  <el-container>
    <el-header>Header</el-header>
    <el-container class="container">
      <el-main>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    export default {
        name: "App"
    }
</script>

<style scoped>
  .el-header, .el-footer {
    text-align: center;
    background-color: #B3C0D1;
    border-bottom: 1px solid rgba(0, 0, 0, .22);
    padding: 0 20px;
  }


  .el-main {
    background-color: #E9EEF2;
    color: #333;
    text-align: center;
    margin: 15px 310px 0 20px;
    min-width: 70%;
    max-width: 70%;
    min-height: 92%;
    max-height: 92%;
  }

  .el-container {
    background-color: #E2E2E2;
    height: 98vh;
  }

  .container {
    background-color: #E2E2E2;
    color: #333;
    text-align: center;
    margin: 0 auto;
    max-width: 100%;
    min-height: 93%;
    max-height: 93%;
  }


</style>
