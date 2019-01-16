import Main from '../views/Main';

export const page404 = {
    path: '/*',
    name: 'error-404',
    meta: {
        title: '404-页面不存在'
    },
    component: () => import('@/views/error-page/404.vue')
};

export const appRouter = [
    {
        path: '/login',
        name: 'login',
        meta: {
            title: 'Login - 登录'
        },
        component: () => import('@/views/Login.vue')
    },
    {
        path: '/',
        component: Main,
        children: [
            {
                path: 'home',
                name: 'home',
                meta: {title: '主页'},
                component: () => import('@/views/Home.vue')
            },
            {
                path: 'article',
                name: 'article',
                component: () => import('@/views/Article.vue'),
                meta: {title: '发表文章'}
            },
            {
                path: 'article/:id',
                name: 'update',
                component: () => import('@/views/Update.vue'),
                meta: {title: '更新文章'}
            },
            {
                path: 'articleList',
                name: 'articleList',
                component: () => import('@/views/ArticleList.vue'),
                meta: {title: '文章列表'}
            },
            {
                path: 'version',
                name: 'version',
                component: () => import('@/views/Version.vue'),
                meta: {title: '版本信息'}
            }
        ]
    }
]

export default [...appRouter, page404];
