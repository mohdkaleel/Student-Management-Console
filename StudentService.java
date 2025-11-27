package com.studentapp.service;

import com.studentapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private int nextId = 1;

    public Student addStudent(Student s) {
        s.setId(nextId++);
        students.add(s);
        return s;
    }

    public List<Student> listAll() {
        return new ArrayList<>(students);
    }

    public Optional<Student> getById(int id) {
        return students.stream().filter(st -> st.getId() == id).findFirst();
    }

    public boolean updateStudent(int id, Student newData) {
        Optional<Student> opt = getById(id);
        if (opt.isEmpty()) return false;
        Student existing = opt.get();
        existing.setName(newData.getName());
        existing.setEmail(newData.getEmail());
        existing.setGrade(newData.getGrade());
        return true;
    }

    public boolean deleteStudent(int id) {
        return students.removeIf(st -> st.getId() == id);
    }

    public void clearAll() {
        students.clear();
        nextId = 1;
    }
}

