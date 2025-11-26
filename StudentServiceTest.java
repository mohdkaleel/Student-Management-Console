package com.studentapp.service;

import com.studentapp.model.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void addListAndGet() {
        StudentService s = new StudentService();
        s.clearAll();
        Student created = s.addStudent(new Student("Test", "t@example.com", "A"));
        assertTrue(created.getId() > 0);
        assertEquals(1, s.listAll().size());
        assertTrue(s.getById(created.getId()).isPresent());
    }

    @Test
    void updateAndDelete() {
        StudentService s = new StudentService();
        s.clearAll();
        Student created = s.addStudent(new Student("X", "x@example.com", "B"));
        boolean updated = s.updateStudent(created.getId(), new Student("X2", "x2@example.com", "A"));
        assertTrue(updated);
        assertEquals("X2", s.getById(created.getId()).orElseThrow().getName());

        boolean deleted = s.deleteStudent(created.getId());
        assertTrue(deleted);
        assertTrue(s.listAll().isEmpty());
    }
}
