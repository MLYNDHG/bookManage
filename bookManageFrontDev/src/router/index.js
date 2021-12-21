import Vue from "vue";
import Router from "vue-router";
import Login from "@/views/admin/login.vue";
import AdminHome from "@/views/admin/adminHome.vue"
import BookManage from "@/views/admin/bookManage.vue"
import BookInfo from "@/views/admin/bookInfo.vue"
import adminIndex from "@/views/admin/adminIndex.vue"

Vue.use(Router);

const originalPush = Router.prototype.push

Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router = new Router({
    routes: [{
<<<<<<< HEAD
        path: "/login",
        component: Login,
        children: [

        ]},
        {
            path: "/home",
            component: AdminHome,
            children: [{
                path: "/",
                component: adminIndex
            },
                {
                    path: "/home/bookmanage",
                    component: BookManage
                },
                {
                    path: "/home/bookinfo",
                    component: BookInfo
                }
            ]
=======
        path: "/",
        component: login,
        children: [

        ]
    },
    {
        path: "/home",
        component: adminHome,

>>>>>>> 0a7112e9a205acc03463093ae46e4df173f90705
    }]
});

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆

// router.beforeEach((to, from, next) => {
<<<<<<< HEAD
    // if (to.path === '/login') {
    //     next();
    // }else{
    //     let token = localStorage.getItem('Authorization');

    //     if (token === null || token === '') {
    //         next('/login');
    //     } else {
    //         next();
    //     }
=======
//     if (to.path === '/login') {
//         next();
//     } else {
//         let token = localStorage.getItem('Authorization');

        //         if (token === null || token === '') {
        //             next('/login');
        //         } else {
        //             next();
        //         }
        //     }
>>>>>>> 0a7112e9a205acc03463093ae46e4df173f90705
    // }
// });
export default router;