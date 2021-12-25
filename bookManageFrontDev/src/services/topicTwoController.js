import request from '@/utils/request' 

/*
*@param: resources:class com.Httb.bookManage.mbg.entity.Resources
*/
export function saveResourcesBasic(resources) {
    return request({
        url: request.baseUrl + `/saveResourcesBasic`,
        method: 'POST',
        data:resources
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
/*
*@param: id:class java.lang.Integer

*/
export function deleteResources(id) {
    return request({
        url: request.baseUrl + `/deleteResources`,
        method: 'GET',
        params:{
            id:id,

        }
    })
}
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