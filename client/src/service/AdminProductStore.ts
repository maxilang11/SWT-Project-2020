import { computed, reactive } from 'vue'

import { Product } from '@/service/Product'

const state = reactive({
  errormessage: ''
})

// Zum Bearbeiten eines Produktes
async function edit(
  id: number,
  name: string,
  amount: number,
  city: string,
  country: string,
  price: number,
  category: string
) {
  const editProductData: Product = {
    id,
    name,
    amount,
    city,
    country,
    price,
    category
  }
  try {
    const response = await fetch('/api/productEdit', {
      method: 'POST',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(editProductData)
    })
    const jsondata = await response.text()
    if (!response.ok) {
      return Promise.reject(jsondata || response.status)
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// Zum Löschen eines Produktes
async function deleteProduct(product: Product) {
  try {
    const response = await fetch('/api/delProduct', {
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

export function useAdminProduct() {
  return {
    errormessage: computed(() => state.errormessage),
    edit,
    deleteProduct
  }
}
