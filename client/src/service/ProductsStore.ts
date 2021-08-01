import { computed, reactive } from 'vue'
import { Product } from '@/service/Product'

const state = reactive({
  liste: Array<Product>(),
  errormessage: ''
})

// Man erhält alle Produkte aus der Datenbank
async function update() {
  try {
    const response = await fetch('/api/products', {
      method: 'GET',
      headers: {
        'content-type': 'application/json'
      }
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
    const productliste = jsondata
    state.liste = productliste
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// Man erhält alle Produkte einer Kategorie
async function categories(category: string) {
  try {
    const response = await fetch('/api/category/' + category, {
      method: 'GET',
      headers: {
        'content-type': 'application/json'
      }
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
    const productliste = jsondata
    state.liste = productliste
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// Suche nach einem Produkt
async function search(search: String) {
  try {
    const response = await fetch('/api/search/' + search, {
      method: 'GET',
      headers: {
        'content-type': 'application/json'
      }
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
    const productliste = jsondata
    state.liste = productliste
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

async function changeAmount(id: number, amount: number) {
  try {
    const response = await fetch('/api/changeAmount/' + id + '/' + amount, {
      method: 'POST',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// Das Produkt wird aus der Liste state gelöscht
function popFromList(ele: Product): void {
  state.liste = state.liste.filter(product => product.id !== ele.id)
}

// Zum Exportieren der Produktliste
async function exportProducts(file: String) {
  try {
    const response = await fetch('/api/admin/products/export/' + file, {
      method: 'POST',
      headers: {
        'content-type': 'application/pdf',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(state.liste)
    })
    const jsondata = await response
    window.open('http://localhost:8080/api/admin/products/export/' + file)
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

export function useProducts() {
  return {
    liste: computed(() => state.liste),
    errormessage: computed(() => state.errormessage),
    update,
    search,
    categories,
    popFromList,
    exportProducts,
    changeAmount
  }
}
