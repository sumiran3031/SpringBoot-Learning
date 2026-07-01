package com.sumiran.mysql_database.service;

import com.sumiran.mysql_database.model.Student;
import com.sumiran.mysql_database.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }

    public Student updateStudent(int id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
        student.setName(updatedStudent.getName());
        student.setCourse(updatedStudent.getCourse());
        student.setMarks(updatedStudent.getMarks());
        return studentRepository.save(student);
    }

    public String deleteStudent(int id) {
        studentRepository.deleteById(id);
        return "Student deleted with id: " + id;
    }
}