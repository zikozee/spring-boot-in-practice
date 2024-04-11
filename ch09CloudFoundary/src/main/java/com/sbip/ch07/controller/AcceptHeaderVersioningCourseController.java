package com.sbip.ch07.controller;

import com.sbip.ch07.model.Course;
import com.sbip.ch07.model.LegacyCourseDto;
import com.sbip.ch07.model.ModernCourseDto;
import com.sbip.ch07.repository.CourseRepository;
import com.sbip.ch07.service.CourseService;
import com.sbip.ch07.util.CourseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Course Controller", description = "This REST controller provide services to manage courses in the Course Tracker application")
@RestController
@RequestMapping(path="courses")
@RequiredArgsConstructor
public class AcceptHeaderVersioningCourseController {
	
	private final CourseService courseService;
	private final CourseRepository courseRepository;

	@GetMapping(produces = "application/vnd.sip.app-v1+json")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides all courses available in the course Tracker application")
	public Iterable<LegacyCourseDto> getAllLegacyCourses() {
		return courseService.getCourses();
	}


	@PostMapping(produces = "application/vnd.sip.app-v1+json")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creates a new course in the course Tracker application")
	public LegacyCourseDto createLegacyCourse(@RequestBody LegacyCourseDto legacyCourseDto) {
		return courseService.createCourse(legacyCourseDto);
	}

	@GetMapping(produces = "application/vnd.sip.app-v2+json")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides all courses available in the course Tracker application")
	public Iterable<ModernCourseDto> getAllCourses() {
		List<ModernCourseDto> modernCourseDtoList = new ArrayList<>();

		courseRepository.findAll()
				.forEach(c -> modernCourseDtoList.add(CourseUtil.buildModernCourseDto(c)));
		return modernCourseDtoList;
	}


	@PostMapping(produces = "application/vnd.sip.app-v2+json")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creates a new course in the course Tracker application")
	public ModernCourseDto createCourse(@RequestBody ModernCourseDto modernCourseDto) {

		Course save = courseRepository.save(CourseUtil.from(modernCourseDto));
		return CourseUtil.buildModernCourseDto(save);
	}



}
