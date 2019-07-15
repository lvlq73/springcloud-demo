package com.springcloud.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 消费者中心
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudConsumerServcieApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConsumerServcieApplication.class, args);
	}
}
