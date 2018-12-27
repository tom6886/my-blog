<template>
  <div>
    <nav-header :active="active"/>
    <!-- 左侧文章内容 -->
    <el-row
      class="content-blog"
      justify="center"
      type="flex">
      <el-col :span="10">
        <nuxt-link
          v-for="item in list"
          :key="item._id"
          :to="{name:'Front-id',params:{id:item._id}}"
          class="box-href">
          <el-card
            class="box-card"
            shadow="hover">
            <h2 class="box-title">{{ item.title }}</h2>
            <div class="box-icon">
              <span><i class="el-icon-date"/>&nbsp;{{ item.time }}</span>
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
        <el-card class="about">
          <div class="about-title">about Me</div>
          <div class="about-name">
            <img
              alt="brianlee"
              src="~/static/images/name.png">
          </div>
          <div class="about-content">
            <p>网名：Brian·Lee</p>

            <p>职业：Web前端工程师</p>

            <p>邮箱：cn_brian@163.com</p>
          </div>
        </el-card>
        <!-- 近期文章开始 -->
        <el-card class="article">
          <div class="article-title">近期文章</div>
          <hr>
          <nuxt-link
            v-for="item in lately"
            :key="item._id"
            :to="{name:'Front-id',params:{id:item._id}}"
            class="article-link">
            <i class="el-icon-edit"/>&nbsp;&nbsp;{{ item.title }}
          </nuxt-link>
        </el-card>
        <!-- 近期文章结束 -->

        <!-- 友情链接开始 -->
        <el-card class="link">
          <div class="link-title">友情链接</div>
          <hr>
          <div class="link-content">
            <a
              class="link-url"
              href="/"
              target="_blank">虚位以待</a>
            <a
              class="link-url"
              href="/"
              target="_blank">虚位以待</a>
          </div>
        </el-card>
        <!-- 友情链接结束 -->
      </el-col>
    </el-row>
    <nav-footer/>
  </div>
</template>

<script>
  import NavHeader from '~/components/NavHeader.vue'
  import NavFooter from '~/components/Footer.vue'
  import { baseurl } from '~/plugins/url.js'

  export default {
    components: {
      NavHeader,
      NavFooter
    },
    data() {
      return {
        active: 'index'
      }
    },
    async asyncData({ app }) {
      // 服务器端渲染数据
      // let json = {page:1,pagesize:5}
      // let {data} =await app.$axios.get(`${baseurl}/api/article/getFrontArticle`,{params:json});
      // let {list,count} = data;
      // let lately = list.slice(0,4);
      // return {list,count,lately}
    },
    methods: {
      pagination(page) {
        let json = { page, pagesize: 5 }
        this.$axios.get(`${baseurl}/api/article/getFrontArticle`, { params: json }).then(res => {
          let { error, count, list } = res.data
          this.list = list
        })
      }
    },
    head() {
      return {
        title: 'Tom的个人博客-一个基于Nuxt构建的博客网站',
        meta: [
          { hid: 'description', name: 'description', content: '汤波个人博客，是一个记录博主学习和成长的自媒体博客。关注于web全栈技术的学习研究。' },
          { hid: 'keywords', name: 'keywords', content: '汤波,互联网,自媒体,汤波博客,科技博客,Brian,独立博客,个人博客,原创博客,全栈,全栈开发' },
          { hid: 'author', content: 'tom' }
        ]
      }
    }
  }
</script>

<style lang="less">
  @import './../assets/css/Index/Content.less';
</style>
