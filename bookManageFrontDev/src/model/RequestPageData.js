/**
 @param pageNo:class java.lang.Integer
 @param pageSize:class java.lang.Integer
*/
class pageCondition {
<<<<<<< HEAD
    constructor() {//ES6构造器
        this.pageNo = 0
        this.pageSize = 0
=======
    constructor() {
        this.pageNo = ''
        this.pageSize = ''
>>>>>>> 4ae46674cf9930c1558ea5f83dee24799d3ae568
    }
}

/**
@param pageCondition:class pageCondition
@param condition:class object
*/
export default class RequestPageData {
    constructor() {
        this.pageCondition = new pageCondition();
<<<<<<< HEAD
        this.condition = '';
=======
        this.condition = ''
>>>>>>> 4ae46674cf9930c1558ea5f83dee24799d3ae568
    }
}