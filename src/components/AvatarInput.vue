<template>
    <label class="avatar-container">
        <v-avatar size="44" class="avatar">
            <v-img v-if="props.img" :src="props.img" cover />
            <span v-else class="avatar-text">
                {{ letterAvatar }}
            </span>
            <input type="file" class="avatar-input" />
        </v-avatar>
    </label>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps<{
    img?: string
    size: number
    text: string
}>()

const letterAvatar = computed(() => props.text[0].toLocaleUpperCase())
</script>

<style scoped lang="scss">
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
</style>
