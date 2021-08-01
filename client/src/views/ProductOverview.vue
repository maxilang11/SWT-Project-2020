<template>
    <div class="button">
        <button class="orderBtn" @click="productOrder= ' '"> Produkte nachbestellen</button>
    <div class="overlay" v-if="productOrder" @click="productOrder=''"></div>
    <div class="productOrder" v-if="productOrder">
        <ProductOrder :product-id="idProduct" :product-name="nameProduct"/>
        <Button id="close" @click="productOrder=''">schließen</Button>
    </div>
    </div>
</template>
<script>
import { ref } from 'vue'
import ProductOrder from '../components/ProductOrder.vue'
export default {
    props: ['productId', 'productName'],
    name: 'ProductOverview',
    components: {
        ProductOrder
    },
    setup(props) {
        const idProduct = ref(props.productId)
        const nameProduct = ref(props.productName)
        const productOrder = ref('')
        return {
            productOrder,
            idProduct,
            nameProduct
        }
    }
}
</script>
<style scoped>

.orderBtn{
    border-radius: 15px;
    background: #f2f2f2;
    color: black;
    width: 15vw;
    height: 4vh;
    font-size: 1.2vw;
    font-family: "Open Sans";
    position:relative;
    margin-left: 0%;
    padding: 0.3% 0 2.1% 0;
    border: 0.1vw solid #0066FF;
  }

  .overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 98;
    height: 1100px;
}
.productOrder {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    background-color: darkgrey;
    z-index: 99;
    width:100%;
    max-width: 50vw;
}
#close {
    margin-top: 2%;
    border-radius: 8px;
    background: rgb(240,240,240);
    border: 1px solid black;
    color: black;
    width: 10vw;
    height: 4vh;
    font-size: 1.5vw;
    font-family: "Open Sans";
    margin-left: 5%;
    margin-right: 55%;
    padding: 0% 0 2% 0;
    position: relative;
    margin-bottom: 2%;
}
@media (max-width: 666px){
    .orderBtn{
    border-radius: 15px;
    background: #f2f2f2;
    color: black;
    width: auto;
    height: auto;
    font-size: 3.0vw;
    font-family: "Open Sans";
    position:relative;
    margin-left: 0%;
    padding: 0.3% 0 2.1% 0;
    /* border: 0.1vw solid #0066FF; */
  }

  .overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 98;
    height: 1100px;
}
.productOrder {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    background-color: darkgrey;
    z-index: 99;
    width:100%;
    max-width: 50vw;
}
#close {
    margin-top: 2%;
    border-radius: 8px;
    background: rgb(240,240,240);
    border: 1px solid black;
    color: black;
    width: 10vw;
    height: 4vh;
    font-size: 1.5vw;
    font-family: "Open Sans";
    margin-left: 5%;
    margin-right: 55%;
    padding: 0% 0 2% 0;
    position: relative;
    margin-bottom: 2%;
    }
}
</style>
