package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;


public class CourseManager extends AppCompatActivity {
    private Course selectedCourse = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_manager);

        final Toast NO_SELECTED_COURSE_TOAST = Toast.makeText(this, "You must select a course first.", Toast.LENGTH_SHORT);

        Button addBtn = (Button) findViewById(R.id.addButton);

        Intent courseIntent = new Intent(this, CourseCreate.class);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(courseIntent);
            }
        });

        Button editBtn = (Button) findViewById(R.id.editBtn);
        Intent editCourseIntent = new Intent(this, CourseCreate.class);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCourse != null) {
                    editCourseIntent.putExtra("selectedCourseName", selectedCourse.getName());
                    startActivity(editCourseIntent);
                } else {
                    NO_SELECTED_COURSE_TOAST.show();
                }
            }
        });

        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCourse != null) {
                    Courses.removeCourses(selectedCourse);
                    drawCourses();
                } else {
                    NO_SELECTED_COURSE_TOAST.show();
                }

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        drawCourses();
    }
    private void drawCourses() {
        selectedCourse = null;
        LinearLayout ll = findViewById(R.id.courseListLayout);
        ll.removeAllViews();
        RadioGroup rg = new RadioGroup(this);
        ll.addView(rg);
        for(Course course : Courses.getCourses()) {
            RadioButton courseRB = new RadioButton(this);
            courseRB.setText(course.getName());
            courseRB.setId(View.generateViewId());
            courseRB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCourse = course;
                }
            });
            rg.addView(courseRB);
        }
    }
    public Course getSelectedCourse() {
        return selectedCourse;
    }
}