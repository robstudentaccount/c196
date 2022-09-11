package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TermManagerActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.stuart.robert.wgu.c196.v3.TERM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_manager);

        Button addTermBtn = (Button) findViewById(R.id.addTermBtn);
        View.OnClickListener tl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMA();
            }
        };
        addTermBtn.setOnClickListener(tl);
    }
    public void returnToMA() {
        // Create new term
        TextView newTermName = findViewById(R.id.termNameTextView);
        TextView startDateTxt = findViewById(R.id.termStartDateTxt);
        TextView endDateTxt = findViewById(R.id.termEndDateTxt);

        // Term Dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDateDate = LocalDate.parse(startDateTxt.getText().toString(), formatter);
        LocalDate endDateDate = LocalDate.parse(endDateTxt.getText().toString(), formatter);

        // Create Term Object
        Term newTerm = new Term(newTermName.getText().toString(), startDateTxt.getText().toString(), endDateTxt.getText().toString());
        Terms.addNewTerm(newTerm);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        boolean success = databaseHelper.addTerm(newTerm);
        if (success == false) {
            System.out.println("Unable to add to the database");
        } else {
            System.out.println("Added to the database");
        }
        Intent intent = new Intent(this, com.stuart.robert.wgu.c196.v3.MainActivity.class);
        startActivity(intent);
    }
}