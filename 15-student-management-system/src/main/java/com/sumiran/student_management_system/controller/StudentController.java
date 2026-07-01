package com.sumiran.student_management_system.controller;

import com.sumiran.student_management_system.dto.StudentDTO;
import com.sumiran.student_management_system.model.Student;
import com.sumiran.student_management_system.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Student API", description = "Student Management REST API")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add new student")
    public StudentDTO addStudent(@Valid @RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping
    @Operation(summary = "Get all students")
    public List<StudentDTO> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public StudentDTO getStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student")
    public StudentDTO updateStudent(@PathVariable int id,
                                    @Valid @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student")
    public String deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }

    @GetMapping("/page")
    @Operation(summary = "Get students with pagination and sorting")
    public Page<StudentDTO> getStudentsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String dir) {
        return service.getStudentsPaged(page, size, sortBy, dir);
    }
}