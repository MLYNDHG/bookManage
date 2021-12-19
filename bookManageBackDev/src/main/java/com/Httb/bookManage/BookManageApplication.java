package com.Httb.bookManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.Httb.bookManage.dao","com.Httb.bookManage.mbg.dao"})
public class BookManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManageApplication.class, args);
    }

}
