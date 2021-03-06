import Vue from 'vue'

export default {
  install() {
    Vue.prototype.$apiUrl = "http://localhost";
    Vue.prototype.$loadArticleUrl = Vue.prototype.$apiUrl + '/admin/loadArticle';
    Vue.prototype.$loginUrl = Vue.prototype.$apiUrl + '/admin/login';
    Vue.prototype.$verifyUrl = Vue.prototype.$apiUrl + '/admin/verify';

    Vue.prototype.$crawlerArticleType = {type: 1, dec: 'crawler'};
    Vue.prototype.$originalArticleType = {type: 2, dec: 'original'};
    Vue.prototype.$recommendArticleType = {type: 3, dec: 'recommend'};
  }
}
