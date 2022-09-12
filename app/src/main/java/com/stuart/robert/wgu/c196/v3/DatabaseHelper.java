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
        long insert = db.insert("sections", null, cv);
        section.setId((int) insert);
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
                "s.instructor_phone,s.instructor_email,c.notes" +
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
                Course section = new Course(id, termID, courseID, name, start_date, end_date,
                        notes, status, instructorName,instructorTN,instructorEmail);
                sections.add(section);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sections;
    }
}
