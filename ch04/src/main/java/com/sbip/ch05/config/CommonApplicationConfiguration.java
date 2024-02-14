package com.sbip.ch05.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 21 Jan, 2024
 */

@Slf4j
@Configuration
public class CommonApplicationConfiguration {


    @Bean
    @Conditional(CustomCondition.class)
    public String loadingCondition(){
      log.info("Custom Condition 1 loaded");
      return "loaded";
    }

    @Bean
    @ConditionalOnProperty("testing.condition2")
    public String loadingCondition2(){
        log.info("Custom Condition 2 loaded");
        return "loaded";
    }
}
