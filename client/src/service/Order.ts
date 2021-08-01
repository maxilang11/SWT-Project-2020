import { User } from '@/service/User'
import { Product } from '@/service/Product'
export interface Order {
  totalPrice: number
  productList: Array<Product>
  amount: any
  deliveryOption: String
  payment: String
  orderstatus?: String
  date?: Date
  user?: User
  totalAmount?: number
}
