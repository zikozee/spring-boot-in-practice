package com.sbip.ch03;

import com.sbip.ch03.domin.Course;
import com.sbip.ch03.repository.CourseRepository;
import com.sbip.ch03.repository.CustomizedCourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Ezekiel Eromosei
 * @created: 14 March 2023
 */

@DataJpaTest
public class CourseTrackerApplicationTests {

    @Autowired
    private CustomizedCourseRepository customizedCourseRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void givenCreateCourseWhenFindAllCoursesThenExpectOneCourse() {
        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4,
                "’Spring Boot gives all the power of the Spring Framework without all of the complexities");
                customizedCourseRepository.save(course);
        assertEquals(Collections.singletonList(customizedCourseRepository.findAll()).size(), 1);
    }

    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
        // Saving a list of courses
        courseRepository.saveAll(getCourseList());
        assertTrue(courseRepository.existsByName("Spring"));
        assertFalse(courseRepository.existsByName("Mastering JavaScript"));
        assertEquals(courseRepository.countByCategory("Python"), 2);

    }

    private List<Course> getCourseList(){
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Spring", "Python", 1, "good course"));
        courses.add(new Course("Spring", "Python", 1, "good course"));
        return courses;
    }
}
