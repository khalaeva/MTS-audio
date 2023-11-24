<template>
  <v-app>
    <Navbar/>
    <router-view />
  </v-app>
</template>

<script setup>
  import Navbar from '@/components/Navbar.vue'
</script>
