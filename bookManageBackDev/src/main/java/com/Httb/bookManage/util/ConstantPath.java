package com.Httb.bookManage.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 用于存放项目中的常量
 */
@Component
public class ConstantPath {

    // 照片访问网络地址
    @Value("${image.relative.path}")
    private String image_path;

    // 图片存放的绝对路径
    public static final String DIR = "D:\\Vue\\bookManage\\image_resource\\";

    // 读取图片的网络路径
    public static String PATH;

    // 给PATH赋值
    @PostConstruct
    public void init() {
        PATH = this.image_path;
    }
}
