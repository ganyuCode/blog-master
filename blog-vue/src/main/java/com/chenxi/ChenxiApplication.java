package com.chenxi;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ganyu.mapper")
@SpringBootApplication(scanBasePackages = {"com.ganyu", "com.chenxi"})
public class ChenxiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChenxiApplication.class,args);
    }
}
