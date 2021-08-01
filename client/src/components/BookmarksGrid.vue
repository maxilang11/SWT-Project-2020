<template>
  <div class="flex-items">
    <div class="image-wrap">
      <img
        :src="
          require(`../assets/images/${bookmarkItems.name.toLowerCase()}.png`)
        "
      />
    </div>
    <div class="info">
      <div class="delete">
        <span @click="delItem(bookmarkItems)"
          ><i class="fas fa-times fa-2x"></i
        ></span>
      </div>
      <p class="title">{{ bookmarkItems.name }}</p>
      <p class="description">
        {{ bookmarkItems.city }} - {{ bookmarkItems.country }}
      </p>
      <p class="price">{{ bookmarkItems.price }}€</p>
      <div class="plusminus">
        <button class="minus" @click="remove(bookmarkItems)">
          <i class="fas fa-minus"></i>
        </button>
        <h2 v-if="bookmarkItems.amount > 0">{{ count }}</h2>
        <h2 v-else>{{ 0 }}</h2>
        <button class="plus" @click="add(bookmarkItems)">
          <i class="fas fa-plus"></i>
        </button>
        <button class="buttonCart" @click="addToCart(bookmarkItems)">
          <i class="fas fa-shopping-cart" />
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, ref, defineComponent } from 'vue'
import { useCart } from '../service/ShoppingCartStore'
import { useBookmark } from '../service/BookmarkStore'
import { Product } from '../service/Product'
export default defineComponent({
  name: 'BookmarksGrid',
  props: ['bookmarkItems'],
  setup(props) {
    const totalPrice = computed(() =>
      (quantity.value * props.bookmarkItems.price).toFixed(2)
    )
    const quantity = ref(props.bookmarkItems.amountCart)
    const { pushToCart, addToShoppingCart, quantityCart } = useCart()
    const count = ref(1)
    const { popFromBookmark, deleteFromBookmark } = useBookmark()

    // entsprechender Zähler für Anzahl der Produkte, die in den Warenkorb soll
    function add(product: Product) {
      if (product.amount > 0) {
        if (count.value < product.amount) {
          count.value++
        }
      }
    }

    // entsprechender Zähler für Anzahl der Produkte, die in den Warenkorb soll
    function remove(product: Product) {
      if (count.value > 0) {
        count.value--
      }
    }

    // fügt entsprechend geklicktes Produkt mit angepasster Anzahl dem Warenkorb hinzu
    function addToCart(product: Product) {
      if (product.amount > 0 && count.value > 0) {
        const a = product.amount
        product.amount = count.value
        pushToCart(product)
        const userid = localStorage.getItem('userid')
        addToShoppingCart(userid !== null ? parseInt(userid) : 0, product)
        quantityCart(product)
        delItem(product)
        product.amount = a - count.value
        if (product.amount > 0) {
          count.value = 1
        } else {
          count.value = 0
        }
      }
    }

    // löscht nach klick des Sterns das Produkt von der Merkliste
    function delItem(product: Product): void {
      popFromBookmark(product)
      const userid = localStorage.getItem('userid')
      deleteFromBookmark(userid !== null ? parseInt(userid) : 0, product)
    }
    return { add, remove, count, delItem, totalPrice, addToCart }
  }
})
</script>

<style scoped>
.flex-items {
  color: black;
  display: flex;
  flex-direction: row;
  width: 25vw;
  box-shadow: 0px 0px 16px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin-bottom: 5%;
}
.image-wrap img {
  display: flex;
  width: 10vw;
  height: 15vh;
  object-fit: cover;
  margin-top: 50%;
  margin-left: 2%;
}
.info {
  text-align: left;
  display: flex;
  flex-direction: column;
  width: 90vw;
  padding-left: 4%;
}
.title {
  font-size: 1.6vw;
  font-weight: bold;
  margin-top: 26%;
  margin-bottom: 2%;
}
.description {
  font-size: 1.2vw;
  padding: 0% 5% 0% 0%;
  margin-top: 1%;
}
.price {
  font-size: 1.6vw;
  font-weight: bold;
  padding: 0% 5% 0% 0%;
  margin-top: 0%;
  margin-bottom: 5%;
}
.minus,
.plus {
  background-color: #0066ff;
  color: black;
  border-radius: 3px;
  width: 1.5vw;
  height: 3vh;
  border: none;
  font-size: 1vw;
  padding: 10% 5% 1% 2%;
}
.plusminus {
  display: grid;
  grid-template-columns: repeat(4, 0fr);
  padding: 6% 3% 2% 3%;
  margin-top: 3%;
  margin-bottom: 0%;
}
.plusminus h2 {
  width: 3vw;
  font-size: 1vw;
  text-align: center;
  margin-top: 0%;
}
.delete {
  justify-content: right;
  color: black;
  font-size: 1.2vw;
  position: absolute;
  margin-left: 11.5%;
  margin-top: 0.9%;
}
.buttonCart {
  background-color: transparent;
  border: none;
  font-size: 1.7vw;
  margin-top: 0%;
  margin-left: 8.5%;
  position: absolute;
  outline: none;
}
@media (max-width: 666px) {
  .flex-items {
    color: black;
    display: flex;
    flex-direction: row;
    width: 25vw;
    box-shadow: 0px 0px 16px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
    margin-bottom: 5%;
  }
  .image-wrap img {
    display: flex;
    width: 0vw;
    height: 0vh;
    object-fit: cover;
    margin-top: 30%;
    margin-left: 2%;
  }
  .info {
    text-align: left;
    display: flex;
    flex-direction: column;
    width: 200vw;
    padding-left: 3%;
  }
  .title {
    font-size: 3.6vw;
    font-weight: bold;
    margin-top: 26%;
    margin-bottom: 2%;
    text-align: center;
  }
  .description {
    font-size: 2.2vw;
    padding: 0% 5% 0% 0%;
    margin-top: 1%;
    text-align: center;
  }
  .price {
    font-size: 3.6vw;
    font-weight: bold;
    padding: 0% 5% 0% 0%;
    margin-top: 0%;
    margin-bottom: 5%;
    margin-left: 30%;
  }
  .minus,
  .plus {
    background-color: #0066ff;
    color: black;
    border-radius: 3px;
    width: 1.5vw;
    height: 3vh;
    border: none;
    font-size: 1.5vw;
    padding: 10% 5% 1% 2%;
  }
  .plusminus {
    display: grid;
    grid-template-columns: repeat(4, 0fr);
    padding: 6% 3% 2% 3%;
    margin-top: 3%;
    margin-bottom: 0%;
    margin-left: 33%;
  }
  .plusminus h2 {
    width: 3vw;
    font-size: 3.5vw;
    text-align: center;
    margin-top: 0%;
  }
  .delete {
    justify-content: right;
    color: black;
    font-size: 2.7vw;
    position: absolute;
    margin-left: 9.5%;
    margin-top: 1%;
  }
  .buttonCart {
    background-color: transparent;
    border: none;
    font-size: 1.7vw;
    margin-top: 0%;
    margin-left: 8.5%;
    position: absolute;
    outline: none;
  }
}
</style>
