<template>
  <div class="category">
    <router-link :to="`/kategorien/${category.title}`"
      ><img
        :src="require(`../assets/images/${category.title.toLowerCase()}.jpg`)"
        class="image"
    /></router-link>
    <div class="detail">
      <h3>{{ category.title }}</h3>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'category',
  props: ['category']
})
</script>

<style scoped>
.category {
  width: 20vw;
  box-shadow: 0px 0px 16px rgba(0, 0, 0, 0.25);
  margin-bottom: 10%;
}
.image {
  width: 100%;
  height: 25vh;
  object-fit: cover;
}
.detail {
  background-color: white;
}
.detail h3 {
  font-size: 1.7vw;
  color: black;
  text-align: center;
  margin: 2%;
}
@media (max-width: 666px) {
  .detail h3 {
    font-size: 3.5vw;
    color: black;
    text-align: center;
    margin: 2%;
  }
}
</style>
