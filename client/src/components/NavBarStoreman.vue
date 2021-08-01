<template>
  <div id="nav-bar">
    <nav id="menu">
      <ul>
        <li>
          <router-link to="/lagerist/bestellungen" class="item"
            >Bestellungen</router-link
          >
        </li>
        <li>
          <router-link to="/lagerist/produkte" class="item"
            >Produkte</router-link
          >
        </li>
      </ul>
    </nav>
    <nav id="button">
      <ul>
        <li>
          <i @click="logout()" class="fas fa-sign-out-alt"></i>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { useUser } from '../service/UserStore'

export default defineComponent({
  name: 'NavBarStoreman',
  setup() {
    const { logout } = useUser()
    return { logout }
  }
})
</script>
<style scoped>
ul {
  list-style-type: none;
}
li {
  float: left;
}
#nav-bar {
  background-color: rgb(99, 99, 99);
  background-size: cover;
  min-height: 50px;
  height: 5vw;
  width: 100vw;
  position: relative;
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
  margin: 0;
  top: 50%;
  left: 27%;
  transform: translate(-50%, -60%);
}
#button {
  position: absolute;
  top: 50%;
  right: 15%;
  transform: translate(-50%, -75%);
  float: left;
  color: white;
}

.router-link-active {
  background-color: #0066ff;
}
</style>
