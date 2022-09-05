package com.stuart.robert.wgu.c196.v3;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String name = "";
    private String startDate;
    private String endDate;
    private String status;
    private String instructorName;
    private String instructorTN;
    private String instructorEmail;
    private String notes;
    private ArrayList<Assessment> assessments = new ArrayList<>();

    public Course(String name, String notes) {
        this.name = name;
        this.notes = notes;
    }
    public Course (String name, String startDate, String endDate, String notes,
                   String status, String instructorName, String instructorTN,
                   String instructorEmail)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorTN = instructorTN;
        this.instructorEmail = instructorEmail;
        this.notes = notes;
        this.assessments.clear();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorTN() {
        return instructorTN;
    }

    public void setInstructorTN(String instructorTN) {
        this.instructorTN = instructorTN;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void addAssessment(Assessment assessment) {
        this.assessments.add(assessment);
    }
    public ArrayList<Assessment> getAssessments() {
        return this.assessments;
    }
    public void clearAssessments() {
        this.assessments.clear();
    }
}
