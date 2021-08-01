<template>
  <div class="errorpage">
    <h1>(404) Error-Page: Seite nicht gefunden</h1>
    <p id="text">Deine eingegebene URL existiert leider nicht.</p>
    <div class="img">
      <img src="../assets/images/errorImage.png" alt="ErrorImage" id="logo" />
      <span>Drücke den Button oder gib mir Fisch!</span>
    </div>
    <router-link to="/startseite">
      <button id="button">Zurück zur Startseite</button></router-link
    >
  </div>
</template>

<script></script>

<style scoped>
h1 {
  font-size: 4vw;
  color: #0066ff;
  margin-top: 5%;
}
#text {
  font-size: 2.5vw;
  margin-top: 3%;
}
#button {
  background-color: #0066ff;
  color: white;
  border-radius: 8px;
  border: none;
  width: 22vw;
  height: 6vh;
  font-size: 2vw;
  padding: 0 0 0.5% 0.5%;
  margin-top: 4%;
}
#logo {
  margin-top: 5%;
  width: 10vw;
  height: 20vh;
}
@media (max-width: 666px) {
  h1 {
    font-size: 10vw;
  }
  #text {
    font-size: 0vw;
    margin-top: 3%;
  }
  #logo {
    margin-top: 5%;
    width: 0%;
    height: 0%;
  }
}
</style>
