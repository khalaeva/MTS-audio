<template>
    <v-dialog
        width="500"
        transition="dialog-bottom-transition"
    >
        <template v-slot:activator="{ props }">
            <v-btn v-bind="props">
                <v-icon icon="mdi-dots-horizontal"></v-icon>
            </v-btn>
        </template>
        <!-- проверка на авторизованность -->

        <template v-slot:default="{ isActive }">
            <v-card>
                <div style="position: flex; text-align:center">
                    <v-icon icon="mdi-account-group" class="mt-1" style="font-size: 80px"></v-icon>
                </div>

                <input
                    class="mr-5 ml-5 mt-5 mb-5 rounded-pill name-input pl-5"
                    type="text"
                    placeholder="Введите название комнаты"
                    v-model="editedRoom.title"
                >

                <v-btn
                    variant="tonal"
                    class="mr-5 ml-5 mb-5 rounded-pill"
                    text="Поменять трек"
                ></v-btn>
                <v-btn
                    variant="tonal"
                    class="mr-5 ml-5 mb-5 rounded-pill"
                    text="Покинуть комнату"
                ></v-btn>
                <v-btn
                    variant="tonal"
                    class="mr-5 ml-5 mb-5 rounded-pill"
                    text="Пригласить участника"
                ></v-btn>

                <div v-for="i in 5" :key="i" class="person mb-5">
                    <div class="icon-back ml-5 mr-5">
                        <v-icon icon="mdi-account" class="mt-1"></v-icon>
                    </div>
                    Имя человека
                    <v-icon
                        @click="deletePerson()"
                        style="position: absolute; right: 10px"
                        icon="mdi-close"
                        class="mt-1"
                    ></v-icon>
                </div>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        text="Отмена"
                        @click="isActive.value = false"
                    ></v-btn>

                    <v-btn
                        text="Сохранить"
                        @click="isActive.value = false; editRoom()"
                    ></v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script setup>
import {reactive} from 'vue'

const emit = defineEmits(['editedRoom'])

const editedRoom = reactive({
    title: "",
    peopleLIst: [],
    trackId: ""
})

function editRoom() {
    emit('editedRoom', editedRoom.value)
}

function deletePerson() {
    console.log('deleted')
}
</script>

<style>
.name-input {
    background-color: #D9D9D9;
    height: 36px;
}

.icon-back {
    background-color: #D9D9D9;
    border-radius: 50%;
    height: 36px;
    width: 36px;
    display: inline-block;
    text-align: center;
}

.person {
    white-space: nowrap;
    display: flex;
    align-items: center;
    position: relative;
}
</style>