package com.hl.base.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * service服务入口类
 * @author liheyu
 * @date 2018-09-26
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages="com.hl.base.service.impl")
@MapperScan("com.hl.base.service.dao")
public class BaseServiceApplication {

	/**
	 * 入口函数
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BaseServiceApplication.class, args);
	}
}
