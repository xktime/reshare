<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-setting"></i>管理</template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="/hello" v-on:click="loadCrawlerArticle()">选项1</el-menu-item>
            <el-menu-item index="/">选项2</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>王小虎</span>
      </el-header>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="title" label="标题" width="250"></el-table-column>
          <el-table-column prop="authorName" label="作者" width="120"></el-table-column>
          <el-table-column prop="origin" label="来源" width="120"></el-table-column>
          <el-table-column prop="url" label="文章地址" width="300"></el-table-column>
          <el-table-column prop="publishTime" label="发布时间" width="180"></el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

<script>
  export default {
    data() {
      return {
        tableData: []
      }
    },
    methods: {
      loadCrawlerArticle: function () {
        const _this = this;
        const api = 'http://localhost:8994/admin/crawlerArticle';
        this.axios.get(api).then((response) => {
          _this.tableData = response.data.data;
        })
      }
    }
  }
  ;
</script>
