<template>
  <el-container style="height: 800px; border: 1px solid #eee">
      <el-main>
        <el-table border height="100%" v-el-table-infinite-scroll="load" :data="tableData">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <iframe v-bind:srcdoc="props.row.content" width="1000" height="500" frameborder="0"></iframe>
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
                user: this.$store.state.account,
            }
        },
        methods: {
            load: function () {
                const _this = this;
                const api = this.$apiUrl + 'admin/loadArticle?size=' + this.count + '&loadArticleType=crawler';
                this.axios.get(api).then((response) => {
                    if (response.data.code != 200) {
                        this.$alert(response.data.errorMessage);
                        return;
                    }
                    _this.tableData = response.data.data;
                });
                this.count += 2;
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
                    if (response.data.code != 200) {
                        this.$alert(response.data.errorMessage);
                        return;
                    }
                    this.load();
                });
            }
        }
    }
    ;
</script>
