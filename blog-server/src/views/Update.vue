<template>
    <div>
        <Form :model="article" :rules="ruleValidate" label-position="top" ref="form">
            <Col span="19">
                <FormItem label="撰写新文章" prop="title">
                    <Input class="article_title" name="title" placeholder="在此输入文章标题" size="large" type="text"
                           v-model="article.title"></Input>
                </FormItem>
                <FormItem label="文章简介" prop="des">
                    <Input class="article_title" placeholder="在此输入文章简介" size="large"
                           v-model="article.des"></Input>
                </FormItem>
                <FormItem label="文章内容" prop="content">
                    <editor :init="editorInit" id="tinymce" v-model="article.content"></editor>
                </FormItem>
                <FormItem>
                    <Button @click="beforeSubmit" class="article_button" type="success">发布文章</Button>
                </FormItem>
            </Col>
            <Col class="content_right" offset="1" span="4">
                <FormItem prop="publishTime">
                    <Card class="card">
                        <label class="article" slot="title">发布日期</label>
                        <DatePicker class="data_picker" name="date" placeholder="Select date"
                                    size="large"
                                    style="width:100%;" type="date" v-model="article.publishTime"></DatePicker>
                    </Card>
                </FormItem>
                <FormItem>
                    <Card class="card">
                        <p slot="title">分类目录</p>
                        <RadioGroup v-model="article.classify" vertical>
                            <Radio label="0">
                                <i class="iconfont icon-h5"></i>
                                <span class="list_menu">前端开发</span>
                            </Radio>
                            <Radio label="1">
                                <Icon class="iconfont icon-nodejs"></Icon>
                                <span class="list_menu">后端开发</span>
                            </Radio>
                        </RadioGroup>
                    </Card>
                </FormItem>
            </Col>
        </Form>
    </div>
</template>
<script>
    import tinymce from 'tinymce/tinymce';
    import 'tinymce/themes/modern/theme';
    import Editor from '@tinymce/tinymce-vue';
    import {fetch} from "../utils/api";

    export default {
        components: {
            'editor': Editor
        },
        data() {
            return {
                article: {
                    id: '',
                    title: '',
                    content: '',
                    publishTime: '',
                    classify: "0",
                    des: ''
                },
                editorInit: {
                    language_url: `${this.$baseUrl}/tinymce/zh_CN.js`,
                    language: 'zh_CN',
                    skin_url: `${this.$baseUrl}//tinymce/skins/lightgray`,
                    height: 300
                },
                ruleValidate: {
                    title: [
                        {required: true, message: '标题不可为空', trigger: 'blur'},
                        {type: 'string', max: 25, message: '标题不可超过25个字', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '内容不可为空', trigger: 'blur'}
                    ],
                    publishTime: [
                        {required: true, type: 'date', message: '请选择发布时间', trigger: 'change'}
                    ]
                }
            }
        },
        created() {
            tinymce.init({});
            this.init();
        },
        methods: {
            async init() {
                let res = await fetch("article/entity/" + this.$route.params.id, {});
                this.article = res.data;
                this.article.classify = this.article.classify.toString();
            },
            beforeSubmit() {
                this.$refs["form"].validate((valid) => {
                    if (valid) {
                        this.submit();
                    } else {
                        this.$Message.error('请正确填写!');
                    }
                })
            },
            async submit() {
                let res = await fetch("article/save", this.article);
                if (res.code !== 0) {
                    this.$Message.error(res.msg);
                    return;
                }

                this.$Message.success('文章更新成功');
            }
        }
    }
</script>
<style>
    @import '../assets/css/admin/article.less';
</style>
