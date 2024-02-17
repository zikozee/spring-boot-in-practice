package com.sbip.ch06.metrics.couseTracker.service;


import com.sbip.ch06.metrics.couseTracker.model.Course;

import java.util.Optional;

public interface CourseService {

    Course createCourse(Course course);

    Optional<Course> findCourseById(Long courseId);

    Iterable<Course> findAllCourses();

    Course updateCourse(Course course);

    void deleteCourseById(Long courseId);

    Long count();

}
