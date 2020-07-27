// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
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

Vue.use(elTableInfiniteScroll);

//引入Element框架
Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
