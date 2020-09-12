import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import("@/view/Index"),
      meta: {
        // requireAuth: true,
        title: "reshare"
      },
    },
    {
      path: '/login',
      name: 'login',
      component: () => import("@/view/Login"),
      meta: {
        title: "reshare登录"
      },
    },
  ]
})
