<template>
    <v-container>
        <v-row class="mx-3">
            <v-col>
                <div class="text-h5">Друзья</div>
            </v-col>
            <v-col class="pt-4">
                <v-btn  prepend-icon="mdi-account-search-outline" size="small" rounded="xl" variant="text">Добавить Друга</v-btn>
            </v-col>
        </v-row>
        <v-row class="px-3" >
            <v-list lines="one" max-height="210" width="100%">
                <v-list-item
                  v-for="n in friends"
                  :key="n"
                >
                    <v-list-item-title>
                        <v-avatar color="grey" class="mr-2">  
                            <v-icon>mdi-account</v-icon>
                        </v-avatar>
                        {{ n }}
                    </v-list-item-title>
                </v-list-item>
            </v-list>
        </v-row>
    </v-container>

</template>

<script setup>
const friends = ['asd', 'asda', 'asdasd', 'asd', 'asda', 'asdasd', 'asd', 'asda', 'asdasd']
</script>