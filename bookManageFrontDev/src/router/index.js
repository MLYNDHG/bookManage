import Vue from "vue";
import Router from "vue-router";
import Login from "@/views/admin/login.vue";
import AdminHome from "@/views/admin/adminHome.vue"
import BookManage from "@/views/admin/bookManage.vue"
import UserList from "@/views/userManage/userList.vue"
import Topic1 from "@/views/assess/topic1.vue"
import Topic2 from "@/views/assess/topic2.vue"
import Topic3 from "@/views/assess/topic3.vue"

Vue.use(Router);

const originalPush = Router.prototype.push

Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router = new Router({
    routes: [{
        path: "/",
        component: Login,
        children: []},
        {
            path: "/home",
            component: AdminHome,
            children: [
                {
                    path: "/home/bookmanage",
                    component: BookManage
                },
                {
                    path:"/home/userlist",
                    component:UserList
                },
                {
                    path:"/home/topic1",
                    component:Topic1
                },
                {
                    path:"/home/topic2",
                    component:Topic2
                },
                {
                    path:"/home/topic3",
                    component:Topic3
                },
            ],
    },
]
});

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆

// router.beforeEach((to, from, next) => {
    // if (to.path === '/login') {
    //     next();
    // }else{
    //     let token = localStorage.getItem('Authorization');

    //     if (token === null || token === '') {
    //         next('/login');
    //     } else {
    //         next();
    //     }
    // }
// });
export default router;
