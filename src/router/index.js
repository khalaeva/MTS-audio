// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('@/views/HomePage.vue'),
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
        component: () => import('@/views/Profile.vue'),
    },
    {
        path: '/',
        name: 'rooms',
        component: () => import('@/views/ListRoom.vue'),
    },
    {
        path: '/rooms/:id',
        name: 'room',
        component: () => import('@/views/RoomPage.vue'),
    },
    {
        path: '/createroom',
        name: 'createroom',
        component: () => import('@/components/CreateRoom.vue'),
    },
    {
        path: '/fakepage/:name',
        name: 'fakepage',
        component: () => import('@/components/FakePage.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router
