<template>
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
            <span><i class="el-icon-date"/>&nbsp;{{ formatDate(item.publish_time) }}</span>
          </div>
          <div class="box-content">{{ item.des }}</div>
        </el-card>
      </nuxt-link>

      <el-pagination
        v-if="count > 0"
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
</template>

<script>
  import {baseurl} from '~/plugins/url.js'
  import About from './Index/About'
  import Lately from './Index/Lately'

  export default {
    components: {
      About,
      Lately
    },
    props: {
      classify: {
        type: Number,
        default: null
      },
      list: {
        type: Array,
        default: null
      },
      count: {
        type: Number,
        default: 0
      },
      lately: {
        type: Array,
        default: null
      }
    },
    methods: {
      async pagination(page) {
        let params = {
          page: page,
          limit: 5,
          classify: this.classify,
          orderBy: 'publish_time',
          order: 'desc'
        }
        let {data} = await this.$axios.post(`${baseurl}/article/list`, params)
        this.list = data.data.records
        let count = parseInt(data.data.total)
      },
      formatDate(date) {
        let _time = new Date(date);
        return _time.getFullYear() + '年' + (_time.getMonth() + 1) + '月' + _time.getDate() + '日'
      }
    }
  }
</script>
