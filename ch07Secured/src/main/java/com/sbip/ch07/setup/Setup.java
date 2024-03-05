package com.sbip.ch07.setup;

import com.sbip.ch07.model.Course;
import com.sbip.ch07.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 05 Mar, 2024
 */

@Configuration
public class Setup {

    @Bean
    CommandLineRunner createCourse(CourseRepository repository) {
        return (args) -> {
            Course spring = Course.builder().name("Spring")
                    .price(50.0)
                    .category("Spring")
                    .description("Spring baby")
                    .rating(4)
                    .build();

            Course python = Course.builder().name("bill")
                    .price(50.0)
                    .category("Python")
                    .description("Python baby")
                    .rating(4)
                    .build();

            repository.saveAll(List.of(spring, python));
        };
    }
}
