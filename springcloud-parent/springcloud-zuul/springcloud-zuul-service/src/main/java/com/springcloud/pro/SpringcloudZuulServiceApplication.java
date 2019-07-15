package com.springcloud.pro;

import com.springcloud.pro.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class SpringcloudZuulServiceApplication {

	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudZuulServiceApplication.class, args);
	}
}
