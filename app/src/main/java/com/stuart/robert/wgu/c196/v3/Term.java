package com.stuart.robert.wgu.c196.v3;

public class Term {
    private int id;
    private String name;

    public Term(int id) {
        this.id = id;
    }
    public Term(String name) {
        //this.id = name;
        this.name = name;
    }
    public String getDisplayName() {
        return "Term " + this.name;
    }
}
