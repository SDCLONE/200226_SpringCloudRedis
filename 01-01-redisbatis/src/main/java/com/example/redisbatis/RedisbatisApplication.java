package com.example.redisbatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.redisbatis.dao")
public class RedisbatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisbatisApplication.class, args);
    }

}
