package com.studentapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.studentapp.dao.DaoException;
import com.studentapp.dao.StudentDao;
import com.studentapp.dao.StudentDaoJdbc;
import com.studentapp.model.Student;

@Service
public class StudentServiceDb {
    private final StudentDao dao = new StudentDaoJdbc();

    public Student addStudent(Student s) {
        try {
            return dao.save(s);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to add student", e);
        }
    }
    public List<Student> listAll() {
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException("Failed to list students", e);
        }
    }

    public Optional<Student> getById(int id) {
        try {
            return dao.findById(id);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to get student", e);
        }
    }

    public boolean updateStudent(int id, Student newData) {
        try {
            return dao.update(id, newData);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to update student", e);
        }
    }

    public boolean deleteStudent(int id) {
        try {
            return dao.delete(id);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }

}
