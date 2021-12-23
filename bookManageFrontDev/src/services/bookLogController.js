import request from '@/utils/request' 

/*
*@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.model.BookLogVO>
*/
export function selectBookLogList(requestPageData) {
    return request({
        url: request.baseUrl + `/selectBookLogList`,
        method: 'POST',
        data:requestPageData
    })
}
/*
*@param: bookLogVO:class com.Httb.bookManage.model.BookLogVO
*/
export function saveBookLog(bookLogVO) {
    return request({
        url: request.baseUrl + `/saveBookLog`,
        method: 'POST',
        data:bookLogVO
    })
}