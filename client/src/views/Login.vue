<template>
  <div class="login">
    <div class="BildundStartseitelogin">
      <h1>
        Willkommen zum <br />
        Super MIMarkt
      </h1>
      <div class="logSts">
        <router-link to="/startseite">
          <button class="btnstartseite" @click="clearLocalStorage()">
            <i class="fa fa-caret-right fa-2x" /> Weiter ohne einloggen
          </button>
        </router-link>
      </div>
    </div>
    <div class="loginth">
      <img src="../assets/images/MiMarktLogo.png" id="logo" />
      <h2>Anmeldung</h2>
      <Form>
        <ErrorMessage name="username" class="val" />
        <div class="user">
          <Field
            v-model="username"
            name="username"
            as="input"
            class="user"
            type="text"
            placeholder="Benutzername"
            :rules="validateName"
          />
          <i class="fa fa-user" />
        </div>
        <ErrorMessage name="password" class="val" />
        <div class="password">
          <Field
            v-model="password"
            name="password"
            type="password"
            as="input"
            class="password"
            placeholder="Passwort"
            :rules="validatePassword"
          />
          <i class="fa fa-lock" />
        </div>
        <div class="logBtn">
          <a class="val">{{ errMsg }}</a
          ><br />
          <button class="btn" @click.prevent="login(username, password)">
            <i class="fa fa-sign-in-alt" />Anmelden
          </button>
        </div>
      </Form>
      <div class="acc">
        <h4>
          Noch kein Nutzer?
          <router-link to="/register">Account erstellen</router-link>
        </h4>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Form, Field, ErrorMessage } from 'vee-validate'
import { defineComponent } from 'vue'
import { useUser } from '../service/UserStore'

export default defineComponent({
  components: {
    Form,
    Field,
    ErrorMessage
  },
  setup() {
    const { login, errormessage } = useUser()
    const errMsg = errormessage
    function validateName(value: string) {
      if (!value || value === 'Benutzername') {
        return 'Bitte geben Sie einen Nutzername ein.'
      }
      return true
    }
    function validatePassword(value: string) {
      if (!value || value === 'Passwort') {
        return 'Bitte geben Sie ein Passwort ein.'
      }
      return true
    }
    function clearLocalStorage() {
      localStorage.clear()
    }

    return {
      validateName,
      validatePassword,
      login,
      errMsg,
      clearLocalStorage
    }
  }
})
</script>

<style scoped>
.login {
  display: flex;
  flex-direction: row;
}
input[type='text'],
input[type='password'] {
  padding: 2% 12%;
  margin: 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  border: 0.1vw solid #0066ff;
}
.BildundStartseitelogin {
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
    url('../assets/images/banner.jpg');
  background-size: cover;
  display: flex;
  flex-direction: column;
  width: 70vw;
  height: 100vh;
}
.password {
  border-radius: 8px;
  border: none;
  background-color: black;
  width: 18vw;
  height: 4vh;
  text-align: left;
  color: grey;
  font-size: 1vw;
  position: relative;
  margin-left: 20%;
}
.password i {
  position: absolute;
  left: 3%;
  top: 25%;
  color: white;
}
.val {
  color: red;
  font-size: 1vw;
  display: inline-block;
  margin-top: 5%;
}
.user {
  border-radius: 8px;
  border: none;
  background-color: black;
  width: 18vw;
  height: 4vh;
  text-align: left;
  color: grey;
  font-size: 1vw;
  position: relative;
  margin-left: 20%;
  margin-bottom: 1%;
}
.user i {
  position: absolute;
  left: 3%;
  top: 25%;
  color: white;
}
.btn {
  border-radius: 8px;
  background: #0066ff;
  border: none;
  color: white;
  width: 18vw;
  height: 4vh;
  font-size: 1.2vw;
  position: relative;
  margin-left: 0%;
  padding: 0.7% 0 5.5% 0;
  margin-top: 5%;
}
.logBtn i {
  position: absolute;
  left: 3%;
  top: 20%;
  color: white;
}
.btnstartseite {
  margin-top: 8%;
  color: white;
  height: 6vh;
  width: 18vw;
  padding: 0.7% 0 1% 2%;
  position: relative;
  font-size: 1.3vw;
  background-color: black;
  border-radius: 8px;
  border: none;
}
.btnstartseite i {
  font-size: 2vw;
  position: absoulte;
  padding-top: 2%;
}
.logSts i {
  position: absolute;
  left: 6%;
  top: 3%;
  color: white;
}
h2 {
  font-size: 1.8vw;
  margin-top: 5%;
  margin-bottom: 10%;
  color: white;
}
h1 {
  font-size: 4.3vw;
  color: white;
  padding-top: 10%;
}
h4 {
  color: white;
  font-size: 0.9vw;
  margin-top: 0;
}
#logo {
  margin-top: 10%;
  margin-bottom: 0%;
  margin-left: 27%;
  width: 15vw;
  height: 25vh;
}
.loginth {
  background-color: rgb(99, 99, 99);
  display: flex;
  flex-direction: column;
  width: 30vw;
  height: 100vh;
}
.acc {
  margin-top: 5%;
}
/* iPhone 6 Plus portrait */
@media (max-width: 666px) {
  h1 {
    font-size: 8vw;
    color: white;
    padding-top: 60%;
  }

  #logo {
    margin-top: 140%;
    margin-bottom: 0%;
    margin-left: 27%;
    width: 17vw;
    height: 17vh;
  }
  h2 {
    font-size: 4vw;
    margin-top: 5%;
    margin-bottom: 10%;
    color: white;
  }

  .btn {
    border-radius: 8px;
    background: #0066ff;
    border: none;
    color: white;
    width: 18vw;
    height: 4vh;
    font-size: 2.5vw;
    position: relative;
    margin-left: 0%;
    padding: 0.7% 0 5.5% 0;
    margin-top: 5%;
  }

  h4 {
    color: white;
    font-size: 3vw;
  }

  .btnstartseite {
    margin-top: 18%;
    color: white;
    height: 16vh;
    width: 32vw;
    padding: 0.9% 0 1% 1%;
    position: relative;
    font-size: 4.5vw;
    background-color: black;
    border-radius: 10px;
    border: none;
  }
}
</style>
