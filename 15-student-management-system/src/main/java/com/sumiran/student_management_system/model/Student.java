package com.sumiran.student_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Course cannot be empty")
    private String course;

    @Min(value = 0, message = "Marks cannot be negative")
    private int marks;

    private String email;

    public Student() {}

    public Student(String name, String course, int marks, String email) {
        this.name = name;
        this.course = course;
        this.marks = marks;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}