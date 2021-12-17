// 包含多个事件回调函数的对象
// 通过执行: commit()来触发 mutation 的调用, 间接更新 state
// 谁来触发: 组件中: $store.dispatch(‘action 名称’, data1) // ‘zzz’
// 可以包含异步代码(定时器, ajax) 


//调用mutations
export const setUser = ({ commit }, user) => {
    commit("userStatus", user);
}
export const setToken = ({ commit }, token) => {
    commit("setToken", token);
}