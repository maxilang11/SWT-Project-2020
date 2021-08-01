<template>
  <div class="product-div">
    <div class="product-image">
      <img
        :src="require(`../assets/images/${cartItems.name.toLowerCase()}.png`)"
      />
    </div>
    <div class="description">
      <h3>{{ cartItems.name }} (Artikel-Nr.: {{ cartItems.id }})</h3>
      <h4>{{ cartItems.city }} - {{ cartItems.country }}</h4>
      <h4>Kategorie: {{ cartItems.category }}</h4>
    </div>
    <div class="number">
      <h2>{{ amount[cartItems.id] }}</h2>
      <h1>{{ cartItems.price }}€</h1>
      <h1>{{ (cartItems.price * amount[cartItems.id]).toFixed(2) }}€</h1>
    </div>
    <div class="remove">
      <span @click="delItem(cartItems, amount[cartItems.id])" class="removeBtn"
        ><i class="fas fa-times" />
      </span>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue'
import { useCart } from '../service/ShoppingCartStore'
import { Product } from '../service/Product'
export default defineComponent({
  name: 'ShoppingCartGrid',
  props: ['cartItems'],
  setup(props) {
    const {
      popFromCart,
      deleteFromShoppingCart,
      quantityCartDel,
      amount,
      getAmount,
      totalPriceDel
    } = useCart()
    const userid = localStorage.getItem('userid')

    onMounted(async () => {
      await getAmount(userid !== null ? parseInt(userid) : 0)
    })
    /**
     * Das Prdoukt wird aus dem Warenkorb gelöscht und
     * der Bestand des Produktes wird wieder erhöht
     */
    function delItem(product: Product, amount: number): void {
      const a = product.amount
      product.amount = amount
      popFromCart(product)
      deleteFromShoppingCart(userid !== null ? parseInt(userid) : 0, product)
      quantityCartDel(product, amount)
      product.amount = a
      totalPriceDel(amount * product.price)
    }
    return { delItem, amount }
  }
})
</script>

<style scoped>
.product-div {
  display: flex;
  width: 65vw;
  outline: 2px solid #ddd;
  margin-bottom: 2%;
  margin-top: 0%;
}
h3 {
  font-size: 1.5vw;
  margin-top: 4%;
  margin-bottom: 2%;
  padding: 0% 0% 0% 0%;
}
h4 {
  font-size: 1.2vw;
  margin-top: 0%;
  margin-bottom: 0%;
  padding: 0% 0% 0% 0%;
}
h1 {
  margin-left: 50%;
  font-size: 1.6vw;
  width: 5vw;
}
h2 {
  margin-left: 25%;
  font-size: 1.6vw;
  width: 2vw;
}
.product-image img {
  width: 6vw;
  height: 10vh;
  display: flex;
  object-fit: cover;
  margin-left: 2%;
  margin-top: 40%;
}
.description {
  display: flex;
  flex-direction: column;
  width: 30vw;
  text-align: left;
  padding-left: 3%;
}
.number {
  display: flex;
  flex-direction: row;
  margin-top: 7%;
}
.remove {
  font-size: 2vw;
  position: absolute;
  margin-left: 63%;
  margin-top: 0.5%;
}
@media (max-width: 666px) {
}
</style>
