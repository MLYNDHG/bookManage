import request from '@/utils/request' 

/*
*@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.mbg.entity.Firstitemsystem>
*/
export function selectSystemList(requestPageData) {
    return request({
        url: request.baseUrl + `/selectSystemList`,
        method: 'POST',
        data:requestPageData
    })
}
/*
*@param: firstitemsystem:class com.Httb.bookManage.mbg.entity.Firstitemsystem
*/
export function insertSystem(firstitemsystem) {
    return request({
        url: request.baseUrl + `/insertSystem`,
        method: 'POST',
        data:firstitemsystem
    })
}
/*
*@param: file:interface org.springframework.web.multipart.MultipartFile
*/
export function insertSystemOfExcel(file) {
    return request({
        url: request.baseUrl + `/insertSystemOfExcel`,
        method: 'POST',
        data:file
    })
}

