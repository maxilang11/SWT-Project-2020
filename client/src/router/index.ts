import { createRouter, createWebHistory } from 'vue-router'
import UserProfile from '../views/UserProfile.vue'
import Categories from '../views/Categories.vue'
import Products from '../views/Products.vue'
import Homepage from '../views/HomePage.vue'
import ShoppingCart from '../views/ShoppingCart.vue'
import Bookmarks from '../views/Bookmarks.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import OrderConfirmation from '../views/OrderConfirmation.vue'
import OrderOverviewStoreman from '../views/OrderOverviewStoreman.vue'
import OrderDetailStoreman from '../views/OrderDetailStoreman.vue'
import PageNotFound from '../views/PageNotFound.vue'
import Bar from '../views/Bar.vue'
import AdminChat from '../views/AdminChat.vue'
import OrderList from '../views/OrderList.vue'
import AdminOverview from '../views/AdminOverview.vue'
import ProductOverview from '../views/ProductOverview.vue'
import ProductsOverviewStoreman from '../views/ProductsOverviewStoreman.vue'

const history = createWebHistory()
const router = createRouter({
  history,
  routes: [
    {
      path: '/kategorien/',
      name: 'kategorie',
      component: Categories,
      meta: { showNavBar: true }
    },
    {
      path: '/kategorien/:kategoriename',
      name: 'produkt',
      component: Products,
      meta: { showNavBar: true }
    },
    {
      path: '/startseite',
      name: 'startseite',
      component: Homepage,
      meta: { showNavBar: true }
    },
    {
      path: '/warenkorb',
      name: 'warenkorb',
      component: ShoppingCart,
      meta: { showNavBar: true }
    },
    {
      path: '/bookmarks',
      name: 'bookmarks',
      component: Bookmarks,
      meta: { showNavBar: true }
    },
    {
      path: '/',
      redirect: 'login',
      component: Login,
      meta: { showNavBar: false }
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: { showNavBar: false }
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
      meta: { showNavBar: false }
    },
    {
      path: '/bestellbestaetigung',
      name: 'bestellbestaetigung',
      component: OrderConfirmation,
      meta: { showNavBar: true }
    },
    {
      path: '/profil',
      name: 'profil',
      component: UserProfile,
      meta: { showNavBar: true }
    },
    {
      path: '/lagerist/bestellungen',
      name: 'lageristBestelluebersicht',
      component: OrderOverviewStoreman,
      meta: { showNavBarStoreman: true }
    },
    {
      path: '/lagerist/bestelldetails/:idOrder',
      name: 'lageristBestelldetails',
      component: OrderDetailStoreman,
      meta: { showNavBarStoreman: true }
    },
    {
      path: '/admin/bar',
      name: 'adminbar',
      component: Bar
    },
    {
      path: '/admin/startseite',
      name: 'adminchat',
      component: AdminChat,
      meta: { showNavBarAdmin: true }
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'PageNotFound',
      component: PageNotFound,
      meta: { showNavBar: false }
    },
    {
      path: '/bestelluebersicht',
      name: 'bestelluebersicht',
      component: OrderList,
      meta: { showNavBar: true }
    },
    {
      path: '/admin/produkte',
      name: 'adminProdukte',
      component: AdminOverview,
      meta: { showNavBarAdmin: true }
    },
    {
      path: '/lagerist/produktuebersicht',
      name: 'produktuebersicht',
      component: ProductOverview,
      meta: { showNavBarStoreman: true }
    },
    {
      path: '/lagerist/produkte',
      name: 'lageristProdukte',
      component: ProductsOverviewStoreman,
      meta: { showNavBarStoreman: true }
    }
  ]
})

export default router
