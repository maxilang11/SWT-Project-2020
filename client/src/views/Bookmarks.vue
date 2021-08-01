<template>
  <div class="bookmarks">
    <h1>Merkliste</h1>
    <section class="products">
      <BookmarksGrid
        :bookmarkItems="item"
        v-for="item in bookmarkItems"
        :key="item.id"
      />
    </section>
  </div>
</template>

<script>
import { defineComponent, onMounted } from 'vue'
import BookmarksGrid from '../components/BookmarksGrid.vue'
import { useBookmark } from '../service/BookmarkStore'

export default defineComponent({
  name: 'Bookmarks',
  components: { BookmarksGrid },
  setup() {
    const userid = localStorage.getItem('userid')
    const { liste, update } = useBookmark()
    const bookmarkItems = liste

    function delItem(artNum) {
      bookmarkItems.value = bookmarkItems.value.filter(
        product => product.artNum !== artNum
      )
    }

    /* zu Beginn des Aufrufs der Merklistseite wird entsprechend der UserID des aktuell
    eingeloggten Users ein mal die Liste von Produkten in der Merkliste geupdated */
    onMounted(async () => {
      await update(userid !== null ? parseInt(userid) : 0)
    })

    return { bookmarkItems, delItem }
  }
})
</script>

<style scoped>
h1 {
  font-size: 2.8vw;
  color: black;
  text-align: left;
  margin-bottom: 3%;
  margin-left: 20.5%;
}
.products {
  display: grid;
  grid-template-columns: repeat(2, 0fr);
  grid-column-gap: 6%;
  grid-row-gap: 5%;
  margin-left: 20.5%;
  margin-bottom: 4%;
}
@media (max-width: 666px) {
  h1 {
    font-size: 6vw;
    margin-left: 37%;
  }
}
</style>
