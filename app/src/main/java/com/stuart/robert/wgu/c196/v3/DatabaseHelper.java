package com.stuart.robert.wgu.c196.v3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "studentportal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Terms Table
        String createTablesStatement = "CREATE TABLE " +
                "terms (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",start_date TEXT" +
                ", end_date TEXT" +
                ",name TEXT );";
        db.execSQL(createTablesStatement);

        // Courses Table
        createTablesStatement = "CREATE TABLE " +
            "courses (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
            ",name TEXT,notes TEXT);";
        db.execSQL(createTablesStatement);

        // Assessments Table
        createTablesStatement = "CREATE TABLE " +
                "assessments (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", name TEXT,assessment_type TEXT);";
        db.execSQL(createTablesStatement);

        // Sections Table
        createTablesStatement = "CREATE TABLE " +
                "sections (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", term_id INTEGER" +
                ", course_id INTEGER" +
                ", name TEXT" +
                ", start_date TEXT" +
                ", end_date TEXT" +
                ", status TEXT" +
                ", instructor_name TEXT" +
                ", instructor_phone TEXT" +
                ", instructor_email TEXT" +
                ", notification_start_id INTEGER" +
                ", notification_end_id INTEGER" +
                ", notification_start_active INTEGER" +
                ", notification_end_active INTEGER" +
                ");";
        db.execSQL(createTablesStatement);

        // Section Assessment Table
        createTablesStatement = "CREATE TABLE " +
                "section_assessment (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", section_id INTEGER" +
                ", assessment_id INTEGER" +
                ", start_date TEXT" +
                ", end_date TEXT" +
                ", notification_start_id INTEGER" +
                ", notification_end_id INTEGER" +
                ", notification_start INTEGER" +
                ", notification_end INTEGER" +
                ");";
        db.execSQL(createTablesStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addTerm(Term term) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", term.getDisplayName());
        cv.put("start_date", term.getStartDateString());
        cv.put("end_date", term.getEndDateString());
        long insert = db.insert("terms", null, cv);
        term.setId((int) insert);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Term> getTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        String query = "SELECT id, start_date,end_date,name FROM terms";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
            do {
                int id = cursor.getInt(0);
                String startDate = cursor.getString(1);
                String endDate = cursor.getString(2);
                String title = cursor.getString(3);
                Term term = new Term(title,startDate,endDate, id);
                terms.add(term);
                System.out.println(term.getDisplayName());
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return terms;
    }
    public boolean deleteTerm(Term term) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM terms WHERE id = " + term.getId();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", course.getName());
        cv.put("notes", course.getNotes());
        long insert = db.insert("courses", null, cv);
        course.setId((int) insert);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        String query = "SELECT id, name, notes FROM courses";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String notes = cursor.getString(2);
                Course course = new Course(id, name, notes);
                courses.add(course);
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return courses;
    }
    public boolean deleteCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM courses WHERE id = " + course.getId();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean updateCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE courses " +
                "SET name = '" + course.getName() + "'" +
                ", notes = '" + course.getNotes() + "'" +
                "WHERE id = " + course.getId();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean addAssessment(Assessment assessment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", assessment.getTitle());
        cv.put("assessment_type", assessment.getType());
        long insert = db.insert("assessments", null, cv);
        assessment.setId((int) insert);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Assessment> getAssessments() {
        ArrayList<Assessment> assessments = new ArrayList<>();
        String query = "SELECT id, name, assessment_type FROM assessments";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String assessment_type = cursor.getString(2);
                Assessment assessment = new Assessment(id, name, assessment_type);
                assessments.add(assessment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return assessments;
    }
    public boolean updateAssessment(Assessment assessment) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE assessments " +
                "SET name = '" + assessment.getTitle() + "'" +
                ", assessment_type = '" + assessment.getType() + "'" +
                "WHERE id = " + assessment.getId();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteAssessment(Assessment assessment) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM assessments WHERE id = " + assessment.getId();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addSection(Course section, int termID, int courseID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("term_id", termID);
        cv.put("course_id", courseID);
        cv.put("name", section.getName());
        cv.put("start_date", section.getStartDate());
        cv.put("end_date", section.getEndDate());
        cv.put("status", section.getStatus());
        cv.put("instructor_name", section.getInstructorName());
        cv.put("instructor_phone", section.getInstructorTN());
        cv.put("instructor_email", section.getInstructorEmail());
        cv.put("notification_start_id", section.getStartNotificationID());
        cv.put("notification_end_id", section.getEndNotificationID());
        cv.put("notification_start_active", section.getNotificationStartActive());
        cv.put("notification_end_active", section.getNotificationEndActive());

        long insert = db.insert("sections", null, cv);
        section.setSectionID((int) insert);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Course> getSections() {
        ArrayList<Course> sections = new ArrayList<>();
        String query = "SELECT s.id, s.term_id, s.course_id, " +
                "s.name,s.start_date,s.end_date,s.status,s.instructor_name," +
                "s.instructor_phone,s.instructor_email,c.notes," +
                "notification_start_id, notification_end_id," +
                "notification_start_active," +
                "notification_end_active" +
                " FROM sections s JOIN courses c ON c.id = s.course_id";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
            do {
                int id = cursor.getInt(0);
                int termID = cursor.getInt(1);
                int courseID = cursor.getInt(2);
                String name = cursor.getString(3);
                String start_date = cursor.getString(4);
                String end_date = cursor.getString(5);
                String status = cursor.getString(6);
                String instructorName = cursor.getString(7);
                String instructorTN = cursor.getString(8);
                String instructorEmail = cursor.getString(9);
                String notes = cursor.getString(10);
                int startNotificationID = cursor.getInt(11);;
                int endNotificationID = cursor.getInt(12);
                int notificationStartActive = cursor.getInt(13);
                int notificationEndActive = cursor.getInt(14);
                Course section = new Course(id, termID, courseID, name, start_date, end_date,
                        notes, status, instructorName,instructorTN,instructorEmail);
                section.setStartNotificationID(startNotificationID);
                section.setEndNotificationID(endNotificationID);
                section.setNotificationStartActive(notificationStartActive);
                section.setNotificationEndActive(notificationEndActive);
                sections.add(section);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sections;
    }
    public boolean updateSection(Course section) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE sections " +
                "SET name = '" + section.getName() + "'" +
                ", start_date = '" + section.getStartDate() + "'" +
                ", end_date = '" + section.getEndDate() + "'" +
                ", status = '" + section.getStatus() + "'" +
                ", instructor_name = '" + section.getInstructorName() + "'" +
                ", instructor_phone = '" + section.getInstructorTN() + "'" +
                ", instructor_email = '" + section.getInstructorEmail() + "'" +
                ", notification_start_active = '" + section.getNotificationStartActive() + "'" +
                ", notification_end_active = '" + section.getNotificationEndActive() + "'" +
                "WHERE id = " + section.getSectionID();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteSection(Course section) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM sections WHERE id = " + section.getId();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean addSectionAssessment(int sectionID, Assessment assessment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("section_id", sectionID);
        cv.put("assessment_id", assessment.getId());
        System.out.println("Heeey: " + String.valueOf(assessment.getId()));
        cv.put("start_date", assessment.getStartDate());
        cv.put("end_date", assessment.getEndDate());
        cv.put("end_date", assessment.getEndDate());
        cv.put("notification_start_id", assessment.getStartNotificationID());
        cv.put("notification_end_id", assessment.getEndNotificationID());
        cv.put("notification_start", assessment.getNotifyStart());
        cv.put("notification_end", assessment.getNotifyEnd());
        long insert = db.insert("section_assessment", null, cv);
        assessment.setSectionAssessmentID((int) insert);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Assessment> getSectionAssessments(int sectionID) {
        ArrayList<Assessment> assessments = new ArrayList<>();
        String query = "SELECT sa.id, sa.section_id, sa.assessment_id, sa.start_date, sa.end_date " +
                ",a.name, a.assessment_type, notification_start_id, notification_end_id" +
                ",notification_start, notification_end" +
                " FROM section_assessment sa " +
                "JOIN assessments a ON a.id = sa.assessment_id " +
                "WHERE section_id = " + sectionID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
            do {
                int id = cursor.getInt(0);
                int section_id = cursor.getInt(1);
                int assessment_id = cursor.getInt(2);
                String start_date = cursor.getString(3);
                String end_date = cursor.getString(4);
                String assessmentTitle = cursor.getString(5);
                String assessmentType = cursor.getString(6);
                int startNotificationID = cursor.getInt(7);
                int endNotificationID = cursor.getInt(8);
                int startNotification = cursor.getInt(9);
                int endNotification = cursor.getInt(10);
                Assessment assessment = new Assessment(assessmentTitle, assessmentType);
                assessment.setSectionAssessmentID(id);
                assessment.setType(assessmentType);
                assessment.setStartDate(start_date);
                assessment.setEndDate(end_date);
                assessment.setId(assessment_id);
                assessment.setStartNotificationID(startNotificationID);
                assessment.setEndNotificationID(endNotificationID);
                assessment.setNotifyStart(startNotification);
                assessment.setNotifyEnd(endNotification);
                assessments.add(assessment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return assessments;
    }
    public boolean deleteSectionAssessments(int sectionID) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM section_assessment " +
                "WHERE section_id = " + sectionID;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteSectionAssessment(int sectionAssessmentID) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM section_assessment " +
                "WHERE id = " + sectionAssessmentID;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public int getBiggestNotificationID() {
        int maxID = -1;

        String asQuery = "SELECT MAX(notification_start_id, notification_end_id)" +
                "FROM section_assessment";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(asQuery,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
                int m1 = cursor.getInt(0);
                if (m1 > maxID) {
                    maxID = m1;
                }
        }

        String aQuery = "SELECT MAX(notification_start_id, notification_end_id)" +
                "FROM sections";
        db = this.getReadableDatabase();
        cursor = db.rawQuery(asQuery,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
            int m2 = cursor.getInt(0);
            if (m2 > maxID) {
                maxID = m2;
            }
        }
        return maxID;
    }
}
