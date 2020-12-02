<template>
  <el-container style="background-color: #E2E2E2;overflow:auto; max-height: 97vh;"
                v-infinite-scroll="load"
                infinite-scroll-immediate="true"
                :infinite-scroll-disabled="scrollDisabled">
    <el-header style="height: 45px">
      <app_header></app_header>
    </el-header>
    <el-container class="container">
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
              <el-avatar v-if="data.url != null" :size="85" fit="contain" :src="data.url"></el-avatar>
              <el-avatar v-else :size="85" fit="contain" icon="el-icon-user-solid"></el-avatar>
            </el-col>
            <el-col :span="19">
              <span class="item_title">
                <el-link :href="data.url" target="_blank" :underline="false">{{data.title}}</el-link>
              </span><br><br>
              <span class="text-desc"><span>{{data.status}}</span> 个回复 •
                <span>{{data.status}}</span> 次浏览 •
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
                // tableData: [],
                page: 1,
                scrollDisabled: false,
                type: '',
                tableData: [
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        status: 1,
                        title: "123",
                        publishTime: 1598430915141
                    },
                    {
                        url: null,
                        status: 2,
                        title: "123",
                        publishTime: 1598430915142
                    },
                    {
                        url: "/#/detail/2020202020",
                        status: 3,
                        title: "123666",
                        publishTime: 1598430915143
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        status: 4,
                        title: "123",
                        publishTime: 1598430915145
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        status: 4,
                        title: "123",
                        publishTime: 1598430915149
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        status: 4,
                        title: "123",
                        publishTime: 1598430915144
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        status: 4,
                        title: "123",
                        publishTime: 1598430915144
                    }
                    ,
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        status: 4,
                        title: "123",
                        publishTime: 1598430915144
                    }],
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
                let data = new FormData();
                data.append("page", this.page);
                data.append("loadArticleType", this.type);
                if (this.$store.state.loging) {
                    data.append("token", this.$store.state.token);
                }
                const _this = this;
                const api = this.$loadArticleUrl;
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
                this.tableData = [];
                this.page = 1;
                this.$router.push('/' + type);
                this.load();
            },
        },
        components: {
            app_header,
        },
        computed: {
            original() {
                return this.$originalArticleType;
            },
            crawler() {
                return this.$crawlerArticleType;
            },
            recommend() {
                return this.$recommendArticleType;
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

  .container {
    background-color: #E2E2E2;
    color: #333;
    text-align: center;
    margin: 0 auto;
    max-width: 40%;
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
