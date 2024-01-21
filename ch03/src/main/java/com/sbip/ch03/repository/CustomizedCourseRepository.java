package com.sbip.ch03.repository;

import com.sbip.ch03.domin.Course;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * @author: Ezekiel Eromosei
 * @created: 14 March 2023
 */

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long> {
    // exposing on the save(S entity) and findAll()
    Stream<Course> streamAllByCategory(String category);
}
