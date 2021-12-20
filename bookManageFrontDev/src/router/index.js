import Vue from "vue";
import Router from "vue-router";
import login from "@/views/admin/login.vue";
import adminHome from "@/views/admin/adminHome.vue"

Vue.use(Router);

const router = new Router({
    routes: [{
        path: "/login",
        component: login,
        children: [

        ]},
        {
            path: "/home",
            component: adminHome,
        
    }]
});

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
// router.beforeEach((to, from, next) => {
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
// });
export default router;