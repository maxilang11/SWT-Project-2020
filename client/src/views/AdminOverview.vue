<template>
  <div id="adminhome">
    <div id="admin">
      <h1>Produkte</h1>
      <div id="download">
        <button id="downloadButton" @click="exportPDF">
          Export der Produkte als PDF
        </button>
        <button id="downloadButton" @click="exportSQL">
          Export der Produkte als CSV
        </button>
        <button id="downloadButton" @click="exportXLS">
          Export der Produkte als Excel
        </button>
      </div>
      <div class="option" id="option">
        <h2>Produkte nach Kategorien sortieren</h2>
        <div id="checkbox">
          <input
            type="radio"
            id="fruit"
            value="Obst & Gemüse"
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="fruit"> Obst & Gemüse </label><br />
          <input
            type="radio"
            id="snack"
            value="Süßigkeiten & Getränke"
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="snack"> Süßigkeiten & Getränke </label><br />
          <input
            type="radio"
            id="fresh"
            value="Fisch & Fleisch"
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="fresh"> Fisch & Fleisch </label><br />
          <input
            type="radio"
            id="cold"
            value="Tiefkühl"
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="cold">Tiefkühlprodukte </label><br />
          <input
            type="radio"
            id="deal"
            value="Angebote"
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="deal">Angebote </label><br />
          <input
            type="radio"
            id="all"
            value=""
            name="deliveryOpt"
            v-model="picked"
            style="zoom: 2"
          />
          <label for="deal">Alle </label>
        </div>
      </div>
      <div id="products">
        <table cellspacing="0" class="table2">
          <thead>
            <th></th>
            <th>Produkt</th>
            <th width="30%">Preis</th>
            <th>Herkunft</th>
          </thead>
          <tbody>
            <AdminProducts
              :product="product"
              v-for="product in products"
              :key="product.title"
            />
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, computed, ref } from 'vue'
import AdminProducts from '../components/AdminProducts.vue'
import { useProducts } from '../service/ProductsStore'

export default defineComponent({
  name: 'AdminOverview',
  components: {
    AdminProducts
  },

  setup() {
    const { liste, update, exportProducts } = useProducts()
    onMounted(async () => {
      await update()
    })
    const picked = ref('')

    // Produktliste wird nach ausgewählten Kategorien sortiert
    const products = computed(() =>
      liste.value.filter(
        product =>
          product.category === picked.value.split(' &')[0] ||
          product.category === picked.value.split('& ')[1] ||
          picked.value === ''
      )
    )

    // Lädt die Produktliste als pdf herunter
    function exportPDF() {
      exportProducts('pdf')
    }
    // Lädt die Produktliste als xls herunter
    function exportXLS() {
      exportProducts('xls')
    }
    // Lädt die Produktliste als csv herunter
    function exportSQL() {
      exportProducts('csv')
    }

    return { products, exportPDF, exportXLS, exportSQL, liste, picked }
  }
})
</script>
<style scoped>
#adminhome {
  margin-left: 15%;
  text-align: left;
}
#admin h1 {
  margin-top: 2%;
  font-size: 2.8vw;
}
#admin h2 {
  margin-top: 8vh;
  font-size: 1.8vw;
}
#downloadButton {
  margin-top: 2%;
  font-size: 1.4vw;
  border-radius: 16px;
  border-color: #0066ff;
  height: 8vh;
  width: 22vw;
  margin-right: 2%;
}
#checkbox {
  position: relative;
  margin-bottom: 2%;
  font-size: 1.4vw;
}
#products {
  margin-top: 4%;
}
.table2 {
  border-collapse: collapse;
  border-spacing: 0 1rem;
  width: 80%;
  text-align: left;
  margin-bottom: 2%;
}
#products tr:nth-child(odd) {
  background-color: #f2f2f2;
  border: 2px solid #0066ff;
}
#products th {
  padding: 2vh;
  font-size: 1.3vw;
}
</style>
