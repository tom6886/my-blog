<template>
    <div>
        <nav-header></nav-header>
        <Row justify="space-between" style="min-width:1200px;" type="flex">
            <Col span="3">
                <nav-left class="navleft"></nav-left>
            </Col>
            <Col class="routerView" span="21">
                <router-view></router-view>
            </Col>
        </Row>

    </div>
</template>

<script>
    import NavHeader from '../components/NavHeader';
    import NavLeft from '../components/NavLeft';

    export default {
        name: "Main",
        components: {
            NavHeader,
            NavLeft
        }
    }
</script>

<style lang="less">
    @import '../assets/css/admin/style.less';

    body {
        min-width: 1200px;
    }
</style>
