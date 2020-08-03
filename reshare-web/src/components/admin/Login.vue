<template>
  <div class="login-container">
    <el-form :model="form" :rules="rules" status-icon ref="ruleForm2" label-position="left" label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="form.account" auto-complete="off" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="form.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox v-model="checked" class="rememberme">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    account: '',
                    password: '',
                },
                rules: {
                    account: [{required: true, message: 'please enter your account', trigger: 'blur'}],
                    password: [{required: true, message: 'enter your password', trigger: 'blur'}]
                },
                checked: false
            }
        },
        methods: {
            handleSubmit(event) {
                this.$refs.ruleForm2.validate((valid) => {
                    if (valid) {
                        let data = new FormData();
                        data.append("account", this.form.account);
                        data.append("password", this.form.password);
                        const api = this.$apiUrl + 'admin/login';
                        this.axios.post(api, data).then(
                            (response) => {
                                if (response.data.code != 200) {
                                    this.logining = false;
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
    };
</script>

<style scoped>
  .login-container {
    width: 100%;
    height: 100%;
  }

  .login-page {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  label.el-checkbox.rememberme {
    margin: 0px 0px 15px;
    text-align: left;
  }
</style>
