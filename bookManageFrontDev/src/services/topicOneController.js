import request from '@/utils/request' 

/*
*
*/
export function selectTypeList() {
    return request({
        url: request.baseUrl + `/selectTypeList`,
        method: 'GET',
        params:{
            
        }
    })
}
/*
*@param: resourcesVO:class com.Httb.bookManage.model.ResourcesVO
*/
export function selectResourceBasicList(resourcesVO) {
    return request({
        url: request.baseUrl + `/selectResourceBasicList`,
        method: 'POST',
        data:resourcesVO
    })
}