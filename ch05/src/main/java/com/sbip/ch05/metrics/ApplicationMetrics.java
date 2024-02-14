package com.sbip.ch05.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 25 Jan, 2024
 */

@Configuration
public class ApplicationMetrics {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(){ //http://localhost:8081/sbip/metrics/jvm.gc.pause  you will see the tag added
        return registry -> registry.config()
                .commonTags("application", "course-tracker");
    }
}
