import request from '@/utils/request' 

/*
*@param: file:interface org.springframework.web.multipart.MultipartFile
*/
export function uploadImage(file) {
    return request({
        url: request.baseUrl + `/uploadImage`,
        method: 'POST',
        data:file
    })
}
/*
*@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.mbg.entity.User>
*/
export function selectUserList(requestPageData) {
    return request({
        url: request.baseUrl + `/selectUserList`,
        method: 'POST',
        data:requestPageData
    })
}
/*
*@param: user:class com.Httb.bookManage.mbg.entity.User
*/
export function login(user) {
    return request({
        url: request.baseUrl + `/login`,
        method: 'POST',
        data:user
    })
}
/*
*@param: userVO:class com.Httb.bookManage.model.UserVO
*/
export function updatePassword(userVO) {
    return request({
        url: request.baseUrl + `/updatePassword`,
        method: 'POST',
        data:userVO
    })
}
/*
*@param: user:class com.Httb.bookManage.mbg.entity.User
*/
export function saveUser(user) {
    return request({
        url: request.baseUrl + `/saveUser`,
        method: 'POST',
        data:user
    })
}