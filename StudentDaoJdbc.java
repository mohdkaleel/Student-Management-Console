package com.studentapp.dao;

import com.studentapp.db.DatabaseUtil;
import com.studentapp.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoJdbc implements StudentDao {

    public StudentDaoJdbc() {

    }

    @Override
    public Student save(Student student) throws DaoException {
        String sql = "INSERT INTO student (name, email, grade) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getGrade());
            int count = ps.executeUpdate();
            if (count == 0) throw new DaoException("Insert failed, no rows affected.");
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) student.setId(keys.getInt(1));
            }
            return student;
        } catch (SQLException e) {
            throw new DaoException("Failed to save student", e);
        }
    }

    @Override
    public Optional<Student> findById(int id) throws DaoException {
        String sql = "SELECT id, name, email, grade FROM student WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find student by id: " + id, e);
        }
    }

    @Override
    public List<Student> findAll() throws DaoException {
        String sql = "SELECT id, name, email, grade FROM student ORDER BY id";
        List<Student> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DaoException("Failed to fetch all students", e);
        }
    }

    @Override
    public boolean update(int id, Student s) throws DaoException {
        String sql = "UPDATE student SET name = ?, email = ?, grade = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getGrade());
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to update student with id " + id, e);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        String sql = "DELETE FROM student WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to delete student with id " + id, e);
        }
    }


    private Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setGrade(rs.getString("grade"));
        return s;
    }
}

