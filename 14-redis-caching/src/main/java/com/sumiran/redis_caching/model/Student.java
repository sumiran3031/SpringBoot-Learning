package com.sumiran.redis_caching.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String course;
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