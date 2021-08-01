<template>
  <div id="app">
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.0.7/css/all.css"
    />
    <header>
      <NavBar v-if="$route.meta.showNavBar" />
      <NavBarStoreman v-if="$route.meta.showNavBarStoreman" />
      <NavBarAdmin v-if="$route.meta.showNavBarAdmin" />
    </header>
    <router-view />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import NavBar from './components/NavBar.vue'
import NavBarStoreman from './components/NavBarStoreman.vue'
import NavBarAdmin from './components/NavBarAdmin.vue'
export default defineComponent({
  name: 'App',
  components: {
    NavBar,
    NavBarStoreman,
    NavBarAdmin
  }
})
</script>

<style>
@font-face {
  font-family: 'Open Sans';
  src: local('Open Sans'),
    url(./assets/fonts/OpenSans-Regular.ttf) format('truetype');
}
* {
  box-sizing: border-box;
}
#page-wrap {
  margin: auto;
  max-width: 80vw;
  min-height: 100vh;
  padding-bottom: 8rem;
}
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-family: 'Open Sans';
}
.blueHint {
  background-color: #0066ff;
  color: white;
}
.redHint {
  background-color: red;
  color: white;
}
.fas.fa-sign-out-alt:hover {
  color: #4b4b4b;
}
.fas.fa-sign-out-alt {
  margin-top: 30px;
  font-size: 1.7vw;
  border: 0.2vw solid;
  border-radius: 100%;
  padding: 0.4vw;
}
</style>
