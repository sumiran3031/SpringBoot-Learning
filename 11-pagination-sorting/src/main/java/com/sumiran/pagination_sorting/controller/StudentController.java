package com.sumiran.pagination_sorting.controller;

import com.sumiran.pagination_sorting.model.Student;
import com.sumiran.pagination_sorting.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Add single student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    // Add multiple students
    @PostMapping("/bulk")
    public List<Student> addAllStudents(@RequestBody List<Student> students) {
        return service.addAllStudents(students);
    }

    @GetMapping("/page")
    public Page<Student> getByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return service.getStudentsByPage(page, size);
    }

    @GetMapping("/page-sort")
    public Page<Student> getWithSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return service.getStudentsWithSorting(page, size, sortBy, direction);
    }

    @GetMapping("/sort")
    public List<Student> getSorted(
            @RequestParam(defaultValue = "name") String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return service.getStudentsSortedBy(field, direction);
    }
}