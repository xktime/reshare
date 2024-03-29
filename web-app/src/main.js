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
import elTableInfiniteScroll from 'el-table-infinite-scroll'
import global from './constant/global.js'
import JSONbig from 'json-bigint';

Vue.config.productionTip = false;

const JSONbigToString = JSONbig({ storeAsString: true })

const service = axios.create({
  timeout: 50000,
  baseURL: '',
  transformResponse: [function (data) {
    try {
      //转换
      return JSONbigToString.parse(data)
    } catch (err) {
      //转换失败就直接按原数据返回
      return data;
    }
  }]
});

//引入axios
Vue.use(VueAxios, service);
Vue.use(Vuex);
//引入Element框架
Vue.use(ElementUI);
Vue.use(elTableInfiniteScroll);
//引入全局常量
Vue.use(global);

//注册状态全局管理器
export const store = new Vuex.Store({
  state: {
    token: localStorage.getItem("token"),
    account: localStorage.getItem("account"),
    loging: localStorage.getItem("loging"),
  },
  mutations: {
    addToken(state, token) {
      localStorage.setItem("token", token);
      state.token = token;
    },
    login(state, account) {
      localStorage.setItem("loging", true);
      localStorage.setItem("account", account);
      state.loging = true;
      state.account = account;
    },
    logout(state) {
      localStorage.removeItem("loging");
      localStorage.removeItem("token");
      state.loging = false;
      state.token = '';
    }
  }
});

router.beforeEach((to, from, next) => {
  //修改body的user agent stylesheet
  document.body.setAttribute('style', 'margin:0');
  //获取标题
  if (to.meta.title) {
    document.title = to.meta.title
  }
  if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
    if (store.state.loging) {  // 通过vuex state获取当前的token是否存
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
