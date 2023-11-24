<template>
    <v-bottom-navigation>
        <v-btn v-for="item in items" :value="item.value">
          <v-icon>{{ item.icon }}</v-icon>
      
          <span>{{ item.text }}</span>
        </v-btn>
    </v-bottom-navigation>
</template>

<script>
const items = [
    { icon: 'mdi-play', text: 'Слушать', value: 'play', route: '/' },
    { icon: 'mdi-heart', text: 'Избранное', value: 'favorites', route: '/' },
    { icon: 'mdi-magnify', text: 'Поиск', value: 'search', route: '/' },
    { icon: 'mdi-magnify', text: 'Комнаты', value: 'rooms', route: '/rooms' },
] 
</script>