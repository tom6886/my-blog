<template>
  <div>
    <article-list
      :classify="2"
      :list="list"
      :count="count"
      :lately="lately"/>
  </div>
</template>

<script>
  import ArticleList from '../components/ArticleList'
  import {baseurl} from '~/plugins/url.js'

  export default {
    components: {
      ArticleList
    },
    async asyncData({app}) {
      let params = {
        page: 1,
        limit: 5,
        classify: 2,
        orderBy: 'publish_time',
        order: 'desc'
      }

      let {data} = await app.$axios.post(`${baseurl}/article/list`, params)
      let list = data.data.records
      let count = parseInt(data.data.total)
      let lately = list.slice(0, 4)
      return {list, count, lately}
    }
  }
</script>

<style lang="less">
  @import './../assets/css/Index/Content.less';
</style>
