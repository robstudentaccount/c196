package com.stuart.robert.wgu.c196.v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class courseSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_section);
        drawCourses();
    }
    private void drawCourses() {
        LinearLayout courseListView = (LinearLayout) findViewById(R.id.courseListView);
        TextView t = new TextView(this);
        t.setText("HHHHHHHHHHHHHHHHHHHHHHHH");
        courseListView.addView(t);
    }
}