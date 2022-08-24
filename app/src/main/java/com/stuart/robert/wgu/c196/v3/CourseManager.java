package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CourseManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_manager);

        Button addBtn = (Button) findViewById(R.id.addButton);

        Intent courseIntent = new Intent(this, CourseName.class);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(courseIntent);
            }
        });



    }
}