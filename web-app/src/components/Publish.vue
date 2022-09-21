<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="publish-page">
    <el-form-item label="主题标题" prop="title" required>
      <el-input type="text"
                placeholder="请输入主题标题"
                v-model="ruleForm.title"
                autocomplete="off" maxlength="20" show-word-limit></el-input>
    </el-form-item>
    <el-form-item label="正文" prop="content" required>
      <el-input type="textarea"
                placeholder="请输入内容"
                :autosize="{ minRows: 10, maxRows: 10}"
                v-model="ruleForm.content"
                autocomplete="off" maxlength="10000" show-word-limit></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="publish()">发布</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    title: '',
                    content: '',
                },
                rules: {
                    title: [{required: true, message: '标题不能为空', trigger: 'blur'}],
                    content: [{required: true, message: '文章内容不能为空', trigger: 'blur'}],
                }
            };
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
                        data.append("title", this.ruleForm.title);
                        data.append("content", this.ruleForm.content);
                        data.append("token", this.$store.state.token);
                        const api = this.$publishArticleUrl;
                        this.axios.post(api, data).then(
                            (response) => {
                                if (response.data.code != 200) {
                                    this.$alert(response.data.errorMessage);
                                    return;
                                }
                                this.$router.push({path: this.$route.query.redirect || '/'});
                            }
                        );
                    } else {
                        console.log('error submit!');
                        return false;
                    }
                })
            }
        }
    }
</script>

<style scoped>
  .publish-page {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    margin: 180px auto;
    min-width: 45%;
    max-width: 45%;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
</style>
