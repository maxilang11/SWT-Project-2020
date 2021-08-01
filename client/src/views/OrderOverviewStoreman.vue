<template>
  <div class="page-wrap">
    <h1>Eingegangene Bestellungen</h1>
    <table class="orders">
      <thead>
        <tr class="headlines">
          <th>Name, Vorname</th>
          <th id="amount">Produktanzahl</th>
          <th id="date">Datum</th>
          <th id="order">Bestellung vollständig</th>
        </tr>
      </thead>
      <tbody>
        <OrderOverviewGrid
          :order="order"
          v-for="order in orderItems"
          :key="order.id"
        />
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import OrderOverviewGrid from '../components/OrderOverviewGrid.vue'
import { defineComponent, onMounted } from 'vue'
import { useOrder } from '../service/OrderStore'
export default defineComponent({
  name: 'OrderOverviewStoreman',
  components: { OrderOverviewGrid },
  setup() {
    const { getOrders, orders } = useOrder()
    const orderItems = orders

    onMounted(async () => {
      await getOrders()
    })

    return { orderItems }
  }
})
</script>

<style scoped>
.orders {
  border-collapse: collapse;
  border-spacing: 0 1rem;
  width: 80%;
  margin-top: 3%;
  text-align: left;
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
.headlines {
  display: flex;
  flex-direction: row;
  font-size: 1.5vw;
  color: gray;
  margin-bottom: 0.2%;
  width: 68vw;
}
.headlines th {
  margin-left: 4%;
}
#amount {
  margin-left: 5%;
}
#date {
  margin-left: 7%;
}
#order {
  margin-left: 9.5%;
}
</style>
