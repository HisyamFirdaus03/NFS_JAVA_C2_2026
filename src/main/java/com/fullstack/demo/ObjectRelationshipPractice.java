package com.fullstack.demo;

import com.fullstack.demo.model.Course;
import com.fullstack.demo.model.CourseOffering;
import com.fullstack.demo.model.Instructor;

/**
 * Day 3 Assignment 04 - Object relationships and composition.
 *
 * Composition = "one object HAS another object":
 *   Course has an Instructor
 *   CourseOffering has a Course AND has an Instructor
 * We link real objects together, not just copy their text.
 */
public class ObjectRelationshipPractice {
    public static void main(String[] args) {
        // --- Task A: create two instructors ---
        Instructor aina = new Instructor("I001", "Aina Rahman", "Java and Spring Boot");
        Instructor marcus = new Instructor("I002", "Marcus Lee", "React and Frontend Development");

        // --- Task B: create two courses ---
        Course javaCourse = new Course("C001", "Java Fundamentals", 14, "Beginner");
        Course reactCourse = new Course("C002", "React Frontend Development", 21, "Intermediate");

        // --- Task C: assign instructors to courses (Course HAS an Instructor) ---
        javaCourse.setInstructor(aina);
        reactCourse.setInstructor(marcus);

        System.out.println("=== Courses ===");
        javaCourse.printSummary();
        System.out.println();
        reactCourse.printSummary();

        // --- Task D + F: create course offerings ---
        // CourseOffering uses COMPOSITION because it HAS a Course and HAS an
        // Instructor. We pass the actual Course and Instructor objects (not just
        // their names as text), so an offering points at the real objects and
        // can read their up-to-date details.
        CourseOffering offering1 = new CourseOffering(
                "OFF001",
                "Java Fundamentals June Intake",
                javaCourse,
                aina,
                "2026-06-29",
                "2026-06-30",
                25,
                "Physical");

        CourseOffering offering2 = new CourseOffering(
                "OFF002",
                "React Frontend July Intake",
                reactCourse,
                marcus,
                "2026-07-01",
                "2026-07-03",
                20,
                "Hybrid");

        // --- Extension: a THIRD offering that REUSES the same javaCourse ---
        // Same course template, different schedule. This is exactly why Course
        // and CourseOffering are separate: one course can run many times.
        CourseOffering offering3 = new CourseOffering(
                "OFF003",
                "Java Fundamentals July Weekend Intake",
                javaCourse,
                aina,
                "2026-07-18",
                "2026-07-19",
                15,
                "Online");

        // --- Task E: print the course offerings ---
        System.out.println();
        System.out.println("=== Course Offerings ===");
        offering1.printSummary();
        System.out.println();
        offering2.printSummary();
        System.out.println();
        offering3.printSummary();
    }
}
