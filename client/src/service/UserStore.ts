import { computed, reactive } from 'vue'

import { User } from '@/service/User'
import router from '@/router'

const state = reactive({
  liste: Array<User>(),
  errormessage: '',
  actUserProfile: {
    gender: String,
    username: String,
    surname: String,
    name: String,
    strees: String,
    addresnr: String,
    city: String,
    zipcode: String,
    email: String
  }
})

const jwt = reactive({
  token: ''
})

/*
schickt username und password per POST an den Server
und erwartet einen JWT bei erfolgreichem Login. Dieser
wird dann im LocalStorage unter token gespeichert.
*/
async function login(username: string, password: string) {
  localStorage.clear()
  const actUser: User = { username, password }
  actUser.username = username
  actUser.password = password

  try {
    const response = await fetch('/api/userlogin', {
      method: 'POST',
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify(actUser)
    })
    const jsondata = await response.text()
    if (!response.ok) {
      state.errormessage = jsondata
      return Promise.reject(jsondata || response.status)
    }
    jwt.token = jsondata
    localStorage.setItem('token', jsondata)
    if (jsondata.length > 0) {
      if (username === 'LAGERLAGER') {
        router.push('/lagerist/bestellungen')
      } else if (username === 'ADMINADMIN') {
        router.push('/admin/startseite')
      } else {
        router.push('/startseite')
      }
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// leert den LocalStorage, damit der JWT gel
function logout() {
  localStorage.clear()
  router.push('/login')
}

// entschlüsselt den JWT, um den Usernamen zu erhalten
function jwtDecrypt() {
  jwt.token = localStorage.getItem('token') || '{}'
  var base64Url = jwt.token.split('.')[1]
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
  var jsonPayload = decodeURIComponent(
    atob(base64)
      .split('')
      .map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
      })
      .join('')
  )
  return JSON.parse(jsonPayload)
}

// UserID wird mit Hilfe des username im LocalStorage gespeichert
async function getUserID(username: string) {
  try {
    const response = await fetch('/api/profileUser/' + username, {
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
    localStorage.setItem('userid', jsondata.id)
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// hiermit kann ein User seine Daten anpassen
async function edit(
  gender: string,
  username: string,
  surname: string,
  name: string,
  street: string,
  addressnr: number,
  city: string,
  zipcode: number,
  email: string
) {
  const editUserData: User = {
    gender,
    username,
    surname,
    name,
    street,
    addressnr,
    city,
    zipcode,
    email
  }
  try {
    const response = await fetch('/api/userEdit', {
      method: 'POST',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(editUserData)
    })
    const jsondata = await response.text()
    if (!response.ok) {
      state.errormessage = jsondata
      return Promise.reject(jsondata || response.status)
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

// anhand des username, werden alle Daten des Users im State gespeichert.
async function profileUser(username: string) {
  try {
    const response = await fetch('/api/profileUser/' + username, {
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
    const user = jsondata
    state.actUserProfile = user
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

/*
User wird registriert, solange es den username noch nicht gibt.
Bei erfolgreicher Registrierung wird man zum Login weitergeleitet und
kann sich nun mit seinen eben erstellten Userdaten anmelden.
*/
async function register(
  gender: string,
  username: string,
  surname: string,
  name: string,
  street: string,
  addressnr: number,
  city: string,
  zipcode: number,
  email: string,
  password: string
) {
  const newUser: User = {
    gender,
    username,
    surname,
    name,
    street,
    addressnr,
    city,
    zipcode,
    email,
    password
  }
  try {
    const response = await fetch('/api/userregister', {
      method: 'POST',
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify(newUser)
    })
    const jsondata = await response.text()
    if (!response.ok) {
      state.errormessage = jsondata
      return Promise.reject(jsondata || response.status)
    }

    if (!(jsondata === 'Username gibt es bereits!')) {
      state.errormessage = ''
      router.push('/login')
    }
  } catch (e) {
    state.errormessage = 'Fehler bei der Serverkommunikation'
  }
}

export function useToken() {
  return {
    token: computed(() => jwt.token),
    login,
    jwtDecrypt
  }
}

export function useUser() {
  return {
    liste: computed(() => state.liste),
    errormessage: computed(() => state.errormessage),
    actUserProfile: computed(() => state.actUserProfile),
    login,
    getUserID,
    logout,
    profileUser,
    edit,
    register
  }
}
