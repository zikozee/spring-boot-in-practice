package com.sbip.ch04.customfailureanalyzer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 21 Jan, 2024
 */

@Component
public class UrlAccessibilityHandler {

    @Value("${api.url:https://dog.ceo/}")
    public String url;

    @EventListener(classes = ContextRefreshedEvent.class) // the Event Listener is invoked once Spring Boot Publishes ContextRefreshedEvent
    public void listen(){
        // throw this exception as we assume the sight is not reachable
//        throw new UrlNotAccessibleException(url);  // comment out to disable
    }
}
