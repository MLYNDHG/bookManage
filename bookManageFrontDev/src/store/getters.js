// 用于获取store.js文件中的参数
// 包含多个计算属性(get)的对象
// 谁来读取: 组件中: $store.getters.xxx

export const isLogin = state => state.isLogin;
export const userName = state => state.userName;
export const roleName = state => state.roleName;