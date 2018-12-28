<template>
    <div class="login_images">
        <!-- <router-link :to="{name:'admin'}">跳转admin页面</router-link> -->
        <Row>
            <Col offset="20" span="4" style="float:right;margin-right:10%;margin-top:20%;">
                <Card>
                    <p slot="title">欢迎登录</p>
                    <Input name="username" placeholder="请输入用户名" type="text" v-model="username">
                        <span slot="prepend"><Icon :size="16" type="ios-person"></Icon></span>
                    </Input>
                    <br>
                    <Input name="password" placeholder="请输入密码" type="password" v-model="password">
                        <span slot="prepend"><Icon :size="14" type="md-lock"></Icon></span>
                    </Input>
                    <br>
                    <Button @click="login" long type="primary">登录</Button>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                username: '',
                password: ''
            }
        },
        methods: {
            login() {
                this.$Message.error(this.username)
            }
        }
    }
</script>

<style lang="less">
    .login_images {
        width: 100%;
        height: 100vh;
        background: url('../assets/images/login.jpeg') no-repeat center center;
        background-size: cover;
    }
</style>
