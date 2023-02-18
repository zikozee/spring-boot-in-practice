package com.zikozee.booting.constraint.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Ezekiel Eromosei
 * @created: 18 February 2023
 */

@Getter
@Setter
public class Course {

    private long id;
    private String name;
    private String category;

    @Min(value = 1, message="A course should have a minimum of 1 rating")
    @Max(value = 5, message="A course should have a maximum of 5 rating")
    private int rating;
}
