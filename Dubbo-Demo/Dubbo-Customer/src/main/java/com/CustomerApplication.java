package com;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author ping.dai
 */
@SpringBootApplication
@EnableDubbo
@EnableHystrix
public class CustomerApplication {
    public static void main(String[] args) {
        System.out.println(SpringApplication.run(CustomerApplication.class,args));
    }
}
