<template>
  <tr class="order">
    <td>{{ order.id }}</td>
    <td>
      <div v-if="order.productList.length == 1">
        {{ order.productList[0].name }}
      </div>
      <div v-if="order.productList.length == 2">
        {{ order.productList[0].name }}, {{ order.productList[1].name }}
      </div>
      <div v-if="order.productList.length == 3">
        {{ order.productList[0].name }}, {{ order.productList[1].name }},
        {{ order.productList[2].name }}
      </div>
      <div v-if="order.productList.length == 4">
        {{ order.productList[0].name }}, {{ order.productList[1].name }},
        {{ order.productList[2].name }} +
        {{ order.productList.length - 3 }} Produkt
      </div>
      <div v-if="order.productList.length > 4">
        {{ order.productList[0].name }}, {{ order.productList[1].name }},
        {{ order.productList[2].name }} +
        {{ order.productList.length - 3 }} Produkte
      </div>
    </td>
    <td>{{ d[2] }}.{{ d[1] }}.{{ d[0] }}</td>
    <td>{{ order.orderstatus }}</td>
    <td>{{ order.totalPrice.toFixed(2) }}€</td>
  </tr>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'order',
  props: ['order'],

  setup(props) {
    const dateOrder = props.order.date.toString()
    const d = dateOrder.split('-')

    return { dateOrder, d }
  }
})
</script>

<style scoped>
.order td {
  padding: 30px;
  font-size: 20px;
}

#orders {
  box-sizing: border-box;
  float: left;
  margin-right: 1vh;
}
</style>
