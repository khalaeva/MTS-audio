// Utilities
import { defineStore } from 'pinia'

export const useAppStore = defineStore('app', {
    state: () => ({
        userId: 4,
        username: 'Nikita',
    }),
})
