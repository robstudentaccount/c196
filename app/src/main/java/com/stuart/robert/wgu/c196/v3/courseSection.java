package com.stuart.robert.wgu.c196.v3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class courseSection extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener pickedDate;
    private final Calendar myCalendarStart = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    private Course selectedCourse;
    private String selectedTermName;
    private Term selectedTerm;
    private String selectedSectionID;
    private Course selectedSection;
    private Assessment selectedAssessment;

    TextView startDateTxt;
    TextView endDateTxt;
    Button cancelBtn;
    Button addBtn;
    Spinner sectionStatus;
    Button deleteBtn;
    TextView instructorNameTxt;
    TextView instructorTN;
    TextView instructorEmail;
    TextView courseNotes;
    ScrollView courseScrollView;
    TextView courseSelectTitle;
    private LinearLayout courseSectionAssessmentLayout;
    private static ArrayList<Assessment> tempAssessmentList = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        drawAssessments();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_section);

        Intent intent = getIntent();
        selectedTermName = intent.getStringExtra("Term Name");
        selectedTerm = Terms.getTermByName(selectedTermName);
        selectedSectionID = intent.getStringExtra("SectionID");
        selectedSection = selectedTerm.getSectionByID(selectedSectionID);

        startDateTxt = (TextView) findViewById(R.id.startDateTxt);
        endDateTxt = (TextView) findViewById(R.id.endDateText);
        cancelBtn = (Button) findViewById(R.id.courseSectionCancelBtn);
        addBtn = (Button) findViewById(R.id.courseSectionAddBtn);
        sectionStatus = (Spinner) findViewById(R.id.sectionStatusSpinner);
        deleteBtn = (Button) findViewById(R.id.courseSectionDeleteBtn);
        deleteBtn.setVisibility(View.GONE);
        instructorNameTxt = (EditText) findViewById(R.id.instructorNameTxt);
        instructorTN = (EditText) findViewById(R.id.instructorPhoneNumberTextView);
        instructorEmail = (EditText) findViewById(R.id.instructorEmailTextView);
        courseNotes = (EditText) findViewById(R.id.courseNotesTextView);
        courseScrollView = (ScrollView) findViewById(R.id.scrollView2);
        courseSelectTitle = (TextView) findViewById(R.id.courseSectionSelectCourseTextView);
        courseSectionAssessmentLayout = (LinearLayout) findViewById(R.id.courseSectionAssessmentLayout);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTerm.removeSection(selectedSection);
                finish();
            }
        });

        drawCourses();

        if (selectedSection != null) {
            courseSelectTitle.setText("Course: " + selectedSection.getName());
            courseScrollView.setVisibility(View.GONE);
            startDateTxt.setText(selectedSection.getStartDate());
            endDateTxt.setText(selectedSection.getEndDate());
            addBtn.setText("Save");
            deleteBtn.setVisibility(View.VISIBLE);
            instructorNameTxt.setText(selectedSection.getInstructorName());
            instructorTN.setText(selectedSection.getInstructorTN());
            instructorEmail.setText(selectedSection.getInstructorEmail());
            courseNotes.setText(selectedSection.getNotes());
            switch (selectedSection.getStatus()) {
                case "In Progress":
                    sectionStatus.setSelection(1);
                    break;
                case "Completed":
                    sectionStatus.setSelection(2);
                    break;
                case "Dropped":
                    sectionStatus.setSelection(3);
                    break;
                case "Plan to Take":
                    sectionStatus.setSelection(4);
                    break;
                default:
                    System.out.println("STATUS: " + selectedSection.getStatus());
            }


            System.out.println(selectedSection.getStartDate());
        }

        View.OnClickListener calendarClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                TextView textView = (TextView)view;
                String info = textView.getText().toString();
                if(info.equals(""))info="03/22/2022";
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


                new DatePickerDialog(courseSection.this, pickedDate,
                        myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        };

        startDateTxt.setOnClickListener(calendarClick);
        endDateTxt.setOnClickListener(calendarClick);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Pick a course, start date and end date.", Toast.LENGTH_SHORT);
                if (startDateTxt.getText().toString().equals("") | endDateTxt.getText().toString().equals("")) {
                    toast.show();
                    return;
                }

                if (selectedSection != null) {
                    selectedSection.setStartDate(startDateTxt.getText().toString());
                    selectedSection.setEndDate(endDateTxt.getText().toString());
                    selectedSection.setName(selectedCourse.getName());
                    selectedSection.setStatus(sectionStatus.getSelectedItem().toString());
                    selectedSection.setInstructorName(instructorNameTxt.getText().toString());
                    selectedSection.setInstructorTN(instructorTN.getText().toString());
                    selectedSection.setInstructorEmail(instructorEmail.getText().toString());
                } else {
                    selectedCourse.setStartDate(startDateTxt.getText().toString());
                    selectedCourse.setEndDate(endDateTxt.getText().toString());

                    Course section = new Course(selectedCourse.getName(),
                            selectedCourse.startDate, selectedCourse.endDate,
                            selectedCourse.notes,
                            sectionStatus.getSelectedItem().toString(),
                            instructorNameTxt.getText().toString(),
                            instructorTN.getText().toString(),
                            instructorEmail.getText().toString());
                    selectedTerm.addSection(section);
                }
                finish();
            }
        });




    }
    private void drawCourses() {
        LinearLayout courseListView = (LinearLayout) findViewById(R.id.courseListView);
        courseListView.removeAllViews();
        RadioGroup rg = new RadioGroup(this);
        courseListView.addView(rg);
        for (Course course : Courses.getCourses()) {
            RadioButton courseRT = new RadioButton(this);
            courseRT.setId(View.generateViewId());
            courseRT.setText(course.getName());

            if (selectedSection != null) {
                courseRT.setEnabled(false);
                if (course.getName().equals(selectedSection.getName())) {
                    courseRT.setChecked(true);
                    selectedCourse = course;
                    courseNotes.setText(selectedCourse.getNotes());
                }
            }

            courseRT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCourse = course;
                    courseNotes.setText(course.getNotes());
                }


            });
            drawAssessments();
            rg.addView(courseRT);

        }
    }

    private void drawAssessments() {
        ConstraintLayout.LayoutParams assessmentParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);

        courseSectionAssessmentLayout.removeAllViews();
        for (Assessment assessment : tempAssessmentList) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            TextView assessmentTV = new TextView(this);
            assessmentTV.setText(assessment.getTitle() + " By: " + assessment.getEndDate());
            assessmentTV.setLayoutParams(assessmentParams);
            row.addView(assessmentTV);

            ImageView readMore = new ImageView(this);
            readMore.setImageResource(R.drawable.ic_read_more);
            readMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedAssessment = assessment;
                    Intent intent = new Intent(getApplicationContext(), SectionAssessmentDialog.class);
                    intent.putExtra("selectedAssessmentID", selectedAssessment.getTitle());
                    startActivity(intent);
                }
            });

            row.addView(readMore);
            courseSectionAssessmentLayout.addView(row);
        }

    }

    public void showSectionAssessmentDialog(View view) {
        Intent intent = new Intent(this, SectionAssessmentDialog.class);
        startActivity(intent);
        drawAssessments();
    }
    public static void addAssessment(Assessment assessment) {
        tempAssessmentList.add(assessment);
    }
    public static Assessment getAssessmentByID(String id) {
        for (Assessment assessment : tempAssessmentList) {
            if (assessment.getTitle().equals(id)) {
                return assessment;
            }
        }
        return null;
    }
    public static void deleteAssessment(Assessment assessment) {
        tempAssessmentList.remove(assessment);
    }
}