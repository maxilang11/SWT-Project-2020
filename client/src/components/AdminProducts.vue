<template>
  <tr class="product">
    <td>
      <img
        :src="require(`../assets/images/${product.name.toLowerCase()}.png`)"
        class="image"
      />
    </td>
    <td>{{ product.name }}</td>
    <td>{{ product.price }}€</td>
    <td>{{ product.city }}/{{ product.country }}</td>
    <td>
      <button id="editBn" @click="editProduct = ' '">Produkt bearbeiten</button>
      <button id="delBn" @click="delProduct = ' '">Produkt löschen</button>
    </td>
    <div class="overlay" v-if="editProduct" @click="editProduct = ''"></div>
    <div class="editProduct" v-if="editProduct">
      <Button id="close" @click="editProduct = ''">schließen</Button>
      <EditProducts :product="product" />
    </div>
    <div class="overlay" v-if="delProduct" @click="delProduct = ''"></div>
    <div class="editProduct" v-if="delProduct">
      <Button id="close2" @click="delProduct = ''">schließen</Button>
      <DelProduct :product="product" />
    </div>
  </tr>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import EditProducts from '../components/EditProduct.vue'
import DelProduct from '../components/DelProduct.vue'
import { useProducts } from '../service/ProductsStore'

export default defineComponent({
  name: 'product',
  props: ['product'],
  components: {
    EditProducts,
    DelProduct
  },

  setup() {
    const editProduct = ref('')
    const delProduct = ref('')
    const { liste } = useProducts()

    return { editProduct, delProduct, liste }
  }
})
</script>

<style scoped>
.product td {
  padding: 1%;
  font-size: 1.1vw;
}
#editBn {
  font-size: 1.1vw;
  border-radius: 18px;
  border-color: #0066ff;
  height: 8vh;
  width: 15vw;
  margin-bottom: 7%;
}
#delBn {
  font-size: 1.1vw;
  border-radius: 18px;
  border-color: #fd4b4b;
  height: 8vh;
  width: 15vw;
}
.image {
  width: 8vw;
}
.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 98;
  background-color: rgb(0, 0, 0, 0.3);
  height: 2000vh;
}
.editProduct {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 99;
  background-color: white;
}
#close {
  margin-left: 80%;
  margin-top: 3%;
  border-radius: 18px;
  background: #0066ff;
  border: none;
  color: white;
  width: 8vw;
  height: 4vh;
  font-size: 1vw;
}
#close2 {
  margin-left: 80%;
  margin-top: 5%;
  border-radius: 12px;
  background: #0066ff;
  border: none;
  color: white;
  width: 5vw;
  height: 4vh;
  font-size: 0.7vw;
}
</style>
