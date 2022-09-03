package com.stuart.robert.wgu.c196.v3;

import java.io.Serializable;

public class Course implements Serializable {
    String name = "";
    String startDate;
    String endDate;
    public Course(String name) {
        this.name = name;
    }
    public Course (String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
