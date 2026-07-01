package com.sumiran.unit_testing.repository;

import com.sumiran.unit_testing.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}