<template>
    <v-app v-if="appStore.isAuthorized">
        <v-main class="main">
            <div class="container">
                <router-view />
            </div>
        </v-main>
        <Navbar />
    </v-app>
    <v-app v-else>
        <AuthForm
            :login-stage="loginStage"
            @change-stage="changeStage()"
            @successfull-login="successfullLogin()"
        />
    </v-app>
</template>

<script setup>
import { ref } from 'vue'
import { useAppStore } from './store/app'
import Navbar from '@/components/Navbar.vue'
import AuthForm from './components/AuthForm.vue'

const appStore = useAppStore()

const loginStage = ref(true)
function changeStage() {
    loginStage.value = !loginStage.value
}
function successfullLogin() {
    appStore.isAuthorized = !appStore.isAuthorized
}
</script>

<style scoped>
.container {
    max-width: 600px !important;
    height: 100%;
    margin: 0 auto;
}
</style>
