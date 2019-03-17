<template>
    <Row justify="center" type="flex">
        <Col span="24">
            <h2 style="margin-bottom:0.5%;">浅尝辄止:</h2>
            <Table :columns="columns" :data="front" border></Table>
            <div class="pagination" v-show="frontPager.total !== 0">
                <Page :current="frontPager.current" :page-size="frontPager.size" :total="frontPager.total"
                      @on-change="frontPage" show-total></Page>
            </div>
        </Col>
        <Col span="24" style="margin-top:5%;">
            <h2 style="margin-bottom:0.5%;">后端文章:</h2>
            <Table :columns="columns" :data="back" border></Table>
            <div class="pagination" v-show="backPager.total !== 0">
                <Page :current="backPager.current" :page-size="backPager.size" :total="backPager.total"
                      @on-change="backPage" show-total></Page>
            </div>
        </Col>
        <!-- 封装模态框 -->
        <Modal v-model="modal2" width="360">
            <p slot="header" style="color:#f60;text-align:center">
                <Icon type="information-circled"></Icon>
                <span>删除文章提醒</span>
            </p>
            <div style="text-align:center">
                <p style="font-weight:bold;font-size:16px;">此删除操作将会永久删除，且无法恢复</p>
                <p style="font-weight:bold;">你确定要删除么？</p>
            </div>
            <div slot="footer">
                <Button :loading="modal_loading" @click="delet" long size="large" type="error">确定删除</Button>
            </div>
        </Modal>
        <!-- 模态框结束 -->
    </Row>
</template>
<script>
    import {fetch} from "../utils/api";

    export default {
        data() {
            return {
                front: [],
                back: [],
                frontPager: {
                    current: null,
                    pages: null,
                    total: null,
                    size: null
                },
                backPager: {
                    current: null,
                    pages: null,
                    total: null,
                    size: null
                },
                del: null,
                ok: null,
                modal2: false,
                modal_loading: false,
                id: '',
                list: '',
                columns: [
                    {
                        title: '文章标题',
                        key: 'title'
                    },
                    {
                        title: '文章简介',
                        key: 'des',
                        render: (h, params) => {
                            if (!params.row.des) {
                                return h('span', {}, "无");
                            }

                            return h('span', {}, params.row.des);
                        }
                    },
                    {
                        title: '发布时间',
                        key: 'publish_time'
                    },
                    {
                        title: '创建时间',
                        key: 'create_time'
                    },
                    {
                        title: '发布人',
                        key: 'display_name'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.update(params.row.id)
                                        }
                                    }
                                }, '修改'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.remove(params.row.id, params.row.classify)
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ]
            }
        },
        created() {
            //初始化
            this.getList(2, 1);
            this.getList(1, 1);
        },
        methods: {
            update(id) {
                this.$router.push({name: 'update', params: {id}});
            },
            remove(id, list) {
                this.modal2 = true;
                this.id = id;
                this.list = list;
            },
            async getList(classify, page) {
                try {
                    let params = {
                        page: page,
                        limit: 6,
                        classify: classify
                    };
                    let res = await fetch("article/list", params);
                    if (classify === 2) {
                        this.setFront(res.data);
                    } else {
                        this.setBack(res.data);
                    }
                } catch (error) {
                    this.$Message.error(error);
                }
            },
            setFront(data) {
                this.front = data.records;
                this.frontPager =
                    {
                        current: parseInt(data.current),
                        pages: parseInt(data.pages),
                        total: parseInt(data.total),
                        size: parseInt(data.size)
                    }
            },
            setBack(data) {
                this.back = data.records;
                this.backPager =
                    {
                        current: parseInt(data.current),
                        pages: parseInt(data.pages),
                        total: parseInt(data.total),
                        size: parseInt(data.size)
                    }
            },
            frontPage(index) {
                this.getList(2, index);
            },
            backPage(index) {
                this.getList(1, index)
            },
            async delet() {
                try {
                    this.modal_loading = true;
                    this.modal2 = false;

                    let res = await fetch("article/delete/" + this.id, {});
                    this.modal_loading = false;

                    if (res.code !== 0) {
                        this.$Message.error(res.msg);
                        return;
                    }

                    this.$Message.success('文章删除成功');
                    this.getList(this.list, 1);
                } catch (e) {
                    this.modal_loading = false;
                    this.$Message.error(error);
                }
            }
        }
    }
</script>
<style lang="less">
    .ivu-table-cell {
        overflow: hidden !important;
        text-overflow: ellipsis !important;
        white-space: nowrap !important;
    }

    .pagination {
        float: right;
        margin-top: 2rem;
    }
</style>
