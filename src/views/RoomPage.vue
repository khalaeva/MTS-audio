<template>
    <v-app-bar extension-height="60" class="bar" elevation="2">
        <div class="room-top px-5">
            <div class="room-info">
                <div class="room-info__picture">
                    <avatar-input
                        :img="roomStore.img"
                        :size="buttonSize"
                        :text="roomStore.name"
                    />
                </div>
                <p class="room-info__name">{{ roomStore.name }}</p>
            </div>
            <div class="room-options">
                <v-btn icon="mdi-dots-vertical" :size="buttonSize" />
            </div>
        </div>
        <template #extension>
            <div class="track px-5">
                <v-avatar
                    :image="roomStore.track.img"
                    class="track-avatar"
                    :size="buttonSize"
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
        <div class="chat__messages">
            <template v-for="message in roomStore.messages">
                <div
                    :class="{ 'is-mine': isMyMessage(message.sender.id) }"
                    class="message-container"
                >
                    <div class="message">
                        <p class="message__text">{{ message.text }}</p>
                        <div class="message-bottom">
                            <p
                                v-if="!isMyMessage(message.sender.id)"
                                class="message__sender"
                            >
                                {{ message.sender.name }}
                            </p>
                            <p class="message__time">{{ message.time }}</p>
                        </div>
                    </div>
                </div>
            </template>
        </div>
        <div class="chat__input">
            <v-text-field
                v-model="messageText"
                class="text-input"
                density="compact"
                variant="outlined"
                hide-details
                rounded
            />
            <v-btn
                @click="sendMessage()"
                icon="mdi-send"
                :size="buttonSize"
                elevation="0"
            />
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRoomStore } from '@/store/room'
import { useAppStore } from '@/store/app'
import AvatarInput from '@/components/AvatarInput.vue'

const buttonSize = 40

const roomStore = useRoomStore()
const appStore = useAppStore()

const isMyMessage = computed(() => (id: number) => id === appStore.userId)

const messageText = ref('')
function sendMessage() {
    try {
        const date = new Date()
        roomStore.messages.push({
            time: `${date.getHours()}:${date.getMinutes()}`,
            text: messageText.value,
            sender: { id: appStore.userId, name: appStore.username },
        })
        messageText.value = ''
    } catch (e) {
        console.log(e)
    }
}
</script>

<style scoped lang="scss">
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
.track {
    width: 100%;
    border-top: 1px solid rgb(156, 156, 156);
    padding: 10px 0;
    display: flex;
    align-items: center;
    gap: 10px;
    .track-avatar {
        background-color: rgb(0, 183, 255);
    }
    .track-info__name {
        font-size: 16px;
        font-weight: 500;
    }
    .track-info__author {
        font-size: 12px;
    }
}
.chat {
    padding: 0 10px 44px;
    height: 100%;
    overflow-y: auto;
}
.message-container {
    display: flex;
    .message {
        padding: 12px 16px;
        width: max-content;
        max-width: 50%;
        border-radius: 24px;
        background-color: rgb(175, 175, 175);
    }
    &.is-mine {
        justify-content: end;
        .message {
            background-color: rgb(0, 183, 255);
        }
    }
    .message__text {
        font-size: 16px;
    }
    .message-bottom {
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 10px;
        gap: 5px;
    }
    .message__time {
        width: 100%;
        display: flex;
        justify-content: flex-end;
    }
}
.chat__input {
    position: fixed;
    bottom: 56px;
    left: 0;
    width: 100%;
    display: flex;
    align-items: center;
    background: #fff;
}
</style>
