<template>
  <div>
    <nav-header :active="active"/>
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
