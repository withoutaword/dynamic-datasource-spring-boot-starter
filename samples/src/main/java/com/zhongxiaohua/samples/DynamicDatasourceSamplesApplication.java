package com.zhongxiaohua.samples;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class DynamicDatasourceSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDatasourceSamplesApplication.class, args);
	}

}
