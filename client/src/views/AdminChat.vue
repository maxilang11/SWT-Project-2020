<template>
  <div class="root">
    <h1>Admin-Daten</h1>
    <button class="btn" id="api">Spring-Boot-Admin</button>
    <div v-if="mydata">
      <h2>Anzahl an Benutzern: {{ mydata }} Benutzer</h2>
    </div>
    <div v-if="mytop">
      <h2>Top 5 Produkte mit dem höchsten Lagerbestand</h2>
    </div>
    <div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Produkt</th>
            <th>Verfügbarkeit</th>
            <th>Preis</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="e in mytop" :key="e.id">
            <td>{{ e.id }}</td>
            <td>{{ e.name }}</td>
            <td>{{ e.amount }} Einheiten</td>
            <td>{{ e.price }}€</td>
          </tr>
        </tbody>
      </table>
      <div v-if="mysection">
        <h2>Produkte je Preisklasse</h2>
      </div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Preisklasse</th>
            <th>Produkte in Preisklasse von sehr günstig bis teuer</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="e in mysection" :key="e.id">
            <td></td>
            <td>Artikel in Preisklasse:{{ e }}</td>
          </tr>
        </tbody>
      </table>
      <div v-if="mychat">
        <!-- <p>Chat mit Admin{{mychat}}</p> -->
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  data() {
    return {
      mydata: null,
      mytop: null,
      mychat: null,
      mysection: null
    }
  },
  mounted() {
    // Simple GET request using fetch
    fetch('/users')
      .then(response => response.json())
      .then(data => (this.mydata = data.page.totalElements))

    fetch('/api/products/top')
      .then(response => response.json())
      .then(data => (this.mytop = data))

    fetch('/api/products/section')
      .then(response => response.json())
      .then(data => (this.mysection = data))

    // fetch(
    //   'https://api.telegram.org/bot1586820092:AAGWsfGFjSi3jjrdQdd3_ciqrsSO_xhyQFY/getUpdates'
    // )
    //   .then(response => response.json())
    //   .then(data => (this.mychat = data))
  }
})
</script>
<style></style>
