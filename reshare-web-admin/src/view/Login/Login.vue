<template>
  <div class="login">
    <div class="login-form">
      <div class="login-header">
        <img src="../../assets/images/logo.svg" width="100" height="100" alt="">
        <p>reshare后台管理</p>
      </div>
      <el-input
        placeholder="请输入用户名"
        suffix-icon="fa fa-user"
        v-model="account"
        style="margin-bottom: 18px"
      >
      </el-input>

      <el-input
        placeholder="请输入密码"
        suffix-icon="fa fa-keyboard-o"
        v-model="password"
        type="password"
        style="margin-bottom: 18px"
        @keyup.native.enter="login"
      >
      </el-input>

      <el-button
        type="primary" :loading="loginLoading"
        style="width: 100%;margin-bottom: 18px"
        @click="login"
      >登录
      </el-button>
<!--      <div>-->
<!--        <el-checkbox v-model="remember"> remember</el-checkbox>-->
<!--        <a href="javascript:;" style="float: right;color: #3C8DBC;font-size: 14px">Register</a>-->
<!--      </div>-->

    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                account: '',
                password: '',
                loginLoading: false,
                remember: true,
            }
        },
        methods: {
            login(event) {
                this.loginLoading = true;
                let data = new FormData();
                data.append("account", this.account);
                data.append("password", this.password);
                const api = this.$loginUrl;
                this.axios.post(api, data).then(
                    (response) => {
                        if (response.data.code != 200) {
                            this.$alert(response.data.errorMessage);
                            this.loginLoading = false;
                            return;
                        }
                        setTimeout(() => {
                            this.loginLoading = false;
                            this.$store.commit('login', this.account);
                            this.$store.commit('addToken', response.data.data);
                            this.$notify({
                                title: '登录成功',
                                message: '欢迎登录reshare管理系统',
                                type: 'success'
                            });
                            this.$router.push({path: this.$route.query.redirect || '/'});
                        }, 1000);
                    }
                );
            }
        }
    };
</script>

<style lang="less">
  @import "Login.less";
</style>
