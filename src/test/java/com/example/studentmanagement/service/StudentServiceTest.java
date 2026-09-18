package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void addStudent_assignsIdAndStoresRecord() {
        Student student = new Student(null, "Ananya Sharma", "B.Tech CSE");

        Student saved = studentService.addStudent(student);

        assertNotNull(saved.getId(), "Saved student should be assigned an id");
        assertEquals("Ananya Sharma", saved.getName());
        assertEquals("B.Tech CSE", saved.getCourse());
        assertEquals(1, studentService.countStudents());
    }

    @Test
    void getAllStudents_returnsAllAddedRecords() {
        studentService.addStudent(new Student(null, "Ravi Kumar", "MCA"));
        studentService.addStudent(new Student(null, "Priya Singh", "B.Sc IT"));

        Collection<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
    }

    @Test
    void getStudentById_returnsNullWhenNotFound() {
        Student result = studentService.getStudentById(999L);

        assertNull(result);
    }
}
