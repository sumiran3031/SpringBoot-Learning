package com.sumiran.jpa_h2_database.repository;

import com.sumiran.jpa_h2_database.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}