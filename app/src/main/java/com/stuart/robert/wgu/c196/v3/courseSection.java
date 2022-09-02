package com.stuart.robert.wgu.c196.v3;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class courseSection extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_section);
        drawCourses();




        TextView startDateTxt = (TextView) findViewById(R.id.startDateTxt);
        TextView endDateTxt = (TextView) findViewById(R.id.endDateText);

        View.OnClickListener calendarClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                TextView textView = (TextView)view;
                String info = textView.getText().toString();
                if(info.equals(""))info="03/22/22";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                startDate = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        myCalendarStart.set(Calendar.YEAR, year);
                        myCalendarStart.set(Calendar.MONTH, monthOfYear);
                        myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        textView.setText(sdf.format(myCalendarStart.getTime()));
                    }
                };


                new DatePickerDialog(courseSection.this, startDate,
                        myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();



            }
        };

        startDateTxt.setOnClickListener(calendarClick);
        endDateTxt.setOnClickListener(calendarClick);

    }
    private void drawCourses() {
        LinearLayout courseListView = (LinearLayout) findViewById(R.id.courseListView);
        TextView t = new TextView(this);
        t.setText("HHHHHHHHHHHHHHHHHHHHHHHH");
        courseListView.addView(t);
    }
}