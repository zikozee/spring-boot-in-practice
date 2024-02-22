package com.sbip.ch06.event;

import com.sbip.ch06.metrics.couseTracker.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Slf4j
@Component
@RepositoryEventHandler
public class CourseEventHandler{


    @HandleBeforeCreate(Course.class)
    public void onBeforeCreate(Course course) {
        log.info("HANDLER: before creation: {}", course);
        System.out.println("HANDLER: before creation: "+ course);
    }

    @HandleAfterCreate
    protected void onAfterCreate(Course course) {
        log.info("HANDLER: after creation: {}", course);
        System.out.println("HANDLER: before creation: "+ course);
    }

    @HandleBeforeSave
    protected void onBeforeSave(Course course) {
        log.info("HANDLER: before save: {}", course);
        System.out.println("HANDLER: before creation: "+ course);
    }

    @HandleAfterSave
    protected void onAfterSave(Course course) {
        log.info("HANDLER: after save: {}", course);
        System.out.println("HANDLER: before creation: "+ course);
    }


}
