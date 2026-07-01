package com.sumiran.unit_testing.service;

import com.sumiran.unit_testing.model.Student;
import com.sumiran.unit_testing.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student addStudent(Student student) {
        return repo.save(student);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }

    public Student updateStudent(int id, Student updated) {
        Student student = getStudentById(id);
        student.setName(updated.getName());
        student.setCourse(updated.getCourse());
        student.setMarks(updated.getMarks());
        return repo.save(student);
    }

    public String deleteStudent(int id) {
        repo.deleteById(id);
        return "Student deleted: " + id;
    }
}