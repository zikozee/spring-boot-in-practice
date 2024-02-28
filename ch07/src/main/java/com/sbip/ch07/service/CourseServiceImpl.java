package com.sbip.ch07.service;

import java.util.Optional;

import com.sbip.ch07.exception.CourseNotFoundException;
import com.sbip.ch07.model.Course;
import com.sbip.ch07.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepository courseRepository;

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course getCourseById(long courseId) {
		return findCourseById(courseId);
	}

	@Override
	public Iterable<Course> getCoursesByCategory(String category) {
		return courseRepository.findAllByCategory(category);
	}

	@Override
	public Iterable<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course updateCourse(Long courseId, Course course) {
		Course existingCourse = findCourseById(courseId);

		existingCourse.setName(course.getName());
		existingCourse.setCategory(course.getCategory());
		existingCourse.setDescription(course.getDescription());
		existingCourse.setRating(course.getRating());
			
		return courseRepository.save(existingCourse);
	}

	@Override
	public void deleteCourses() {
		courseRepository.deleteAll();
	}

	@Override
	public void deleteCourseById(long courseId) {
		findCourseById(courseId);
		courseRepository.deleteById(courseId);
	}


	private Course findCourseById(Long courseId){
		return courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException(String.format("No Course with id %s is available", courseId)));
	}

}
