package com.example.springsecuritysecond;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.springsecuritysecond.mapper")
public class SpringsecuritysecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritysecondApplication.class, args);
    }

}
