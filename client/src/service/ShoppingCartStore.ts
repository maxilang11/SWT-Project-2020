import { computed, reactive } from 'vue'
import { Product } from '@/service/Product'

const state = reactive({
  liste: Array<Product>(),
  errormessage: '',
  quantity: 0,
  amount: new Map<number, number>(),
  totalPrice: 0
})

// speichert die komplette Produktliste in einem State
async function update(id: number) {
  try {
    const response = await fetch('/api/cart/' + id, {
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

// speichert Menge der Produkte die im Warenkorb liegen in State
async function getQuantityCart(id: number) {
  try {
    const response = await fetch('/api/shoppingCartQuantity/' + id, {
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
    state.quantity = productliste
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// speichert Gesamtpreis in einem State
async function getTotalPrice(id: number) {
  try {
    const response = await fetch('/api/totalPrice/' + id, {
      method: 'GET',
      headers: {
        'content-type': 'application/json'
      }
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
    const price = jsondata
    state.totalPrice = Math.abs(price)
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// aktualisiert Gesamtpreis im Frontend in einem State
function totalPriceDel(price: number): void {
  state.totalPrice -= price
}

// erhält die einzelnen Mengen die im Warenkorb liegen
async function getAmount(id: number) {
  try {
    const response = await fetch('/api/amount/' + id, {
      method: 'GET',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    })
    const jsondata = await response.json()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
    const productliste = jsondata
    state.amount = productliste
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// aktualisiert Menge die im Warenkorb liegt im Frontend (state)
function quantityCart(ele: Product): void {
  state.quantity += ele.amount
}

// aktualisiert Menge die im Warenkorb liegt im Frontend (state)
function quantityCartDel(ele: Product, amountNumber: number): void {
  state.quantity -= amountNumber
}

// fügt im Frontend etwas zur Warenkorbliste hinzu (state)
function pushToCart(ele: Product): void {
  state.liste.push(ele)
}

// fügt product zum passenden Warenkorb hinzu
async function addToShoppingCart(id: number, product: Product) {
  try {
    const response = await fetch('/api/cart/' + id, {
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

// löscht im Frontend etwas von der Warenkorbliste (state)
function popFromCart(ele: Product): void {
  state.liste = state.liste.filter(product => product.id !== ele.id)
}

// löscht product vom passenden Warenkorb
async function deleteFromShoppingCart(id: number, product: Product) {
  try {
    const response = await fetch('/api/cartdel/' + id, {
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

// leert Warenkorbliste (state)
function delCartList(): void {
  state.quantity = 0
  state.liste = []
}

export function useCart() {
  return {
    liste: computed(() => state.liste),
    errormessage: computed(() => state.errormessage),
    quantity: computed(() => state.quantity),
    amount: computed(() => state.amount),
    totalPrice: computed(() => state.totalPrice),
    update,
    pushToCart,
    addToShoppingCart,
    popFromCart,
    deleteFromShoppingCart,
    getQuantityCart,
    quantityCart,
    quantityCartDel,
    getAmount,
    getTotalPrice,
    totalPriceDel,
    delCartList
  }
}
