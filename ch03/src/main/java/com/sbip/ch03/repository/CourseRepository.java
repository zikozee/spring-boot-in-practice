package com.sbip.ch03.repository;

import com.sbip.ch03.domin.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author: Ezekiel Eromosei
 * @created: 14 March 2023
 */

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Iterable<Course> findByNameOrCategory(String name, String category);
    Iterable<Course> findAllByCategory(String category);
    boolean existsByName(String name);
    int countByCategory(String category);
    Iterable<Course> findByNameStartsWith(String name);
    Stream<Course> streamAllByCategory(String category);
}
