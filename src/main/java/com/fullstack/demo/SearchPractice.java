package com.fullstack.demo;

import com.fullstack.demo.model.Course;
import com.fullstack.demo.repository.CourseRepository;
import com.fullstack.demo.repository.InMemoryCourseRepository;
import com.fullstack.demo.service.CourseService;

import java.util.List;

/**
 * Day 3 Assignment 05 - Search by level using a loop, then compare with stream.
 */
public class SearchPractice {
    public static void main(String[] args) {
        CourseRepository courseRepository = new InMemoryCourseRepository();
        CourseService courseService = new CourseService(courseRepository);

        // At least four courses, with mixed levels.
        courseService.createCourse(new Course("C001", "Java Fundamentals", 14, "Beginner"));
        courseService.createCourse(new Course("C002", "React Frontend Development", 21, "Intermediate"));
        courseService.createCourse(new Course("C003", "MongoDB Basics", 9, "Beginner"));
        courseService.createCourse(new Course("C004", "Spring Boot API Development", 18, "Intermediate"));

        // --- Loop version (Task C) ---
        System.out.println("=== Beginner Courses (loop) ===");
        List<Course> beginnerCourses = courseService.searchByLevelUsingLoop("Beginner");
        for (Course course : beginnerCourses) {
            System.out.println(course.getCourseId() + " - " + course.getTitle());
        }

        // --- Stream version (Optional Task D) - should give the same result ---
        System.out.println();
        System.out.println("=== Beginner Courses (stream) ===");
        List<Course> beginnerCoursesStream = courseService.searchByLevelUsingStream("Beginner");
        for (Course course : beginnerCoursesStream) {
            System.out.println(course.getCourseId() + " - " + course.getTitle());
        }

        // --- Duration search (Optional Task E) ---
        System.out.println();
        System.out.println("=== Courses 14 hours or longer ===");
        List<Course> longCourses = courseService.searchByMinimumDurationUsingLoop(14);
        for (Course course : longCourses) {
            System.out.println(course.getCourseId() + " - " + course.getTitle()
                    + " (" + course.getDurationHours() + " hours)");
        }
    }
}
