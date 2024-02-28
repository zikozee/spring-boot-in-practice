package com.sbip.ch07.controller;

import java.util.Optional;

import com.sbip.ch07.model.Course;
import com.sbip.ch07.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="courses")
@RequiredArgsConstructor
public class CourseController {
	
	private final CourseService courseService;

	@GetMapping
	public Iterable<Course> getAllCourses() {
		return courseService.getCourses();
	}
	
	@GetMapping("{id}")
	public Course getCourseById(@PathVariable("id") long courseId) {
		return courseService.getCourseById(courseId);
	}
	
	@GetMapping("category/{name}")
	public Iterable<Course> getCourseByCategory(@PathVariable("name") String category) {
		return courseService.getCoursesByCategory(category);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}
	
	@PutMapping("{id}")
	public Course updateCourse(@PathVariable("id") long courseId, @RequestBody Course course) {
		return courseService.updateCourse(courseId, course);
	}
	
	@DeleteMapping("{id}")
	void deleteCourseById(@PathVariable("id") long courseId) {
		courseService.deleteCourseById(courseId);
	}
	
	@DeleteMapping
	void deleteCourses() {
		courseService.deleteCourses();
	}

}
