package com.Httb.bookManage.exception;

/**
 * 上传文件时
 * 文件未提交异常
 */
public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
