import {request,baseUrl} from '@/utils/request' 

/*
*@param: id:class java.lang.Integer

*/
export function deleteBook(id) {
    return request({
        url: baseUrl + '/deleteBook/'+id,
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
        url: baseUrl + `/saveBook`,
        method: 'POST',
        data:book
    })
}
/*
*@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.model.BookVO>
*/
export function selectBookList(requestPageData) {
    return request({
        url: baseUrl + `/selectBookList`,
        method: 'POST',
        data:requestPageData
    })
}