<template>
    <Menu :open-names="['1','2']" @on-select="getIndex" active-name="admin" class="menu-left"
          style="width:100%;min-height:100vh;">
        <Submenu name="1">
            <template slot="title">
                <Icon type="md-cog"/>
                控制面板
            </template>
            <!--<MenuItem name="admin">-->
                <!--<Icon type="ios-alert-outline" />-->
                <!--系统概览-->
            <!--</MenuItem>-->
            <MenuItem name="article">
                <Icon type="ios-book-outline" />
                发布文章
            </MenuItem>
            <MenuItem name="articleList">
                <Icon type="ios-paper-outline" />
                文章列表
            </MenuItem>
        </Submenu>
        <Submenu name="2">
            <template slot="title">
                <Icon type="md-alert" />
                版本控制
            </template>
            <MenuItem name="version">
                <Icon type="ios-cloud-upload-outline" />
                发布版本
            </MenuItem>
        </Submenu>
    </Menu>
</template>
<script>
    export default {
        data() {
            return {}
        },
        methods: {
            getIndex(val) {
                this.$router.push({name: val});
            }
        }
    }
</script>
