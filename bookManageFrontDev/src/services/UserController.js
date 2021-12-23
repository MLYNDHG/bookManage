import request from '@/utils/request' 

/*
*@param: file:interface org.springframework.web.multipart.MultipartFile
*/
<<<<<<< HEAD
export function uploadImage(file) {
    return request({
        url: request.baseUrl + `/uploadImage`,
=======
export function saveUser(user) {
    return request({
        url: request.baseUrl + `/saveUser`,
>>>>>>> 3f86c140374b015f225d05d7a82fba088df27bbc
        method: 'POST',
        data:file
    })
}
/*
<<<<<<< HEAD
*@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.mbg.entity.User>
*/
export function selectUserList(requestPageData) {
    return request({
        url: request.baseUrl + `/selectUserList`,
        method: 'POST',
        data:requestPageData
=======
*@param: file:interface org.springframework.web.multipart.MultipartFile
*/
export function uploadImage(file) {
    return request({
        url: request.baseUrl + `/uploadImage`,
        method: 'POST',
        data:file
>>>>>>> 3f86c140374b015f225d05d7a82fba088df27bbc
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
<<<<<<< HEAD
*@param: userVO:class com.Httb.bookManage.model.UserVO
*/
export function updatePassword(userVO) {
    return request({
        url: request.baseUrl + `/updatePassword`,
        method: 'POST',
        data:userVO
=======
*@param: user:class com.Httb.bookManage.mbg.entity.User
*/
export function login(user) {
    return request({
        url: request.baseUrl + `/login`,
        method: 'POST',
        data:user
>>>>>>> 3f86c140374b015f225d05d7a82fba088df27bbc
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