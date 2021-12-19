package com.Httb.bookManage.exception;

/**
 * 用户存在异常，用于用户注册
 */
public class UserExistsException extends RuntimeException {

    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }
}
