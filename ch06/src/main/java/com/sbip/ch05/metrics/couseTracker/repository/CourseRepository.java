package com.sbip.ch05.metrics.couseTracker.repository;


import com.sbip.ch05.metrics.couseTracker.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
