import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'loadIndex',//用来加载文章的主页模板
      component: () => import("@/view/IndexOfLoad"),
      meta: {
        // requireAuth: true,
        title: "reshare"
      },
    },
    {
      path: '/fuc',
      name: 'fucIndex',//功能的主页模板
      component: () => import("@/view/IndexOfFuc"),
      meta: {
        requireAuth: true,
      },
      children: [
        {
          path: '/login',
          name: 'login',
          component: () => import("@/components/Login"),
          meta: {
            title: "reshare登录"
          },
        },
        {
          path: '/publish',
          name: 'publish',
          component: () => import("@/components/Publish"),
          meta: {
            title: "创作",
            requireAuth: true,
          },
        },
        {
          path: '/register',
          name: 'register',
          component: () => import("@/components/Register"),
          meta: {
            title: "注册"
          },
        },
      ]
    },
  ]
})
