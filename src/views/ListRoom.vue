<template>
    <v-container class="mt-6 ml-3">
        <v-row>
            <v-col cols="8">
                <v-btn 
                size="small" 
                prepend-icon="mdi-plus" 
                block rounded="xl"
                >
                    Создать комнату
                </v-btn>
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
const rooms = [1, 2, 3, 4]
</script>