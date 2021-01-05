package com.gao.consumer;

import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackages = {"com.gao.feign"})
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerBootApplication.class, args);
    }
    @Bean
    Retryer feignRetryer() {
        return  new Retryer.Default();
    }
}
