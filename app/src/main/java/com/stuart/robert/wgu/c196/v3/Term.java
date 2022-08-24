package com.stuart.robert.wgu.c196.v3;

import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;

import java.time.LocalDate;

public class Term {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

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

}
