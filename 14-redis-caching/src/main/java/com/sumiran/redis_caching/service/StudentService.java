package com.sumiran.redis_caching.service;

import com.sumiran.redis_caching.model.Student;
import com.sumiran.redis_caching.repository.StudentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "students", key = "#id")
    public Student getStudentById(int id) {
        System.out.println("Fetching from DATABASE for id: " + id);
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));
    }

    @CachePut(value = "students", key = "#id")
    public Student updateStudent(int id, Student updated) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));
        student.setName(updated.getName());
        student.setCourse(updated.getCourse());
        student.setMarks(updated.getMarks());
        System.out.println("Updating DATABASE and CACHE for id: " + id);
        return repo.save(student);
    }

    @CacheEvict(value = "students", key = "#id")
    public String deleteStudent(int id) {
        repo.deleteById(id);
        System.out.println("Deleted from DATABASE and CACHE for id: " + id);
        return "Student deleted: " + id;
    }
}