import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      /*子路由 智慧助手的展示页面是主页面的子页面*/
      children:[
        {
          path: '/chatAi',
          name: 'chatAi',
          component: () => import('../views/ChatAi.vue'),
        },
      ]
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

export default router
