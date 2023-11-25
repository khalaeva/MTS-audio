<template>
    <v-app-bar extension-height="60" class="bar">
        <div class="room-top">
            <div class="room-info">
                <div class="room-info__picture">
                    <label class="avatar-container">
                        <v-avatar size="44" class="avatar">
                            <v-img
                                v-if="roomStore.img"
                                src="../assets/123.png"
                                cover
                            />
                            <span v-else class="avatar-text">
                                {{ letterAvatar }}
                            </span>
                            <input type="file" class="avatar-input" />
                        </v-avatar>
                    </label>
                </div>
                <p class="room-info__name">{{ roomStore.name }}</p>
            </div>
            <div class="room-options">
                <v-btn icon="mdi-dots-vertical" size="36" />
            </div>
        </div>
        <template #extension>
            <div class="track">
                <v-avatar
                    :image="roomStore.track.img"
                    class="track-avatar"
                    size="44"
                    rounded="lg"
                />
                <div class="track-info">
                    <p class="track-info__name">
                        {{ roomStore.track.name }}
                    </p>
                    <p class="track-info__author">
                        {{ roomStore.track.author }}
                    </p>
                </div>
            </div>
        </template>
    </v-app-bar>
    <div class="chat">
        <template v-for="message in roomStore.messages">
            <div class="message">
                <p class="message__text">{{ message.text }}</p>
                <p class="message__time">{{ message.time }}</p>
            </div>
        </template>
    </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoomStore } from '@/store/room'

const roomStore = useRoomStore()
const letterAvatar = computed(() => roomStore.name[0].toLocaleUpperCase())
</script>

<style scoped lang="scss">
.bar {
    padding: 0 20px;
}
.room-top {
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.room-info {
    display: flex;
    align-items: center;
    font-size: 20px;
    gap: 10px;
}
.avatar-container {
    display: block;
    border-radius: 100%;
    overflow: hidden;
    cursor: pointer;
    .avatar {
        background-color: rgb(240, 158, 6);
    }
    .avatar-text {
        color: rgb(255, 255, 255);
        font-size: 24px;
    }
    .avatar-input {
        display: none;
    }
}
.track {
    padding: 10px 0;
    display: flex;
    align-items: center;
    gap: 10px;
    .track-avatar {
        background-color: rgb(0, 183, 255);
    }
    .track-info__name {
        font-size: 20px;
    }
    .track-info__author {
        font-size: 16px;
    }
}
.chat {
    padding: 10px;
    .message {
        padding: 8px;
    }
}
</style>
