package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AssessmentCreate extends AppCompatActivity {
    private Button addBtn;
    private Button cancelBtn;
    private EditText assessmentNameTextView;
    private Spinner assessmentTypeSpinner;
    private Assessment selectedAssessment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_create);

        Intent intent = getIntent();
        int getSelectedAssessmentID = intent.getIntExtra("selectedAssessmentID", -1);
        selectedAssessment = Assessments.getAssessmentByID(getSelectedAssessmentID);



        addBtn = (Button) findViewById(R.id.assessmentAddBtn);
        cancelBtn = (Button) findViewById(R.id.assessmentCancelBtn);
        assessmentNameTextView = (EditText) findViewById(R.id.assessmentNameEditText);
        assessmentTypeSpinner = (Spinner) findViewById(R.id.assessmentTypeSpinner);

        if (selectedAssessment != null) {
            addBtn.setText("Save");
            assessmentNameTextView.setText(selectedAssessment.getTitle());
            switch (selectedAssessment.getType()) {
                case "Objective":
                    assessmentTypeSpinner.setSelection(1);
                    break;
                case "Performance":
                    assessmentTypeSpinner.setSelection(2);
                    break;
                default:
                    assessmentTypeSpinner.setSelection(0);
                    break;
            }
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                if (selectedAssessment != null) {
                    selectedAssessment.setTitle(assessmentNameTextView.getText().toString());
                    selectedAssessment.setType(assessmentTypeSpinner.getSelectedItem().toString());
                    databaseHelper.updateAssessment(selectedAssessment);
                } else {
                    Assessment newAssessment = new Assessment(assessmentNameTextView.getText().toString()
                            , assessmentTypeSpinner.getSelectedItem().toString());
                    databaseHelper.addAssessment(newAssessment);
                    Assessments.addAssessment(newAssessment);
                }
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}