# 1.1 Purpose

The purpose of this SRS is to define the requirements for Phase 2 
of the Student Management Console Project.
In this phase, the system will implement all CRUD operations using 
an in-memory ArrayList.
No database is used at this stage; data exists only during runtime.

# 1.2 Scope

The Phase 2 system allows an admin to:
1. Add new students
2. View all students
3. Search a student by ID
4. Update student details
5. Delete a student

All operations are performed on Java objects stored in memory (ArrayList).


## 2. User Stories
1. **As an admin, I want to add a new student so that I can store their information in the system.
2. **As an admin, I want to view all students so I can see the complete list of student records.
3. **As an admin, I want to search for a student by ID so I can quickly locate a student.
4. **As an admin, I want to update a student’s details so I can correct mistakes or update new information.
5. **As an admin, I want to delete a student so I can remove outdated or unnecessary records.


# 3.Functional Requirements (FR)

 **FR-01: Add Student**
System should allow the admin to input:
     
       * Name
      * Email
      * Grade

* System should generate a unique ID automatically (incremental integer).
* Student object is stored in an in-memory ArrayList.

**FR-02: View All Students**

* System should print all student records stored in the ArrayList.
* If list is empty, show “No students found.”

**FR-03: Search Student by ID**

* System should allow admin to enter an ID.
* System should search the ArrayList for matching ID.
* If found: display student details.
* If not found: display “Student not found.”

**FR-04: Update Student**
System should allow admin to input:

* Existing student ID
* New Name
* New Email
* New Grade 
* If ID exists → update fields
* Otherwise → show “Not found”

**FR-05: Delete Student**

* System should allow admin to enter an ID.
* Remove student object from the ArrayList.
* If found → display “Deleted”
* If not found → show “Not found”



# 4. System Design (Phase 2)

**Class Structure**

**Class: Student**
* id (int)
* name (String)
* email (String)
* grade (String)


**Class: StudentService**
Methods:
* addStudent(Student)
* listAll()
* getById(int)
* updateStudent(int, Student)
* deleteStudent(int)
* clearAll() (for testing)

**Uses:**

* private List<Student> students = new ArrayList<>();
* private int nextId = 1;

Main Class:

* Displays menu
* Reads user input via Scanner
* Calls StudentService methods