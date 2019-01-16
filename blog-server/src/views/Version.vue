<template>
    <div>
        <Form :model="version" :rules="ruleValidate" label-position="top" ref="form">
            <FormItem label="版本号" prop="title">
                <Input placeholder="输入版本号，例如:1.0.0" size="large" v-model="version.version"></Input>
            </FormItem>
            <FormItem>
                <editor :init="editorInit" id="tinymce" v-model="version.content"></editor>
            </FormItem>
            <FormItem>
                <Button @click="beforeSubmit" class="version_button" type="success">发布新版本</Button>
            </FormItem>
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
                version: {
                    version: '1.0.0',
                    content: ''
                },
                editorInit: {
                    language_url: '/tinymce/zh_CN.js',
                    language: 'zh_CN',
                    skin_url: '/tinymce/skins/lightgray',
                    height: 300
                },
                ruleValidate: {
                    version: [
                        {required: true, message: '版本号不可为空', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '内容不可为空', trigger: 'blur'}
                    ]
                }
            }
        },
        mounted() {
            tinymce.init({});
        },
        methods: {
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
                let res = await fetch("version/save", this.version);
                if (res.code !== 0) {
                    this.$Message.error(res.msg);
                    return;
                }

                this.$Message.success('版本发布成功');
            }
        }
    }
</script>
<style lang="less">
    .version_content {
        margin-top: 2rem;
    }

    .version_button {
        float: right;
        margin-top: 2rem;
    }
</style>
