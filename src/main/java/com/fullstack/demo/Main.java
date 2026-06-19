package com.fullstack.demo;

public class Main {
    public static void main(String[] args) {
        Course course1 = new Course("C101", "Intro to Java", 40, "Beginner",
                "Programming", true);
        Instructor instructor1 = new Instructor("I001", "Aisha", "Java & Spring Boot");
        course1.setInstructor(instructor1);
        course1.printSummary();

        System.out.println();

        Course course2 = new Course("C202", "Legacy COBOL", 20, "Advanced",
                "Project", false);
        course2.printSummary();
    }
}
