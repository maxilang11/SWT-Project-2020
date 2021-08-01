<template>
  <div class="container">
    <h1>ADMIN-DATEN</h1>
    <button class="btn" id="api">Spring-Boot-Admin</button>
    <h3 class="p-3 text-center">Anzahl an Benutzern unserer Anwendung</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Anzahl</th>
        </tr>
      </thead>
      <tbody>
        <td>{{ userCount }}</td>
        <!-- @GetMapping("/products/countAll") -->
      </tbody>
    </table>
    <h3 class="p-3 text-center">Alle Benutzer unserer Anwendung</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Role</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.username }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.role }}</td>
        </tr>
      </tbody>
    </table>
    <h3 class="p-3 text-center">Anzahl an Produkten in der Datenbank</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Anzahl</th>
        </tr>
      </thead>
      <tbody>
        <td>17</td>
        <!-- @GetMapping("/products/countAll") -->
      </tbody>
    </table>
    <h3 class="p-3 text-center">
      Unsere Top-5 Produkte mit dem höchsten Lagerbestand
    </h3>
    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Lagerbestand</th>
        </tr>
      </thead>
      <tbody>
        <!-- @GetMapping("products/top") -->
        <tr v-for="product in top5" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.name }}</td>
          <td>{{ product.amount }}</td>
        </tr>
      </tbody>
    </table>
    <h3 class="p-3 text-center">Anzahl der Prdoukte nach Preisklassen</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Preisklasse</th>
          <th>Anzahl</th>
        </tr>
      </thead>
      <tbody>
        <!-- @GetMapping("/products/section") -->
        <tr v-for="product in product" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.name }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue'
import { useChart } from '../service/Charts'
export default defineComponent({
  setup() {
    const { top, top5 } = useChart()
    onMounted(async () => {
      await top()
    })
    return { top5 }
  }
})
</script>

<style>
.table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 400px;
  margin-left: 43%;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.p-3 text-center {
  text-align: center;
}

.table thead tr {
  background-color: #0066ff;
  color: #ffffff;
  text-align: center;
}
.table tbody tr {
  border-bottom: 1px solid #dddddd;
  text-align: center;
}
.table tbody tr:nth-of-type(even) {
  background-color: #f3f3f3;
}
.table tbody tr:last-of-type {
  border-bottom: 2px solid #009879;
}
.container {
  position: relative;
  width: 100%;
  overflow: hidden;
}
</style>
