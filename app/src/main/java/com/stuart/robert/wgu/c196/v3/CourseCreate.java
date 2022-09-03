package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CourseCreate extends AppCompatActivity {
    private Course selectedCourse = null;
    private EditText courseNotesTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        TextView courseName = (TextView) findViewById(R.id.newCourseNameTextView);
        Button addCourseBtn = (Button) findViewById(R.id.addCourseBtn);
        courseNotesTextView = (EditText) findViewById(R.id.courseCreateTextView);

        Intent i = getIntent();
        String receivedCourseName = i.getStringExtra("selectedCourseName");
        if (receivedCourseName != null) {
            selectedCourse = Courses.getCourseByName(receivedCourseName);
        }
        if (selectedCourse != null) {
            courseName.setText(selectedCourse.getName());
            courseNotesTextView.setText(selectedCourse.getNotes());
            addCourseBtn.setText("Save");
        }



        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCourse == null) {
                    Courses.addCourse(new Course(courseName.getText().toString(), courseNotesTextView.getText().toString()));
                    finish();
                } else {
                    selectedCourse.setName(courseName.getText().toString());
                    selectedCourse.setNotes(courseNotesTextView.getText().toString());
                    finish();
                }

            }
        });

        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}