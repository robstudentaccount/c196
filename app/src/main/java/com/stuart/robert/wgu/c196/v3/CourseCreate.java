package com.stuart.robert.wgu.c196.v3;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
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
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                if (selectedCourse == null) {
                    Course newCourse = new Course(courseName.getText().toString(), courseNotesTextView.getText().toString());
                    databaseHelper.addCourse(newCourse);
                    Courses.addCourse(newCourse);
                } else {
                    selectedCourse.setName(courseName.getText().toString());
                    selectedCourse.setNotes(courseNotesTextView.getText().toString());
                    databaseHelper.updateCourse(selectedCourse);
                }
                finish();
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
    public void shareClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:"));  // This ensures only SMS apps respond
        intent.putExtra(Intent.EXTRA_TEXT, courseNotesTextView.getText().toString());
        intent.putExtra(Intent.EXTRA_TITLE, "Course Notes");
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, null);
        startActivity(shareIntent);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        startActivity(intent);


    }
}