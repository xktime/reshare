import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: () => import("@/components/HelloWorld")
    },
    {
      path: '/hello',
      name: 'HelloWorld2',
      component: () => import("@/components/HelloWorld2")
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import("@/components/admin/Admin")
    }
  ]
})
