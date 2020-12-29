<template>
  <el-container>
    <el-main class="Article-page">
      <el-row>
        <!--todo 文章页面完善-->
        <el-col :span="5">
          <el-avatar :size="85" fit="contain" :src="article.images"></el-avatar>
        </el-col>
        <el-col :span="19">
          <p v-html="article.title"></p>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <el-row>
        <p v-html="article.content"></p>
      </el-row>
    </el-main>
    <el-footer>
        <!--todo 评论模块-->
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
                    comments: null,
                    content: "<p>前言需求</p>↵<hr>↵<p>本篇算法介绍的十大常用算法的：分治算法，那么在前面的一些算法",
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
                console.log(_this.article);
                document.title = _this.article.title;
            });
        },
    }
</script>

<style>
  .Article-page {
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
</style>
