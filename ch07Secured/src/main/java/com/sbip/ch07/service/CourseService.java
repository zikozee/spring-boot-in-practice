package com.sbip.ch07.service;


import com.sbip.ch07.model.LegacyCourseDto;


public interface CourseService {

	LegacyCourseDto createCourse(LegacyCourseDto legacyCourseDto);

	LegacyCourseDto getCourseById(long courseId);
	
	Iterable<LegacyCourseDto> getCoursesByCategory(String category);
	
	Iterable<LegacyCourseDto> getCourses();

	LegacyCourseDto updateCourse(Long courseId, LegacyCourseDto course);
	
	void deleteCourseById(long courseId);

	void deleteCourses();
}
