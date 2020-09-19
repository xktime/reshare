<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="publish-page demo-ruleForm">
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
                        let data = new FormData();
                        data.append("account", this.form.account);
                        data.append("password", this.form.password);
                        const api = this.$apiUrl + 'login/common';
                        this.axios.post(api, data).then(
                            (response) => {
                                if (response.data.code != 200) {
                                    this.$alert(response.data.errorMessage);
                                    return;
                                }
                                this.$store.commit('login', this.form.account);
                                this.$store.commit('addToken', response.data.data);
                                this.$router.push({path: this.$route.query.redirect || '/admin'});
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

<style>
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
