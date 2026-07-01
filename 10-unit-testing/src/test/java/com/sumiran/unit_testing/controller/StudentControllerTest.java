package com.sumiran.unit_testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumiran.unit_testing.model.Student;
import com.sumiran.unit_testing.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllStudents() throws Exception {
        when(service.getAllStudents()).thenReturn(Arrays.asList(
                new Student(1, "Sumiran", "Full Stack Java", 85),
                new Student(2, "Riya", "React", 90)
        ));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Sumiran"));
    }

    @Test
    void testAddStudent() throws Exception {
        Student student = new Student(1, "Sumiran", "Full Stack Java", 85);

        when(service.addStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sumiran"));
    }

    @Test
    void testGetStudentById() throws Exception {
        when(service.getStudentById(1))
                .thenReturn(new Student(1, "Sumiran", "Full Stack Java", 85));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sumiran"))
                .andExpect(jsonPath("$.course").value("Full Stack Java"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        when(service.deleteStudent(1)).thenReturn("Student deleted: 1");

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted: 1"));
    }
}