import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import("@/view/Layout/App"),
      meta: {
        // requireAuth: true,
        title: "首页"
      },
      children: [
        {
          path: '/verify/crawler',
          name: 'VerifyCrawlerArticle',
          component: () => import("@/view/verify/CrawlerArticle"),
          meta: {
            title: "爬取文章审核"
          },
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import("@/view/Login/Login"),
      meta: {
        title: "reshare登录"
      },
    },
  ]
})
