package com.sbip.ch06.metrics.couseTracker.service;

import com.sbip.ch06.metrics.couseTracker.model.Course;
import com.sbip.ch06.metrics.couseTracker.repository.CourseRepository;
//import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@ManagedResource
public class DefaultCourseService implements CourseService, NotificationPublisherAware {

    private final CourseRepository courseRepository;
//    private final Counter createCourseCounter;
    private final Timer createCourseTimer;
    private final DistributionSummary distributionSummary;
    private  NotificationPublisher publisher;


    @SneakyThrows
    public Course createCourse(Course course) {
//        createCourseCounter.increment();

        distributionSummary.record(course.getRating());
        Course createdCourse = createCourseTimer.recordCallable(() -> courseRepository.save(course));
        publisher.sendNotification(new Notification("new Course", createdCourse.getRating(), createdCourse.getId(), "new course created"));
        return createdCourse;
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

    @ManagedOperation
    public Long count(){
        long size = this.findAllCourses().spliterator().estimateSize();
        publisher.sendNotification(new Notification("Course Size", size, size, System.currentTimeMillis(), "size fetched successfully"));
        return size;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        publisher = notificationPublisher;
    }
}
