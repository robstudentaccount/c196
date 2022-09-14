package com.stuart.robert.wgu.c196.v3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SectionAssessmentDialog extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener pickedDate;
    private final Calendar myCalendarStart = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    private int selectedSectionID;

    private RadioGroup rg;
    private Button cancelButton;
    private Button addButton;
    private Button deleteButton;
    private EditText assessmentStartDate;
    private EditText assessmentEndDate;
    private Assessment selectedAssessment;
    private TextView title;
    private ScrollView assessmentScrollView;
    private Assessment pickedAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_assessment_dialog);

        Intent intent = getIntent();
        selectedSectionID = intent.getIntExtra("selectedSectionID", -1);


        addButton = (Button) findViewById(R.id.assessmentSectionAddButton);
        cancelButton = (Button) findViewById(R.id.assessmentSectionCancelBtn);
        assessmentStartDate = (EditText) findViewById(R.id.assessmentSectionStartDate);
        assessmentEndDate = (EditText) findViewById(R.id.assessmentSectionEndDate);
        deleteButton = (Button) findViewById(R.id.assessmentSectionDeleteButton);
        title = (TextView) findViewById(R.id.titleLabel);
        rg = (RadioGroup) findViewById(R.id.sectionAssessmentRadioGroup);
        assessmentScrollView = (ScrollView) findViewById(R.id.assessmentScrollView);

        String selectedAssessmentID = intent.getStringExtra("selectedAssessmentID");
        int selectedSectionAssessmentID = intent.getIntExtra("selectedSectionAssessmentID", -1);
        selectedAssessment = courseSection.getAssessmentBySectionAssessmentID(selectedSectionAssessmentID);

        if (selectedAssessmentID != null) {
            title.setText("Assessment: " + selectedAssessment.getTitle());
            assessmentScrollView.setVisibility(View.GONE);
            assessmentStartDate.setText(selectedAssessment.getStartDate());
            assessmentEndDate.setText(selectedAssessment.getEndDate());
            addButton.setText("Save");
        } else {
            deleteButton.setVisibility(View.GONE);
        }

        LinearLayout.LayoutParams rbParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        rg.setLayoutParams(rbParams);
        rg.removeAllViews();
        for (Assessment assessment : Assessments.getAssessments()) {
            RadioButton rb = new RadioButton(this);
            rb.setId(View.generateViewId());
            rb.setText(assessment.getTitle());
            rb.setLayoutParams(rbParams);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pickedAssessment = assessment;
                }
            });
            rg.addView(rb);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAssessment != null) {
                    selectedAssessment.setStartDate(assessmentStartDate.getText().toString());
                    selectedAssessment.setEndDate(assessmentEndDate.getText().toString());
                    String endMSG = "Assessment " + selectedAssessment.getTitle() + " ends today!";
                    String startMSG = "Assessment " + selectedAssessment.getTitle() + " starts today!";
                    createNotification(assessmentEndDate.getText().toString(), endMSG, selectedAssessment.getEndNotificationID());
                    createNotification(assessmentEndDate.getText().toString(), startMSG, selectedAssessment.getStartNotificationID());

                } else {
                    System.out.println("CLICKED!!");
                    Assessment a = new Assessment(pickedAssessment.getTitle(),
                            pickedAssessment.getType());
                    a.setStartDate(assessmentStartDate.getText().toString());
                    a.setEndDate(assessmentEndDate.getText().toString());
                    a.setId(pickedAssessment.getId());
                    String endMSG = "Assessment " + a.getTitle() + " ends today!";
                    String startMSG = "Assessment " + a.getTitle() + " starts today!";
                    createNotification(assessmentEndDate.getText().toString(), endMSG, a.getEndNotificationID());
                    createNotification(assessmentEndDate.getText().toString(), startMSG, a.getStartNotificationID());
                    boolean b = courseSection.addAssessment(a);
                }
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseSection.deleteAssessment(selectedAssessment);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        View.OnClickListener calendarClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                TextView textView = (TextView) view;
                String info = textView.getText().toString();
                if (info.equals("")) info = "03/22/2022";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                pickedDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        myCalendarStart.set(Calendar.YEAR, year);
                        myCalendarStart.set(Calendar.MONTH, monthOfYear);
                        myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        textView.setText(sdf.format(myCalendarStart.getTime()));
                    }
                };


                new DatePickerDialog(SectionAssessmentDialog.this, pickedDate,
                        myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        };
        assessmentStartDate.setOnClickListener(calendarClick);
        assessmentEndDate.setOnClickListener(calendarClick);
    }

    private void createNotification(String date, String msg, int id) {
        // End Date Notification
        Date future;
        Long trigger = null;
        try {
            future = sdf.parse(date);
            trigger =future.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intent=new Intent(getApplicationContext(),TermsBroadcastReceiver.class);
        intent.putExtra("key",msg);
        PendingIntent sender=PendingIntent.getBroadcast(getApplicationContext(),id,intent,0);
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
    }

}