import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import("@/view/Layout/App"),
      meta: {
        // requireAuth: true,
        title: "reshare后台管理系统"
      },
      children: [
        {
          path: '/verify/:type',
          name: 'VerifyCrawlerArticle',
          component: () => import("@/view/verify/VerifyArticleTable"),
          meta: {
            // requireAuth: true,
            title: "文章审核"
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
