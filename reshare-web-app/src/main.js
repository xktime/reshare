// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import elTableInfiniteScroll from 'el-table-infinite-scroll';

Vue.config.productionTip = false;

Vue.prototype.$apiUrl = "http://localhost/";

//引入axios
Vue.use(VueAxios, axios);
Vue.use(Vuex);
//引入Element框架
Vue.use(ElementUI);
Vue.use(elTableInfiniteScroll);

//注册状态全局管理器
export const store = new Vuex.Store({
  state: {
    token: '',
    account: '',
    loging: false,
  },
  mutations: {
    addToken(state, token) {
      localStorage.setItem("token", token);
      state.token = token;
    },
    login(state, account) {
      state.loging = true;
      state.account = account;
    },
    logout(state) {
      state.loging = false;
      localStorage.removeItem("token");
      state.token = '';
    }
  }
});

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  store.state.token = localStorage.getItem('token');//获取本地存储的token
  if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
    if (store.state.token !== null && store.state.token !== '') {  // 通过vuex state获取当前的token是否存
      next();
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  } else {
    next();
  }
});


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
});
