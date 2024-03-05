package com.sbip.ch07.repository;


import com.sbip.ch07.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
	
	Iterable<Course> findAllByCategory(String category);
	Iterable<Course> findByName(String name);
}
