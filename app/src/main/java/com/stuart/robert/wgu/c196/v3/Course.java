package com.stuart.robert.wgu.c196.v3;

import java.io.Serializable;

public class Course implements Serializable {
    String name = "";
    String startDate;
    String endDate;
    String status;
    public Course(String name) {
        this.name = name;
    }
    public Course (String name, String startDate, String endDate, String status) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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
}
