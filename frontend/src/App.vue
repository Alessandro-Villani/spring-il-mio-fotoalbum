<script>
import axios from 'axios';
import PhotoCard from './components/PhotoCard.vue';
import MessageForm from './components/MessageForm.vue';

const baseApiUrl = "http://localhost:8080/api/v1/"

export default {
  name: "Foto Album",
  data() {
    return {
      photos: [],
      messageFormIsShown: false,
      photoId: null,
      searchTerm: ''
    }
  },
  components: { PhotoCard, MessageForm },
  methods: {
    fetchPhotos() {
      axios.get(baseApiUrl + 'photos', {
        params: {
          title: this.searchTerm.trim()
        }
      }
      )
        .then(res => this.photos = res.data)
        .catch(e => console.log(e))
    },
    showMessageForm(id) {
      this.photoId = id;
      this.messageFormIsShown = true;
    },
    closeMessageForm() {
      this.messageFormIsShown = false;
      this.photoId = null;
    },
    sendComment(comment) {
      axios.post(baseApiUrl + 'comments/store', comment)
        .then(() => {
          this.fetchPhotos();
        })
        .catch(e => console.log(e))

    }

  },
  mounted() {
    this.fetchPhotos();
  }
}
</script>

<template>
  <main class="container py-5">
    <div class="input-group mb-3">
      <input type="text" class="form-control" placeholder="Cerca foto..." v-model="searchTerm" @keyup.enter="fetchPhotos">
      <button class="btn btn-primary" type="button" @click="fetchPhotos"><i
          class="fa-solid fa-magnifying-glass"></i></button>
    </div>
    <h1 class="text-white text-center mt-5" v-if="!photos.length">Nessun risultato</h1>
    <div class="row row-cols-1">
      <PhotoCard v-for="photo in photos" :key="photo.id" :photo="photo" @message="showMessageForm"
        @comment="sendComment" />
      <MessageForm v-if="messageFormIsShown" :photoId="photoId" @close="closeMessageForm" />
    </div>
  </main>
</template>

<style lang="scss">
@use './assets/styles/style.scss';
</style>
