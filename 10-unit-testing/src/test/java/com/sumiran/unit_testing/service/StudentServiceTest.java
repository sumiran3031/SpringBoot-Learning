package com.sumiran.unit_testing.service;

import com.sumiran.unit_testing.model.Student;
import com.sumiran.unit_testing.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository repo;

    @InjectMocks
    private StudentService service;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1, "Sumiran", "Full Stack Java", 85);
    }

    @Test
    void testAddStudent() {
        when(repo.save(student)).thenReturn(student);

        Student result = service.addStudent(student);

        assertNotNull(result);
        assertEquals("Sumiran", result.getName());
        verify(repo, times(1)).save(student);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(
                student,
                new Student(2, "Riya", "React", 90)
        );
        when(repo.findAll()).thenReturn(students);

        List<Student> result = service.getAllStudents();

        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGetStudentById_Found() {
        when(repo.findById(1)).thenReturn(Optional.of(student));

        Student result = service.getStudentById(1);

        assertNotNull(result);
        assertEquals("Sumiran", result.getName());
    }

    @Test
    void testGetStudentById_NotFound() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                service.getStudentById(99));
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(repo).deleteById(1);

        String result = service.deleteStudent(1);

        assertEquals("Student deleted: 1", result);
        verify(repo, times(1)).deleteById(1);
    }
}