<template>
  <div class="start">
    <div class="checkIcon">
      <i class="far fa-check-circle"></i>
    </div>
    <div class="text">
      <h1>
        Vielen Dank für Ihre Bestellung {{ user.gender }} {{ user.surname }}!
      </h1>
      <p>
        Wir kümmern uns so schnell wie möglich um Ihre <br />
        Bestellung. <br />
        Weitere Informationen zum Status Ihrer Bestellung <br />
        erhalten Sie per E-Mail.
      </p>
      <router-link to="/startseite" class="item">
        <button class="btnBack">
          <i class="fa fa-sign-in-alt fa-flip-horizontal" id="icon" />zurück zur
          Startseite
        </button>
      </router-link>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted } from 'vue'
import { useToken, useUser } from '../service/UserStore'
export default defineComponent({
  name: 'OrderConfirmation',
  components: {},
  setup() {
    const { jwtDecrypt } = useToken()
    const { profileUser, actUserProfile } = useUser()
    const user = actUserProfile
    const name = jwtDecrypt().sub
    onMounted(async () => {
      await profileUser(name)
    })
    return {
      user
    }
  }
})
</script>

<style scoped>
.start {
  display: flex;
  flex-direction: row;
}
.checkIcon {
  color: #0066ff;
  font-size: 9vw;
  margin-left: 9%;
  margin-top: 9%;
}
h1 {
  color: #0066ff;
  font-size: 3.3vw;
}
p {
  font-size: 1.9vw;
  color: black;
}
.text {
  margin-left: 7%;
  margin-top: 9%;
}
.btnBack {
  background-color: #0066ff;
  color: white;
  border-radius: 8px;
  border: none;
  width: 19vw;
  height: 6vh;
  font-size: 1.5vw;
  position: relative;
  padding: 0 0 0.5% 3%;
  margin-left: 50%;
  margin-top: 5%;
}
#icon {
  position: absolute;
  left: 3%;
  top: 22%;
}
.btnBack:hover {
  color: #ccc8c8;
  border: 0.1em solid black;
}
</style>
