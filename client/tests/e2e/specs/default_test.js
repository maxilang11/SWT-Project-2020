// https://docs.cypress.io/api/introduction/api.html

describe('Startseite', () => {
  it('Besuche die Startseite des Super MIMarkt', () => {
    cy.visit('http://localhost:8081/login')
    cy.get('h1').contains('Willkommen zum Super MIMarkt')
  })

  it('Titel', () => {
    cy.title().should('include', 'Vue App')
  })
})

describe('Startseite Buttons /', () => {
  it('Suche den Einloggen-Button', () => {
    cy.get('.btnstartseite').contains('Weiter ohne einloggen')
    cy.get('.btn').contains('Anmelden')
  })
})

describe('Anmeldung Placholder-Texte /', () => {
  it('Überprüfe den Platzhalter', () => {
    cy.get('.user').within(() => {
      cy.get('input:first').should('have.attr', 'placeholder', 'Benutzername')
    })

    cy.get('.password').within(() => {
      cy.get('input:first').should('have.attr', 'placeholder', 'Passwort')
    })
  })
})

describe('Anmeldung - Login', () => {
  beforeEach(function () {
    cy.fixture('loginAdmin').as('data')
  })

  it('Anmeldedaten eingeben', () => {
    cy.log('Eingabe Username')
    cy.get('input[name="username"]')
      .type('fakename', { delay: 100 })
      .should('have.value', 'fakename')
    cy.log('Eingabe Password')
    cy.get('input[name="password"]')
      .type('fakepassword', { delay: 100 })
      .should('have.value', 'fakepassword')
    // cy.get('div#app div.logBtn > button').click() //
  })
})

describe('Weiter ohne einloggen', () => {
  it('weiter ohne einloggen', () => {
    cy.get('.btnstartseite')
      .contains('ohne einloggen')
      .click()
  })
})
describe('Angebote Grid Widget', () => {
  it('Teste Angebote Widget Name', () => {
    cy.get('h1').contains('Angebote')
  })
})

describe('Suchleiste', () => {
  it('Suche nach einem Produkt', () => {
    cy.get('input')
      .should('have.attr', 'placeholder', 'Suche')
      .type('Apfel')
      .should('have.value', 'Apfel')
  })
})

describe('Seitenwechsel', () => {
  it('Wechselt auf die Kategorien', () => {
    cy.get('nav#menu li:nth-child(2) > a').click()
    cy.get(':nth-child(1) > .detail > h3').contains('Getränke')
    cy.get(':nth-child(2) > .detail > h3').contains('Obst')
    cy.get(':nth-child(3) > .detail > h3').contains('Gemüse')
    cy.get(':nth-child(4) > .detail > h3').contains('Süßigkeiten')
    cy.get(':nth-child(5) > .detail > h3').contains('Teigwaren')
    cy.get(':nth-child(6) > .detail > h3').contains('Fleisch')
    cy.get(':nth-child(7) > .detail > h3').contains('Fisch')
    cy.get('div#app div:nth-child(1) > a > img').click()
  })
})

describe('+ Button', () => {
  it('Druecke den + - Button 10 mal', () => {
    for (let index = 0; index < 10; index++) {
      cy.get(':nth-child(1) > .detail > .number > .plus').click()
    }
  })
})

describe('Warenkorb', () => {
  it('Teste den Warenkorb', () => {
    cy.get(
      'div#app div:nth-child(1) > div.detail > div.number > button.buttonCart > i'
    ).click()
    cy.get('nav#button li:nth-child(2) > a')
      .contains('Warenkorb')
      .click()
    cy.wait(2000)
    cy.get('p#error').contains(
      'Ihr Warenkorb ist zurzeit leer, fügen Sie bitte Produkte dem Warenkorb hinzu, um eine Bestellung abzuschließen!'
    )
  })
})

describe('Merkliste Button', () => {
  it('Wechselt zur Merkliste', () => {
    cy.get('nav#button li:nth-child(1) > a').click()
  })

  it('Merkliste Ueberschrift', () => {
    cy.get('div#app h1').contains('Merkliste')
  })
})

