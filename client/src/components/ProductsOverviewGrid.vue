<template>
  <tr class="product-div">
      <td id="id">{{product.id}}</td>
      <td id="col">{{product.name}}</td>
      <td id="col">{{product.amount}} Stk.</td>
      <td id="col2">
        <ProductOverview :product-id="product.id" :product-name="product.name"/>
      </td>
  </tr>
</template>

<script lang="ts">
  import ProductOverview from '../views/ProductOverview.vue'
  export default {
    components: { ProductOverview },
    name: 'ProductsOverviewGrid',
    props: { product: Object }
  }

</script>

<style scoped>
  .product-div td{
    padding: 1%;
    font-size: 1.4vw;
    position: relative;
  }

  id {
    width: 3vw;
    padding-left: 20%;
  }

  col {
    width: 16vw;
    display: inline-block;
    padding-top: 0%;
  }

  col2 {
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
</style>
