import { computed, reactive } from 'vue'
import { Product } from '@/service/Product'

const state = reactive({
  top5: Array<Product>(),
  errormessage: ''
})

async function top() {
  try {
    const response = await fetch('/api/products/top', {
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
    state.top5 = productliste
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}
export function useChart() {
  return {
    top5: computed(() => state.top5),
    errormessage: computed(() => state.errormessage),
    top
  }
}
