/**@param id:class java.lang.Integer
@param username:class java.lang.String
@param nickname:class java.lang.String
@param password:class java.lang.String
@param head:class java.lang.String
@param tel:class java.lang.String
@param email:class java.lang.String
@param status:class java.lang.Boolean
@param isSuper:class java.lang.Boolean
*/

//ES6的语法,类和对象面向对象语言
export default class User {
    constructor() {
        this.id = ''
        this.username = ''
        this.nickname = ''
        this.password = ''
        this.head = ''
        this.tel = ''
        this.email = ''
        this.status = ''
        this.isSuper = ''
    }
}