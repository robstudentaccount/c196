package com.stuart.robert.wgu.c196.v3;

import java.util.ArrayList;

public class Courses {
    private static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Course> getCourses() {
        return courses;
    }
    public static void addCourse(Course newCourse) {
        Course existingCourse = getCourseByName(newCourse.getName());
        if (existingCourse == null) {
            courses.add(newCourse);
        }
    }
    public static Course getCourseByName(String courseName) {
        for (Course existingCourse : courses) {
            if (existingCourse.getName().equals(courseName)) {
                System.out.println("Course already exists.");
                return existingCourse;
            }
        }
        return null;
    }
    public static void removeCourses(Course course) {
        courses.remove(course);
    }
}
