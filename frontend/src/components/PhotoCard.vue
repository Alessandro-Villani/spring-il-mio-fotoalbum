<script>
export default {
    name: 'Photo Card',
    props: {
        photo: Object,
    },
    emits: ['message']
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
                <button class="btn btn-primary" @click="$emit('message', photo.id)"><i
                        class="fa-solid fa-envelope"></i></button>
            </div>
        </div>
    </div>
</template>

<style scoped lang="scss">
.card {
    background-color: lightsteelblue;
}
</style>