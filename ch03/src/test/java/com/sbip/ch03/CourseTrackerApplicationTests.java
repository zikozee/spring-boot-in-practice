package com.sbip.ch03;

import com.sbip.ch03.domin.Course;
import com.sbip.ch03.repository.CourseRepository;
import com.sbip.ch03.repository.CustomizedCourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Autowired
    private EntityManager entityManager;


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

    @Test
    void givenDataAvailableWhenSortsFirstPageThenGetSortedSData() {
        Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Order.asc("Name")));
        Condition<Course> sortedFirstCourseCondition = new Condition<Course>() {
            @Override
            public boolean matches(Course course) {
                return course.getId() == 4 && course.getName().equals("Cloud Native Spring Boot Application Development");
            }
        };
        assertThat(courseRepository.findAll(pageable)).first().has(sortedFirstCourseCondition);
    }

    @Test
    void givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
        Pageable customSortPageable = PageRequest.of(0,5, Sort.by("Rating")
                .descending().and(Sort.by("Name")));
        Condition<Course> customSortFirstCourseCondition = new Condition<Course>() {
            @Override
            public boolean matches(Course course) {
                return course.getId() == 2 && course.getName().equals("Getting Started with Spring Security DSL");
            }
        };
        assertThat(courseRepository.findAll(customSortPageable)).first().has(customSortFirstCourseCondition);
    }

    @Test
    public void givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
        courseRepository.saveAll(getCourseList());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Course> courseCriteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);

        Predicate courseCategoryPredicate = criteriaBuilder.equal(courseRoot.get("category"), "Spring");
        courseCriteriaQuery.where(courseCategoryPredicate);

        TypedQuery<Course> query = entityManager.createQuery(courseCriteriaQuery);
        Assertions.assertThat(query.getResultList().size()).isEqualTo(3);

    }
}
