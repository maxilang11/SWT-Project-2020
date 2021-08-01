<template>
  <div id="order">
    <h1>Bestellung abschließen</h1>
    <h2>Bestellübersicht</h2>
    <div id="orderlist">
      <table cellspacing="0" class="tableP">
        <thead>
          <th width="70%">Produkt</th>
          <th>Anzahl</th>
          <th>Preis</th>
        </thead>
        <tbody>
          <OrderListRow
            :orderItems="item"
            v-for="item in orderItems"
            :key="item.title"
          />
        </tbody>
      </table>
    </div>
    <hr />
    <div id="price">
      <h2>Gesamtpreis {{ totalPrice.toFixed(2) }}€</h2>
      <p>Der Preis enthält 19% MwSt.</p>
    </div>
    <p id="warning" v-if="warning == ' '">
      Es muss jeweils eine Möglichkeit ausgewählt werden!
    </p>
    <div class="option" id="option">
      <h2>Lieferoptionen</h2>
      <div>
        <div class="deliveryOption">
          <input
            type="radio"
            id="pickup"
            value="Abholung in der Filiale"
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="pickup"> Abholung in der Filiale </label>
          <input
            type="radio"
            id="delivery"
            value="Lieferung nach Hause"
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="delivery"> Lieferung nach Hause </label>
        </div>
      </div>
    </div>
    <div class="payment" id="payment">
      <h2>Bezahlmethode</h2>
      <div>
        <div class="imgcontainer">
          <input
            type="radio"
            id="paypal"
            value="PayPal"
            name="payment"
            v-model="payment"
            style="zoom: 2"
          />
          <img src="../assets/images/paypal.png" class="img" />
        </div>
        <div class="imgcontainer">
          <input
            type="radio"
            id="visa"
            value="Visa"
            name="payment"
            v-model="payment"
            style="zoom: 2"
          />
          <img src="../assets/images/visa.png" class="img" />
        </div>
        <div class="imgcontainer">
          <input
            type="radio"
            id="mastercard"
            value="Mastercard"
            name="payment"
            v-model="payment"
            style="zoom: 2"
          />
          <img src="../assets/images/mastercard.png" class="img" />
        </div>
        <div class="imgcontainer">
          <input
            type="radio"
            id="bar"
            value="Barzahlung"
            name="payment"
            v-model="payment"
            style="zoom: 2"
          />
          <p>Barzahlung</p>
        </div>
      </div>
    </div>
    <div class="button">
      <Button
        id="checkoutBn"
        @click="checkoutOrder(picked, payment), (warning = ' ')"
        >Bestellung abschließen</Button
      >
    </div>
  </div>
</template>

<script lang="ts">
import { onMounted, computed, ref, defineComponent } from 'vue'
import OrderListRow from '../components/OrderListRow.vue'
import { useCart } from '../service/ShoppingCartStore'
import { useToken } from '../service/UserStore'
import { useOrder } from '../service/OrderStore'
import router from '../router/index'

export default defineComponent({
  name: 'OrderList',
  components: { OrderListRow },
  setup() {
    const {
      liste,
      update,
      amount,
      getTotalPrice,
      totalPrice,
      delCartList
    } = useCart()
    const orderItems = liste
    const { checkout } = useOrder()
    const { jwtDecrypt } = useToken()
    const orderService = ''
    const warning = ref('')
    const userid = localStorage.getItem('userid')

    onMounted(async () => {
      await update(userid !== null ? parseInt(userid) : 0)
      await getTotalPrice(userid !== null ? parseInt(userid) : 0)
    })

    const mwt = computed(() => totalPrice.value * 0.19)

    // Daten werden als Bestellung abgespeichert und Warenkorb wird geleert
    function checkoutOrder(picked: String, payment: String) {
      const name = jwtDecrypt().sub
      if (picked != null && payment != null) {
        checkout(
          totalPrice.value,
          name,
          liste.value,
          amount.value,
          picked,
          payment
        )
        delCartList()
        router.push('/bestellbestaetigung')
      }
    }
    return {
      orderItems,
      mwt,
      totalPrice,
      checkoutOrder,
      orderService,
      warning
    }
  }
})
</script>

<style scoped>
#order {
  margin-left: 15%;
  margin-right: 15%;
  text-align: left;
}
#order tr:nth-child(odd) {
  background-color: #f2f2f2;
  border: 2px solid #2196f3;
}
#order h2 {
  font-size: 35px;
}
#order th {
  padding: 20px;
  font-size: 30px;
}
.tableP {
  border-collapse: collapse;
  width: 70vw;
  margin-top: 50px;
  text-align: left;
}
#price h2 {
  padding-left: 51vw;
  font-size: 30px;
  margin-top: 0.5em;
}
hr {
  margin-top: 0.3em;
  display: block;
}

#price p {
  padding-left: 51vw;
  color: rgb(131, 129, 129);
}
.payment {
  display: flex;
  flex-direction: column;
  margin-bottom: 12px;
  font-size: 20px;
}
.img {
  max-width: 90%;
}
.imgcontainer {
  display: flex;
  padding-right: 45px;
  padding-bottom: 20px;
  float: left;
}
.deliveryOption {
  margin-bottom: 12px;
  font-size: 22px;
}
#checkoutBn {
  border-radius: 8px;
  background: #0066ff;
  border: none;
  color: white;
  width: 18vw;
  height: 8vh;
  font-size: 1.4vw;
  position: relative;
  margin-left: 40vw;
}
#warning {
  color: rgb(233, 74, 74);
}
</style>
