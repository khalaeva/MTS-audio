// Utilities
import { defineStore } from 'pinia'

export const useAppStore = defineStore('app', {
    state: () => ({
        isAuthorized: false,
        userId: 4,
        username: 'Nikita',
    }),
})
