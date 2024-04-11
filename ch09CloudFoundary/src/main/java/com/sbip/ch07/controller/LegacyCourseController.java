package com.sbip.ch07.controller;

import com.sbip.ch07.model.Course;
import com.sbip.ch07.model.LegacyCourseDto;
import com.sbip.ch07.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Course Controller", description = "This REST controller provide services to manage courses in the Course Tracker application")
@RestController
@RequestMapping(path="courses/v1")
@RequiredArgsConstructor
public class LegacyCourseController {
	
	private final CourseService courseService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides all courses available in the course Tracker application")
	public Iterable<LegacyCourseDto> getAllCourses() {
		return courseService.getCourses();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides course details for the supplied course id from the course Tracker application")
	public LegacyCourseDto getCourseById(@PathVariable("id") long courseId) {
		return courseService.getCourseById(courseId);
	}
	
	@GetMapping("category/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides course details for the supplied course category from the course Tracker application")
	public Iterable<LegacyCourseDto> getCourseByCategory(@PathVariable("name") String category) {
		return courseService.getCoursesByCategory(category);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creates a new course in the course Tracker application")
	public LegacyCourseDto createCourse(@RequestBody LegacyCourseDto legacyCourseDto) {
		return courseService.createCourse(legacyCourseDto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Updates the course details in the Course Tracker Application for the supplied course id")
	public LegacyCourseDto updateCourse(@PathVariable("id") long courseId, @RequestBody LegacyCourseDto legacyCourseDto) {
		return courseService.updateCourse(courseId, legacyCourseDto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes the course details for the supplied course id from the supplied course id")
	void deleteCourseById(@PathVariable("id") long courseId) {
		courseService.deleteCourseById(courseId);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes all courses from the supplied Course Tracker Application")
	void deleteCourses() {
		courseService.deleteCourses();
	}

}
