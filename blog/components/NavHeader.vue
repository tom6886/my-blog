<template>
  <div>
    <div class="nav">
      <el-row
        class="NavHeader"
        justify="center"
        type="flex">
        <el-col :span="20">
          <nuxt-link
            :to="{name:'index'}"
            class="logo">Tang's Blog
          </nuxt-link>
          <el-menu
            :default-active="active"
            active-text-color="#41b883"
            class="el-menu-demo navbar"
            mode="horizontal"
            text-color="#fff"
            @select="handleSelect">
            <el-menu-item index="index">后端文章</el-menu-item>
            <!--<el-menu-item index="front">前端文章</el-menu-item>-->
            <el-menu-item index="front">浅尝辄止</el-menu-item>
            <el-menu-item index="version">更新日志</el-menu-item>
            <el-menu-item index="about">关于我</el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </div>
    <img
      alt="banner"
      class="banner_img"
      src="~/static/images/banner.jpg">
  </div>
</template>

<script>
  export default {
    data() {
      return {
        activeIndex: 'index'
      }
    },
    computed: {
      active() {
        return this.$route.name
      }
    },
    methods: {
      handleSelect(key) {
        this.$router.push({name: key})
      }
    }
  }
</script>
<style lang="less">
  @import './../assets/css/Index/NavHeader.less';
</style>
