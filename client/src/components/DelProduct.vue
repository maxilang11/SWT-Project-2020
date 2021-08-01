<template>
  <div id="delete">
    <h1>Willst du das Produkt wirklich löschen?</h1>
    <button id="delBn" @click="delProduct(product)">Produkt löschen</button>
  </div>
</template>
<script lang="ts">
import { defineComponent } from 'vue'
import { useAdminProduct } from '../service/AdminProductStore'
import { Product } from '../service/Product'
import router from '../router/index'

import { useProducts } from '../service/ProductsStore'
export default defineComponent({
  name: 'delproduct',
  props: ['product'],
  setup(props) {
    const { deleteProduct } = useAdminProduct()
    const { popFromList } = useProducts()

    // Löscht das Product
    function delProduct(product: Product) {
      deleteProduct(product)
      popFromList(product)
      router.go(0)
    }
    return { delProduct }
  }
})
</script>

<style scoped>
#delete {
  width: 30vw;
  height: 20vh;
}

h1 {
  font-size: 1.4vw;
  text-align: center;
  color: black;
  margin-top: 6%;
  margin-bottom: 6%;
}
#delBn {
  font-size: 1.4vw;
  border-radius: 18px;
  background-color: #d44242;
  height: 8vh;
  color: white;
  width: 15vw;
  margin-bottom: 2%;
  margin-left: 25%;
}
</style>
