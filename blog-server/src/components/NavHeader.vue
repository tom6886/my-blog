<template>
    <div>
        <Menu active-name="1" mode="horizontal" theme="light">
            <Row justify="center" type="flex">
                <Col span="23">
                    <div class="nav-left">
                        <div class="logo">
                            May's Blog
                        </div>
                    </div>
                    <div class="nav-right">
                        <div class="user-dropdown-menu-con">
                            <Row align="middle" class="user-dropdown-innercon" justify="end" type="flex">
                                <Dropdown @on-click="handleClickUserDropdown" transfer trigger="click">
                                    <a href="javascript:void(0)">
                                        <span class="main-user-name">{{ user.name }}</span>
                                        <Icon type="md-arrow-dropdown"></Icon>
                                    </a>
                                    <DropdownMenu slot="list">
                                        <DropdownItem name="ownSpace">个人中心</DropdownItem>
                                        <DropdownItem name="changePass">修改密码</DropdownItem>
                                        <DropdownItem divided name="loginout">退出登录</DropdownItem>
                                    </DropdownMenu>
                                </Dropdown>
                                <Avatar style="background: #619fe7;margin-left: 10px;"></Avatar>
                            </Row>
                        </div>
                    </div>
                </Col>
            </Row>
        </Menu>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                user: {
                    name: "bobo"
                }
            }
        },
        methods: {
            handleClickUserDropdown() {

            }
        }
    }
</script>
<style lang="less">
    .logo {
        font-size: 25px;
        font-weight: bold;
        float: left;
        color: #41b883;
    }

    .nav-right {
        float: right;
        line-height: 58px;
    }
</style>
