// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('@/views/Home.vue'),
    },
    {
        path: '/register',
        name: 'registration',
        component: () => import('@/views/RegisterPage.vue'),
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/LoginPage.vue'),
    },
    {
        path: '/profile',
        name: 'profile',
        component: () => import('@/views/Profile.vue')
    },
    {
        path: '/rooms',
        name: 'rooms',
        component: () => import('@/views/ListRoom.vue'),
    },
    {
        path: '/createroom',
        name: 'createroom',
        component: () => import('@/components/CreateRoom.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router
