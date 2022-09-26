import Vue from 'vue'

export default {
  install() {
    Vue.prototype.$gatewayUrl = "http://localhost";
    Vue.prototype.$loadSimpleArticleUrl = Vue.prototype.$gatewayUrl + '/article/load/simpleArticles';
    Vue.prototype.$loadArticleDetailUrl = Vue.prototype.$gatewayUrl + '/article/load/articleDetails';
    Vue.prototype.$loginUrl = Vue.prototype.$gatewayUrl + '/user/login/app';
    Vue.prototype.$publishArticleUrl = Vue.prototype.$gatewayUrl + '/article/article/publish';
    Vue.prototype.$publishCommentUrl = Vue.prototype.$gatewayUrl + '/comment/comment/publish';
    Vue.prototype.$registerUrl = Vue.prototype.$gatewayUrl + '/user/register';

    Vue.prototype.$crawlerArticleType = {type: 1, dec: 'crawler'};
    Vue.prototype.$originalArticleType = {type: 2, dec: 'original'};
    Vue.prototype.$recommendArticleType = {type: 3, dec: 'recommend'};

    Vue.prototype.$articleCommentType = {type: 1, dec: 'article'};
  }
}
