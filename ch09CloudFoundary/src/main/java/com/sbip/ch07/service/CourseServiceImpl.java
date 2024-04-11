package com.sbip.ch07.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sbip.ch07.exception.CourseNotFoundException;
import com.sbip.ch07.model.Course;
import com.sbip.ch07.model.LegacyCourseDto;
import com.sbip.ch07.repository.CourseRepository;
import com.sbip.ch07.util.CourseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepository courseRepository;

	@Override
	public LegacyCourseDto createCourse(LegacyCourseDto legacyCourseDto) {
		Course course = CourseUtil.from(legacyCourseDto);
		return CourseUtil.buildLegacyCourseDto(courseRepository.save(course));
	}

	@Override
	public LegacyCourseDto getCourseById(long courseId) {
		return findCourseById(courseId);
	}

	@Override
	public List<LegacyCourseDto> getCoursesByCategory(String category) {
		List<LegacyCourseDto> legacyCourseDtos = new ArrayList<>();
		courseRepository.findAllByCategory(category).spliterator()
				.forEachRemaining(c -> legacyCourseDtos.add(CourseUtil.buildLegacyCourseDto(c)));
		return legacyCourseDtos;
	}

	@Override
	public List<LegacyCourseDto> getCourses() {
		List<LegacyCourseDto> legacyCourseDtos = new ArrayList<>();
		courseRepository.findAll().spliterator()
				.forEachRemaining(c -> legacyCourseDtos.add(CourseUtil.buildLegacyCourseDto(c)));
		return legacyCourseDtos;
	}

	@Override
	public LegacyCourseDto updateCourse(Long courseId, LegacyCourseDto course) {
		LegacyCourseDto legacyCourseDto = findCourseById(courseId);

		Course existingCourse = CourseUtil.from(legacyCourseDto);

		existingCourse.setId(courseId);
		existingCourse.setName(course.name());
		existingCourse.setCategory(course.category());
		existingCourse.setRating(course.rating());
		existingCourse.setPrice(0.0);
		existingCourse.setDescription(course.description());


		return CourseUtil.buildLegacyCourseDto(courseRepository.save(existingCourse));
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


	private LegacyCourseDto findCourseById(Long courseId){
		return courseRepository.findById(courseId)
				.map(CourseUtil::buildLegacyCourseDto)
				.orElseThrow(() -> new CourseNotFoundException(String.format("No Course with id %s is available", courseId)));
	}

}
