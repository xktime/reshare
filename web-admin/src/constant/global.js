import Vue from 'vue'

export default {
  install() {
    Vue.prototype.$gatewayUrl = "http://localhost";
    Vue.prototype.$loadArticleUrl = Vue.prototype.$gatewayUrl + '/article/load/verifyArticles';
    Vue.prototype.$loginUrl = Vue.prototype.$gatewayUrl + '/user/login/admin';
    Vue.prototype.$verifyUrl = Vue.prototype.$gatewayUrl + '/article/admin/verify';

    Vue.prototype.$crawlerArticleType = {type: 1, dec: 'crawler'};
    Vue.prototype.$originalArticleType = {type: 2, dec: 'original'};
    Vue.prototype.$recommendArticleType = {type: 3, dec: 'recommend'};
  }
}
