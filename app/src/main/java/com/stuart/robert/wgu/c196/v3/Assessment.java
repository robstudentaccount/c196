package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;

public class Assessment {
    private int id;
    private String title;
    private String type;
    private String startDate;
    private String endDate;
    private int startNotificationID = -1;
    private int endNotificationID = -1;
    private int sectionAssessmentID;

    public int getStartNotificationID() {
        return startNotificationID;
    }

    public void setStartNotificationID(int startNotificationID) {
        this.startNotificationID = startNotificationID;
    }

    public int getEndNotificationID() {
        return this.endNotificationID;
    }

    public void setEndNotificationID(int endNotificationID) {
        this.endNotificationID = endNotificationID;
    }

    public Assessment(String title, String type) {
        this.startNotificationID = ++MainActivity.numAlert;
        this.endNotificationID = ++MainActivity.numAlert;
        System.out.println("End Notification ID " + Integer.toString(endNotificationID));
        this.title = title;
        this.type = type;
    }
    public Assessment(int id, String title, String type) {
        this.id = id;
        this.startNotificationID = ++MainActivity.numAlert;
        this.endNotificationID = ++MainActivity.numAlert;
        System.out.println("End Notification ID " + Integer.toString(endNotificationID));
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if (this.startNotificationID < 0) {
            this.startNotificationID = ++MainActivity.numAlert;
        }
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        if (this.endNotificationID < 0) {
            this.endNotificationID = ++MainActivity.numAlert;
        }
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectionAssessmentID() {
        return sectionAssessmentID;
    }

    public void setSectionAssessmentID(int sectionAssessmentID) {
        this.sectionAssessmentID = sectionAssessmentID;
    }


}
