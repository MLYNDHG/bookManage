package com.wtth.bookManage;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan({"com.wtth.bookManage.mbg.dao","com.wtth.bookManage.dao"})
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
