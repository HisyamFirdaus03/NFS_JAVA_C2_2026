package com.fullstack.demo;

public class Main {
    public static void main(String[] args) {
        Course javaCourse = new Course("C001", "Java Fundamentals", 40, "Beginner",
                "Programming", true);
        Course reactCourse = new Course("C002", "React Essentials", 30, "Intermediate",
                "Frontend", true);

        Instructor aina = new Instructor("I001", "Aina Rahman", "Java and Spring Boot");
        Instructor budi = new Instructor("I002", "Budi Santoso", "React and TypeScript");

        CourseOffering offering1 = new CourseOffering(
                "OFF001",
                "Java Fundamentals - June 2026 Intake",
                javaCourse,
                aina,
                "2026-06-19",
                "2026-06-20",
                25,
                "Physical");

        CourseOffering offering2 = new CourseOffering(
                "OFF002",
                "React Essentials - July 2026 Intake",
                reactCourse,
                budi,
                "2026-07-01",
                "2026-07-15",
                30,
                "Online");

        offering1.printOfferingSummary();
        System.out.println();
        offering2.printOfferingSummary();
    }
}
