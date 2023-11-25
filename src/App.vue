<template>
    <v-app v-if="isLogin">
        <v-main class="main">
            <div class="container">
                <router-view />
            </div>
        </v-main>
        <Navbar />
    </v-app>
    <v-app v-else>
        <LoginPage />
    </v-app>
</template>

<script setup>
import Navbar from '@/components/Navbar.vue'
import LoginPage from './views/LoginPage.vue'

const isLogin = true
</script>

<style scoped>
.container {
    max-width: 600px !important;
    height: 100%;
    margin: 0 auto;
}
</style>
