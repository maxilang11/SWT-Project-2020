<template>
  <div id="edit">
    <div class="editUser">
      <h1>Mein Profil bearbeiten</h1>
      <Form
        @submit="
          editU(
            user.gender,
            user.username,
            user.surname,
            user.name,
            user.street,
            user.addressnr,
            user.city,
            user.zipcode,
            user.email
          )
        "
      >
        <div class="choose">
          <ErrorMessage name="gender" class="val" />
          <div>
            <Field
              v-model="user.gender"
              name="gender"
              as="select"
              class="gender"
              type="text"
              placeholder="Gender"
              :rules="validateField"
            >
              <option>Frau</option>
              <option>Herr</option>
            </Field>
          </div>
        </div>
        <div class="form-group">
          <ErrorMessage name="name" class="val" />
          <ErrorMessage name="surname" class="valr" />
          <div>
            <Field
              v-model="user.name"
              name="name"
              as="input"
              class="name"
              type="text"
              placeholder="Vorname"
              :rules="validateField"
            >
            </Field>
            <Field
              v-model="user.surname"
              name="surname"
              as="input"
              class="surname"
              type="text"
              placeholder="Nachname"
              :rules="validateField"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <ErrorMessage name="street" class="val" />
          <ErrorMessage name="addressnr" class="valr" />
          <div>
            <Field
              v-model="user.street"
              name="street"
              as="input"
              class="street"
              type="text"
              placeholder="Straße"
              :rules="validateField"
            >
            </Field>
            <Field
              v-model="user.addressnr"
              name="addressnr"
              as="input"
              class="addressnr"
              type="text"
              placeholder="Hausnr."
              :rules="validateNumber"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <ErrorMessage name="city" class="val" />
          <ErrorMessage name="zipcode" class="valr" />
          <div>
            <Field
              v-model="user.city"
              name="city"
              as="input"
              class="city"
              type="text"
              placeholder="Stadt"
              :rules="validateField"
            >
            </Field>
            <Field
              v-model="user.zipcode"
              name="zipcode"
              as="input"
              class="zipcode"
              type="text"
              placeholder="PLZ"
              :rules="validateNumber"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <ErrorMessage name="email" class="val" />
          <div>
            <Field
              v-model="user.email"
              name="email"
              as="input"
              class="email"
              type="text"
              placeholder="E-Mail"
              :rules="validateEmail"
            >
            </Field>
          </div>
        </div>
        <div class="editBtn">
          <button class="btn" type="submit">Speichern</button>
        </div>
      </Form>
    </div>
  </div>
</template>
<script lang="ts">
import { Form, Field, ErrorMessage, useForm } from 'vee-validate'
import { useToken, useUser } from '../service/UserStore'
import { onMounted, defineComponent } from 'vue'
import router from '../router/index'

export default defineComponent({
  components: {
    Form,
    Field,
    ErrorMessage
  },
  setup() {
    const { handleSubmit } = useForm()
    const onSubmit = handleSubmit((values) => {
      alert(JSON.stringify(values, null, 2))
    })

    function validateEmail(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(value)) {
        return 'Keine gültige E-Mail-Adresse!'
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
    const { jwtDecrypt } = useToken()
    const { profileUser, actUserProfile, edit } = useUser()
    const user = actUserProfile
    const name = jwtDecrypt().sub

    onMounted(async () => {
      await profileUser(name)
    })

    // Profil des Nutzers wird bearbeitet
    function editU(
      gender: string,
      username: string,
      surname: string,
      name: string,
      street: string,
      adressnr: number,
      city: string,
      zipcode: number,
      email: string
    ) {
      edit(
        gender,
        username,
        surname,
        name,
        street,
        adressnr,
        city,
        zipcode,
        email
      )
      router.go(0)
    }
    return {
      onSubmit,
      validateEmail,
      validateField,
      validateNumber,
      user,
      editU
    }
  }
})
</script>

<style scoped>
#edit {
  display: flex;
  flex-direction: row-reverse;
}
h1 {
  font-size: 2.7vw;
  text-align: left;
  color: black;
  margin-top: 4%;
  margin-left: 3%;
  margin-bottom: 7%;
}
.val {
  color: red;
  font-size: 1vw;
  margin-top: 0%;
  display: flex;
  justify-content: space-between;
  margin-left: 7%;
}
.valr {
  color: red;
  font-size: 1vw;
  margin-left: 53%;
  display: flex;
  justify-content: space-between;
}
.editUser {
  background-color: white;
  display: flex;
  flex-direction: column;
  flex-direction: column;
  width: 100vw;
  height: 90vh;
}
.surname {
  margin-left: 3%;
  border-radius: 8px;
  background-color: black;
  width: 20vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0% 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.name {
  margin-left: 3%;
  border-radius: 8px;
  background-color: black;
  width: 20vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.street {
  margin-left: 3%;
  border-radius: 8px;
  background-color: black;
  width: 20vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.addressnr {
  margin-left: 3%;
  border-radius: 8px;
  background-color: black;
  width: 5vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.city {
  margin-left: 3%;
  border-radius: 8px;
  background-color: black;
  width: 20vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.zipcode {
  margin-left: 3%;
  border-radius: 8px;
  background-color: black;
  width: 10vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.email {
  margin-left: 3%;
  border-radius: 8px;
  background-color: black;
  width: 20vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: white;
  padding: 0 0 0.2% 1%;
  border: 0.1vw solid #0066ff;
}
.form-group {
  margin-bottom: 1.5%;
}
.btn {
  border-radius: 8px;
  background: #0066ff;
  border: none;
  color: white;
  width: 15vw;
  height: 7vh;
  font-size: 1.4vw;
  position: relative;
  margin-left: 3%;
  padding: 0.3% 0 2% 0;
  margin-top: 5%;
}

.gender {
  display: block;
  font-size: 1.1vw;
  width: 15%;
  height: 7vh;
  color: white;
  max-width: 10%;
  box-sizing: border-box;
  margin-left: 3%;
  margin-bottom: 1.5%;
  border: 1px solid #aaa;
  border-radius: 8px;
  background-color: black;
  padding: 0.2% 0 0.2% 0.5%;
  border: 0.1vw solid #0066ff;
}
</style>
