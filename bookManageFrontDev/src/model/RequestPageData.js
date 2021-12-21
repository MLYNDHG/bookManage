/**
 @param pageNo:class java.lang.Integer
 @param pageSize:class java.lang.Integer
*/
class pageCondition {
    constructor() {//ES6构造器
        this.pageNo = 0
        this.pageSize = 0
    }
}

/**
@param pageCondition:class pageCondition
@param condition:class object
*/
export default class RequestPageData {
    constructor() {
        this.pageCondition = new pageCondition();
        this.condition = '';
    }
}