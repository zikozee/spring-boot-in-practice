package com.sbip.ch06.event;

import com.sbip.ch06.metrics.couseTracker.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Service;


/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Slf4j
@Service
public class CourseRepositoryEvent extends AbstractRepositoryEventListener<Course>  {



    @Override
    protected void onBeforeCreate(Course course) {
        log.info("before creation: {}", course);
    }

    @Override
    protected void onAfterCreate(Course course) {
        log.info("after creation: {}", course);
    }

    @ManagedOperation
    @Override
    protected void onBeforeSave(Course course) {
        log.info("before save: {}", course);
    }

    @ManagedOperation
    @Override
    protected void onAfterSave(Course course) {
        log.info("after save: {}", course);
    }


}
