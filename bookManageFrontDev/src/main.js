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
import '@/assets/styles/global.css'
import '@/assets/icons'
// import axios from 'axios';

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
    render: h => h(App)//render渲染     h => h(App):语法糖js
}).$mount('#app')      //挂载app到vue上
                        //js id选择器是# class选择器是. 

//配置请求的根路径
// axios.defaults.baseURL = '';
//把配置在axios上的路径挂载到全局
// Vue.prototype.$http = axios