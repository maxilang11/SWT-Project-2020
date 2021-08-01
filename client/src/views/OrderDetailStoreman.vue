<template>
  <div class="page-wrap">
    <div class="info">
      <h1>Bestellung Nr. {{ order.id }}</h1>
      <div class="user">
        <p>
          {{ order.user.surname }} {{ order.user.name }} <br />
          {{ order.user.zipcode }} {{ order.user.city }} <br />
          {{ order.user.street }} {{ order.user.addressnr }}<br />{{
            order.user.email
          }}
        </p>
      </div>
      <div class="delivery">
        <h2>Lieferoptionen</h2>
        <p>{{ order.deliveryOption }}</p>
      </div>
      <h3>Produkte</h3>
    </div>
    <table class="orders">
      <thead>
        <tr class="headlines">
          <th id="name">Produkt</th>
          <th id="amount">Anzahl</th>
          <th id="wrapped">Produkte wurden eingepackt</th>
        </tr>
      </thead>
      <tbody>
        <OrderDetailGrid
          :orderDetail="orderDetail"
          v-for="orderDetail in order.productList"
          :key="orderDetail.id"
        />
      </tbody>
    </table>
    <p id="warning" v-if="warning == ' '">
      Um eine Bestellung abzuschließen muss jedes Produkt eingepackt werden.
    </p>
    <div class="completeBtn">
      <button class="btn" @click="orderFinish(order), (warning = ' ')">
        Bestellung vollständig,<br />Bestätigung an den Kunden senden
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import OrderDetailGrid from '../components/OrderDetailGrid.vue'
import { defineComponent, onMounted, ref } from 'vue'
import { useOrder } from '../service/OrderStore'
import { useRoute } from 'vue-router'
import { Order } from '../service/Order'
import router from '../router/index'
export default defineComponent({
  name: 'OrderDetailStoreman',
  components: { OrderDetailGrid },
  setup() {
    const { getOrderById, order, getStatusById, packed, delPacked } = useOrder()
    const path = useRoute().params.idOrder.toString()
    const warning = ref('')

    onMounted(async () => {
      await getOrderById(parseInt(path))
    })

    /**
     * Ueberpruefung ob alle Produkte eingepackt wurden, wenn ja dann: Status wird geaendert, Weiterleitung auf Uebersichtsseite;
     * wenn nicht: Fehlermeldung wird ausgegeben
     */
    function orderFinish(orders: Order) {
      if (packed.value.length === orders.productList.length) {
        getStatusById(parseInt(path))
        router.push('/lagerist/bestellungen')
        delPacked()
      }
    }
    return {
      order,
      orderFinish,
      warning
    }
  }
})
</script>

<style scoped>
.orders {
  border-collapse: collapse;
  border-spacing: 0 1rem;
  width: 60%;
  text-align: left;
  margin-bottom: 2%;
}
.page-wrap {
  margin-left: 15%;
  display: flex;
  flex-direction: column;
}
h1 {
  font-size: 2.8vw;
  color: black;
}
.headlines {
  display: flex;
  flex-direction: row;
  font-size: 1.5vw;
  color: black;
  margin-top: 1%;
}
.info {
  text-align: left;
}
#name {
  width: 5vw;
}
#amount {
  width: 5vw;
  margin-left: 26%;
}
#wrapped {
  width: 50vw;
  margin-left: 20%;
}
.user {
  font-size: 2vw;
  color: black;
  margin-bottom: 2%;
}
h2,
h3 {
  font-size: 2vw;
  color: black;
  margin-bottom: 0%;
}
.delivery p {
  font-size: 1.7vw;
  color: black;
  margin-bottom: 2%;
  margin-top: 0.5%;
}
.btn {
  border-radius: 8px;
  background: #0066ff;
  border: none;
  color: white;
  width: 25vw;
  height: 9vh;
  font-size: 1.3vw;
  text-align: left;
  float: center;
  padding-left: 1%;
  margin-left: 9%;
  outline: none;
}
.completeBtn {
  margin-bottom: 2%;
}
#warning {
  color: red;
  font-size: 1vw;
  margin-bottom: 1%;
  margin-top: 0%;
  margin-right: 3%;
}
</style>
