// Utilities
import { defineStore } from 'pinia'

export const useRoomStore = defineStore('room', {
    state: () => ({
        id: 0,
        name: 'Рум №1',
        img: null,
        track: {
            img: null,
            name: 'ЗА ДЕНЬГИ ДА',
            author: 'INSTASAMKA',
        },
        messages: [
            {
                text: 'Hello!',
                time: '12:34',
                sender: { name: 'Dmitriy', id: 1 },
            },
            {
                text: 'Oh no! Its really bad. Im so sorry man omg',
                time: '12:36',
                sender: { name: 'Nikita', id: 4 },
            },
        ],
    }),
})
