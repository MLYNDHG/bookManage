import request from '@/utils/request'

/*
*@param: uid:int

*/
export function getUserInfoById(uid) {
    return request({
        url: request.baseUrl + `/getUserInfoById`,
        method: 'GET',
        params: {
            uid: uid,

        }
    })
}
/*
*@param: password:class java.lang.String
@param: username:class java.lang.String

*/
export function login(password, username) {
    return request({
        url: request.baseUrl + `/login`,
        method: 'GET',
        params: {
            password: password,
            username: username,

        }
    })
}