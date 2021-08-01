<template>
  <div id="edit">
    <div class="editProduct">
      <h1>{{ products.name }} bearbeiten</h1>
      <Form
        @submit="
          editP(
            products.id,
            products.name,
            products.amount,
            products.city,
            products.country,
            products.price,
            products.category
          )
        "
      >
        <div class="form-group">
          <ErrorMessage name="city" class="val2" />
          <ErrorMessage name="country" class="valr" />
          <div id="description">
            Produktbeschreibung:
            <Field
              v-model="products.city"
              name="city"
              as="input"
              class="city"
              type="text"
              placeholder="Stadt"
              :rules="validateField"
            >
            </Field>
            <Field
              v-model="products.country"
              name="country"
              as="input"
              class="country"
              type="text"
              placeholder="Land"
              :rules="validateField"
            >
            </Field>
          </div>
        </div>
        <div class="form-group">
          <ErrorMessage name="price" class="val" />
          <div id="description">
            Preis:
            <Field
              v-model="products.price"
              name="price"
              as="input"
              class="price"
              input-type="text"
              placeholder="Preis"
              :rules="validateNumber"
            >
            </Field>
            €
          </div>
          <div class="choose">
            <ErrorMessage name="category" class="val" />
            <div id="description">
              <Field
                v-model="products.category"
                name="category"
                as="select"
                class="category"
                type="text"
                placeholder="Kategorie"
                :rules="validateField"
              >
                <option>Getränke</option>
                <option>Obst</option>
                <option>Gemüse</option>
                <option>Süßigkeiten</option>
                <option>Teigwaren</option>
                <option>Fleisch</option>
                <option>Fisch</option>
                <option>Backwaren</option>
                <option>Tiefkühl</option>
              </Field>
            </div>
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
import { defineComponent, reactive } from 'vue'
import { useAdminProduct } from '../service/AdminProductStore'
import router from '../router/index'

export default defineComponent({
  name: 'editProduct',
  components: {
    Form,
    Field,
    ErrorMessage
  },
  props: ['product'],
  setup(props) {
    const { handleSubmit } = useForm()
    const onSubmit = handleSubmit(values => {
      alert(JSON.stringify(values, null, 2))
    })
    const products = reactive(props.product)

    // Ein Feld darf nicht leer sein
    function validateField(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      return true
    }

    // Der Preis darf nicht leer sein und muss zwei Nachkommastellen haben
    function validateNumber(value: string) {
      if (!value) {
        return 'Pflichtfeld, bitte ausfüllen!'
      }
      if (!/^[0-9]*.[0-9][0-9]$/i.test(value)) {
        return 'Der Preis braucht 2 Nachkommastellen!'
      }
      if (isNaN(Number(value))) {
        return 'Keine gültige Nummer!'
      }
      return true
    }
    const { edit } = useAdminProduct()

    // Zum Bearbeiten des Produktes
    function editP(
      id: number,
      name: string,
      amount: number,
      city: string,
      country: string,
      price: number,
      category: string
    ) {
      edit(id, name, amount, city, country, price, category)
      router.go(0)
    }
    return {
      onSubmit,
      validateField,
      validateNumber,
      editP,
      products
    }
  }
})
</script>

<style scoped>
#edit {
  width: 52vw;
  height: 62vh;
}
h1 {
  font-size: 2.7vw;
  text-align: left;
  color: black;
  margin-top: 2%;
  margin-left: 3%;
  margin-bottom: 5%;
}
#description {
  margin-left: 3%;
  font-size: 1.4vw;
}
.val {
  color: red;
  font-size: 1vw;
  display: flex;
  justify-content: space-between;
  margin-left: 7%;
}
.valr {
  color: red;
  font-size: 1vw;
  margin-left: 67%;
  display: flex;
  justify-content: space-between;
}
.val2 {
  color: red;
  font-size: 1vw;
  margin-left: 35%;
  display: flex;
  justify-content: space-between;
}
.editProduct {
  background-color: white;
  display: flex;
  flex-direction: column;
  flex-direction: column;
}
.city {
  margin-left: 3%;
  border-radius: 8px;
  background-color: white;
  width: 15vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: black;
  border: 0.1vw solid #0066ff;
}
.price {
  margin-left: 3%;
  border-radius: 8px;
  background-color: white;
  width: 5vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: black;
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
  margin-top: 4%;
}

.category {
  display: block;
  font-size: 1.1vw;
  width: 15vw;
  height: 7vh;
  color: black;
  box-sizing: border-box;
  margin-bottom: 1.5%;
  margin-top: 5%;
  border: 1px solid #aaa;
  border-radius: 8px;
  background-color: white;
  border: 0.1vw solid #0066ff;
}
.country {
  margin-left: 3%;
  border-radius: 8px;
  background-color: white;
  width: 15vw;
  height: 7vh;
  text-align: left;
  font-size: 1.2vw;
  color: black;
  border: 0.1vw solid #0066ff;
}
</style>
