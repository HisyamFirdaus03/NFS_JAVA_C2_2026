package com.fullstack.demo;

import com.fullstack.demo.model.Course;
import com.fullstack.demo.repository.CourseRepository;
import com.fullstack.demo.repository.InMemoryCourseRepository;
import com.fullstack.demo.service.CourseService;

/**
 * Day 3 Assignment 01 - Build and trace the code flow.
 *
 * Layers involved (top calls down into the next):
 *   CodeFlowPractice -> CourseService -> CourseRepository
 *                       -> InMemoryCourseRepository -> LinkedHashMap
 */
public class CodeFlowPractice {
    public static void main(String[] args) {
        System.out.println("=== Add and Find Course ===");

        // --- Task A: create the repository and service ---

        // Why the repository first? The service can't store or find anything on
        // its own - it delegates to a repository. So the repository (the thing
        // that actually holds the data) must exist before we can hand it over.
        // We use the InMemoryCourseRepository, which keeps courses in a
        // LinkedHashMap in memory.
        CourseRepository courseRepository = new InMemoryCourseRepository();

        // Why does CourseService need CourseRepository? Because the service
        // owns the business rules (validation, duplicate checks) but NOT the
        // storage. We inject the repository through the constructor so the
        // service has something to save to and read from. The type is the
        // CourseRepository *interface*, so we could swap in a database-backed
        // repository later without changing the service.
        CourseService courseService = new CourseService(courseRepository);

        // --- Task B: create one new course and save it THROUGH the service ---
        Course springBootCourse = new Course(
                "C004",
                "Spring Boot API Development",
                18,
                "Intermediate");

        // Go through the service, not the repository directly: the service
        // validates the course and rejects duplicates before saving.
        courseService.createCourse(springBootCourse);

        // --- Task C: retrieve the same course BY ID through the service ---
        // getCourseById returns a Course directly (the service unwraps the
        // Optional from the repository, or throws CourseNotFoundException).
        Course foundCourse = courseService.getCourseById("C004");
        foundCourse.printSummary();

        // --- Task D: trace of what just happened ---
        // 1. This demo class calls CourseService (createCourse / getCourseById).
        // 2. CourseService validates the course and checks for duplicates.
        // 3. CourseService asks CourseRepository to save or find the course.
        // 4. InMemoryCourseRepository stores/looks up the course in its
        //    LinkedHashMap (in memory).
        // 5. The Course object travels back up and is returned to this class,
        //    where we print it.
    }
}
