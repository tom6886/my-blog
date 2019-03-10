<template>
    <editor :init="editorInit" id="tinymce" :value="content" @input="$emit('input', $event)"></editor>
</template>

<script>
    import {upload} from "../utils/upload";
    import tinymce from 'tinymce/tinymce'
    import 'tinymce/themes/modern/theme'
    import Editor from '@tinymce/tinymce-vue'
    import 'tinymce/plugins/image'
    import 'tinymce/plugins/link'
    import 'tinymce/plugins/code'
    import 'tinymce/plugins/table'
    import 'tinymce/plugins/lists'
    import 'tinymce/plugins/contextmenu'
    import 'tinymce/plugins/wordcount'
    import 'tinymce/plugins/colorpicker'
    import 'tinymce/plugins/textcolor'

    export default {
        props: ["aId", "content"],
        components: {
            'editor': Editor
        },
        data() {
            return {
                editorInit: null
            }
        },
        created() {
            let _this = this;
            _this.editorInit = {
                language_url: `${this.$baseUrl}/tinymce/zh_CN.js`,
                language: 'zh_CN',
                skin_url: `${this.$baseUrl}//tinymce/skins/lightgray`,
                height: 300,
                plugins: 'link lists image code table colorpicker textcolor wordcount contextmenu',
                toolbar: 'bold italic underline strikethrough | fontsizeselect | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist | outdent indent blockquote | undo redo | link unlink image code | removeformat',
                branding: false,

                /* we override default upload handler to simulate successful upload*/
                images_upload_handler: function (blobInfo, success, failure) {
                    upload(_this.aId, blobInfo.blob()).then(res => {
                        success(res.data)
                    }).catch(res => {
                        failure(res)
                    })
                }
            }
        },
        mounted() {
            tinymce.init({});
        },
        model: {
            prop: 'content',
            event: 'input'
        }
    }
</script>

<style scoped>

</style>