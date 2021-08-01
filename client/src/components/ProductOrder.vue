<template>
  <div class="order">
    <h1>Produkte beim Händler nachbestellen</h1>
    <p>Produkt: {{ productName }}</p>
    <p id="sel">
      Anzahl:
      <select class="select" v-model="selected">
        <option value="0">0</option>
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="30">30</option>
        <option value="40">40</option>
        <option value="50">50</option>
      </select>
    </p>
    <button id="btn" @click="onSubmit()">Produkte bestellen</button>
  </div>
</template>
<script>
import { ref } from 'vue'
import { useProducts } from '../service/ProductsStore'
import router from '../router/index'
export default {
  props: ['productId', 'productName'],
  name: 'ProductOrder',
  setup(props) {
    const { changeAmount } = useProducts()
    const selected = ref('')
    function onSubmit() {
      const x = parseInt(selected.value)
      changeAmount(props.productId, x)
      router.go(0)
    }
    return {
      onSubmit,
      selected
    }
  }
}
</script>

<style scoped>
.order {
  text-align: left;
  padding-left: 5%;
  font-family: 'Open Sans';
  display: flex;
  flex-direction: column;
}
.order h1 {
  font-size: 2.3vw;
}
.order p {
  font-size: 1.6vw;
  margin-top: 0%;
  margin-bottom: 1%;
}
#sel {
  margin-top: 1.5%;
}
.select {
  margin-left: 2%;
  font-size: 1.5vw;
}
#btn {
  border-radius: 8px;
  background: #0066ff;
  border: none;
  color: white;
  width: 18vw;
  height: 4vh;
  font-size: 1.5vw;
  font-family: 'Open Sans';
  position: relative;
  margin-left: 0%;
  padding: 0.8% 0 5% 0;
  margin-top: 4%;
  margin-bottom: 0%;
}
</style>