// describe('Browserhistorie', () => {
//   it('Browser VOR und BACK-Button', () => {
//     cy.get('div#app h1').contains('Warenkorb')
//     cy.go(-1) // back
//     cy.get('div#app h1').contains('Merkliste')
//     cy.go(-1) // back
//     cy.get('div#app h1').contains('Willkommen')
//     cy.go(1)
//     cy.go(1)
//     cy.get('div#app h1').contains('Warenkorb')
//   })
// })

describe('404-Seite', () => {
  it('Besuche 404-Seite des Super MIMarkt', () => {
    cy.visit('http://localhost:8081/abc')
    cy.get('div#app h1').contains('(404) Error-Page: Seite nicht gefunden')
    cy.get('p#text').contains('Deine eingegebene URL existiert leider nicht.')
  })

  it('Teste Pinguin', () => {
    cy.get('div#app span').contains('Drücke den Button oder gib mir Fisch!')
    cy.get('div#app div > img#logo').should(
      'have.attr',
      'src',
      '/img/errorImage.03e479da.png'
    )
  })

  it('Button Farbe', () => {
    cy.get('button#button').contains('Startseite')
    cy.get('button#button')
      .should('have.css', 'background-color')
      .and('eq', 'rgb(0, 102, 255)')
  })
  it('Teste font-family', () => {
    cy.get('button#button')
      .should('be.visible')
      .should('have.css', 'font-family')
      .and('match', /Open Sans/)
  })

  it('Gehe zur Startseite', () => {
    cy.visit('http://localhost:8081/login')
  })
})

describe('Account erstellen', () => {
  it('Account erstellen Link', () => {
    cy.visit('http://localhost:8081/login')
    cy.get('div#app h4')
      .contains('kein Nutzer')
      .should('be.visible')
    cy.get('div#app h4 > a')
      .contains('Account erstellen')
      .click()
    cy.get('div#app h4 > a').click()
    cy.visit('http://localhost:8081/register')
  })

  it('Felder ueberpruefen', () => {
    cy.get('div#register h1')
      .should('be.visible')
      .contains('Registrierung')
    cy.get('div#register select')
      .select('Frau')
      .should('have.value', 'Frau')
    cy.get(':nth-child(3) > :nth-child(1) > .field-50')
      .should('have.attr', 'placeholder', 'Username')
      .type('userCypress3')
      .should('have.value', 'userCypress3')
    cy.get(':nth-child(4) > :nth-child(1) > .field-50')
      .should('have.attr', 'placeholder', 'Nachname')
      .type('Testernachname')
      .should('have.value', 'Testernachname')
    cy.get(':nth-child(4) > :nth-child(2) > .field-50')
      .should('have.attr', 'placeholder', 'Vorname')
      .type('Firstname')
      .should('have.value', 'Firstname')
    cy.get(':nth-child(5) > :nth-child(1) > .field-80')
      .should('have.attr', 'placeholder', 'Straße')
      .type('am Weg')
      .should('have.value', 'am Weg')
    cy.get(':nth-child(5) > :nth-child(2) > .field-20')
      .should('have.attr', 'placeholder', 'Nummer')
      .type('17')
      .should('have.value', '17')
    cy.get(':nth-child(6) > :nth-child(1) > .field-80')
      .should('have.attr', 'placeholder', 'Stadt')
      .type('Teststadt')
      .should('have.value', 'Teststadt')
    cy.get(':nth-child(6) > :nth-child(2) > .field-20')
      .should('have.attr', 'placeholder', 'PLZ')
      .type('55100')
      .should('have.value', '55100')
    cy.get('.field-100')
      .should('have.attr', 'placeholder', 'E-Mail-Adresse')
      .type('info@mi-markt.de')
      .should('have.value', 'info@mi-markt.de')
    cy.get(':nth-child(8) > :nth-child(1) > .field-50')
      .should('have.attr', 'placeholder', 'Passwort')
      .type('IchbineintollesPasswort123!')
      .should('have.value', 'IchbineintollesPasswort123!')
    cy.get(':nth-child(8) > :nth-child(2) > .field-50')
      .should('have.attr', 'placeholder', 'Passwort wiederholen')
      .type('IchbineintollesPasswort123!')
      .should('have.value', 'IchbineintollesPasswort123!')
  })

  it('Registrieren-Button', () => {
    cy.wait(2000)
    cy.get('.btn').click()
    cy.wait(2000)
  })
})

