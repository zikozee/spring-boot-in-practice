package com.sbip.ch08rsocketintro.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 26 Mar, 2024
 */

@Data
@NoArgsConstructor
public class Course {

    private UUID courseId = UUID.randomUUID();
    private long created = Instant.now().getEpochSecond();
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }
}
