<template>
  <div class="page-wrap">
    <h1>Warenkorb</h1>
    <table class="cart">
      <thead>
        <tr class="headlines">
          <th>Produktname</th>
          <th id="anzahl">Anzahl</th>
          <th>Einzelpreis</th>
          <th>Gesamtpreis</th>
        </tr>
      </thead>
      <tbody>
        <ShoppingCartGrid
          :cartItems="item"
          v-for="item in cartItems"
          :key="item.id"
        />
      </tbody>
      <tfoot></tfoot>
    </table>
    <h3 id="total-sum">
      Gesamtsumme:
      {{ Math.abs(totalPrice.toFixed(2)) }}€
    </h3>
    <p id="mwst">inkl. 19% MwSt.: {{ Math.abs(mwst.toFixed(2)) }}€</p>
    <h4>
      <p v-if="totalPrice <= 0" id="error">
        Ihr Warenkorb ist zurzeit leer, fügen Sie bitte Produkte dem Warenkorb
        hinzu, um eine Bestellung abzuschließen!
      </p>
      <p v-else id="errorelse"></p>
    </h4>
    <router-link to="/bestelluebersicht">
      <button v-show="totalPrice > 0" id="checkoutBtn">
        Produkte bestellen
      </button>
    </router-link>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, computed } from 'vue'
import ShoppingCartGrid from '../components/ShoppingCartGrid.vue'
import { useCart } from '../service/ShoppingCartStore'

export default defineComponent({
  name: 'ShoppingCart',
  components: { ShoppingCartGrid },
  setup() {
    const { liste, update, totalPrice, getTotalPrice } = useCart()
    const cartItems = liste
    const userid = localStorage.getItem('userid')

    /* zu Beginn des Aufrufs der Merklistseite wird entsprechend der UserID des aktuell
    eingeloggten Users ein mal die Liste von Produkten sowie der TotalPrice im Warenkorb geupdated */
    onMounted(async () => {
      await update(userid !== null ? parseInt(userid) : 0)
      await getTotalPrice(userid !== null ? parseInt(userid) : 0)
    })
    const mwst = computed(() => totalPrice.value * 0.19)

    return { cartItems, totalPrice, mwst }
  }
})
</script>

<style scoped>
.cart {
  border-collapse: separate;
  border-spacing: 0 1rem;
  border-block-end: 6px solid black;
  width: 80%;
}
.page-wrap {
  margin-left: 15%;
  display: flex;
  flex-direction: column;
}
h1 {
  font-size: 2.8vw;
  color: black;
  text-align: left;
  margin-bottom: 2%;
}
h4 {
  font-size: 1.5vw;
  color: black;
  margin-left: 26%;
  margin-bottom: 2%;
}

.headlines {
  display: flex;
  flex-direction: row;
  font-size: 1.2vw;
  color: gray;
  margin-left: 6%;
  margin-bottom: 0%;
}
.headlines th {
  margin-left: 6%;
  padding-bottom: 0%;
}
#anzahl {
  margin-left: 37%;
}
#total-sum {
  text-align: center;
  margin-bottom: 0%;
  margin-left: 26%;
  margin-top: 1%;
  font-size: 1.7vw;
  color: black;
}
#mwst {
  text-align: center;
  margin-top: 0%;
  margin-left: 19%;
  font-size: 0.9vw;
  color: black;
}
#checkoutBtn {
  background-color: #0066ff;
  border: none;
  border-radius: 8px;
  color: white;
  font-size: 1.4vw;
  width: 15vw;
  height: 4.5vh;
  margin-left: 52%;
  margin-top: 1%;
  transition: all 0.2s ease-in-out;
  color: rgba(255, 255, 255, 1);
  box-shadow: 0 5px 15px rgba(145, 92, 182, 0.9);
}
@media (max-width: 666px) {
  h1 {
    font-size: 5vw;
    color: black;
    text-align: left;
    margin-bottom: 2%;
  }
  #error {
    font-size: 3vw;
    text-align: left;
  }
  #anzahl {
    margin-left: 37%;
  }
  #total-sum {
    text-align: center;
    margin-bottom: 0%;
    margin-left: 1%;
    margin-top: 1%;
    font-size: 5.2vw;
    color: black;
  }
  #mwst {
    text-align: center;
    margin-top: 0%;
    margin-left: 1%;
    font-size: 2.9vw;
    color: black;
  }
  #checkoutBtn {
    background-color: #0066ff;
    border: none;
    border-radius: 8px;
    color: white;
    font-size: 4.5vw;
    width: 30vw;
    height: 12.5vh;
    margin-left: 25%;
    margin-top: 1%;
  }
  th {
    font-size: 2vw;
    text-align: left;
  }
  .headlines {
    display: flex;
    flex-direction: row;
    font-size: 1.2vw;
    color: gray;
    margin-left: 1%;
    margin-bottom: 0%;
  }
  .headlines th {
    margin-left: 3%;
    padding-bottom: 0%;
  }
}
</style>
