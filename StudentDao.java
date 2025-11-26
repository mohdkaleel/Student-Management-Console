package com.studentapp.dao;

import com.studentapp.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface to allow easier testing / mocking.
 */
public interface StudentDao {
    Student save(Student student) throws DaoException;
    Optional<Student> findById(int id) throws DaoException;
    List<Student> findAll() throws DaoException;
    boolean update(int id, Student s) throws DaoException;
    boolean delete(int id) throws DaoException;
}
