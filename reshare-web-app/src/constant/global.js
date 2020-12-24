import Vue from 'vue'

export default {
  install() {
    Vue.prototype.$apiUrl = "http://localhost";
    Vue.prototype.$loadArticleUrl = Vue.prototype.$apiUrl + '/article/load';
    Vue.prototype.$loginUrl = Vue.prototype.$apiUrl + '/login/common';
    Vue.prototype.$publishUrl = Vue.prototype.$apiUrl + '/account/publish';
    Vue.prototype.$registerUrl = Vue.prototype.$apiUrl + '/account/register';

    Vue.prototype.$crawlerArticleType = 1;
    Vue.prototype.$originalArticleType = 2;
    Vue.prototype.$recommendArticleType = 3;
  }
}
