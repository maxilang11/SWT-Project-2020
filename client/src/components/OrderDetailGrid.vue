<template>
  <tr class="order-div">
    <td id="col">{{ orderDetail.name }}</td>
    <td id="col">{{ order.amount[orderDetail.id] }} Stk.</td>
    <td id="col2">
      <button
        v-if="checked == ''"
        class="btnPacked"
        @click="check(orderDetail)"
        value=""
      >
        Einpacken
      </button>
    </td>
    <td id="col3" v-if="checked == ' '">Eingepackt</td>
  </tr>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import { useOrder } from '../service/OrderStore'
import { useRoute } from 'vue-router'
import { Product } from '../service/Product'
export default defineComponent({
  name: 'OrderDetailGrid',
  props: ['orderDetail'],

  setup() {
    const { getOrderById, order, pushToPacked } = useOrder()
    const path = useRoute().params.idOrder.toString()
    const checked = ref('')
    onMounted(async () => {
      await getOrderById(parseInt(path))
    })
    /**
     * Produkt ist eingepackt
     */
    function check(product: Product) {
      pushToPacked(product)
      checked.value = ' '
    }
    return { order, check, pushToPacked, checked }
  }
})
</script>

<style scoped>
.order-div td {
  padding: 1%;
  font-size: 1.4vw;
}
#col {
  width: 20vw;
  display: inline-block;
  padding-top: 1%;
}
#col2 {
  width: 1vw;
  display: inline-block;
  padding-top: 1%;
  margin-left: 4%;
}
#col3 {
  width: 5vw;
  display: inline-block;
  padding-top: 1%;
}
tr:nth-child(even) {
  background-color: white;
}
tr:nth-child(odd) {
  background-color: #f2f2f2;
  border: 2px solid #0066ff;
}
.btnPacked {
  border-radius: 8px;
  background: #0066ff;
  border: none;
  color: white;
  font-size: 1.2vw;
  height: 4vh;
  width: 8vw;
}
</style>
