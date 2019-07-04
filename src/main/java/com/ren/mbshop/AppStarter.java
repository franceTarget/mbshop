package com.ren.mbshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@MapperScan(basePackages = { "com.ren.mbshop.dao" })
@EnableCaching
public class AppStarter {
	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}
}
