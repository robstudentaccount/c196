package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AssessmentManagement extends AppCompatActivity {

    private RadioGroup rg;
    private Button addBtn;
    private Button deleteBtn;
    private Button editAssessmentBtn;
    private Assessment selectedAssessment;
    private Toast assessmentSelectErr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_management);

        assessmentSelectErr = Toast.makeText(AssessmentManagement.this, "You must select a assessment first", Toast.LENGTH_SHORT);
        rg = (RadioGroup) findViewById(R.id.assessmentManageRadioGroup);

        addBtn = (Button) findViewById(R.id.addAssessmentButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentManagement.this, AssessmentCreate.class);
                startActivity(intent);
            }
        });

        deleteBtn = (Button) findViewById(R.id.deleteAssessmentBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAssessment != null) {
                    Assessments.removeAssessment(selectedAssessment);
                    selectedAssessment = null;
                    drawAssessments();
                } else {
                    assessmentSelectErr.show();
                }
            }
        });

        editAssessmentBtn = (Button) findViewById(R.id.editAssessmentBtn);
        editAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAssessment != null) {
                    Intent intent = new Intent(AssessmentManagement.this, AssessmentCreate.class);
                    intent.putExtra("selectedAssessmentID", selectedAssessment.getId());
                    startActivity(intent);
                } else {
                    assessmentSelectErr.show();
                }
            }
        });

        drawAssessments();

    }

    @Override
    protected void onResume() {
        super.onResume();
        drawAssessments();
    }

    private void drawAssessments() {
        rg.removeAllViews();
        for (Assessment assessment : Assessments.getAssessments()) {
            RadioButton newRB = new RadioButton(this);
            newRB.setId(View.generateViewId());
            newRB.setText(assessment.getTitle());
            newRB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedAssessment = assessment;
                }
            });

            rg.addView(newRB);
        }
    }
}