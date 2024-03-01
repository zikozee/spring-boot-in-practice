package com.sbip.ch07.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Mar, 2024
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${app.description}") String appDescription,
                                 @Value("${app.version}") String appVersion){

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Course Tracker API")
                                .version(appVersion)
                                .description(appDescription)
                                .termsOfService("http://swagger.io/terms/")
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                                .url("http://springdoc.org")
                                )
                );
    }
}
