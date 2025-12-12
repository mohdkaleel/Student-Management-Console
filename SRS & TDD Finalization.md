
# SRS & TDD Finalization checklist (for submission)
    - Software Requirements Specification (SRS)
    - Ensure the SRS document contains the following elements:
    - A clear description of the system’s goal, boundaries, and primary stakeholders (student user / administrator).
    - The core functional scenarios, focusing on CRUD interactions.
    - Documented non-functional expectations such as error handling behavior, usability, and reliability.
    - Configuration details (e.g., environment setup, DB connection info).
    - Precise acceptance criteria for each feature.
    - An attached database design section, including sql/schema.sql.
    - A short example of a typical console run, showing prompts and expected responses.

2. TDD:
    - JUnit tests must verify all CRUD operations: create, findById, findAll, update, and delete.
    - Tests should execute against an H2 in-memory database, and the CI pipeline must run mvn test successfully.
    - Every test method should have a meaningful name and assert the expected outcome explicitly.
   
3. Repo hygiene:
    - pom.xml should only include the essential libraries and build plugins (use the template provided).
    - Remove leftover debugging code, unused imports, or commented-out fragments.
    - Make sure the repository contains:
    - README.md
    - sql/schema.sql
    - All necessary JUnit tests






