<template>
  <div>
    <el-row
      justify="center"
      type="flex">
      <el-col :span="10">
        <h1>更新日志</h1>
      </el-col>
    </el-row>
    {{ list.length }}
    <el-row
      v-for="item in list"
      :key="item.id"
      class="version"
      justify="center"
      type="flex">
      <el-col :span="10">
        <el-card class="box-card">
          <div
            slot="header"
            class="clearfix">
            <span style="font-weight:bold;">{{ item.version }}</span>
            <span style="float:right">{{ item.createTime }}</span>
          </div>
          <div v-html="item.content">
            {{ item.content }}
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { baseurl } from '~/plugins/url.js'

  export default {
    async asyncData({ app }) {
      let { data } = await app.$axios.post(`${baseurl}/version/list`, {})
      let list = data.data
      return { list }
    }
  }
</script>

<style scoped>

</style>
