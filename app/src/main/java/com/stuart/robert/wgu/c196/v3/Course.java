package com.stuart.robert.wgu.c196.v3;

import java.io.Serializable;

public class Course implements Serializable {
    String name = "";
    String startDate;
    String endDate;
    String status;
    String instructorName;
    String instructorTN;
    String instructorEmail;
    String notes;

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
}
