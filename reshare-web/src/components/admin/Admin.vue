<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-setting"></i>文章审核</template>
          <el-menu-item-group>
            <template slot="title">审核</template>
            <el-menu-item v-on:click="loadCrawlerArticle()">爬取文章</el-menu-item>
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
            <el-dropdown-item>注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>{{user}}</span>
      </el-header>
          <el-main>
            <el-table
              border
              height="490px"
              v-el-table-infinite-scroll="load"
              :data="tableData"
            >
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <iframe v-bind:srcdoc="props.row.content" width="1000" height="300" frameborder="0"></iframe>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column prop="title" label="标题" width="300" sortable></el-table-column>
              <el-table-column prop="authorName" label="作者" width="120" sortable></el-table-column>
              <el-table-column prop="url" label="文章地址" width="300" sortable></el-table-column>
              <el-table-column prop="origin" label="来源" width="120" sortable></el-table-column>
              <el-table-column prop="publishTime" label="发布时间" width="180" sortable></el-table-column>
              <el-table-column prop="status" label="状态" width="100" :formatter="statusFormatter"
                               sortable></el-table-column>
              <el-table-column fixed="right" label="操作">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="audit(scope.row, 2)">通过
                  </el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="audit(scope.row, 1)">不通过
                  </el-button>
                </template>
              </el-table-column>
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
                tableData: [],
                count: 10,
                user: sessionStorage.getItem("user"),
                articleType: "crawler",
            }
        },
        methods: {
            load: function () {
                console.log(sessionStorage.getItem("user"));
                const _this = this;
                const api = this.$apiUrl + 'admin/loadArticle?size=' + this.count + '&loadArticleType=' + this.articleType;
                this.axios.get(api).then((response) => {
                    _this.tableData = response.data.data;
                });
                this.count += 2;
            },
            loadCrawlerArticle: function () {
                this.articleType = "crawler";
                this.load();
            },
            statusFormatter(row) {
                const status = row.status;
                switch (status) {
                    case 0:
                        return "未审核";
                    case 1:
                        return "未通过";
                    case 2:
                        return "通过";
                }
            },
            audit(row, status) {
                const api = this.$apiUrl + 'admin/verify?articleId=' + row.id + '&status=' + status;
                this.axios.get(api).then((response) => {
                    this.load();
                });
            }
        }
    }
    ;
</script>
