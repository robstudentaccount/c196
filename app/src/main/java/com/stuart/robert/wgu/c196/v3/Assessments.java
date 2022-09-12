package com.stuart.robert.wgu.c196.v3;

import java.util.ArrayList;

public class Assessments {
    private static ArrayList<Assessment> assessments = new ArrayList<>();
    public static void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }
    public static void addAssessments(ArrayList<Assessment> as) {
        for (Assessment a : as) {
            assessments.add(a);
        }
    }
    public static void removeAssessment(Assessment assessment) {
        assessments.remove(assessment);
    }
    public static ArrayList<Assessment> getAssessments () {
        return assessments;
    }
    public static Assessment getAssessmentByID(int id) {
        for (Assessment assessment : assessments) {
            if (assessment.getId() == id) {
                return assessment;
            }
        }
        return null;
    }

}
