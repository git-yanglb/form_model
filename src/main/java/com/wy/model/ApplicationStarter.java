package com.wy.model;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wy.model.mapper")
public class ApplicationStarter {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		logger.info("Applicaiton started!");
	}

}
