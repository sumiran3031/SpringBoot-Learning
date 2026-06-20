package com.sumiran.jpa_h2_database.controller;

import com.sumiran.jpa_h2_database.model.Student;
import com.sumiran.jpa_h2_database.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @Valid @RequestBody Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(updatedStudent.getName());
        student.setCourse(updatedStudent.getCourse());
        student.setMarks(updatedStudent.getMarks());

        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
        return "Student deleted with id: " + id;
    }
}