<template>
  <div id="nav-bar">
    <nav id="menu">
      <router-link to="/startseite">
        <img src="../assets/images/MiMarktLogo.png" alt="logo" id="logo" />
      </router-link>
      <ul>
        <li>
          <router-link to="/startseite" class="item">Angebote</router-link>
        </li>
        <li>
          <router-link to="/kategorien" class="item">Kategorien</router-link>
        </li>
      </ul>
    </nav>
    <nav id="button">
      <ul>
        <li>
          <router-link to="/bookmarks" class="item">
            <i class="far fa-star" /> Merkliste
          </router-link>
        </li>
        <li>
          <router-link to="/warenkorb" class="item">
            <i class="fas fa-shopping-cart" /> Warenkorb ({{ quantity }})
          </router-link>
        </li>
        <li v-if="isLoggedIn()">
          <router-link to="/profil" class="item">
            <i class="fas fa-user" /> Mein Profil
          </router-link>
        </li>
        <li v-else>
          <router-link to="/login" class="item">
            <i class="fas fa-user" /> Login
          </router-link>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue'
import { useCart } from '../service/ShoppingCartStore'

export default defineComponent({
  name: 'NavBar',
  setup() {
    const { quantity, getQuantityCart } = useCart()
    const userid = localStorage.getItem('userid')

    onMounted(async () => {
      await getQuantityCart(userid !== null ? parseInt(userid) : 0)
    })
    // für Conditional Rendering, ob Login oder Mein Profil angezeigt wird
    function isLoggedIn() {
      if (localStorage.getItem('token') !== null) {
        return true
      } else {
        return false
      }
    }

    return { quantity, isLoggedIn }
  }
})
</script>

<style scoped>
#logo {
  left: -11%;
  top: -10%;
  width: 4.5vw;
  position: absolute;
}
#nav-bar {
  background-color: rgb(99, 99, 99);
  background-size: cover;
  min-height: 50px;
  height: 5vw;
  width: 100vw;
  position: relative;
}
ul {
  list-style-type: none;
}
li {
  float: left;
}
.router-link-active {
  background-color: #0066ff;
}
.item:hover {
  color: #4b4b4b;
}
.item {
  padding-right: 1vw;
  padding-top: 1vw;
  padding-left: 1vw;
  padding-bottom: clamp(0.8rem, 1.2vw, 2.45rem);
  border-radius: 10px 10px 0px 0px;
  text-decoration: none;
  font-size: 1.7vw;
  color: white;
}
#menu {
  position: absolute;
  top: 50%;
  left: 27%;
  transform: translate(-50%, -60%);
}
#button {
  position: absolute;
  top: 50%;
  right: 0%;
  transform: translate(-30%, -60%);
  float: left;
}
</style>
