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
        String createTablesStatement = "CREATE TABLE " +
                "terms (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",start_date TEXT" +
                ", end_date TEXT" +
                ",name TEXT )";
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
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Term> getTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        String query = "SELECT * FROM terms";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            // loop through result and add them to return list.
            do {
                int termID = cursor.getInt(0);
                String startDate = cursor.getString(1);
                String endDate = cursor.getString(2);
                String title = cursor.getString(3);
                Term term = new Term(title,startDate,endDate);
                terms.add(term);
                System.out.println(term.getDisplayName());
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return terms;
    }
}
