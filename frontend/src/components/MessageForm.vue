<script>
import axios from 'axios';

const baseApiUrl = "http://localhost:8080/api/v1/";

export default {
    name: 'Message Form',
    data() {
        return {
            message: {
                email: '',
                message: '',
                photoId: this.photoId,
            },
            emailEmpty: false,
            messageEmpty: false
        }
    },
    props: {
        photoId: Number,
    },
    emits: ['close'],
    methods: {
        sendMessage() {
            axios.post(baseApiUrl + 'messages/store', this.message)
                .then(() => {
                    this.$emit('close');
                })
                .catch(e => {
                    this.emailEmpty = false;
                    this.messageEmpty = false;
                    if (this.message.email === "") this.emailEmpty = true;
                    if (this.message.message === "") this.messageEmpty = true;
                })
        }
    }

}
</script>

<template>
    <div class="message d-flex justify-content-center align-items-center">
        <div class="card position-relative p-5">
            <form class="d-flex flex-column align-items-center" @submit.prevent="sendMessage">
                <button type="button" class="close btn btn-danger" @click="$emit('close')"><i
                        class="fa-solid fa-xmark"></i></button>
                <h5 class="mb-4">Invia Messaggio</h5>
                <div class="d-flex flex-column align-items-center mb-3">
                    <label for="email">Email</label>
                    <input class="mb-1" type="email" name="email" id="email" v-model="message.email">
                    <small class="text-danger" v-if="emailEmpty">L'email è obbligatoria</small>
                </div>
                <div class="col-12 d-flex flex-column align-items-center mb-5">
                    <label for="message">Messaggio</label>
                    <textarea class="col-12 mb-1" name="message" id="message" rows="10"
                        v-model="message.message"></textarea>
                    <small class="text-danger" v-if="messageEmpty">Il messaggio è obbligatorio</small>
                </div>
                <button class="btn btn-success">Invia</button>
            </form>
        </div>
    </div>
</template>

<style scoped lang="scss">
.message {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba($color: #000000, $alpha: 0.3);
    z-index: 2;

    .close {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 10px;
    }
}
</style>