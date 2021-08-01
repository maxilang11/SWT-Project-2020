import { computed, reactive } from 'vue'
import { Product } from '@/service/Product'

const state = reactive({
  liste: Array<Product>(),
  errormessage: ''
})

// speichert die komplette Produktliste in einem State
async function update(id: number) {
  try {
    const response = await fetch('/api/bookmark/' + id, {
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

// Funktion im Frontend, die Produkte auf der Merklistseite verfügbar macht
function pushToBookmark(ele: Product): void {
  state.liste.push(ele)
}

// fügt Produkte zur passenden Merkliste hinzu
async function addToBookmark(id: number, product: Product) {
  try {
    const response = await fetch('/api/bookmark/' + id, {
      method: 'POST',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(product)
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// Funktion im Frontend, die Produkte von der Merklistseite entfernt
function popFromBookmark(ele: Product): void {
  state.liste = state.liste.filter(product => product.id !== ele.id)
}

// löscht Produkte von der entsprechenden Merkliste
async function deleteFromBookmark(id: number, product: Product) {
  try {
    const response = await fetch('/api/bookmarkdel/' + id, {
      method: 'POST',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(product)
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

export function useBookmark() {
  return {
    liste: computed(() => state.liste),
    errormessage: computed(() => state.errormessage),
    update,
    pushToBookmark,
    addToBookmark,
    popFromBookmark,
    deleteFromBookmark
  }
}
