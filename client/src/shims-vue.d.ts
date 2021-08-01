declare module '*.vue' {
  import { defineComponent } from 'vue'
  const component: ReturnType<typeofdefineComponent>
  export default component
}
