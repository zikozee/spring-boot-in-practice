package com.sbip.ch06.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

// this ensures event is published asynchronously
    // by default publishers and listeners are executed on the same thread
@Configuration  // or simply place async on the listener
public class EventConfiguration {
    public ApplicationEventMulticaster simpleApplicationEventMultiCaster(){
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}
