package com.sbip.ch06.metrics.couseTracker.service;

import com.sbip.ch06.metrics.couseTracker.model.Course;
import com.sbip.ch06.metrics.couseTracker.repository.CourseRepository;
//import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultCourseService implements CourseService {

    private final CourseRepository courseRepository;
//    private final Counter createCourseCounter;
    private final Timer createCourseTimer;
    private final DistributionSummary distributionSummary;


    @SneakyThrows
    public Course createCourse(Course course) {
//        createCourseCounter.increment();

        distributionSummary.record(course.getRating());
        return createCourseTimer.recordCallable(() -> courseRepository.save(course));
    }

    public Optional<Course> findCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public Long count(){
        return this.findAllCourses().spliterator().estimateSize();
    }
}
