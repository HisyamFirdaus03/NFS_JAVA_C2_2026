package com.fullstack.demo;

import com.fullstack.demo.model.Course;
import com.fullstack.demo.repository.CourseRepository;
import com.fullstack.demo.repository.InMemoryCourseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Day 3 Assignment 02 - Interface and Repository Storage Practice.
 *
 * This demo talks to the repository DIRECTLY (no service layer), so you can
 * see where the data actually lives before the service gets involved.
 */
public class RepositoryPractice {
    public static void main(String[] args) {
        // --- Task A: create the repository using the INTERFACE type ---
        // The variable type is CourseRepository (the interface = what actions
        // are available), but the actual object is InMemoryCourseRepository
        // (the implementation = how those actions work today, using a
        // LinkedHashMap). We could swap in a different implementation later
        // without changing the code below.
        CourseRepository courseRepository = new InMemoryCourseRepository();

        // --- Task B: save three courses directly through the repository ---
        Course apiCourse = new Course("C005", "API Documentation", 7, "Beginner");
        courseRepository.save(apiCourse);

        Course collectionsCourse = new Course("C006", "Java Collections Practice", 12, "Beginner");
        courseRepository.save(collectionsCourse);

        Course cleanCodeCourse = new Course("C007", "Clean Code Basics", 8, "Intermediate");
        courseRepository.save(cleanCodeCourse);

        // --- Task C: print all courses ---
        System.out.println("=== All Courses ===");
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            course.printSummary();
            System.out.println();
        }

        // --- Task D: find one course using Optional ---
        // findById returns Optional<Course>, not a Course directly. Optional is
        // a box that may or may not hold a value - it forces us to handle the
        // "not found" case instead of risking a null.
        System.out.println("=== Find C006 ===");
        Optional<Course> optionalCourse = courseRepository.findById("C006");
        if (optionalCourse.isPresent()) {
            Course foundCourse = optionalCourse.get();
            foundCourse.printSummary();
        } else {
            System.out.println("Course not found.");
        }

        // --- Task E: check if a course exists ---
        System.out.println();
        System.out.println("=== Exists Check ===");
        System.out.println("C007 exists: " + courseRepository.existsById("C007"));
    }
}
