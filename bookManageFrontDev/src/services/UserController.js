import request from '@/utils/request' 

/*
*@param: user:class com.Httb.bookManage.mbg.entity.User
*/
export function save(user) {
    return request({
        url: request.baseUrl + `/save`,
        method: 'POST',
        data:user
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