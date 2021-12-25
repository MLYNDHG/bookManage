import request from '@/utils/request' 

/*
*@param: file:interface org.springframework.web.multipart.MultipartFile@param: mid:int
*/
export function insertSystemInfoOfExcel(file,mid) {
    return request({
        url: request.baseUrl + `/insertSystemInfoOfExcel`,
        method: 'POST',
        data:file,
        params:{
            mid:mid,

        }
    })
}
/*
*@param: widList:java.util.List<java.lang.Integer>
*/
export function deleteSystemInfoOfList(widList) {
    return request({
        url: request.baseUrl + `/deleteSystemInfoOfList`,
        method: 'POST',
        data:widList
    })
}
/*
*@param: wid:class java.lang.Integer

*/
export function deleteByPrimaryKeySystemInfo(wid) {
    return request({
        url: request.baseUrl + `/deleteByPrimaryKeySystemInfo`,
        method: 'GET',
        params:{
            wid:wid,

        }
    })
}
/*
*@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.mbg.entity.Firstitemsysteminfo>
*/
export function selectSystemInfoList(requestPageData) {
    return request({
        url: request.baseUrl + `/selectSystemInfoList`,
        method: 'POST',
        data:requestPageData
    })
}
/*
*@param: firstitemsysteminfo:class com.Httb.bookManage.mbg.entity.Firstitemsysteminfo
*/
export function insertSystemInfo(firstitemsysteminfo) {
    return request({
        url: request.baseUrl + `/insertSystemInfo`,
        method: 'POST',
        data:firstitemsysteminfo
    })
}