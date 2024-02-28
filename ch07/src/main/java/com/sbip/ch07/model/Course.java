package com.sbip.ch07.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "COURSES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "RATING")
	private int rating;

	@Column(name = "DESCRIPTION")
	private String description;
}
