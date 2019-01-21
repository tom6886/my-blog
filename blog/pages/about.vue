<template>
  <div>
    <!-- 并非采用后台动态添加，后期增加动态修改 -->
    <el-row
      class="about_content"
      justify="center"
      type="flex">
      <el-col
        :span="15"
        class="about">
        <el-card class="box-card">
          <h1>About Blog</h1>
          <hr class="line">
          <p>简单介绍下博客使用技术：</p>
          <p class="about_title">博客前端：</p>
          <p class="about_text"><b>采用服务器端渲染(ssr)</b>,基于 Vue.js 的通用应用框架:<b>Nuxt.js + Element-UI + @nuxtjs/axios +
          Less</b></p>
          <p class="about_title">博客后端:</p>
          <p class="about_text">UI层：使用 <b>Vue + Vue-Router + Vue-cli + axios + Less + IView</b></p>
          <p class="about_text">服务层：使用 <b>Spring Boot + Spring Security + JWT + Mysql</b></p>
          <h1>About Me</h1>
          <hr class="line">
          <p class="about_title">个人简介</p>
          <ul>
            <li>姓名：汤波</li>
            <li>英文名：Tang</li>
            <li>爱好：写代码</li>
          </ul>
          <p class="about_title">技能</p>
          <ul>
            <li>前端：HTML、CSS、Javacript(ES next)、JQuery、Vue全家桶等等</li>
            <li>UI框架：Bootstrap、Layer-UI、WEUI、Muse-UI、Vux、MUI+HUI、Element-UI、Mint-UI、Iview 等等</li>
            <li>后端：Spring Boot、Spring Cloud、C#、.net core</li>
          </ul>
          <p class="about_title">联系方式</p>
          <p class="about_text">邮箱：641923573@qq.com</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="less" scoped>
  @import '../assets/css/Index/About.less';
</style>
