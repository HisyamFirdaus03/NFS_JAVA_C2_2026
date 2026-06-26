package com.fullstack.demo;

import com.fullstack.demo.exception.CourseNotFoundException;
import com.fullstack.demo.model.Course;
import com.fullstack.demo.repository.CourseRepository;
import com.fullstack.demo.repository.InMemoryCourseRepository;
import com.fullstack.demo.service.CourseService;

/**
 * Day 3 Assignment 03 - Exception practice.
 *
 * The service THROWS when something is wrong; this demo (the caller) decides
 * how to react - here we catch and print a friendly message instead of letting
 * the program crash.
 */
public class ExceptionPractice {
    public static void main(String[] args) {
        // --- Task A: set up the service ---
        CourseRepository courseRepository = new InMemoryCourseRepository();
        CourseService courseService = new CourseService(courseRepository);

        // --- Task B: add two courses through the service ---
        courseService.createCourse(new Course("C001", "Java Fundamentals", 14, "Beginner"));
        courseService.createCourse(new Course("C002", "React Frontend Development", 21, "Intermediate"));

        // --- Task C: find an existing course (works normally) ---
        System.out.println("=== Find C001 (exists) ===");
        Course course = courseService.getCourseById("C001");
        course.printSummary();

        // --- Task D: find a missing course and catch the exception ---
        System.out.println();
        System.out.println("=== Find C999 (missing) ===");
        try {
            Course missingCourse = courseService.getCourseById("C999");
            missingCourse.printSummary();
        } catch (CourseNotFoundException e) {
            System.out.println("Friendly message for user: " + e.getMessage());
        }

        // --- Task E: a second try/catch with a different friendly message ---
        System.out.println();
        System.out.println("=== Find C888 (missing) ===");
        try {
            Course anotherMissing = courseService.getCourseById("C888");
            anotherMissing.printSummary();
        } catch (CourseNotFoundException e) {
            System.out.println("Cannot display course details because the course does not exist.");
        }

        System.out.println();
        System.out.println("Program finished without crashing.");
    }
}
