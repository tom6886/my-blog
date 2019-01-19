<template>
  <div>
    <nav-header/>
    <nuxt/>
    <nav-footer/>
  </div>
</template>
<script>
  import NavHeader from '~/components/NavHeader.vue'
  import NavFooter from '~/components/Footer.vue'

  export default {
    components: {
      NavHeader,
      NavFooter
    }
  }
</script>
