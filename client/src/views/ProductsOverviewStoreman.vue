<template>
  <div class="page-wrap">
    <h1>Produkte</h1>
    <h2>Produkte nach Kategorien sortieren</h2>
    <div>
      <label for="getraenke"><input type="radio" id="getraenke" value="Getränke" v-model="checkedNames" @change="sortByCategorie">Getränke</label>
    </div>
    <br>
    <div>
      <label for="obst"><input type="radio" id="obst" value="Obst" v-model="checkedNames" @change="sortByCategorie">Obst</label>
    </div>
    <br>
    <div>
      <label for="gemuese"><input type="radio" id="gemuese" value="Gemüse" v-model="checkedNames" @change="sortByCategorie">Gemüse</label>
    </div>
    <br>
    <div>
      <label for="suesses"><input type="radio" id="suesses" value="Süßigkeiten" v-model="checkedNames" @change="sortByCategorie">Süßigkeiten</label>
    </div>
    <br>
    <div>
      <label for="teig"><input type="radio" id="teig" value="Teigwaren" v-model="checkedNames" @change="sortByCategorie">Teigwaren</label>
    </div>
    <br>
    <div>
      <label for="fleisch"><input type="radio" id="fleisch" value="Fleisch" v-model="checkedNames" @change="sortByCategorie">Fleisch</label>
    </div>
    <br>
    <div>
      <label for="back"><input type="radio" id="back" value="Backwaren" v-model="checkedNames" @change="sortByCategorie">Backwaren</label>
    </div>
    <br>
    <div>
      <label for="tk"><input type="radio" id="tk" value="Tiefkühl" v-model="checkedNames" @change="sortByCategorie">Tiefkühlprodukte</label>
    </div>
    <br>
    <div>
      <label for="alle"><input type="radio" id="alle" value="alle" v-model="checkedNames" @change="sortByCategorie">Alle Produkte</label>
    </div>
    <br>
    <table class="products">
      <thead>
        <tr class="headlines">
          <th>Produkt-ID</th>
          <th>Produkt</th>
          <th id="amount">Menge im Lager</th>
          <th id="order">Produkte nachbestellen</th>
        </tr>
      </thead>
      <tbody>
        <ProductsOverviewGrid
          :product="product"
          v-for="product in products"
          :key="product.id"
        />
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
  import ProductsOverviewGrid from '../components/ProductsOverviewGrid.vue'
  import { onMounted, ref } from 'vue'
  import { useProducts } from '../service/ProductsStore'
  export default {
    components: { ProductsOverviewGrid },
    setup() {
      const { liste, update, categories } = useProducts()
      var products = liste
      var checkedNames = ref()
      onMounted(async () => {
        await update()
      })
      async function sortByCategorie() {
        if (checkedNames.value === 'alle') {
          return update()
        }
        await categories(checkedNames.value)
      }
      return {
        products,
        sortByCategorie,
        checkedNames
      }
    }
  }
</script>

<style scoped>
  .products {
    border-collapse: collapse;
    border-spacing: 0 1rem;
    width: 80%;
    margin-top: 3%;
    text-align:left;
  }
  .page-wrap {
    margin-left: 15%;
    display: flex;
    flex-direction: column;
  }
  label {
    display: block;
    text-indent: -15px;
    float:left;
    font-family: 'Open Sans';
    font-size: 1.4vw;
  }
  input {
    width: 13px;
    height: 13px;
    padding: 0;
    margin:0;
    top: -1px;
    *overflow: hidden;
    margin: 2px 0px;
    width: 80px;
    vertical-align: center;
    horiz-align: center;
  }
  h2 {
    font-size: 1.4vw;
    font-family: 'Open Sans';
    color: black;
    text-align: left;
    margin-bottom: 2%;
  }
  h1 {
    font-size: 2.8vw;
    font-family: 'Open Sans';
    color: black;
    text-align: left;
    margin-bottom: 2%;
  }
  .headlines {
    font-size: 1.5vw;
    font-family: "Open Sans";
    color: gray;
    margin-bottom: 0.2%;
    width: 68vw;
  }
  .headlines th {
    margin-left: 5.5%;
  }
  #order {
    margin-left: 15%;
  }
  #amount{
    margin-left: 8%;
  }
  /* iPhone 6 Plus portrait */
@media (max-width: 666px){
  h1 {
    font-size: 5.5vw;
    font-family: 'Open Sans';
    color: black;
    text-align: center;
    margin-bottom: 2%;
  }
  h2 {
    font-size: 3.4vw;
    font-family: 'Open Sans';
    color: black;
    text-align: center;
    margin-bottom: 2%;
  }
  .products {
    border-collapse: collapse;
    border-spacing: 0 1rem;
    width: 80%;
    margin-top: 3%;
    text-align:left;
  }
  .page-wrap {
    margin-left: 15%;
    display: flex;
    flex-direction: column;
  }
  label {
    display: block;
    text-indent: -15px;
    float:center;
    font-family: 'Open Sans';
    font-size: 4.4vw;
  }
  input {
    width: 13px;
    height: 13px;
    padding: 0;
    margin:0;
    top: -1px;
    *overflow: hidden;
    margin: 2px 0px;
    width: 80px;
    vertical-align: center;
    horiz-align: center;
  }

}
</style>
