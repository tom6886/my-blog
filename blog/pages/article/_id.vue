<template>
  <div>
    <el-row
      justify="center"
      type="flex">
      <el-col
        :span="14"
        class="detail_title">
        <div>{{ title }}</div>
        <div class="time">发布时间：{{ publishTime }}&nbsp;&nbsp;&nbsp;&nbsp;分类：{{ classify === 0 ? '前端文章' : '后端文章' }}</div>
      </el-col>

    </el-row>
    <el-row
      justify="center"
      type="flex">
      <el-col
        :span="14"
        class="detail_content">
        <el-card>
          <div v-show="!content">暂无文章数据...</div>
          <div
            class="md markdown-body"
            v-html="content"/>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { baseurl } from '~/plugins/url.js'

  export default {
    async asyncData({ app, params }) {
      let { data } = await app.$axios.post(`${baseurl}/server/article/entity/` + params.id, {})
      let { content, des, classify, publishTime, title } = data.data
      return { title, des, content, classify, publishTime }
    },
    head() {
      return {
        title: this.title,
        meta: [
          { hid: 'description', name: 'description', content: `${this.des}` },
          { hid: 'author', content: 'brian' }
        ]
      }
    }
  }
</script>

<style lang="less">
  @import './../../assets/css/Index/Detail.less';
</style>
