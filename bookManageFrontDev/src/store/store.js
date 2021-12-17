import Vue from 'vue'
import Vuex from 'vuex'
import * as getters from './getters'
import * as mutations from './mutations'
import * as actions from './actions'
Vue.use(Vuex);
// 每一个 Vuex 应用的核心就是 store（仓库）。
// “store”基本上就是一个容器，它包含着应用中大部分的状态 (state)，
// state即Vuex中的基本数据并且它应该是唯一的
const store = new Vuex.Store({
    //存储用户登录信息的一个状态
    state: {
        userName: null, //当前用户
        roleName: null, //用户相关的信息
        isLogin: false, //登录状态
        token: '', //用户登录成功后持有的token
        // 存储token
        Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : ''
    },
    //关联着其他三个目录，目的松耦合
    getters,
    mutations,
    actions
})
export default store;