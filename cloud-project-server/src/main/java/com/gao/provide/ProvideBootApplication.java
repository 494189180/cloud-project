package com.gao.provide;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.gao"})
@MapperScan("com.gao.mapper")
public class ProvideBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProvideBootApplication.class, args);
    }
}
