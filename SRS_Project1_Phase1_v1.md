## 1. Purpose
This project is a console-based Java application that allows an administrator to manage student records using CRUD operations.


## 2. Scope
The system will allow:
- Adding new students
- Viewing all students
- Searching students by ID
- Updating student information
- Deleting student records

The final system will use:
- Java 17 (Console UI)
- MySQL (Database)
- JDBC (Database interaction)

---

## 3. User Stories
1. **As an admin, I want to add a new student** so that their details are stored in the system.
2. **As an admin, I want to view all students** so I can see the existing records.
3. **As an admin, I want to search a student by ID** to quickly locate a specific record.
4. **As an admin, I want to update a student's details** so that incorrect information can be corrected.
5. **As an admin, I want to delete a student** to remove outdated or invalid records.

---

## 4. Functional Requirements

### **FR-01 — Add Student**
- Input: name, email, grade
- System stores student in DB with unique ID

### **FR-02 — View All Students**
- System displays all student records

### **FR-03 — Search Student by ID**
- System retrieves student using `id`

### **FR-04 — Update Student**
- System updates name, email, grade

### **FR-05 — Delete Student**
- System removes student with given ID

---
