package com.sumiran.unit_testing.controller;

import com.sumiran.unit_testing.model.Student;
import com.sumiran.unit_testing.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id,
                                 @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }
}