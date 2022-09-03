package com.stuart.robert.wgu.c196.v3;

import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;

import java.time.LocalDate;
import java.util.ArrayList;

public class Term {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Course> sections = new ArrayList<>();
    public Term(int id) {
        this.id = id;
    }
    public Term(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getDisplayName() {
        return this.name;
    }
    public String getStartDateString() {
        return startDate.toString();
    }
    public String getEndDateString() {
        return endDate.toString();
    }
    public void addSection(Course course) {
        sections.add(course);
    }
    public Course getSectionByID(String id) {
        for (Course section: sections) {
            if (section.getName().equals(id)) {
                return section;
            }
        }
        return null;
    }
    public void removeSection(Course section) {
        sections.remove(section);
    }
    public ArrayList<Course> getSections () {
        return sections;
    }
}
