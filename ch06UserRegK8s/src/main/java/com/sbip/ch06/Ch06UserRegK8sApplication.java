package com.sbip.ch06;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class Ch06UserRegK8sApplication implements ApplicationContextAware {

	@Getter
	private static ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Ch06UserRegK8sApplication.class);
		application.setAllowBeanDefinitionOverriding(true);
		application.run(args);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}


}
