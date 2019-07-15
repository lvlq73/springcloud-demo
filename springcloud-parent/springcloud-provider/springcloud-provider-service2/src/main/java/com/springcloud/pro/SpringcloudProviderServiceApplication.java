package com.springcloud.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 提供者服务中心
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudProviderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudProviderServiceApplication.class, args);
	}
}
