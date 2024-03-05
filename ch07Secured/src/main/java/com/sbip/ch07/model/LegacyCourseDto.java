package com.sbip.ch07.model;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 04 Mar, 2024
 */

public record LegacyCourseDto(Long id, String name, String category, int rating, String description) {
    public LegacyCourseDto(String name, String category, int rating, String description) {
        this(null, name, category, rating, description);
    }
}
