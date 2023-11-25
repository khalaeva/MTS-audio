<template>
    <v-bottom-navigation>
        <v-btn v-for="item in items" :value="item.value" router :to="item.route">
            <v-icon>{{ item.icon }}</v-icon>
            <span>{{ item.text }}</span>
        </v-btn>
    </v-bottom-navigation>
</template>

<script setup>
const items = [
    { icon: 'mdi-play', text: 'Слушать', value: 'play', route: '/login' },
    { icon: 'mdi-heart', text: 'Избранное', value: 'favorites', route: '/' },
    { icon: 'mdi-magnify', text: 'Поиск', value: 'search', route: '/' },
    {
        icon: 'mdi-account-group',
        text: 'Комнаты',
        value: 'rooms',
        route: '/rooms',
    },
]
</script>
