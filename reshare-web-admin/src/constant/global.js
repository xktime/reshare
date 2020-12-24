import Vue from 'vue'

export default {
  install() {
    Vue.prototype.$apiUrl = "http://localhost";
    Vue.prototype.$loadArticleUrl = Vue.prototype.$apiUrl + '/admin/loadArticle';
    Vue.prototype.$loginUrl = Vue.prototype.$apiUrl + '/admin/login';
    Vue.prototype.$verifyUrl = Vue.prototype.$apiUrl + '/admin/verify';

    Vue.prototype.$crawlerArticleType = 1;
    Vue.prototype.$originalArticleType = 2;
    Vue.prototype.$recommendArticleType = 3;
  }
}
