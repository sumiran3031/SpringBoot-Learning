package com.sumiran.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Entity
@Schema(description = "Student entity")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated student ID")
    private int id;

    @NotBlank(message = "Name cannot be empty")
    @Schema(description = "Student name", example = "Sumiran")
    private String name;

    @NotBlank(message = "Course cannot be empty")
    @Schema(description = "Course name", example = "Full Stack Java")
    private String course;

    @Min(value = 0, message = "Marks cannot be negative")
    @Schema(description = "Marks obtained", example = "85")
    private int marks;

    public Student() {}

    public Student(String name, String course, int marks) {
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
}