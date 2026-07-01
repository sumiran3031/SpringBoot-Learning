
package com.sumiran.demo.repository;
import com.sumiran.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}