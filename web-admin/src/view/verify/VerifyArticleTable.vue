<template>
  <el-container>
    <el-main>
      <el-table border height="100%" width="100%"
                v-el-table-infinite-scroll="load"
                :infinite-scroll-disabled="scrollDisabled"
                :data="tableData">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <iframe v-bind:srcdoc="props.row.content" width="100%" height="500" frameborder="0"></iframe>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="title" label="标题" min-width="25%" sortable></el-table-column>
        <el-table-column prop="authorName" label="作者" min-width="13%" sortable></el-table-column>
        <el-table-column prop="url" label="文章地址" min-width="30%" sortable>
          <template slot-scope="scope">
            <a :href="scope.row.url" target="_blank" class="buttonText">{{ scope.row.url }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="origin" label="来源" min-width="10%" sortable></el-table-column>
        <el-table-column prop="publishTime" label="发布时间" min-width="20%" sortable>
          <template slot-scope="scope">
            {{ new Date(scope.row.publishTime).toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" min-width="10%" :formatter="statusFormatter"
                         sortable></el-table-column>
        <el-table-column min-width="20%" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="audit(scope.row, 2)">通过</el-button>
            <el-button size="mini" type="danger" @click="audit(scope.row, 1)">不通过</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<style>
.el-container {
  height: 88vh;
  width: 100%;
  border: 1px solid #eee;
}

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
      page: 1,
      scrollDisabled: false,
      user: this.$store.state.account,
      type: '',
    }
  },
  methods: {
    load: function () {
      this.scrollDisabled = true;
      const type = this.$route.params.type;
      //是否切换加载类型
      const isReload = !(type === this.type);
      if (isReload) {
        this.type = type;
        this.page = 1;
        //如果切换加载类型，清空之前的数据
        this.tableData = [];
      }
      //todo 滚动加载好像有一些问题
      let lastTime = new Date().getTime();
      if (this.tableData[this.tableData.length - 1]) {
        const publishTime = this.tableData[this.tableData.length - 1].publishTime;
        lastTime = new Date(publishTime).getTime();
      }
      const api = this.$loadArticleUrl;
      const _this = this;
      let data = new FormData();
      data.append("page", this.page);
      data.append("loadArticleType", this.getType(type));
      data.append("lastTime", lastTime);
      data.append("size", 10);
      this.axios.post(api, data).then((response) => {
        if (response.data.code !== 200) {
          this.$alert(response.data.errorMessage);
          return;
        }
        //如果没有后续禁止滚动
        if (response.data.data == null || response.data.data.length <= 0) {
          this.scrollDisabled = true;
        }
        //todo id精度问题需要解决
        _this.tableData = _this.tableData.concat(response.data.data);
        this.page++;
      });
      this.scrollDisabled = false;
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
      const api = this.$verifyUrl;
      let data = new FormData();
      data.append("articleId", row.id);
      data.append("status", status);
      data.append("type", this.getType(this.$route.params.type));
      this.axios.post(api, data).then((response) => {
        if (response.data.code !== 200) {
          this.$alert(response.data.errorMessage);
          return;
        }
        row.status = status;
      });
    },
    getType(type) {
      switch (type) {
        case this.$crawlerArticleType.dec:
          return this.$crawlerArticleType.type;
        case this.$originalArticleType.dec:
          return this.$originalArticleType.type;
        default:
          return 0;
      }
    },
  }
}
;
</script>
