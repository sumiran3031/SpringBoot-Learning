package com.sumiran.demo.service;


import com.sumiran.demo.model.Student;
import com.sumiran.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student add(Student s) { return repo.save(s); }

    public List<Student> getAll() { return repo.findAll(); }

    public Student getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }

    public Student update(int id, Student updated) {
        Student s = getById(id);
        s.setName(updated.getName());
        s.setCourse(updated.getCourse());
        s.setMarks(updated.getMarks());
        return repo.save(s);
    }

    public String delete(int id) {
        repo.deleteById(id);
        return "Student deleted: " + id;
    }
}