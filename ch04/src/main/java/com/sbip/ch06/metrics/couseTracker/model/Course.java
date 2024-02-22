package com.sbip.ch06.metrics.couseTracker.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "COURSES")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	@NotEmpty(message = "Course name field can't be empty")
	private String name;

	@Column(name = "CATEGORY")
	@NotEmpty(message = "Course category field can't be empty")
	private String category;

	@Column(name = "RATING")
	@Min(value = 1)
	@Max(value = 5)
	private int rating;

	@Column(name = "DESCRIPTION")
	@NotEmpty(message = "Course description field can't be empty")
	private String description;

	public Course() {}

	public Course(Long id, String name, String category, int rating, String description) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", name='" + name + '\'' +
				", category='" + category + '\'' +
				", rating=" + rating +
				", description='" + description + '\'' +
				'}';
	}
}
