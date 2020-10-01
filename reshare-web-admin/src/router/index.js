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
        requireAuth: true,
        title: "reshare后台管理系统"
      },
      children: [
        {
          path: '/verify/crawler',
          name: 'VerifyCrawlerArticle',
          component: () => import("@/view/verify/CrawlerArticle"),
          meta: {
            // requireAuth: true,
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
        title: "reshare后台登录"
      },
    },
  ]
})
