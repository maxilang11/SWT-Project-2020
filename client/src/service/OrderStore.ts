import { Order } from '../service/Order'
import { computed, reactive } from 'vue'
import { Product } from '../service/Product'

const state = reactive({
  userOrders: new Array<Order>(),
  errormessage: '',
  orders: new Array<Order>(),
  packed: new Array<Product>()
})

const o = reactive({
  order: {
    id: 0,
    productList: [],
    orderstatus: '',
    totalPrice: 0,
    amount: {},
    date: Date.prototype,
    user: {
      gender: '',
      username: '',
      surname: '',
      name: '',
      street: '',
      addressnr: 0,
      city: '',
      zipcode: 0,
      email: ''
    },
    totalAmount: 0
  }
})

// Man bekommt alle Bestellungen aus der Datenbank
async function getOrders() {
  try {
    const response = await fetch('/api/orders', {
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
    const orderliste = jsondata
    state.orders = orderliste
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}
// Der Bestellstatus wird verändert
async function getStatusById(orderId: number) {
  try {
    const response = await fetch('/api/orderstatus/' + orderId, {
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
    const statusById = jsondata
    o.order = statusById
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// Man erhält eine Bestellung anhand ihrer ID
async function getOrderById(orderId: number) {
  try {
    const response = await fetch('/api/orders/' + orderId, {
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
    const orderById = jsondata
    o.order = orderById
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}
// Fügt ein Produkt zu packed (den eingepackten Podukten) hinzu
function pushToPacked(ele: Product): void {
  state.packed.push(ele)
}
// Leert packed
function delPacked(): void {
  state.packed = []
}

// Es wird eine Bestellung erstellt
async function checkout(
  totalPrice: number,
  username: string,
  productList: Array<Product>,
  amount: any,
  deliveryOption: String,
  payment: String
) {
  const newOrder: Order = {
    totalPrice,
    productList,
    amount,
    deliveryOption,
    payment
  }
  try {
    const response = await fetch('/api/checkout/' + username, {
      method: 'POST',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(newOrder)
    })
    const jsondata = await response.text()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// Man erhält alle Bestellungen eines Nutzers
async function getUserOrders(username: String) {
  try {
    const response = await fetch('/api/userOrders/' + username, {
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
    const userOrd = jsondata
    state.userOrders = userOrd
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

export function useOrder() {
  return {
    userOrders: computed(() => state.userOrders),
    orders: computed(() => state.orders),
    order: computed(() => o.order),
    packed: computed(() => state.packed),
    checkout,
    getUserOrders,
    getOrders,
    getOrderById,
    getStatusById,
    pushToPacked,
    delPacked
  }
}
