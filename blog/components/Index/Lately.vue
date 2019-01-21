<template>
  <el-card class="article">
    <div class="article-title">近期文章</div>
    <hr>
    <nuxt-link
      v-for="item in lately"
      :key="item.id"
      :to="{name:'article-id',params:{id:item.id}}"
      class="article-link">
      <i class="el-icon-edit"/>&nbsp;&nbsp;{{ item.title }}
    </nuxt-link>
  </el-card>
</template>

<script>
  export default {
    name: 'Lately',
    props: {
      lately: {
        type: Array,
        default: null
      }
    }
  }
</script>

<style scoped>

</style>
