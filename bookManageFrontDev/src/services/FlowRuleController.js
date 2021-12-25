import request from '@/utils/request'

/*
*@param: id:class java.lang.Integer

*/
export function deleteFlowRule(id) {
    return request({
        url: request.baseUrl + `/deleteFlowRule`,
        method: 'GET',
        params: {
            id: id,

        }
    })
}
/*
 *@param: flowRuleVO:class com.Httb.bookManage.model.FlowRuleVO
 */
export function saveFlowRule(flowRuleVO) {
    return request({
        url: request.baseUrl + `/saveFlowRule`,
        method: 'POST',
        // dataType: "json",
        // headers: {
        //     "Content-Type": "application/json"
        // },
        data: flowRuleVO
    })
}
/*
 *@param: requestPageData:com.Httb.bookManage.util.RequestPageData<com.Httb.bookManage.mbg.entity.Flowrule>
 */
export function selectFlowRuleList(requestPageData) {
    return request({
        url: request.baseUrl + `/selectFlowRuleList`,
        method: 'POST',
        data: requestPageData
    })
}