<template>
  <el-container>
    <el-main class="article-page">
      <el-row>
        <el-col :span="4">
          <el-avatar :size="120" fit="contain" :src="article.images"></el-avatar>
        </el-col>
        <el-col :span="20">
          <el-row>
            <h1>{{article.title}}</h1>
          </el-row>
          <el-row class="visited" type="flex" justify="center">
            <el-col :span="3">
              <p>{{article.authorName}}</p>
            </el-col>
            <el-col :span="7">
              <p>{{new Date(article.publishTime).toLocaleString()}}</p>
            </el-col>
            <el-col :span="3">
              <p>{{article.views}}次浏览</p>
            </el-col>
            <el-col :span="3">
              <p>{{article.collection}}次收藏</p>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <el-row>
        <p v-html="article.content"></p>
      </el-row>
    </el-main>
    <el-footer>
      <el-divider></el-divider>
      <!--todo 评论模块-->
      <div>
        <div class="comment-page" v-for="comment in article.comments" :key="comment.id">
          <el-row>
            <el-col :span="4">
              <el-avatar :size="120" fit="contain" :src="article.images"></el-avatar>
            </el-col>
            <el-col :span="20">
              <el-row>
                <h1>{{comment.content}}</h1>
              </el-row>
              <el-row class="visited" type="flex" justify="center">
                <el-col :span="3">
                  <p>{{comment.authorName}}</p>
                </el-col>
                <el-col :span="7">
                  <p>{{new Date(article.publishTime).toLocaleString()}}</p>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
          <el-divider></el-divider>
        </div>
      </div>
      <!--todo 回复模块-->
    </el-footer>
  </el-container>
</template>

<script>
    export default {
        data() {
            return {
                article: {
                    authorId: 0,
                    authorName: "28640",
                    channelId: 0,
                    channelName: "SegmentFault 思否",
                    collection: 0,
                    comment: 0,
                    comments: [
                        {
                            id: 4,
                            authorName: "123",
                            content: "xixi",
                            publishTime: "2020-12-19T10:39:02.000+0000"
                        },
                        {
                            id: 2,
                            authorName: "1234",
                            content: "xixixi",
                            publishTime: "2020-12-19T10:39:02.000+0000"
                        },
                        {
                            id: 2,
                            authorName: "12345",
                            content: "xixixixi",
                            publishTime: "2020-12-19T10:39:02.000+0000"
                        },
                        {
                            id: 2,
                            authorName: "12345",
                            content: "xixixixi",
                            publishTime: "2020-12-19T10:39:02.000+0000"
                        },
                        {
                            id: 2,
                            authorName: "12345",
                            content: "xixixixi",
                            publishTime: "2020-12-19T10:39:02.000+0000"
                        },
                        {
                            id: 2,
                            authorName: "12345",
                            content: "xixixixi",
                            publishTime: "2020-12-19T10:39:02.000+0000"
                        },
                    ],
                    content: "<p>前言需求</p><hr><p>本篇算法介绍的十大常用算法的：分治算法，那么在前面的一些算法",
                    id: 8,
                    images: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                    labels: "后端,算法,程序员",
                    likes: 0,
                    origin: "SegmentFault 思否",
                    publishTime: "2020-12-19T10:39:02.000+0000",
                    tag: null,
                    title: "我所知道的十大常用算法之分治算法（解决汉诺塔问题）_我所知道系列：数据结构与算法",
                    views: 0,
                },
            }
        },
        methods: {},
        mounted() {
            const api = this.$loadArticleDetailUrl + '?articleId=' + this.$route.params.articleId;
            const _this = this;
            this.axios.get(api).then((response) => {
                if (response.data.code !== 200) {
                    this.$alert(response.data.errorMessage);
                    return;
                }
                _this.article = response.data.data;
                document.title = _this.article.title;
            });
        },
    }
</script>

<style>
  .article-page {
    border-radius: 50px;
    padding: 35px 35px 15px;
    text-align: center;
    margin: 0 auto;
    min-width: 45%;
    max-width: 45%;
    min-height: 50%;
    max-height: 50%;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .comment-page {
    /*border-radius: 50px;*/
    padding: 35px 35px 15px;
    text-align: center;
    margin: 0 auto;
    min-width: 45%;
    max-width: 45%;
    min-height: 50%;
    max-height: 50%;
    background: #fff;
    /*border: 1px solid #eaeaea;*/
    /*box-shadow: 0 0 25px #cac6c6;*/
  }

  .visited {
    color: #778087;
    text-decoration: none;
    word-break: break-word;
    font-size: 10px;
  }
</style>
