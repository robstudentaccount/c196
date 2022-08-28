package com.stuart.robert.wgu.c196.v3;

import java.io.Serializable;

public class Course implements Serializable {
    String name = "";
    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
