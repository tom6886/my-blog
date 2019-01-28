<template>
    <div>
        <Menu active-name="1" mode="horizontal" theme="light">
            <Row justify="center" type="flex">
                <Col span="23">
                    <div class="nav-left">
                        <div class="logo">
                            Tang's Blog
                        </div>
                    </div>
                    <div class="nav-right">
                        <div class="user-dropdown-menu-con">
                            <Row align="middle" class="user-dropdown-innercon" justify="end" type="flex">
                                <Dropdown @on-click="handleClickUserDropdown" transfer trigger="click">
                                    <a href="javascript:void(0)">
                                        <span class="main-user-name">Tang</span>
                                        <Icon type="md-arrow-dropdown"></Icon>
                                    </a>
                                    <DropdownMenu slot="list">
                                        <DropdownItem name="ownSpace">个人中心</DropdownItem>
                                        <DropdownItem name="changePass">修改密码</DropdownItem>
                                        <DropdownItem divided name="logOut">退出登录</DropdownItem>
                                    </DropdownMenu>
                                </Dropdown>
                                <Avatar style="background: #619fe7;margin-left: 10px;"></Avatar>
                            </Row>
                        </div>
                    </div>
                </Col>
            </Row>
        </Menu>
        <Modal
                title="修改密码"
                v-model="modal1">
            <Form :label-width="120" :model="psw" :rules="ruleValidate" ref="form">
                <FormItem label="请输入原密码" prop="origin">
                    <Input name="password" type="password" v-model="psw.origin"></Input>
                </FormItem>
                <FormItem label="请输入新密码" prop="newone">
                    <Input name="password" type="password" v-model="psw.newone"></Input>
                </FormItem>
                <FormItem label="请再次输入新密码" prop="again">
                    <Input name="password" type="password" v-model="psw.again"></Input>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button @click="beforeSubmit" long type="primary">确认</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
    import Cookies from "js-cookie";
    import {fetch} from "../utils/api";

    export default {
        data() {
            const validatePassCheck = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.psw.newone) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                psw: {
                    origin: null,
                    newone: null,
                    again: null
                },
                ruleValidate: {
                    origin: [
                        {required: true, message: '请输入原密码', trigger: 'blur'}
                    ],
                    newone: [
                        {required: true, message: '请输入新密码', trigger: 'blur'}
                    ],
                    again: [
                        {validator: validatePassCheck, trigger: 'blur'}
                    ]
                },
                modal1: false
            }
        },
        methods: {
            handleClickUserDropdown(name) {
                this[name]();
            },
            logOut() {
                Cookies.remove("accessToken");

                this.$router.push({
                    name: 'login'
                });
            },
            changePass() {
                this.modal1 = true;
            },
            beforeSubmit() {
                this.$refs["form"].validate((valid) => {
                    if (valid) {
                        this.submit();
                    } else {
                        this.$Message.error('请填写正确信息!');
                    }
                })
            },
            async submit() {
                let res = await fetch("user/changePwd", this.psw);
                if (res.code !== 0) {
                    this.$Message.error(res.msg);
                    return;
                }
                this.$Message.success('修改密码成功');
                this.modal1 = false;
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
