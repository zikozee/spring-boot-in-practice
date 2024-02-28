package com.sbip.ch07.service;


import com.sbip.ch07.model.Course;

import java.util.Optional;

public interface CourseService {

	Course createCourse(Course course);
	
	Course getCourseById(long courseId);
	
	Iterable<Course> getCoursesByCategory(String category);
	
	Iterable<Course> getCourses();
	
	Course updateCourse(Long courseId, Course course);
	
	void deleteCourseById(long courseId);

	void deleteCourses();
}
