<template>
  <div>

    <!-- 左侧文章内容 -->
    <el-row
      class="content-blog"
      justify="center"
      type="flex">
      <el-col :span="10">
        <nuxt-link
          v-for="item in list"
          :key="item.id"
          :to="{name:'article-id',params:{id:item.id}}"
          class="box-href">
          <el-card
            class="box-card"
            shadow="hover">
            <h2 class="box-title">{{ item.title }}</h2>
            <div class="box-icon">
              <span><i class="el-icon-date"/>&nbsp;{{ item.publishTime }}</span>
              <!-- <span><i class="el-icon-view"></i>&nbsp;115次阅读</span> -->
            </div>
            <div class="box-content">{{ item.des }}</div>
          </el-card>
        </nuxt-link>

        <el-pagination
          :page-size="5"
          :total="count"
          background
          class="pagination"
          layout="prev, pager, next"
          @current-change="pagination"/>
      </el-col>
      <!-- 右侧关于我 -->
      <el-col
        :offset="1"
        :span="5">
        <About/>
        <!-- 近期文章开始 -->
        <Lately :lately="lately"/>
        <!-- 近期文章结束 -->
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { baseurl } from '~/plugins/url.js'
  import About from '../components/Index/About'
  import Lately from '../components/Index/Lately'

  export default {
    components: {
      About,
      Lately
    },
    async asyncData({ app }) {
      let params = {
        page: 1,
        limit: 5,
        classify: 1,
        orderBy: 'publish_time',
        order: 'desc'
      }

      let { data } = await app.$axios.post(`${baseurl}/article/list`, params)
      let list = data.data.records
      let count = parseInt(data.data.total)
      let lately = list.slice(0, 4)
      return { list, count, lately }
    },
    methods: {
      async pagination(page) {
        let params = {
          page: page,
          limit: 5,
          classify: 1,
          orderBy: 'publish_time',
          order: 'desc'
        }
        let { data } = await this.$axios.post(`${baseurl}/article/list`, params)
        this.list = data.data.records
        let count = parseInt(data.data.total)
      }
    }
  }
</script>

<style lang="less">
  @import './../assets/css/Index/Content.less';
</style>
