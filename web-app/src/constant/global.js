import Vue from 'vue'

export default {
  install() {
    Vue.prototype.$apiUrl = "http://localhost";
    Vue.prototype.$loadSimpleArticleUrl = Vue.prototype.$gatewayUrl + '/article/loadSimple';
    Vue.prototype.$loadArticleDetailUrl = Vue.prototype.$gatewayUrl + '/article/loadDetails';
    Vue.prototype.$loginUrl = Vue.prototype.$gatewayUrl + '/login/common';
    Vue.prototype.$publishArticleUrl = Vue.prototype.$gatewayUrl + '/article/publish';
    Vue.prototype.$publishCommentUrl = Vue.prototype.$gatewayUrl + '/comment/publish';
    Vue.prototype.$registerUrl = Vue.prototype.$gatewayUrl + '/account/register';

    Vue.prototype.$crawlerArticleType = {type: 1, dec: 'crawler'};
    Vue.prototype.$originalArticleType = {type: 2, dec: 'original'};
    Vue.prototype.$recommendArticleType = {type: 3, dec: 'recommend'};

    Vue.prototype.$articleCommentType = {type: 1, dec: 'article'};
  }
}
