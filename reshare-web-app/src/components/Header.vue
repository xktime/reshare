<template>
  <el-container>
    <el-aside width="25%"></el-aside>
    <el-link class="header-link" type="primary" href="/" :underline="false">Reshare</el-link>
    <el-aside width="2%"></el-aside>
    <el-container>
      <el-input style="width: 350px;"
                placeholder="请输入内容"
                prefix-icon="el-icon-search"
                v-model="search"
                size="small">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="small">搜索</el-button>
      <el-aside width="20%"></el-aside>
      <el-dropdown class="header-link" v-if="this.$store.state.loging" trigger="click">
            <span class="el-dropdown-link">
              我的<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>
            <el-link href="/#/publish" :underline="false">发布</el-link>
          </el-dropdown-item>
          <el-dropdown-item>
            <el-link href="http://www.baidu.com" :underline="false">设置</el-link>
          </el-dropdown-item>
          <el-dropdown-item>
            <el-link href="http://www.baidu.com" :underline="false">时间轴</el-link>
          </el-dropdown-item>
          <el-dropdown-item>
            <el-link @click="logout" :underline="false">退出</el-link>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <div v-else>
        <el-link class="header-link" type="primary" href="/#/login" :underline="false">登录</el-link>
        <el-link class="header-link" type="primary" href="/#/publish" :underline="false">注册</el-link>
      </div>
    </el-container>
  </el-container>
</template>

<script>
    export default {
        data() {
            return {
                search: null,
            }
        },
        methods: {
            logout(event) {
                this.$store.commit('logout');
                this.$router.push({path: this.$route.query.redirect || '/#/'});
            }
        }
    }
</script>

<style>
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
