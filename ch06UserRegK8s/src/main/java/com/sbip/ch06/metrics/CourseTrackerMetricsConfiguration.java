package com.sbip.ch06.metrics;

import com.sbip.ch06.Ch06UserRegK8sApplication;
import com.sbip.ch06.metrics.couseTracker.service.DefaultCourseService;
import io.micrometer.core.instrument.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 02 Feb, 2024
 */

@Configuration
@RequiredArgsConstructor
public class CourseTrackerMetricsConfiguration {

    @Bean(destroyMethod = "")
    public Counter createCourseCounter(MeterRegistry meterRegistry){
        return Counter.builder("api.courses.created.count")
                .description("Total number of courses created")
                .register(meterRegistry);
    }

    @Bean(destroyMethod = "")
    public Gauge createCourseGauge(MeterRegistry meterRegistry){
        return Gauge.builder("api.courses.created.gauge",
                        () -> Ch06UserRegK8sApplication.getContext().getBean("defaultCourseService", DefaultCourseService.class).count())
                .description("Total number of courses created")
                .register(meterRegistry);
    }

    @Bean(destroyMethod = "")
    public Timer createCourseTimer(MeterRegistry meterRegistry){
        return Timer.builder("api.courses.creation.time")
                .description("Course creation time")
                .register(meterRegistry);
    }

    @Bean(destroyMethod = "")
    public DistributionSummary createdDistributionSummary(MeterRegistry meterRegistry){
        return DistributionSummary.builder("api.courses.distribution.summary")
                .description("Rating distribution summary")
                .register(meterRegistry);
    }
}
