# 1. Phase 3 Overview

In Phase 3, the Student Management System is upgraded from an in-memory
ArrayList version (Phase 2) to a persistent database version using MySQL + JDBC.
All CRUD operations will now interact with a MySQL database instead of memory.

This phase introduces DatabaseUtil, StudentDao, and updates the service layer to use JDBC.

# 2. Phase 3 Objectives

* Integrate MySQL database into the project.
* Replace ArrayList operations with JDBC CRUD operations.
* Write a clean Data Access Layer (DAO) using PreparedStatements.
* Handle SQL exceptions and database connection errors properly.
* Update TDD to describe the StudentDao and SQL queries.

# 3. Tasks of Phase 3 (Explained Step-by-Step)

## 3.1 Create the DatabaseUtil Class

Purpose:
This class creates a database connection using DriverManager, allowing all DAO classes to reuse the same logic.

Responsibilities:
* Load MySQL driver
* Provide getConnection()
* Store DB properties (host, user, password, database)

## 3.2 Create the StudentDao Class

**Purpose:**
This class contains all raw SQL operations.
It replaces the ArrayList logic from Phase 2.

**DAO Responsibilities:**

* Add a student → INSERT
* View all students → SELECT
* Get a student by ID → SELECT WHERE
* Update a student → UPDATE
* Delete a student → DELETE

**Each method uses:**

* Connection
* PreparedStatement
* ResultSet

**This ensures:**

*  Security (prevents SQL injection)
*  Performance
*  Easy DB interaction

#  3.3 Re-implement CRUD Operations With JDBC

Replace the old StudentService with a new service–StudentServiceDb:

* Calls StudentDao
* Wraps SQL exceptions
* Returns Student objects
This makes Main.java database-powered without major code changes.

#  3.4 Update TDD (v2)

Phase 3 requires updating TDD:
* Add database schema definition
* Document StudentDao
* Include exact SQL queries
* Add class relationships
A complete TDD (ready to submit) is provided below.

# 4. StudentDao — Method Requirements

1. save(Student student) sql(INSERT INTO student (name, email, grade) VALUES (?, ?, ?))
2. findById(int id)  sql(SELECT id, name, email, grade FROM student WHERE id = ?)
3. findAll() sql(SELECT id, name, email, grade FROM student ORDER BY id)
4. update(int id, Student s) sql(UPDATE student SET name = ?, email = ?, grade = ? WHERE id = ?)
5. delete(int id) sql(DELETE FROM student WHERE id = ?)
   
