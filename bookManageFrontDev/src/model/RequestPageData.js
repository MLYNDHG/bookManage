/**
 @param pageNo:class java.lang.Integer
 @param pageSize:class java.lang.Integer
*/
class pageCondition {
    constructor() {
        this.pageNo = ''
        this.pageSize = ''
    }
}

/**
@param pageCondition:class pageCondition
@param condition:class object
*/
export default class RequestPageData {
    constructor() {
        this.pageCondition = new pageCondition();
        this.condition = ''
    }
}