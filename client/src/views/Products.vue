<template>
  <div class="start">
    <img src="../assets/images/banner2.jpg" id="banner" />
    <div class="search">
      <input
        type="text"
        class="search1"
        placeholder="Suche"
        v-model="x"
        v-on:keyup.enter="onSubmit"
      />
      <i class="fa fa-search" @click="onSubmit" />
    </div>
    <h1>
      <i @click="back" class="fas fa-arrow-circle-left"></i>
      {{ $route.params.kategoriename }}
    </h1>
    <section class="products">
      <ProductGrid :productLayout="br" v-for="br in products" :key="br.id" />
    </section>
  </div>
</template>
<script lang="ts">
import ProductGrid from '../components/ProductGrid.vue'
import { defineComponent, onMounted, ref } from 'vue'
import { useProducts } from '../service/ProductsStore'
import { useRoute } from 'vue-router'
import router from '../router/index'
export default defineComponent({
  components: { ProductGrid },
  setup() {
    const { liste, search, categories } = useProducts()
    const products = liste
    const path = useRoute().params.kategoriename.toString()
    const x = ref('')
    onMounted(async () => {
      await categories(path)
    })
    function onSubmit() {
      search(x.value)
    }
    // Leitet zurueck auf Kategorie-Seite
    function back() {
      router.push('/kategorien')
    }
    return {
      products,
      onSubmit,
      x,
      back
    }
  }
})
</script>
<style scoped>
.products {
  display: grid;
  grid-template-columns: repeat(3, 0fr);
  grid-column-gap: 5%;
  grid-row-gap: 2%;
  margin-left: 14.5%;
  margin-bottom: 7%;
}
#banner {
  width: 100vw;
  height: 27vh;
  object-fit: cover;
}
.search input[type='text'] {
  padding: 0% 2% 0% 3%;
  display: inline-block;
  box-sizing: border-box;
  border-radius: 8px;
  font-size: 1.3vw;
  width: 30vw;
  height: 4vh;
}
.search {
  text-align: left;
  font-size: 1.3vw;
  position: relative;
  margin-left: 14.5%;
  margin-top: 1%;
  margin-bottom: 0.3%;
}
.search i {
  position: absolute;
  left: 0.5%;
  top: 18%;
  color: black;
}
h1 {
  font-size: 2vw;
  color: black;
  text-align: left;
  margin-left: 14.5%;
  margin-top: 1%;
  margin-bottom: 2.5%;
}
</style>
