package com.sumiran.demo.controller;

import com.sumiran.demo.model.Student;
import com.sumiran.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Student API", description = "CRUD operations for students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add a new student")
    public Student addStudent(@Valid @RequestBody Student student) {
        return service.add(student);
    }

    @GetMapping
    @Operation(summary = "Get all students")
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public Student getStudentById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student by ID")
    public Student updateStudent(@PathVariable int id,
                                 @Valid @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student by ID")
    public String deleteStudent(@PathVariable int id) {
        return service.delete(id);
    }
}