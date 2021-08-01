<template>
  <div id="register">
    <div class="rightImage" />
    <div class="register">
      <h1>Registrierung</h1>
      <Form @submit="onSubmit">
        <div>
          <ErrorMsg name="gender" class="val" />
        </div>
        <Field
          v-model="gender"
          name="gender"
          as="select"
          class="gender"
          placeholder="Geschlecht"
          :rules="validateField"
        >
          <option>Frau</option>
          <option>Herr</option>
        </Field>
        <div class="form-group">
          <div>
            <div>
              <ErrorMsg name="username" class="val" />
              <a class="val" v-if="errMsg === 'Username gibt es bereits!'"
                >{{ errMsg }}
              </a>
            </div>
            <Field
              v-model="username"
              name="username"
              as="input"
              class="field-50"
              type="text"
              placeholder="Username"
              :rules="validateUser"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <div>
            <div>
              <ErrorMsg name="surname" class="val" />
            </div>
            <Field
              v-model="surname"
              name="surname"
              as="input"
              class="field-50"
              type="text"
              placeholder="Nachname"
              :rules="validateField"
            >
            </Field>
          </div>
          <div>
            <div>
              <ErrorMsg name="name" class="val" />
            </div>
            <Field
              v-model="name"
              name="name"
              as="input"
              class="field-50"
              type="text"
              placeholder="Vorname"
              :rules="validateField"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <div>
            <div>
              <ErrorMsg name="street" class="val" />
            </div>
            <Field
              v-model="street"
              name="street"
              as="input"
              class="field-80"
              type="text"
              placeholder="Straße"
              :rules="validateField"
            >
            </Field>
          </div>
          <div>
            <div>
              <ErrorMsg name="addressnr" class="val" />
            </div>
            <Field
              v-model="addressnr"
              name="addressnr"
              as="input"
              class="field-20"
              type="text"
              placeholder="Nummer"
              :rules="validateNumber"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <div>
            <div>
              <ErrorMsg name="city" class="val" />
            </div>
            <Field
              v-model="city"
              name="city"
              as="input"
              class="field-80"
              type="input"
              placeholder="Stadt"
              :rules="validateField"
            >
            </Field>
          </div>
          <div>
            <div>
              <ErrorMsg name="zipcode" class="val" />
            </div>
            <Field
              v-model="zipcode"
              name="zipcode"
              as="input"
              class="field-20"
              type="input"
              placeholder="PLZ"
              :rules="validateNumber"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <div>
            <div>
              <ErrorMsg name="email" class="val" />
            </div>
            <Field
              v-model="email"
              name="email"
              as="input"
              class="field-100"
              type="text"
              placeholder="E-Mail-Adresse"
              :rules="validateEmail"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <div>
            <div>
              <ErrorMsg name="password" class="val" />
            </div>
            <Field
              v-model="password"
              name="password"
              as="input"
              class="field-50"
              type="password"
              placeholder="Passwort"
              :rules="validatePassword"
            >
            </Field>
          </div>
          <div>
            <div>
              <ErrorMsg name="passwordWdh" class="val" />
            </div>
            <Field
              v-model="passwordWdh"
              name="passwordWdh"
              as="input"
              class="field-50"
              type="password"
              placeholder="Passwort wiederholen"
              :rules="validateEqual"
            >
            </Field>
          </div>
        </div>
        <div class="regBtn">
          <button type="submit" class="btn">
            <i class="fa fa-sign-in-alt" />Jetzt Registrieren
          </button>
          <div class="acc">
            <h4>
              Bereits Nutzer?
              <router-link to="/login">Jetzt anmelden</router-link>
            </h4>
          </div>
        </div>
      </Form>
    </div>
  </div>
</template>
<script lang="ts">
import { useUser } from '../service/UserStore'
import { Form, Field, ErrorMessage as ErrorMsg } from 'vee-validate'
import { ref } from 'vue'

export default {
  components: {
    Form,
    Field,
    ErrorMsg
  },
  setup() {
    const { register, errormessage } = useUser()

    const errMsg = errormessage
    const gender = ref('')
    const username = ref('')
    const surname = ref('')
    const name = ref('')
    const street = ref('')
    const addressnr = ref()
    const city = ref('')
    const zipcode = ref()
    const email = ref('')
    const password = ref('')
    const passwordWdh = ref('')

    function onSubmit() {
      register(
        gender.value,
        username.value,
        surname.value,
        name.value,
        street.value,
        addressnr.value,
        city.value,
        zipcode.value,
        email.value,
        password.value
      )
    }

    function validateUser(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      return true
    }

    const emailRegEx = new RegExp(
      /^(\w-*)+(.(\w-*)+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]+)(.[A-Za-z]{2,})$/
    )

    function validateEmail(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      if (!emailRegEx.test(value)) {
        return 'Keine gültige E-Mail-Adresse!'
      }
      return true
    }

    const passwordRegEx = new RegExp(/^(?=.*[A-Z])(?=.*[\W|_])[\w\W]{6,}$/)

    function validatePassword(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      if (!passwordRegEx.test(value)) {
        return 'Mind. ein Großbuchstabe, ein Sonderzeichen und insg. 6 Zeichen!'
      }
      return true
    }
    function validateEqual(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      if (password.value !== passwordWdh.value) {
        return 'Passwörter stimmen nicht überein!'
      }

      return true
    }
    function validateField(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      return true
    }
    function validateNumber(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      if (isNaN(Number(value))) {
        return 'Keine gültige Nummer!'
      }
      return true
    }

    return {
      register,
      errMsg,
      onSubmit,
      validateEmail,
      validatePassword,
      validateField,
      validateNumber,
      validateEqual,
      validateUser,
      password,
      passwordWdh,
      username,
      name,
      surname,
      email,
      street,
      addressnr,
      gender,
      city,
      zipcode
    }
  }
}
</script>

