package com.sbip.ch06.custominfoIndicator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 23 Jan, 2024
 */

@Component
public class CourseInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {

        Map<String, String> map1 = Map.of("name", "Rapid Spring Boot Application Development", "rating", "4");
        Map<String, String> map2 = Map.of("name", "Getting Started with Spring Security DSL", "rating", "5");
        Map<String, String> map3 = Map.of("name", "Getting Started with Spring Cloud Kubernetes", "rating", "3");
        List<Map<String, String>> courses = new ArrayList<>(List.of(map1, map2, map3));
        builder.withDetail("courses", courses);
    }
}
