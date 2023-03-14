package com.sbip.ch03.repository;

import com.sbip.ch03.domin.Course;
import org.springframework.stereotype.Repository;

/**
 * @author: Ezekiel Eromosei
 * @created: 14 March 2023
 */

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long> {
    // exposing on the save(S entity) and findAll()
}
