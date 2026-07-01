package com.sumiran.mysql_database.repository;

import com.sumiran.mysql_database.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}