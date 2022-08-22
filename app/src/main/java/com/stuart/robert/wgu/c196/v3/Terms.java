package com.stuart.robert.wgu.c196.v3;

import java.util.ArrayList;

public class Terms {
    private static ArrayList<Term> terms = new ArrayList<Term>();

    public static ArrayList<Term> getTermsArray() {
        return terms;
    }
    public static Term addNewTerm() {
        int newID = terms.size() + 1;
        Term nt = new Term(newID);
        terms.add(nt);
        return nt;
    }
    public static void addNewTerm(Term newTerm) {
        terms.add(newTerm);
        //MainActivity.addTermToView(newTerm);
    }
    public static ArrayList<Term> getTerms() {
        return terms;
    }
}