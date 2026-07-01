package com.sumiran.student_management_system.repository;

import com.sumiran.student_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}