/**@param sids:java.util.List<java.lang.Integer>
@param flowSources:java.util.List<com.Httb.bookManage.mbg.entity.Sourcelist>
@param flowNotSources:java.util.List<com.Httb.bookManage.mbg.entity.Sourcelist>
@param id:class java.lang.Integer
@param name:class java.lang.String
@param flowpattern:class java.lang.String
@param thresholdtype:class java.lang.String
@param threshold:class java.lang.Integer
@param thresholdpattern:class java.lang.String
@param floweffect:class java.lang.String
@param requestsource:class java.lang.String
@param status:class java.lang.Boolean
@param time:class java.util.Date
*/
export default class FlowRuleVO {
    constructor() {
        this.sids = ''
        this.flowSources = ''
        this.flowNotSources = ''
        this.id = ''
        this.name = ''
        this.flowpattern = ''
        this.thresholdtype = ''
        this.threshold = ''
        this.thresholdpattern = ''
        this.floweffect = ''
        this.requestsource = ''
        this.status = ''
        this.time = ''
    }
}