import request from '@/utils/request' 

/*
*@param: rocketmq:class com.Httb.bookManage.mbg.entity.Rocketmq
*/
export function saveRocketmq(rocketmq) {
    return request({
        url: request.baseUrl + `/saveRocketmq`,
        method: 'POST',
        data:rocketmq
    })
}
/*
*@param: rid:class java.lang.Integer

*/
export function selectRocketmqByResourcesId(rid) {
    return request({
        url: request.baseUrl + `/selectRocketmqByResourcesId`,
        method: 'GET',
        params:{
            rid:rid,

        }
    })
}