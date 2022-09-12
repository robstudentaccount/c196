package com.stuart.robert.wgu.c196.v3;

import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;

import java.time.LocalDate;
import java.util.ArrayList;

public class Term {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<Course> sections = new ArrayList<>();
    public Term(int id) {
        this.id = id;
    }
    public Term(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Term(String name, String startDate, String endDate, int id) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
