<template>
    <v-container class="container">
        <h1 class="element header">{{ content.headerText }}</h1>
        <v-form
            v-model="isFormValid"
            @submit.prevent="formSubmit()"
            validate-on="submit"
            class="element"
        >
            <v-text-field
                v-if="!props.loginStage"
                v-model="userData.email"
                :rules="[formRules.required, formRules.isEmail]"
                class="element"
                label="Электронная почта"
                density="compact"
                variant="outlined"
                rounded
                required
            />
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
                @click:append-inner="changeVisibility(passwordObj)"
                :rules="[formRules.required]"
                :append-inner-icon="passwordsIcon(passwordObj.isTextVisible)"
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
                ref="repeatPasswordInput"
                v-model="userData.repeatedPassword"
                @click:append-inner="changeVisibility(repeatPasswordObj)"
                :rules="[formRules.required, formRules.passwordMatch]"
                :append-inner-icon="
                    passwordsIcon(repeatPasswordObj.isTextVisible)
                "
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
import { ref, reactive, computed, Ref } from 'vue'
import { RouteLocationRaw } from 'vue-router'
import axios from 'axios'
import router from '@/router'

interface PasswordData<T = HTMLInputElement> {
    input: T
    isTextVisible: boolean
}

interface UserData {
    username: string
    password: string
    repeatedPassword?: string
    email?: string
}

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
              submitLink: 'login',
              footerText: 'Нет аккаунта?',
              footerLinkText: 'Зарегистрироваться',
              footerLinkObj: <RouteLocationRaw>{ name: 'registration' },
              redirectLink: <RouteLocationRaw>{ name: 'home' },
          }
        : {
              headerText: 'Регистрация',
              submitButtonText: 'Зарегистрироваться',
              submitLink: 'register',
              footerText: 'Уже есть аккаунт?',
              footerLinkText: 'Войти в аккаунт',
              footerLinkObj: <RouteLocationRaw>{ name: 'login' },
              redirectLink: <RouteLocationRaw>{ name: 'login' },
          },
)

const errorText = ref('')
const isError = computed(() => !!errorText.value)

const userData = reactive({
    email: '',
    username: '',
    password: '',
    repeatedPassword: '',
})
const userDataToSend = computed(() => {
    const result: UserData = {
        username: userData.username,
        password: userData.password,
    }
    if (props.loginStage) {
        result.email = userData.email
        result.repeatedPassword = userData.repeatedPassword
    }
    return result
})
const maxUsernameLength = ref(20)
const passwordInput = ref<HTMLInputElement>()
const passwordObj = reactive<PasswordData<Ref<HTMLInputElement>>>({
    input: passwordInput,
    isTextVisible: false,
})
const repeatPasswordInput = ref<HTMLInputElement>()
const repeatPasswordObj = reactive<PasswordData<Ref<HTMLInputElement>>>({
    input: repeatPasswordInput,
    isTextVisible: false,
})
const passwordsIcon = computed(
    () => (isVisible: boolean) => (isVisible ? 'mdi-eye-off' : 'mdi-eye'),
)
function changeVisibility(passwordData: PasswordData) {
    passwordData.isTextVisible = !passwordData.isTextVisible
    if (!passwordData.input) {
        return
    }
    passwordData.input.type = passwordData.isTextVisible ? 'text' : 'password'
    const passwordLength = passwordData.input.value.length
    passwordData.input.setSelectionRange(passwordLength, passwordLength)
}

const isFormValid = ref<boolean | null>(null)
const formRules = reactive({
    required: (value: string) => !!value || 'Обязательное поле',
    isEmail: (value: string) =>
        /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(value) ||
        'Неверный адрес электронной почты',
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
        await axios.post(content.value.submitLink, userDataToSend.value, {
            timeout: 3000,
        })
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
