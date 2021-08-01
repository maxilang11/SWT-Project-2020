<template>
  <div class="dealLayout">
    <button class="star" @click="addToBookmarks(productLayout)">
      <i class="far fa-star"></i>
    </button>
    <div class="img">
      <img
        :src="
          require(`../assets/images/${productLayout.name.toLowerCase()}.png`)
        "
        class="image"
      />
    </div>
    <div class="detail">
      <h2>{{ productLayout.name }}</h2>
      <h3>(Artikel-Nr.: {{ productLayout.id }})</h3>
      <h4>{{ productLayout.city }} - {{ productLayout.country }}</h4>
      <h4>Kategorie: {{ productLayout.category }}</h4>
      <h5>{{ productLayout.price }}€</h5>
      <div class="number">
        <button class="minus" @click="decrementCounter(productLayout)">
          <i class="fas fa-minus" />
        </button>
        <h2 v-if="productLayout.amount > 0">{{ count }}</h2>
        <h2 v-else>{{ 0 }}</h2>
        <button class="plus" @click="incrementCounter(productLayout)">
          <i class="fas fa-plus" />
        </button>
        <button
          v-if="productLayout.amount > 0"
          class="buttonCart"
          @click="addToCart(productLayout)"
        >
          <i class="fas fa-shopping-cart" />
        </button>
      </div>
      <Hint v-show="loginHintmsg" class="redHint">
        {{ loginHintmsg }}
      </Hint>
      <Hint v-show="cartHintmsg" class="blueHint">
        {{ cartHintmsg }}
      </Hint>
      <Hint v-show="soldoutHintmsg" class="redHint">
        {{ soldoutHintmsg }}
      </Hint>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import { useCart } from '../service/ShoppingCartStore'
import { useBookmark } from '../service/BookmarkStore'
import { Product } from '../service/Product'
import Hint from '../components/Hint.vue'

export default defineComponent({
  components: { Hint },
  name: 'productLayout',
  props: ['productLayout'],
  setup() {
    const { pushToCart, addToShoppingCart, quantityCart } = useCart()
    const { pushToBookmark, addToBookmark } = useBookmark()
    const cartHintmsg = ref('')
    const soldoutHintmsg = ref('')
    const loginHintmsg = ref('')
    const count = ref(1)

    // Produkt wird dem Warenkorb hinzugefueg
    function addToCart(product: Product) {
      if (localStorage.getItem('token') !== null) {
        if (product.amount > 0 && count.value > 0) {
          const a = product.amount
          product.amount = count.value
          pushToCart(product)
          const userid = localStorage.getItem('userid')
          addToShoppingCart(userid !== null ? parseInt(userid) : 0, product)
          quantityCart(product)
          product.amount = a - count.value
          if (product.amount > 0) {
            count.value = 1
          } else {
            count.value = 0
          }
          displayCartHint()
        }
      } else {
        displayLoginHint()
      }
    }

    // Produkt wird der Merkliste hinzugefuegt
    function addToBookmarks(product: Product) {
      if (localStorage.getItem('token') !== null) {
        pushToBookmark(product)
        const userid = localStorage.getItem('userid')
        addToBookmark(userid !== null ? parseInt(userid) : 0, product)
      } else {
        displayLoginHint()
      }
    }

    // Vermindert Anzahl des ausgewaehlten Produkts
    function decrementCounter(product: Product) {
      if (count.value > 0) {
        count.value--
      }
    }
    /**
     * Erhoeht Anzahl des ausgewaehlten Produkts solange,
     * bis die maxmiale Anzahl des Produktes erreicht wurde
     */
    function incrementCounter(product: Product) {
      if (product.amount > 0) {
        if (count.value < product.amount) {
          count.value++
        }
      } else {
        count.value = 0
        displaySoldoutHint()
      }
    }

    // Benachrichtigung wenn Produkt ausverkauft ist
    function displaySoldoutHint() {
      soldoutHintmsg.value = 'Das Produkt ist ausverkauft!'
      setTimeout(function () {
        soldoutHintmsg.value = ''
      }, 3000)
    }
    /**
     * Benachrichtigung wenn Produkt dem Warenkorb hinzugefuegt wurde
     */
    function displayCartHint() {
      cartHintmsg.value = 'zum Warenkorb hinzugefügt!'
      setTimeout(function () {
        cartHintmsg.value = ''
      }, 3000)
    }

    // Benachrichtigung wenn man nicht angemeldet ist
    function displayLoginHint() {
      loginHintmsg.value = 'Bitte zuerst anmelden!'
      setTimeout(function () {
        loginHintmsg.value = ''
      }, 3000)
    }

    return {
      addToCart,
      addToBookmarks,
      decrementCounter,
      incrementCounter,
      count,
      cartHintmsg,
      soldoutHintmsg,
      loginHintmsg
    }
  }
})
</script>

<style scoped>
.dealLayout {
  width: 20vw;
  box-shadow: 0px 0px 16px rgba(0, 0, 0, 0.25);
  border-radius: 5%;
  background-color: rgb(240, 240, 240);
  margin-bottom: 10%;
}
.image {
  width: 15vw;
  height: 20vh;
  border-radius: 5%;
  padding: 2% 1% 2% 2%;
  margin-left: 10%;
}
.img {
  background-color: white;
  border-top-left-radius: 5%;
  border-top-right-radius: 5%;
}
.detail h2 {
  font-size: 1.7vw;
  color: #000;
  margin-top: 2%;
  margin-left: 2%;
  margin-bottom: 0%;
  text-align: left;
  padding: 2% 0 0 4%;
}
.detail h3 {
  font-size: 1vw;
  color: #000;
  margin-left: 2%;
  margin-top: 0%;
  text-align: left;
  padding: 1% 0 0 4%;
}
.detail h4 {
  font-size: 1vw;
  color: black;
  padding: 1% 0 0 4%;
  margin-left: 2%;
  margin-top: 0%;
  margin-bottom: 1%;
  text-align: left;
}
.detail h5 {
  font-size: 1.4vw;
  color: black;
  text-align: right;
  margin-right: 7%;
  margin-top: 4%;
  margin-bottom: 0%;
}
.number {
  display: grid;
  grid-template-columns: repeat(4, 0fr);
  padding: 6% 3% 6% 3%;
  margin-left: 35%;
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
.star {
  color: black;
  position: absolute;
  margin-left: 7%;
  margin-top: 0.5%;
  font-size: 1.7vw;
  background: transparent;
  border: none;
}
.star:focus {
  color: blue;
  outline: none;
}
.number h2 {
  height: 3.2vh;
  width: 3vw;
  font-size: 1.5vw;
  text-align: center;
  padding: 0 0 20% 0;
  margin-top: 0%;
}
</style>
