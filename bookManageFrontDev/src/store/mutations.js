// 包含多个直接更新 state 的方法(回调函数)的对象
// 由action 中的 commit(‘mutation 名称’)来触发
// 只能包含同步的代码, 不能写异步代码

//更改用户状态信息
export const userStatus = (state, user) => {
        //判断用户是否存在
        if (user != null) {
            state.userName = user;
            // state.roleName = JSON.parse(user).roleName;
            // state.isLogin = true;
        } else if (user == null) {
            //登出时清空sessionStroage里面的参数
            sessionStorage.setItem("user", null);
            sessionStorage.setItem("userToken", '');
            state.currentUser = null;
            state.isLogin = false;
            state.assign = '';
        }
    }
    //更改token
export const setToken = (state, token) => {
        if (token != null) {
            state.token = token;
        } else {
            state.token = '';
        }
    }
    //更改list
export const setList = (state, route) => {
    if (route == null || (state.list.some(item=>item.path == route.path))) {
        return
    } 
    state.list.push({
        name: route.name,
        title: route.title,
        path: route.path,
        close: route.close
    });
}

//删除List
export const outList = (state, name) => {
    if (state.list != null) {
        state.list =  state.list.filter(item => item.name !== name);
    } 
}
    // 修改token，并将token存入localStorage
export const changeLogin = (state, token) => {
        state.Authorization = token;
        sessionStorage.setItem('Authorization', token);
    }
    // 修改token，并将token存入localStorage
    // export const realSetToken = (state, user) => {
    //     state.Authorization = user.Authorization;
    //     localStorage.setItem('Authorization', user.Authorization);
    // }