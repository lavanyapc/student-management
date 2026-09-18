package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple in-memory service layer for managing students.
 * Kept in-memory (no database) so the CI/CD demo stays lightweight,
 * while still being fully unit-testable.
 */
@Service
public class StudentService {

    private final Map<Long, Student> studentStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public Student addStudent(Student student) {
        long id = idCounter.incrementAndGet();
        student.setId(id);
        studentStore.put(id, student);
        return student;
    }

    public Collection<Student> getAllStudents() {
        return studentStore.values();
    }

    public Student getStudentById(Long id) {
        return studentStore.get(id);
    }

    public int countStudents() {
        return studentStore.size();
    }
}
