# Student-Management-Console

This project is a lightweight command-line system for managing student records, designed to illustrate clean application layering and interchangeable data sources. It includes two distinct data-handling implementations:

1.An in-memory service, ideal for rapid testing without external setup, and

2.A DAO implementation powered by MySQL via JDBC, showcasing real database persistence using PreparedStatement and structured CRUD operations.

The architecture follows a simple flow — Main → Service → DAO — ensuring loose coupling and easy substitution of components. Error handling is kept straightforward, focusing on common JDBC pitfalls. Core business rules are validated through a set of JUnit 5 unit tests, and the entire project is built and managed using Maven.
