<template>
  <div id="userprofile">
    <div id="order">
      <div id="left">
        <h1>Mein Profil ({{ user.username }})</h1>
        <h2>Meine Bestellungen</h2>
      </div>
      <div id="right">
        <i @click="logout()" class="fas fa-sign-out-alt"></i>
      </div>
      <div class="orderlist">
        <table cellspacing="0" class="table2">
          <thead>
            <th>Nr.</th>
            <th width="70%">Produkte</th>
            <th>Bestelldatum</th>
            <th>Bestellstatus</th>
            <th>Summe</th>
          </thead>
          <tbody>
            <UserOrders
              :order="order"
              v-for="order in orders"
              :key="order.title"
            />
          </tbody>
        </table>
      </div>
      <div>
        <div class="button">
          <button class="Editbutton" @click="editProfile = ' '">
            bearbeiten
          </button>
        </div>
        <div class="overlay" v-if="editProfile" @click="editProfile = ''"></div>
        <div class="editProfile" v-if="editProfile">
          <Button id="close" @click="editProfile = ''">schließen</Button>
          <EditProfile :userProfile="user" />
        </div>
      </div>
    </div>
    <div class="user">
      <h2>Meine Daten</h2>
      <div id="user" class="userData">
        <p>Name:</p>
        <p>Adresse:</p>
        <p>E-Mail Adresse:</p>
      </div>
      <div id="data" class="userData">
        <p>{{ user.name }} {{ user.surname }}</p>
        <p>
          {{ user.street }} {{ user.addressnr }} , {{ user.zipcode }}
          {{ user.city }}
        </p>
        <p>{{ user.email }}</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue'
import EditProfile from '../components/EditProfile.vue'
import UserOrders from '../components/UserOrders.vue'
import { useToken, useUser } from '../service/UserStore'
import { useOrder } from '../service/OrderStore'

export default defineComponent({
  name: 'UserProfile',
  components: {
    UserOrders,
    EditProfile
  },
  setup() {
    const editProfile = ref('')
    const { jwtDecrypt } = useToken()
    const { profileUser, actUserProfile, logout } = useUser()
    const { getUserOrders, userOrders } = useOrder()
    const orders = userOrders
    const user = actUserProfile
    const name = jwtDecrypt().sub

    onMounted(async () => {
      await profileUser(name)
      getUserOrders(name)
    })

    onMounted(async () => {
      await profileUser(name)
    })

    return {
      orders,
      editProfile,
      user,
      logout
    }
  }
})
</script>

<style scoped>
#userprofile {
  position: relative;
}
#order {
  margin-left: 15%;
  margin-right: 15%;
  text-align: left;
}
#order tr:nth-child(odd) {
  background-color: #f2f2f2;
  border: 2px solid #0066ff;
}
#order h1 {
  font-size: 40px;
}
#order h2 {
  font-size: 35px;
}
#order th {
  padding: 20px;
  font-size: 30px;
}
.table2 {
  border-collapse: collapse;
  width: 70vw;
  margin-top: 50px;
  text-align: left;
}
hr {
  margin-top: 0.3em;
  display: block;
}
.button {
  margin-top: 80px;
  text-align: right;
}
.button .Editbutton {
  background-color: #0066ff;
  color: white;
  font-size: 20px;
  padding: 10px 30px;
  font-weight: bold;
  border-radius: 16px;
}
.container {
  display: block;
  position: relative;
  padding-left: 45px;
  margin-bottom: 12px;
  font-size: 22px;
}
.container input {
  position: absolute;
  opacity: 0;
}
.user {
  margin-left: 15%;
  margin-right: 15%;
  text-align: left;
}
.user h2 {
  font-size: 35px;
  margin-bottom: 50px;
}
.user .userData {
  float: left;
  padding-right: 20px;
}
#user p {
  font-size: 30px;
  font-weight: bold;
}
#data p {
  font-size: 30px;
}
.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 98;
  background-color: rgb(0, 0, 0, 0.3);
  height: 150%;
}
.editProfile {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 99;

  width: 100%;
  max-width: 50vw;
  background-color: white;
}
#close {
  margin-left: 85%;
  margin-top: 2%;
  border-radius: 12px;
  background: #0066ff;
  border: none;
  color: white;
  width: 5vw;
  height: 4vh;
  font-size: 0.7vw;
}
#left {
  float: left;
  width: 90%;
}
#right {
  float: right;
  width: 10%;
  text-align: right;
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
