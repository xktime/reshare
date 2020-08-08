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
        requireAuth: true
      },
    },
    {
      path: '/login',
      name: 'login',
      component: () => import("@/components/admin/Login")
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import("@/components/admin/Admin"),
      meta: {
        requireAuth: true
      },
    }
  ]
})
