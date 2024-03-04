package com.sbip.ch07.model;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 04 Mar, 2024
 */

public record ModernCourseDto(Long id, String name, String category, int rating, double price,  String description) {

    public ModernCourseDto(String name, String category, int rating, double price, String description) {
        this(null, name, category, rating, price, description);
    }
}
