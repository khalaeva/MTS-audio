<template>
    <v-app>
        <v-main class="main">
            <div class="container">
                <router-view />
            </div>
        </v-main>
        <Navbar />
    </v-app>
</template>

<script setup>
import Navbar from '@/components/Navbar.vue'
</script>

<style scoped>
.container {
    max-width: 600px !important;
    height: 100%;
    margin: 0 auto;
}
</style>
