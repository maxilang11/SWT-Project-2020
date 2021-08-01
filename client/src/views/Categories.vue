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
        @keyup.enter="show = true"
      />
      <i class="fa fa-search" @click="onSubmit" v-on:click="show = true" />
    </div>
    <h1 v-show="show">Suchergebnisse :</h1>
    <section class="products">
      <ProductGrid :productLayout="br" v-for="br in products" :key="br.id" />
    </section>
    <h1>Kategorien</h1>
    <section class="categories">
      <CategoryGrid
        v-for="category in categories"
        :key="category"
        :category="category"
      />
    </section>
  </div>
</template>
<script lang="ts">
import CategoryGrid from '../components/CategoryGrid.vue'
import { defineComponent, ref } from 'vue'
import ProductGrid from '../components/ProductGrid.vue'
import { useProducts } from '../service/ProductsStore'

export default defineComponent({
  components: { CategoryGrid, ProductGrid },
  setup() {
    if (localStorage.getItem('reloaded')) {
      localStorage.removeItem('reloaded')
    } else {
      localStorage.setItem('reloaded', '1')
      location.reload()
    }
    const categories = ref([
      { title: 'Getränke' },
      { title: 'Obst' },
      { title: 'Gemüse' },
      { title: 'Süßigkeiten' },
      { title: 'Teigwaren' },
      { title: 'Fleisch' },
      { title: 'Fisch' },
      { title: 'Backwaren' },
      { title: 'Tiefkühl' }
    ])

    const { liste, search } = useProducts()
    const products = liste
    const x = ref('')
    var show = false

    function onSubmit() {
      search(x.value)
    }

    return {
      products,
      categories,
      onSubmit,
      show,
      x
    }
  }
})
</script>

<style scoped>
#banner {
  width: 100vw;
  height: 27vh;
  object-fit: cover;
}
h1 {
  font-size: 2vw;
  color: black;
  text-align: left;
  margin-left: 14.5%;
  margin-top: 0.5%;
  margin-bottom: 2.5%;
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
.categories {
  display: grid;
  grid-template-columns: repeat(3, 0fr);
  grid-column-gap: 5%;
  grid-row-gap: 1%;
  margin-left: 14.5%;
}
.products {
  display: grid;
  grid-template-columns: repeat(3, 0fr);
  grid-column-gap: 5%;
  grid-row-gap: 2%;
  margin-left: 14.5%;
}
#banner {
  width: 100vw;
  object-fit: cover;
  height: 27vh;
}
@media (max-width: 666px) {
  h1 {
    font-size: 5vw;
    color: black;
    text-align: left;
    margin-left: 14.5%;
    margin-top: 0.5%;
    margin-bottom: 2.5%;
  }
  .search input[type='text'] {
    padding: 0% 2% 0% 3%;
    display: inline-block;
    box-sizing: border-box;
    border-radius: 8px;
    font-size: 2.5vw;
    width: 30vw;
    height: 6vh;
  }
  .search {
    text-align: left;
    font-size: 2.3vw;
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
}
</style>
