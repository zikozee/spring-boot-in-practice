package com.sbip.ch07.exception;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 28 Feb, 2024
 */

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(String message) {
        super(message);
    }
}
