package com.sumiran.pagination_sorting.repository;

import com.sumiran.pagination_sorting.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}