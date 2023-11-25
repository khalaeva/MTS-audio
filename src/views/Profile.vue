<template>
    <v-container>
        <v-row align="center" justify="center" class="mt-5">
            <v-avatar size="140" color="grey">  
                <span class="text-h5">CJ</span>
            </v-avatar>
        </v-row>
        <v-row justify="center" class="mt-9">
            <v-btn rounded="xl" variant="outlined">Редактировать Профиль</v-btn>
        </v-row>
        <v-row>
            <ListOfFriends class="mt-5"/>
        </v-row>
        <v-row justify="center" class="mt-9">
            <v-btn rounded="xl" variant="outlined" >Выйти</v-btn>
        </v-row>
    </v-container>
</template>

<script setup>
import ListOfFriends from '@/components/ListOfFriends.vue'
</script>