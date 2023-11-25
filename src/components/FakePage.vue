<template>
    <v-container class="pl-9 pt-6">
        <v-row class="mt-2">
            <v-col cols="8">
                <div class="text-h4 mb-1">
                    {{ name }}
                </div>
                <v-btn v-on:click="playM(length)">play</v-btn>
            </v-col>
            <v-col cols="4" align="center" justify="center">
                <router-link to="/profile">
                    <v-avatar color="grey">  
                        <span  class="text-h5">CJ</span>
                    </v-avatar>
                </router-link>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

export default {
    data() {
    return {
      name: this.$route.params.name,
      length: 2
    }
    },
    methods: {
        playM(length) {
            if (length == 0) {
                return 
            }
            else { 
                let audio = new Audio('https://interactive-examples.mdn.mozilla.net/media/cc0-audio/t-rex-roar.mp3');
                audio.play()
                audio.onended = () => {
                    this.length--
                    this.playM(this.length)
                }
            }
        }
    },
    updated() {
        this.name = this.$route.params.name
    }
}
</script>