import request from '@/utils/request' 

/*
*@param: id:class java.lang.Integer

*/
export function deleteBook(id) {
    return request({
        url: request.baseUrl + `/deleteBook`,
        method: 'GET',
        params:{
            id:id,

        }
    })
}
/*
*@param: book:class com.Httb.bookManage.mbg.entity.Book
*/
export function saveBook(book) {
    return request({
        url: request.baseUrl + `/saveBook`,
        method: 'POST',
        data:book
    })
}
/*
*@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.model.BookVO>
*/
export function selectBookList(requestPageData) {
    return request({
        url: request.baseUrl + `/selectBookList`,
        method: 'POST',
        data:requestPageData
    })
}