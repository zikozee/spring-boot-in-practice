package com.sbip.ch07.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 28 Feb, 2024
 */

@RestControllerAdvice
//commented out is for when using @ControllerAdvice
public class CourseTrackerGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CourseNotFoundException.class)
    public ResponseEntity<?> handleCourseNotFound(CourseNotFoundException ex, WebRequest request){

        Response response = Response.builder().error(ex.getMessage()).build();

        return super.handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder(alphabetic = true)
    private static class Response{
        @Builder.Default
        private String id = UUID.randomUUID().toString();
        private String error;
        private List<String> fieldErrors;
    }
}
