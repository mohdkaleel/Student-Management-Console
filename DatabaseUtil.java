package com.studentapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DatabaseUtil {


    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DB = "student_db";
    private static final String USER = "root";
    private static final String PASS = "test";

    private static final String URL = String.format(
            "jdbc:mysql://localhost:3306/student_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
            HOST, PORT, DB);


    private DatabaseUtil() {}

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("MySQL JDBC driver not found: " + e.getMessage());
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

