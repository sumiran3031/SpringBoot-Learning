package com.sumiran.student_management_system.service;

import com.sumiran.student_management_system.dto.StudentDTO;
import com.sumiran.student_management_system.exception.StudentNotFoundException;
import com.sumiran.student_management_system.model.Student;
import com.sumiran.student_management_system.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    private StudentDTO toDTO(Student s) {
        return new StudentDTO(s.getId(), s.getName(),
                s.getCourse(), s.getMarks());
    }

    public StudentDTO addStudent(Student student) {
        return toDTO(repo.save(student));
    }

    public List<StudentDTO> getAllStudents() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(int id) {
        return toDTO(repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id)));
    }

    public StudentDTO updateStudent(int id, Student updated) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id));
        student.setName(updated.getName());
        student.setCourse(updated.getCourse());
        student.setMarks(updated.getMarks());
        student.setEmail(updated.getEmail());
        return toDTO(repo.save(student));
    }

    public String deleteStudent(int id) {
        repo.deleteById(id);
        return "Student deleted with id: " + id;
    }

    public Page<StudentDTO> getStudentsPaged(int page, int size,
                                             String sortBy, String dir) {
        Sort sort = dir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable).map(this::toDTO);
    }
}