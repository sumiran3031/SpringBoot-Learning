package com.sumiran.redis_caching.repository;

import com.sumiran.redis_caching.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}