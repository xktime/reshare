<template>
  <el-container style="background-color: #E2E2E2;overflow:auto; max-height: 98vh;"
                v-infinite-scroll="load"
                infinite-scroll-immediate="true"
                :infinite-scroll-disabled="scrollDisabled">
    <el-header style="height: 45px">
      <el-container>
        <el-aside width="20%"></el-aside>
        <el-container>
          <el-input style="width: 350px;"
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="search"
                    size="small">
          </el-input>
          <el-button type="primary" icon="el-icon-search" size="small">搜索</el-button>
          <el-aside width="25%"></el-aside>
          <el-dropdown class="header-link" v-if="!this.$store.state.loging" trigger="click">
            <span class="el-dropdown-link">
              我的<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-link href="http://www.baidu.com" :underline="false">设置</el-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-link href="http://www.baidu.com" :underline="false">时间轴</el-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-link href="http://www.baidu.com" :underline="false">退出</el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <div v-else>
            <el-link class="header-link" type="primary" :underline="false">登录</el-link>
            <el-link class="header-link" type="primary" :underline="false">注册</el-link>
          </div>
        </el-container>
      </el-container>
    </el-header>
    <el-container class="container">
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
    export default {
        data() {
            return {
                // tableData: [],
                search: null,
                page: 1,
                scrollDisabled: false,
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
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        status: 3,
                        title: "123",
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
                const _this = this;
                const api = this.$apiUrl + 'admin/loadArticle?page=' + this.page + '&loadArticleType=crawler';
                this.axios.get(api).then((response) => {
                    if (response.data.code != 200) {
                        this.$alert(response.data.errorMessage);
                        return;
                    }
                    if (response.data.data == null || response.data.data.length <= 0) {
                        this.scrollDisabled = true;
                    }
                    _this.tableData = _this.tableData.concat(response.data.data);
                    this.page++;
                });
                console.log(this.page);
                this.scrollDisabled = false;
            },
        },
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
    margin: 15px 310px 0 20px;
    min-width: 80%;
    max-width: 80%;
    min-height: 92%;
    max-height: 92%;
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

  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
    font-size: 15px;
  }
  .el-icon-arrow-down {
    font-size: 15px;
  }

  .header-link {
    padding-top: 12px;
    font-size: 15px;
  }

</style>
