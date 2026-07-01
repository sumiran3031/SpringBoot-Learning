package com.sumiran.pagination_sorting.service;

import com.sumiran.pagination_sorting.model.Student;
import com.sumiran.pagination_sorting.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Student> addAllStudents(List<Student> students) {
        return repo.saveAll(students);
    }

    public Page<Student> getStudentsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }
    public Page<Student> getStudentsWithSorting(int page, int size,
                                                String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable);
    }

    public List<Student> getStudentsSortedBy(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();
        return repo.findAll(sort);
    }
}