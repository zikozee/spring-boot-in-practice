package com.sbip.ch06;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class Ch06Application implements ApplicationContextAware {

	@Getter
	private static ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(Ch06Application.class, args);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}


}
