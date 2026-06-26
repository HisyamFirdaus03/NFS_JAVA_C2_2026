package com.fullstack.demo;

import com.fullstack.demo.exception.StudentNotFoundException;
import com.fullstack.demo.model.Student;
import com.fullstack.demo.repository.InMemoryStudentRepository;
import com.fullstack.demo.repository.StudentRepository;
import com.fullstack.demo.service.StudentService;

import java.util.List;

/**
 * Day 3 Assignment 06 - StudentService built with the same
 * repository/service/exception pattern as CourseService.
 */
public class Day3_Assignment06_StudentServicePractice {
    public static void main(String[] args) {
        // Repository first, then service (service needs a repository to work).
        StudentRepository studentRepository = new InMemoryStudentRepository();
        StudentService studentService = new StudentService(studentRepository);

        // --- Register at least 3 students ---
        System.out.println("=== Register Students ===");
        studentService.registerStudent(new Student("S001", "Roberto Chan", "roberto@example.com"));
        studentService.registerStudent(new Student("S002", "Priya Nair", "priya@example.com"));
        studentService.registerStudent(new Student("S003", "Lee Salazae", "lee@example.com"));
        System.out.println("Registered 3 students.");

        // --- Print all students ---
        System.out.println();
        System.out.println("=== All Students ===");
        List<Student> allStudents = studentService.getAllStudents();
        for (Student student : allStudents) {
            student.printProfile();
        }

        // --- Find one student by ID ---
        System.out.println("=== Find Student By ID ===");
        Student found = studentService.getStudentById("S002");
        found.printProfile();

        // --- Search students by name ---
        System.out.println("=== Search Student By Name ===");
        List<Student> matches = studentService.searchByNameUsingLoop("lee");
        for (Student student : matches) {
            System.out.println(student.getStudentId() + " - " + student.getStudentName());
        }

        // --- Missing student: throws, we catch and show a friendly message ---
        System.out.println();
        System.out.println("=== Missing Student Test ===");
        try {
            Student missing = studentService.getStudentById("S999");
            missing.printProfile();
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
