package com.sumiran.exception_handling.controller;

import com.sumiran.exception_handling.exception.StudentNotFoundException;
import com.sumiran.exception_handling.model.Student;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    @PostMapping
    public String addStudent(@Valid @RequestBody Student student) {
        students.add(student);
        return "Student added: " + student.getName();
    }
}