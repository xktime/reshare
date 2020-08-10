import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Admin',
      component: () => import("@/components/admin/Admin"),
      meta: {
        requireAuth: true,
        title: "后台管理系统"
      },
    },
    {
      path: '/login',
      name: 'login',
      component: () => import("@/components/admin/Login"),
      meta: {
        title: "后台登录"
      },
    },
  ]
})
