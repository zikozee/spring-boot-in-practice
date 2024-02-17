package com.sbip.ch06;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class Ch04Application implements ApplicationContextAware {

	@Getter
	private static ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(Ch04Application.class, args);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

}
