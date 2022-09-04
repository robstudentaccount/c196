package com.stuart.robert.wgu.c196.v3;

import java.util.ArrayList;

public class Assessments {
    private static ArrayList<Assessment> assessments = new ArrayList<>();
    public static void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }
    public static void removeAssessment(Assessment assessment) {
        assessments.remove(assessment);
    }
    public static ArrayList<Assessment> getAssessments () {
        return assessments;
    }
    public static Assessment getAssessmentByID(String id) {
        for (Assessment assessment : assessments) {
            if (assessment.getTitle().equals(id)) {
                return assessment;
            }
        }
        return null;
    }

}
