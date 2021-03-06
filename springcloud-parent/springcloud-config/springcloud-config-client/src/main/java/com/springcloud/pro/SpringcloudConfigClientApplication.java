package com.springcloud.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false) //关闭自动注册功能，也可以在配置文件设置，如果要使用高可用服务配置,记得改成true
public class SpringcloudConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigClientApplication.class, args);
	}

}
