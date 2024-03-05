package com.sbip.ch07.controller;

import com.sbip.ch07.model.Course;
import com.sbip.ch07.model.ModernCourseDto;
import com.sbip.ch07.repository.CourseRepository;
import com.sbip.ch07.service.CourseService;
import com.sbip.ch07.util.CourseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Course Controller", description = "This REST controller provide services to manage courses in the Course Tracker application")
@RestController
@RequestMapping(path="courses/v2")
@RequiredArgsConstructor
public class ModernCourseController {

    private final CourseRepository courseRepository;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all courses available in the course Tracker application")
    @PreAuthorize("hasAuthority('read')")
    public Iterable<ModernCourseDto> getAllCourses(@AuthenticationPrincipal Jwt jwt) {
        String author = jwt.getClaim("user_name"); // assuming author is the name of the course
        List<ModernCourseDto> modernCourseDtoList = new ArrayList<>();

        courseRepository.findByName(author)
                .forEach(c -> modernCourseDtoList.add(CourseUtil.buildModernCourseDto(c)));
        return modernCourseDtoList;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new course in the course Tracker application")
    public ModernCourseDto createCourse(@RequestBody ModernCourseDto modernCourseDto, @AuthenticationPrincipal Jwt jwt) {
        String author = jwt.getClaim("user_name");

        Course save = courseRepository.save(CourseUtil.from(modernCourseDto));
        return CourseUtil.buildModernCourseDto(save);
    }


}
