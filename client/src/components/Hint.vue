<template>
  <div class="Hint">
    <slot />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'Hint'
})
</script>

<style scoped>
.Hint {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 3rem;
  border-radius: 8px;
}
</style>
