package com.sbip.ch07.util;

import com.sbip.ch07.model.Course;
import com.sbip.ch07.model.LegacyCourseDto;
import com.sbip.ch07.model.ModernCourseDto;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 04 Mar, 2024
 */

public final class CourseUtil {

    public static LegacyCourseDto buildLegacyCourseDto(Course course){

        return new LegacyCourseDto(course.getId(), course.getName(), course.getCategory(), course.getRating(), course.getDescription());
    }

    public static Course from(LegacyCourseDto legacyCourseDto){
        return new Course(
                legacyCourseDto.id(),
                legacyCourseDto.name(),
                legacyCourseDto.category(),
                legacyCourseDto.rating(),
                0.0,
                legacyCourseDto.description()
        );
    }

    public static ModernCourseDto buildModernCourseDto(Course course){
        return new ModernCourseDto(course.getId(), course.getName(), course.getCategory(), course.getRating(), course.getPrice(), course.getDescription());

    }

    public static Course from(ModernCourseDto modernCourseDto){
        return new Course(
                modernCourseDto.id(),
                modernCourseDto.name(),
                modernCourseDto.category(),
                modernCourseDto.rating(),
                modernCourseDto.price(),
                modernCourseDto.description()
        );
    }
}