describe('Default-User', () => {
  it('default-user', () => {
    cy.visit('http://localhost:8081/login')
    cy.get('div.user > .user')
      .should('have.attr', 'placeholder', 'Benutzername')
      .type('USERNAME1')
      .should('have.value', 'USERNAME1')
    cy.get('div.password > .password')
      .should('have.attr', 'placeholder', 'Passwort')
      .type('username1')
      .should('have.value', 'username1')
    cy.wait(2000)
    cy.get('div#app div.logBtn > button')
      .contains('Anmelden')
      .click()
    cy.get('nav#button li:nth-child(3) > a').click()
    cy.get('div#left > h1')
      .contains('Mein Profil')
      .click()
    cy.wait(2000)
    cy.get('nav#menu li:nth-child(2) > a').click()
    cy.wait(2000)
    cy.get('div#app div:nth-child(2) > a > img').click()
    cy.get(
      'div#app div:nth-child(1) > div.detail > div.number > button.plus > i'
    ).click()
    cy.get(
      'div#app div:nth-child(1) > div.detail > div.number > button.plus > i'
    ).click()
    cy.get(
      'div#app div:nth-child(1) > div.detail > div.number > button.buttonCart > i'
    ).click()
    cy.wait(2000)
    cy.get('nav#button li:nth-child(2) > a').click()
    cy.get('button#checkoutBtn').click()
  })

  describe('Bestellung abschließen', () => {
    it('Bestellen', () => {
      cy.get('input#pickup')
        .check({ force: true })
        .should('be.checked')
      cy.get('input#bar')
        .check({ force: true })
        .should('be.checked')
      cy.get('button#checkoutBn').click()
    })

    describe('Merkliste', () => {
      it('Merkliste', () => {
        cy.visit('http://localhost:8081/kategorien')
        cy.get('div#app div:nth-child(1) > a > img').click()
        cy.get('div#app div:nth-child(1) > button > i').click()
        cy.wait(2000)
        cy.visit('http://localhost:8081/login')
        cy.get('div.user > .user')
          .should('have.attr', 'placeholder', 'Benutzername')
          .type('USERNAME1')
          .should('have.value', 'USERNAME1')
        cy.get('div.password > .password')
          .should('have.attr', 'placeholder', 'Passwort')
          .type('username1')
          .should('have.value', 'username1')
        cy.wait(2000)
        cy.get('div#app div.logBtn > button')
          .contains('Anmelden')
          .click()
        cy.get('div#app div:nth-child(1) > button > i').click()
        cy.get('nav#button li:nth-child(1) > a').click()
      })
    })

    // describe('Viewport', () => {
    //   it('Viewports', () => {
    //     cy.viewport(2999, 2999)
    //     cy.viewport('macbook-15')
    //     cy.wait(200)
    //     cy.viewport('macbook-13')
    //     cy.wait(200)
    //     cy.viewport('macbook-11')
    //     cy.wait(200)
    //     cy.viewport('ipad-2')
    //     cy.wait(200)
    //     cy.viewport('ipad-mini')
    //     cy.wait(200)
    //     cy.viewport('iphone-6+')
    //     cy.wait(200)
    //     cy.viewport('iphone-6')
    //     cy.wait(200)
    //     cy.viewport('iphone-5')
    //     cy.wait(200)
    //     cy.viewport('iphone-4')
    //     cy.wait(200)
    //     cy.viewport('iphone-3')
    //     cy.wait(200)
    //     cy.viewport('ipad-2', 'portrait')
    //     cy.wait(200)
    //     cy.viewport('iphone-4', 'landscape')
    //     cy.wait(200)
    //   })
    // })

    describe('Check URL', () => {
      it('Check the URL ', () => {
        cy.url().should('include', '/bookmarks')
      })
    })

    it('Produktsuche ', () => {
      cy.get('input').should('have.attr', 'placeholder', 'Suche')
      cy.get('div#app input[type="text"]')
        .type('Apfel', { delay: 200 })
        .should('have.value', 'Apfel')
        .type('{enter}')
      cy.get('.search1').clear()
      cy.get('input').should('have.attr', 'placeholder', 'Suche')
      cy.get('div#app input[type="text"]')
        .type('Waschma', { delay: 200 })
        .should('have.value', 'Waschma')
        .type('{enter}')
      cy.get('.detail > :nth-child(1)').contains('Waschmaschine')
      cy.get('.detail > :nth-child(4)').contains('Kategorie')
    })
  })
  describe('Check URL', () => {
    it('Check the URL ', () => {
      cy.url().should('include', '/startseite')
    })
  })

  it('Layout Startseite ', () => {
    cy.get('.banner-box > h1').contains('Willkommen')
    cy.get('.deals > h1').contains('Angebote der Woche')
    cy.get('h2').contains('Gültig')
  })

  it('Produktsuche ', () => {
    cy.get('input').should('have.attr', 'placeholder', 'Suche')
    cy.get('div#app input[type="text"]')
      .type('Apfel', { delay: 200 })
      .should('have.value', 'Apfel')
      .type('{enter}')
    cy.get('.search1').clear()
    cy.get('input').should('have.attr', 'placeholder', 'Suche')
    cy.get('div#app input[type="text"]')
      .type('Waschma', { delay: 200 })
      .should('have.value', 'Waschma')
      .type('{enter}')
    cy.get('.detail > :nth-child(1)').contains('Waschmaschine')
    cy.get('.detail > :nth-child(4)').contains('Kategorie')
  })
})
describe('Check URL', () => {
  it('Check the URL ', () => {
    cy.url().should('include', '/startseite')
  })

  describe('Lagerist', () => {
    it('Lagerist ', () => {
      cy.visit('http://localhost:8081/login')
    })
    it('Viewports', () => {
      cy.viewport(2999, 2999)
      cy.viewport('macbook-15')
      cy.wait(200)
      cy.viewport('macbook-13')
      cy.wait(200)
      cy.viewport('macbook-11')
      cy.wait(200)
      cy.viewport('ipad-2')
      cy.wait(200)
      cy.viewport('ipad-mini')
      cy.wait(200)
      cy.viewport('iphone-6+')
      cy.wait(200)
      cy.viewport('iphone-6')
      cy.wait(200)
      cy.viewport('iphone-5')
      cy.wait(200)
      cy.viewport('iphone-4')
      cy.wait(200)
      cy.viewport('iphone-3')
      cy.wait(200)
      cy.viewport('ipad-2', 'portrait')
      cy.wait(200)
      cy.viewport('iphone-4', 'landscape')
      cy.wait(200)
    })
    it('Lagerist ', () => {
      cy.viewport('macbook-15')
      cy.get('div.user > .user')
        .should('have.attr', 'placeholder', 'Benutzername')
        .type('LAGERLAGER')
        .should('have.value', 'LAGERLAGER')
      cy.get('div.password > .password')
        .should('have.attr', 'placeholder', 'Passwort')
        .type('lagerlager')
        .should('have.value', 'lagerlager')
      cy.wait(2000)
      cy.get('div#app div.logBtn > button')
        .contains('Anmelden')
        .click()
      cy.wait(2000)
    })

    it('Lagerist-Seitenaufbau ', () => {
      cy.get('h1').contains('Eingegangene Bestellungen')
      cy.get('.headlines > :nth-child(1)').contains('Name')
      cy.get('#amount').contains('Produktanzahl')
      cy.get('#order').contains('Bestellung')
    })
  })

  describe('Lagerist-Produkte', () => {
    it('Produktseite ', () => {
      cy.get('nav#menu li:nth-child(2) > a')
        .contains('Produkte')
        .click()
      cy.get('h1').contains('Produkte')
      cy.get('h2').contains('Produkte nach Kategorien sortieren')
      cy.get(':nth-child(1) > #col2 > .button > .orderBtn')
        .contains('Produkte nachbestellen')
        .click()
      cy.get('.order > h1').contains('Produkte beim Händler nachbestellen')
      cy.get('.order > :nth-child(2)').contains('Produkt:')
      cy.get('#btn').contains('Produkte bestellen')
      cy.get('#close').contains('schließen')
      cy.get('.select')
        .select('5')
        .should('have.value', '5')
      cy.wait(1000)
      cy.get('button#btn')
        .contains('Produkte bestellen')
        .click()
    })

    it('Produktseite scrollen ', () => {
      cy.scrollTo(0, 2500)
      cy.wait(200)
      cy.scrollTo(0, 100)
      cy.wait(200)
      cy.scrollTo(0, 2000)
      cy.wait(200)
      cy.scrollTo(0, 3500)
      cy.wait(200)
      cy.scrollTo(0, 0)
    })

    it('Capri-Eis nachbestellen MAX ', () => {
      cy.get(':nth-child(77) > #col2 > .button > .orderBtn')
        .contains('Produkte nachbestellen')
        .click()
      cy.get('.select')
        .select('50')
        .should('have.value', '50')
      cy.get('#btn').click()

      for (let index = 0; index < 10; index++) {
        cy.get(':nth-child(77) > #col2 > .button > .orderBtn')
          .contains('Produkte nachbestellen')
          .click()
        cy.get('.select')
          .select('50')
          .should('have.value', '50')
        cy.get('#btn').click()
      }
    })

    it('Logout ', () => {
      cy.clearCookies()
      cy.clearLocalStorage() // clear all local storage
      cy.visit('http://localhost:8081/login')
    })

    describe('Admin', () => {
      it('Admin-Login ', () => {
        cy.get('div.user > .user')
          .should('have.attr', 'placeholder', 'Benutzername')
          .type('ADMINADMIN')
          .should('have.value', 'ADMINADMIN')
        cy.get('div.password > .password')
          .should('have.attr', 'placeholder', 'Passwort')
          .type('adminadmin')
          .should('have.value', 'adminadmin')
        cy.wait(2000)
        cy.get('div#app div.logBtn > button')
          .contains('Anmelden')
          .click()
      })

      it('Aufbau Startseite Admin ', () => {
        cy.get(
          ':nth-child(1) > tbody > :nth-child(1) > :nth-child(2)'
        ).contains('Capri')
        cy.get('tbody > :nth-child(1) > :nth-child(3)').contains('Einheiten')
        cy.get(':nth-child(3) > thead > tr > :nth-child(2)').contains(
          'Produkte'
        )
        cy.get(':nth-child(2) > .item')
          .contains('Produkte')
          .click()
      })
      it('Produkte Admin Funktionen  ', () => {
        cy.get('div#admin > h1').contains('Produkte')
        cy.get('#download > :nth-child(1)')
          .contains('Produkte')
          .click()
        cy.wait(100)
        cy.get('#download > :nth-child(2)')
          .contains('Produkte')
          .click()
        cy.wait(100)
        cy.get('#download > :nth-child(3)')
          .contains('Produkte')
          .click()
        cy.wait(100)
        cy.get(
          'div#products tr:nth-child(1) > td:nth-child(5) > button#editBn'
        ).click()
        cy.get('.price')
          .clear()
          .type('0.01')
          .should('have.value', '0.01')
        cy.get('.btn')
          .contains('Speichern')
          .click()
        cy.get('tbody > :nth-child(1) > :nth-child(3)').contains('0.01')
        cy.get(':nth-child(77) > :nth-child(5) > #delBn').click()
        cy.get('#delete > #delBn').click()
      })
    })

    describe('Cookies loeschen', () => {
      it('Cookies loeschen ', () => {
        cy.clearCookies()
      })
    })

    describe('LocalStorage loeschen', () => {
      it('LocalStorage loeschen ', () => {
        cy.clearLocalStorage()
      })
    })
  })
})
