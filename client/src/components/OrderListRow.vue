<template>
  <tr class="order">
    <td>{{ orderItems.name }}</td>
    <td>{{ amount[orderItems.id] }}Stk.</td>
    <td>{{ (orderItems.price * amount[orderItems.id]).toFixed(2) }}€</td>
  </tr>
</template>

<script lang="ts">
import { ref, defineComponent, computed, onMounted } from 'vue'
import { useCart } from '../service/ShoppingCartStore'

export default defineComponent({
  name: 'OrderListRow',
  props: ['orderItems'],
  setup(props, context) {
    const quantity = ref(props.orderItems.amountCart)
    const { amount, getAmount } = useCart()
    const totalPrice = computed(() =>
      (quantity.value * props.orderItems.price).toFixed(2)
    )
    const userid = localStorage.getItem('userid')
    onMounted(async () => {
      await getAmount(userid !== null ? parseInt(userid) : 0)
    })
    return {
      quantity,
      totalPrice,
      amount
    }
  }
})
</script>

<style scoped>
.order td {
  padding: 30px;
  font-size: 20px;
}
</style>
