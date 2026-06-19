package com.sumiran.exception_handling.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Student {

    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Course cannot be empty")
    private String course;

    @Min(value = 0, message = "Marks cannot be negative")
    private int marks;

    public Student() {}

    public Student(int id, String name, String course, int marks) {
        this.id = id;
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