package com.zikozee.booting.configurationpoperties;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author: Ezekiel Eromosei
 * @created: 13 February 2023
 */

@Configuration
@RequiredArgsConstructor
public class AppPropertiesRunner {
    private final AppProperties appProperties;

    public AppProperties getAppProperties(){
        return appProperties;
    }
}
