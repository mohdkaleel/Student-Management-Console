# Student-Management-Console

A small console CRUD application managing students with two implementations: an in-memory StudentService (for quick local testing) and a JDBC-backed StudentDaoJdbc for persistence with MySQL. The project demonstrates layering (Main → Service → DAO), JDBC usage with PreparedStatement, basic exception handling, and unit tests for core business logic. Maven is used for build and dependency management; JUnit 5 for tests.
