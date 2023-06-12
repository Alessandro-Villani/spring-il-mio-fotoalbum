<script>
export default {
    name: 'Photo Card',
    data() {

        return {
            comment: {
                content: '',
                photoId: this.photo.id
            }
        }

    },
    props: {
        photo: Object,
    },
    methods: {
        sendComment() {
            this.$emit('comment', this.comment);
            this.comment.content = ''
        }
    },
    emits: ['message', 'comment'],

}
</script>

<template>
    <div class="col d-flex justify-content-center mb-2">
        <div class="col-4">
            <div class="card d-flex flex-column align-items-center p-5">
                <h2 class="mb-3">{{ photo.title.toUpperCase() }}</h2>
                <h5 class="mb-3">Autore: {{ photo.user.username }}</h5>
                <img class="img-fluid mb-3" :src="photo.imageUrl" :alt="photo.title">
                <p>{{ photo.description }}</p>
                <button class="btn btn-primary mb-3" @click="$emit('message', photo.id)"><i
                        class="fa-solid fa-envelope"></i></button>
                <div class="comments col-12 border rounded p-2 bg-light">
                    <h6 class="text-center">Commenti</h6>
                    <div class="comment col-12 text-start py-1" v-for="comment in photo.comments">{{
                        comment.content }}</div>
                    <form class="mt-3" @submit.prevent="sendComment">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Commenta..." v-model="comment.content">
                            <button class="btn btn-primary"><i class="fa-regular fa-comment"></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped lang="scss">
.card {
    background-color: lightsteelblue;

    .comment {
        border-bottom: 1px solid lightblue;

    }
}
</style>