package com.sbip.ch06.setup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 24 Feb, 2024
 */

//@Component
//@RequiredArgsConstructor
//public class SetupManagingLivenessAndReadiness implements CommandLineRunner {
//
//    private final ApplicationContext context;
//
//    // manually ensuring some work is done before bring up liveness and readiness probe
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        AvailabilityChangeEvent.publish(context, ReadinessState.REFUSING_TRAFFIC);
//
//        //OR
//
//        AvailabilityChangeEvent.publish(context, LivenessState.BROKEN);
//
//
//        //do some work
//        Thread.sleep(Duration.ofSeconds(10));
//
//
//        AvailabilityChangeEvent.publish(context, ReadinessState.ACCEPTING_TRAFFIC);
//
//        //OR
//
//        AvailabilityChangeEvent.publish(context, LivenessState.CORRECT);
//
//    }
//
//    // call this http://localhost:8080/sbip/health and monitor the livenessState and monitorState
//}

//@Configuration
//public class SetupManagingLivenessAndReadiness {
//
//
//    // manually ensuring some work is done before bring up liveness and readiness probe
//
//    @Bean
//    public ApplicationRunner runner(ApplicationContext context) {
//
//        return args -> {
//            AvailabilityChangeEvent.publish(context, ReadinessState.REFUSING_TRAFFIC);
//
//            //OR
//
//            AvailabilityChangeEvent.publish(context, LivenessState.BROKEN);
//
//
//            //do some work
//            Thread.sleep(Duration.ofSeconds(10));
//
//
//            AvailabilityChangeEvent.publish(context, ReadinessState.ACCEPTING_TRAFFIC);
//
//            //OR
//
//            AvailabilityChangeEvent.publish(context, LivenessState.CORRECT);
//
//        };
//
//        // call this http://localhost:8080/sbip/health and monitor the livenessState and monitorState
//    }
//}

@Slf4j
@Configuration
public class SetupManagingLivenessAndReadiness {


    // manually ensuring some work is done before bring up liveness and readiness probe

    @Bean
    public CommandLineRunner runner(ApplicationContext context) {

        return args -> {
            log.info(">>>>>>>>disabling readiness to {}<<<<<<<<", ReadinessState.REFUSING_TRAFFIC.name());
            AvailabilityChangeEvent.publish(context, ReadinessState.REFUSING_TRAFFIC);

            //OR
            log.info(">>>>>>>>disabling liveness to {}<<<<<<<<", LivenessState.BROKEN.name());
            AvailabilityChangeEvent.publish(context, LivenessState.BROKEN);


            //do some work
            Thread.sleep(Duration.ofSeconds(10));

            log.info(">>>>>>>>enabling readiness to {}<<<<<<<<", ReadinessState.ACCEPTING_TRAFFIC.name());
            AvailabilityChangeEvent.publish(context, ReadinessState.ACCEPTING_TRAFFIC);

            //OR
            log.info(">>>>>>>>enabling liveness to {}<<<<<<<<", LivenessState.CORRECT.name());
            AvailabilityChangeEvent.publish(context, LivenessState.CORRECT);

        };

        // call this http://localhost:8080/sbip/health and monitor the livenessState and monitorState
    }
}


