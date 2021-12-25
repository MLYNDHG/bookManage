import request from '@/utils/request' 

/*
*@param: redis:class com.Httb.bookManage.mbg.entity.Redis
*/
export function saveRedis(redis) {
    return request({
        url: request.baseUrl + `/saveRedis`,
        method: 'POST',
        data:redis
    })
}
/*
*@param: rid:class java.lang.Integer

*/
export function selectRedisByResourcesId(rid) {
    return request({
        url: request.baseUrl + `/selectRedisByResourcesId`,
        method: 'GET',
        params:{
            rid:rid,

        }
    })
}