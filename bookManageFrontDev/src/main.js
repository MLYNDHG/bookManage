import Vue from 'vue'
import App from './App.vue'
// import axios from 'axios'
import router from './router/index'
import store from './store/store'
import Vconsole from 'vconsole'
import Element from 'element-ui'
import less from 'less'
import 'element-ui/lib/theme-chalk/index.css';
//添加下面这一行
import '@/assets/icons'
import '@/assets/styles/global.css'
//引入组件
import adminHead from '@/components/adminHead/adminHead.vue' // adminHead组件
// 注册为全局组件
Vue.component('adminHead', adminHead)

Vue.use(Element)
Vue.use(less)

Vue.config.productionTip = false

if (process.env.NODE_ENV === "development") {
    let vConsole = new Vconsole();
    Vue.use(vConsole)
}

Vue.config.productionTip = false

new Vue({
    router,
    store,
    // axios,
    render: h => h(App)
}).$mount('#app')