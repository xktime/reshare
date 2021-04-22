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
      <div class="comment-page">
        <div class="comment-cell" v-for="comment in article.comments" :key="comment.id">
          <el-row>
            <el-col :span="4">
              <el-row>
                <el-avatar :size="130" shape="square" fit="contain" :src="article.images"></el-avatar>
              </el-row>
              <el-row>
                <p style="text-align:center;font-size:8px">{{comment.authorName}}</p>
              </el-row>
            </el-col>
            <el-col :span="18">
              <el-row>
                <p>{{comment.content}}</p>
              </el-row>
              <el-row class="visited" type="flex">
                <el-col push="20">
                  <p>{{new Date(article.publishTime).toLocaleString()}}</p>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </div>
      </div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="reply-page">
        <el-form-item prop="content" required>
          <el-input type="textarea"
                    placeholder="请输入内容"
                    :autosize="{ minRows: 10, maxRows: 10}"
                    v-model="ruleForm.content"
                    autocomplete="off" maxlength="10000" show-word-limit></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="publish()">回复</el-button>
        </el-form-item>
      </el-form>
    </el-footer>
  </el-container>
</template>

<script>
    export default {
        inject: ['reload'],
        data() {
            return {
                ruleForm: {
                    content: '',
                },
                rules: {
                    content: [{required: true, message: '文章内容不能为空', trigger: 'blur'}],
                },
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
                            content: "我所知道的十大常用算法之分治算法（解决汉诺塔问题）_我所知道系列：数据结构与算法，那么在前面的一些算法",
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
                    title: "我所知道的十大常用算法之分治算法（解决汉诺塔问题）_我所知道系列：数据结构与算法，那么在前面的一些算法",
                    views: 0,
                },
            }
        },
        methods: {
            publish() {
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        if (!this.$store.state.loging || this.$store.state.token==='') {
                            this.$alert('发布文章，登录状态错误');
                            return;
                        }
                        let data = new FormData();
                        data.append("content", this.ruleForm.content);
                        data.append("token", this.$store.state.token);
                        data.append("bindId", this.$route.params.articleId);
                        data.append("type", "1");//todo commentType
                        const api = this.$publishCommentUrl;
                        this.axios.post(api, data).then(
                            (response) => {
                                if (response.data.code != 200) {
                                    this.$alert(response.data.errorMessage);
                                    return;
                                }
                                this.$router.go(0);
                            }
                        );
                    } else {
                        console.log('error submit!');
                        return false;
                    }
                })
            }
        },
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
    border-radius: 50px;
    box-shadow: 0 0 25px #cac6c6;
    margin: 35px auto;
    min-width: 45%;
    max-width: 45%;
  }

  .comment-cell {
    min-width: 100%;
    max-width: 100%;
    background: #fff;
    padding: 5px;
    line-height: 100%;
    border-bottom: 1px solid #eaeaea;
  }

  .visited {
    color: #778087;
    text-decoration: none;
    word-break: break-word;
    font-size: 10px;
    padding: 40px 35px 20px;
  }

  .reply-page {
    border-radius: 5px;
    margin: 20px auto;
    min-width: 43%;
    max-width: 43%;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
  }
</style>
