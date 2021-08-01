<template>
  <div class="start">
    <div class="banner-box">
      <h1>Willkommen beim Super MIMarkt</h1>
    </div>
    <div class="deals">
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
      <h1>Angebote der Woche</h1>
      <h2>Gültig vom 15.Februar - 22.Februar 2021</h2>
      <section class="dealLayouts">
        <HomepageGrid :dealLayout="br" v-for="br in cartItems" :key="br.id" />
      </section>
    </div>
  </div>
</template>

<script lang="ts">
import HomepageGrid from '../components/HomepageGrid.vue'
import { useProducts } from '../service/ProductsStore'
import { defineComponent, onMounted, ref } from 'vue'
import { useToken, useUser } from '../service/UserStore'
import { useCart } from '../service/ShoppingCartStore'

export default defineComponent({
  components: {
    HomepageGrid
  },
  setup() {
    const { liste, search, categories } = useProducts()
    const { getUserID } = useUser()
    const { jwtDecrypt } = useToken()
    const cartItems = liste
    const x = ref('')
    const { getQuantityCart } = useCart()

    onMounted(async () => {
      await categories('Angebote')
      if (localStorage.getItem('token') !== null) {
        await getUserID(jwtDecrypt().sub)
        const userid = localStorage.getItem('userid')
        await getQuantityCart(userid !== null ? parseInt(userid) : 0)
      }
    })

    function onSubmit() {
      search(x.value)
    }

    return {
      cartItems,
      onSubmit,
      x
    }
  }
})
</script>

<style scoped>
.banner-box {
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
    url('../assets/images/banner.jpg');
  width: 100vw;
  height: 27vh;
  object-fit: cover;
  background-position-y: 20%;
}
.deals {
  margin-left: 15%;
  margin-right: 15%;
}
.start {
  display: grid;
  text-align: left;
}
.banner-box h1 {
  color: white;
  font-size: 3vw;
  margin-left: 15%;
  margin-top: 5%;
  display: flex;
}
h1 {
  font-size: 2vw;
  color: black;
  text-align: left;
  margin-left: 0%;
  margin-top: 1%;
  margin-bottom: 1%;
}
h2 {
  color: black;
  font-size: 1.2vw;
  text-align: left;
  margin-left: 0%;
  margin-top: 0%;
  margin-bottom: 2.5%;
}
.dealLayouts {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-column: span 1;
  grid-column-gap: 2%;
  grid-row-gap: 1%;
}
.search {
  text-align: left;
  font-size: 1.3vw;
  position: relative;
  margin-left: 0%;
  margin-top: 2%;
  margin-bottom: 0.3%;
}
.search input[type='text'] {
  padding: 0% 2% 0% 3%;
  display: inline-block;
  box-sizing: border-box;
  border-radius: 8px;
  font-size: 1.3vw;
  width: 30vw;
  height: 4vh;
  position: relative;
}
.search i {
  position: absolute;
  left: 1%;
  top: 25%;
  color: black;
  font-size: 1vw;
}
/* iPhone 6 Plus portrait */
@media (max-width: 666px) {
  h1 {
    font-size: 4.2vw;
    color: black;
    text-align: left;
    margin-left: 0%;
    margin-top: 1%;
    margin-bottom: 1%;
  }
  h2 {
    color: black;
    font-size: 3.3vw;
    text-align: left;
    margin-left: 0%;
    margin-top: 0%;
    margin-bottom: 2.5%;
  }
}
</style>
