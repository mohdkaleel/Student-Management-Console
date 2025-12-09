package com.studentapp;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.studentapp.model.Student;
import com.studentapp.service.StudentServiceDb;

public class Main {

    private static final StudentServiceDb service = new StudentServiceDb();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> addStudent(sc);
                case "2" -> viewAll();
                case "3" -> searchById(sc);
                case "4" -> updateStudent(sc);
                case "5" -> deleteStudent(sc);
                case "0" -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }


    private static void printMenu() {
        System.out.println("\n=== Student Manager (DB) ===");
        System.out.println("1. Add student");
        System.out.println("2. View all students");
        System.out.println("3. Search by ID");
        System.out.println("4. Update student");
        System.out.println("5. Delete student");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Grade: ");
        String grade = sc.nextLine().trim();

        Student s = new Student(name, email, grade);
        try {
            Student created = service.addStudent(s);
            System.out.println("Added: " + created);
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (isDuplicateKeyError(cause)) {
                System.err.println("Could not add student: email already exists.");
            } else {
                System.err.println("Could not add student: " + brief(e));
            }
        }
    }

private static void viewAll() {
    try {
        List<Student> all = service.listAll();
        if (all.isEmpty()) System.out.println("No students found.");
        else all.forEach(System.out::println);
    } catch (RuntimeException e) {
        System.err.println("Failed to list students: " + brief(e));
        // print full stack trace so you can see the root cause and line numbers
        e.printStackTrace(System.err);

        Throwable c = e.getCause();
        if (c == null) return;

        // If it's our DaoException, show its message + nested cause
        System.err.println("Root cause: " + c.getClass().getName() + ": " + c.getMessage());
        if (c instanceof java.sql.SQLException sq) {
            System.err.println("SQLState=" + sq.getSQLState() +
                    ", errorCode=" + sq.getErrorCode() +
                    ", message=" + sq.getMessage());
            sq.printStackTrace(System.err);
        } else {
            // print nested cause chain
            Throwable inner = c.getCause();
            while (inner != null) {
                System.err.println("Caused by: " + inner.getClass().getName() + ": " + inner.getMessage());
                if (inner instanceof java.sql.SQLException isq) {
                    System.err.println("  SQLState=" + isq.getSQLState() + ", errorCode=" + isq.getErrorCode());
                }
                inner = inner.getCause();
            }
        }
    }
}


    private static void searchById(Scanner sc) {
        Integer id = readId(sc, "Enter ID: ");
        if (id == null) return;
        try {
            Optional<Student> opt = service.getById(id);
            opt.ifPresentOrElse(System.out::println, () -> System.out.println("Student not found"));
        } catch (RuntimeException e) {
            System.err.println("Failed to search: " + brief(e));
        }
    }

    private static void updateStudent(Scanner sc) {
        Integer id = readId(sc, "Enter ID to update: ");
        if (id == null) return;
        System.out.print("New name: ");
        String name = sc.nextLine().trim();
        System.out.print("New email: ");
        String email = sc.nextLine().trim();
        System.out.print("New grade: ");
        String grade = sc.nextLine().trim();
        try {
            boolean ok = service.updateStudent(id, new Student(name, email, grade));
            System.out.println(ok ? "Updated." : "Not found.");
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (isDuplicateKeyError(cause)) {
                System.err.println("Could not update: email already exists.");
            } else {
                System.err.println("Failed to update: " + brief(e));
            }
        }
    }

    private static void deleteStudent(Scanner sc) {
        Integer id = readId(sc, "Enter ID to delete: ");
        if (id == null) return;

        try {
            boolean ok = service.deleteStudent(id);
            System.out.println(ok ? "Deleted." : "Not found.");
        } catch (RuntimeException e) {
            System.err.println("Failed to delete: " + brief(e));
        }
    }
private static Integer readId(Scanner sc, String prompt) {
    System.out.print(prompt);
    String line = sc.nextLine().trim();

    try {
        return Integer.valueOf(line);
    } catch (NumberFormatException ex) {
        throw new IllegalArgumentException("ID must be numeric: '" + line + "'");
    }
}




    private static boolean isDuplicateKeyError(Throwable t) {
        if (t == null) return false;

        if (t instanceof SQLIntegrityConstraintViolationException) {
            return true;
        }

        if (t instanceof SQLException sq) {
            // MySQL duplicate key
            if (sq.getErrorCode() == 1062) return true;

            // Postgres/H2 unique violation (SQLState 23505)
            if ("23505".equals(sq.getSQLState())) return true;
        }

        return isDuplicateKeyError(t.getCause());
    }


    private static String brief(Throwable t) {
        if (t == null) return "unknown error";
        String msg = t.getMessage();
        String cls = t.getClass().getSimpleName();
        return (msg == null || msg.isBlank()) ? cls : (cls + ": " + msg);
    }
}
