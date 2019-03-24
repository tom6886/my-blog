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
    },
    head() {
      return {
        title: 'Tang的博客-个人的心得与分享',
        meta: [
          {hid: 'description', name: 'description', content: '汤波的个人博客，是一个记录博主学习和成长的自媒体博客。关注于web全栈技术的学习研究。'},
          {hid: 'keywords', name: 'keywords', content: '汤波,互联网,自媒体,汤波博客,科技博客,Tang,独立博客,个人博客,原创博客,全栈,全栈开发'},
          {hid: 'author', content: 'tom'}
        ]
      }
    }
  }
</script>
