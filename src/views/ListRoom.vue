<template>
    <v-container class="pl-9 pt-6">
        <v-row class="mt-2">
            <v-col cols="8">
                <div class="text-h4 mb-1">
                    Комнаты
                </div>
            </v-col>
            <v-col cols="4" align="center" justify="center">
                <router-link to="/profile">
                    <v-avatar color="grey">  
                        <span  class="text-h5">CJ</span>
                    </v-avatar>
                </router-link>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="8">
                <CreateRoom/>
            </v-col>
        </v-row>
        <v-row v-for="room in rooms" align="center" justify="start"> 
            <v-col cols="auto" class="ml-2">
                <v-icon>mdi-account-group</v-icon>
            </v-col>
            <v-col
              cols="auto"
              class="pl-0"
            >
              <v-card
                class="mx-auto"
                variant="flat"
              >
                <v-card-item>
                  <div>
                    <div class="text-subtitle-1 mb-1">
                        Название комнаты
                    </div>
                    <div class="text-caption">Сейчас играет...</div>
                  </div>
                </v-card-item>
              </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script setup>
import CreateRoom from '@/components/CreateRoom.vue';
const rooms = [1, 2, 3, 4]
</script>