<template>
  <tr class="order-div">
    <div @click="showOrderDetail">
      <td id="id">{{ order.id }}</td>
      <td id="col">{{ order.user.surname }} {{ order.user.name }}</td>
      <td id="col">{{ order.totalAmount }} Produkte</td>
      <td id="col1">
        {{ dateSplit[2] }}.{{ dateSplit[1] }}.{{ dateSplit[0] }}
      </td>
      <td id="col2">{{ order.orderstatus }}</td>
    </div>
  </tr>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import router from '../router/index'
export default defineComponent({
  name: 'OrderOverviewGrid',
  props: ['order'],
  setup(props) {
    const dateOrder = props.order.date.toString()
    const dateSplit = dateOrder.split('-')

    // Leitet auf Detailseite weiter, wenn Status 'In Bearbeitung' ist
    function showOrderDetail() {
      if (props.order.orderstatus === 'In Bearbeitung') {
        router.push('/lagerist/bestelldetails/' + props.order.id)
      }
    }
    return {
      dateSplit,
      showOrderDetail
    }
  }
})
</script>

<style scoped>
#id {
  width: 2vw;
  margin-left: 1%;
  display: inline-block;
  margin-top: 2%;
  margin-bottom: 2%;
  font-size: 1.3vw;
}
#col {
  width: 15vw;
  display: inline-block;
  font-size: 1.3vw;
}
#col1 {
  width: 9vw;
  display: inline-block;
  font-size: 1.3vw;
}
#col2 {
  width: 22vw;
  display: inline-block;
  font-size: 1.3vw;
  margin-left: 3.5%;
}
tr:nth-child(even) {
  background-color: white;
}
tr:nth-child(odd) {
  background-color: #f2f2f2;
  border: 2px solid #0066ff;
}
</style>
