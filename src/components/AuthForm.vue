<template>
    <v-container class="container">
        <h1 class="element header">{{ content.headerText }}</h1>
        <v-form
            v-model="isFormValid"
            @submit.prevent="formSubmit()"
            class="element"
        >
            <v-text-field
                v-model="userData.username"
                :rules="[formRules.required, formRules.counter]"
                :maxlength="maxUsernameLength"
                counter
                class="element"
                label="Имя пользователя"
                density="compact"
                variant="outlined"
                rounded
                required
            />
            <v-text-field
                ref="passwordInput"
                v-model="userData.password"
                @click:append-inner="changeVisibility()"
                :rules="[formRules.required]"
                :append-inner-icon="passwordIcon"
                class="element"
                type="password"
                label="Пароль"
                density="compact"
                variant="outlined"
                rounded
                required
            />
            <v-text-field
                v-if="!props.loginStage"
                v-model="userData.repeatedPassword"
                :rules="[formRules.required, formRules.passwordMatch]"
                class="element"
                type="password"
                label="Повторите пароль"
                density="compact"
                variant="outlined"
                rounded
                required
            />
            <p class="element error-text" :class="{ 'is-visible': isError }">
                {{ errorText }}
            </p>
            <div class="element">
                <v-btn class="submit-button" type="submit" rounded="xl" block>
                    {{ content.submitButtonText }}
                </v-btn>
            </div>
            <p class="element footer">
                {{ content.footerText }}
                <router-link :to="content.footerLinkObj" class="link">
                    {{ content.footerLinkText }}
                </router-link>
            </p>
        </v-form>
    </v-container>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { RouteLocationRaw } from 'vue-router'
import router from '@/router'

const props = withDefaults(
    defineProps<{
        loginStage?: boolean
    }>(),
    { loginStage: false },
)
const content = computed(() =>
    props.loginStage
        ? {
              headerText: 'Вход',
              submitButtonText: 'Войти',
              footerText: 'Нет аккаунта?',
              footerLinkText: 'Зарегистрироваться',
              footerLinkObj: <RouteLocationRaw>{ name: 'registration' },
              redirectLink: <RouteLocationRaw>{ name: 'home' },
          }
        : {
              headerText: 'Регистрация',
              submitButtonText: 'Зарегистрироваться',
              footerText: 'Уже есть аккаунт?',
              footerLinkText: 'Войти в аккаунт',
              footerLinkObj: <RouteLocationRaw>{ name: 'login' },
              redirectLink: <RouteLocationRaw>{ name: 'login' },
          },
)

const errorText = ref('')
const isError = computed(() => !!errorText.value)

const userData = reactive({
    username: '',
    password: '',
    repeatedPassword: '',
})
const maxUsernameLength = ref(20)
const passwordInput = ref<HTMLInputElement | null>(null)
const showPassword = ref(false)
const passwordIcon = computed(() =>
    showPassword.value ? 'mdi-eye-off' : 'mdi-eye',
)
function changeVisibility() {
    showPassword.value = !showPassword.value
    if (!passwordInput.value) {
        return
    }
    passwordInput.value.type = showPassword.value ? 'text' : 'password'
    const passwordLength = userData.password.length
    passwordInput.value.setSelectionRange(passwordLength, passwordLength)
}

const isFormValid = ref<boolean | null>(null)
const formRules = reactive({
    required: (value: string) => !!value || 'Обязательное поле',
    counter: (value: string) =>
        value.length <= maxUsernameLength.value ||
        `Максимум ${maxUsernameLength.value} символов`,
    passwordMatch: (value: string) =>
        value === userData.password || 'Пароли должны совпадать',
})
async function formSubmit() {
    try {
        if (!isFormValid.value) {
            return
        }
        errorText.value = ''
        // await axios.post(props.submitObj.url, userData, props.submitObj.config)
        router.push(content.value.redirectLink)
    } catch (e) {
        console.log(e)
        errorText.value = 'На сервере произошла ошибка'
    }
}
</script>

<style scoped lang="scss">
.element + .element {
    margin-top: 10px;
}
.header,
.footer,
.error-text {
    text-align: center;
}
.error-text {
    color: rgb(var(--v-theme-red));
    opacity: 0;
    &:empty::before {
        content: '';
        display: inline-block;
    }
    &.is-visible {
        opacity: 1;
    }
}
.submit-button {
    color: #fff;
    background-color: rgb(var(--v-theme-primary));
}
.link {
    text-decoration: none;
    color: rgb(var(--v-theme-primary));
    &:hover {
        color: rgb(var(--v-theme-magenta));
    }
}
</style>
