
# SRS & TDD Finalization checklist (for submission)
Use this checklist before submitting for review.

1. SRS document includes:
    - Purpose, scope, actors (user/admin), main use cases (CRUD), non-functional requirements (error handling), config options, and acceptance criteria.
    - DB schema attached (include `sql/schema.sql`).
    - Sample console session and expected outputs.

2. TDD:
    - JUnit tests cover: create, findById, findAll, update, delete.
    - Tests run on H2 in-memory; CI executes `mvn test`.
    - Test names are descriptive and each test has clear assertions.

3. Repo hygiene:
    - `pom.xml` only contains required dependencies/plugins (use provided `pom.xml`).
    - Remove debug/unused imports.
    - Add `README.md`, `sql/schema.sql`, tests, and `docs/screenshot.png` (the screenshot).

4. Run manual verification:
    - Follow the manual test steps in the README and capture screenshot.



