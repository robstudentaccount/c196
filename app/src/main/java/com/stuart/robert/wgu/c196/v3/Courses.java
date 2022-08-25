package com.stuart.robert.wgu.c196.v3;

import java.util.ArrayList;

public class Courses {
    private static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Course> getCourses() {
        return courses;
    }
    public static void addCourse(Course newCourse) {
        for (Course existingCourse : courses) {
            if (existingCourse.getName() == newCourse.getName()) {
                System.out.println("Course already exists.");
                return;
            }
        }
        courses.add(newCourse);
    }
}