<style scoped>
form {
  text-align: left;
  margin: auto;
}
#register {
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
}
h1 {
  font-size: 3vw;
  text-align: left;
  color: white;
  margin-top: 4%;
  margin-left: 10%;
  margin-bottom: 0%;
}
h4 {
  color: white;
  font-size: 0.9vw;
  margin-top: 0;
}
.val {
  color: red;
  font-size: 0.8rem;
  text-align: left;
}
.register {
  background-color: rgb(99, 99, 99);
  display: flex;
  flex-direction: column;
  width: 80vw;
  height: 100vh;
}
.rightImage {
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
    url('../assets/images/banner.jpg');
  background-position-x: 50%;
  background-size: cover;
  display: flex;
  width: 20vw;
  height: 100vh;
}
.field-50 {
  margin-right: 2vw;
  border-radius: 8px;
  background-color: black;
  width: 30vw;
  height: 5vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 2%;
  border: 0.1vw solid #0066ff;
}
.field-20 {
  border-radius: 8px;
  background-color: black;
  width: 10vw;
  height: 5vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 5%;
  border: 0.1vw solid #0066ff;
}
.field-80 {
  margin-right: 2vw;
  border-radius: 8px;
  background-color: black;
  width: 50vw;
  height: 5vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1.2%;
  border: 0.1vw solid #0066ff;
}
.field-100 {
  border-radius: 8px;
  background-color: black;
  width: 62vw;
  height: 5vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.form-group {
  margin-bottom: 1.5%;
  display: flex;
}
.btn {
  border-radius: 8px;
  background: #0066ff;
  border: none;
  color: white;
  width: 19vw;
  height: 5vh;
  font-size: 1.6vw;
  position: relative;
  padding: 0.3% 0 2% 0;
  margin-top: 3%;
}
.regBtn i {
  position: absolute;
  left: 5%;
  top: 18%;
  color: white;
}
.gender {
  display: block;
  font-size: 1.1vw;
  width: 10%;
  height: 5vh;
  color: white;
  max-width: 10%;
  box-sizing: border-box;
  margin-bottom: 1%;
  border: 1px solid #aaa;
  border-radius: 8px;
  background-color: black;
  padding: 0.2% 0 0.2% 0.5%;
  border: 0.1vw solid #0066ff;
}
.acc {
  margin-top: 2%;
}
/* iPhone 6 Plus portrait */
@media (max-width: 666px) {
  h1 {
    font-size: 5vw;
  }
  h4 {
    font-size: 2vw;
  }
  .btn {
    border-radius: 8px;
    background: #0066ff;
    border: none;
    color: white;
    width: 30vw;
    height: 10vh;
    font-size: 2.5vw;
    position: relative;
    padding: 0.3% 0 2% 0;
    margin-top: 3%;
  }
  .regBtn i {
    position: absolute;
    left: 5%;
    top: 18%;
    color: white;
  }
  .field-50 {
    margin-right: 2vw;
    border-radius: 8px;
    background-color: black;
    width: 30vw;
    height: 5vh;
    text-align: left;
    font-size: 2.2vw;
    color: white;
    padding: 0 0 0.2% 2%;
    border: 0.1vw solid #0066ff;
  }
  .field-20 {
    border-radius: 8px;
    background-color: black;
    width: 10vw;
    height: 5vh;
    text-align: left;
    font-size: 2.2vw;
    color: white;
    padding: 0 0 0.2% 5%;
    border: 0.1vw solid #0066ff;
  }
  .field-80 {
    margin-right: 2vw;
    border-radius: 8px;
    background-color: black;
    width: 50vw;
    height: 5vh;
    text-align: left;
    font-size: 2.2vw;
    color: white;
    padding: 0 0 0.2% 1.2%;
    border: 0.1vw solid #0066ff;
  }
  .field-100 {
    border-radius: 8px;
    background-color: black;
    width: 62vw;
    height: 5vh;
    text-align: left;
    font-size: 2.2vw;
    color: white;
    padding: 0 0 0.2% 1%;
    border: 0.1vw solid #0066ff;
  }
  .val {
    color: red;
    font-size: 0.5rem;
    text-align: left;
  }
}
</style>
