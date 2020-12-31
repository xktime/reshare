<template>
  <el-container class="main-container"
                v-infinite-scroll="load"
                infinite-scroll-immediate="true"
                :infinite-scroll-disabled="scrollDisabled">
    <el-header style="height: 45px">
      <app_header></app_header>
    </el-header>
    <el-container class="load-container">
      <el-aside class="tag">
          <el-row>
            <el-button class="tag-button" type="primary" @click="loadArticle(recommend)" plain>推荐</el-button>
          </el-row>
          <el-row>
            <el-button class="tag-button" type="primary" @click="loadArticle(crawler)" plain>爬取文章</el-button>
          </el-row>
          <el-row>
            <el-button class="tag-button" type="primary" @click="loadArticle(original)" plain>原创文章</el-button>
          </el-row>
      </el-aside>
      <el-scrollbar style="height: 100%" wrap-class="scrollbar-wrapper">
        <el-main>
          <el-row v-for="data in tableData" :key="data.id">
            <!--头像-->
            <el-col :span="5">
              <el-avatar v-if="data.authorProfile != null" :size="85" fit="contain" :src="data.authorProfile"></el-avatar>
              <el-avatar v-else :size="85" fit="contain" icon="el-icon-user-solid"></el-avatar>
            </el-col>
            <el-col :span="19">
              <span class="item_title">
                <el-link :href="'/article/' + data.id" target="_blank" :underline="false">{{data.title}}</el-link>
              </span><br><br>
              <span class="text-desc"><span>{{data.comment}}</span> 个回复 •
                <span>{{data.views}}</span> 次浏览 •
                <span>{{new Date(data.publishTime).toLocaleString()}}</span>
              </span>
              <el-divider></el-divider>
            </el-col>
          </el-row>
        </el-main>
      </el-scrollbar>
    </el-container>
  </el-container>
</template>

<script>
    import app_header from '@/components/Header.vue';

    export default {
        data() {
            return {
                tableData: [
                    {
                        id: 2,
                        authorProfile: 1,
                        views: 2,
                        title: 'hahaha',
                        comment: 0
                    }
                ],
                page: 1,
                scrollDisabled: false,
                type: '',
            }
        },
        methods: {
            //todo 分页导致部分数据重复加载问题
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
                let data = new FormData();
                data.append("page", this.page);
                data.append("loadArticleType", this.getType(this.type));
                if (this.$store.state.loging) {
                    data.append("token", this.$store.state.token);
                }
                const _this = this;
                const api = this.$loadSimpleArticleUrl;
                this.axios.post(api, data).then((response) => {
                    if (response.data.code !== 200) {
                        this.$alert(response.data.errorMessage);
                        return;
                    }
                    //如果没有后续禁止滚动
                    if (response.data.data == null || response.data.data.length <= 0) {
                        this.scrollDisabled = true;
                    }
                    _this.tableData = _this.tableData.concat(response.data.data);
                    this.page++;
                });
                this.scrollDisabled = false;
            },
            loadArticle: function (type) {
                this.$router.push('/' + type);
                this.load();
            },
            getType(type) {
                switch (type) {
                    case this.$crawlerArticleType.dec:
                        return this.$crawlerArticleType.type;
                    case this.$originalArticleType.dec:
                        return this.$originalArticleType.type;
                    case this.$recommendArticleType.dec:
                        return this.$recommendArticleType.type;
                    default:
                        return 0;
                }
            },
        },
        components: {
            app_header,
        },
        computed: {
            original() {
                return this.$originalArticleType.dec;
            },
            crawler() {
                return this.$crawlerArticleType.dec;
            },
            recommend() {
                return this.$recommendArticleType.dec;
            },
        }
    }
</script>

<style scoped>
  .el-header, .el-footer {
    text-align: center;
    background-color: #E9EEF2;
    border-bottom: 1px solid rgba(0, 0, 0, .22);
    padding: 0 20px;
  }


  .el-main {
    background-color: #E9EEF2;
    color: #333;
    text-align: left;
    margin: 35px 310px 0 20px;
    min-width: 80%;
    max-width: 80%;
    min-height: 92%;
    max-height: 92%;
  }

  .tag {
    max-width: 15%;
    margin: 50px 0 0 10px;
  }

  .tag-button {
    width:100%;
    margin-bottom:15px;
  }

  .main-container {
    background-color: #E2E2E2;
    overflow:auto;
    min-height: 100vh;
    max-height: 100vh;
  }

  .load-container {
    background-color: #E2E2E2;
    color: #333;
    text-align: center;
    margin: 0 auto;
    min-width: 45%;
    max-width: 45%;
    min-height: 93%;
    max-height: 93%;
  }

  .text-desc {
    font-size: 12px;
    font-weight: normal;
    color: #999;
  }

  .el-row {
    min-width: 100%;
    max-width: 100%;
  }

  .el-col {
    border-radius: 4px;
  }

  .item_title {
    font-size: 18px;
    line-height: 130%;
    text-shadow: 0 1px 0 #fff;
    word-break: break-word;
    hyphens: auto;
  }


</style>
