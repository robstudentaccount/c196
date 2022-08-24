package com.stuart.robert.wgu.c196.v3;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra(TermManagerActivity.EXTRA_MESSAGE);


        getSupportActionBar().setTitle("Term Manager");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        Intent intent = getIntent();
        String message = intent.getStringExtra(TermManagerActivity.EXTRA_MESSAGE);
        System.out.println(message);
        drawTerms();

        return super.onCreateOptionsMenu(menu);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTermItem:
                Intent termIntent = new Intent(this, TermManagerActivity.class);
                startActivity(termIntent);
                break;
            case R.id.courseManagementItem:
                Intent courseIntent = new Intent(this, CourseManager.class);
                startActivity(courseIntent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void drawTerms() {
        LinearLayout.LayoutParams cbParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams lDownArrow = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        FrameLayout.LayoutParams listTermsLP = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout listTerms = (LinearLayout) findViewById(R.id.maTermView);

        listTerms.setLayoutParams(listTermsLP); //FIX
        listTerms.removeAllViews();

        ArrayList<Term> terms = Terms.getTerms();
        for(Term term : terms) {
            System.out.println(term.getDisplayName());
            //Layout and Box
            ConstraintLayout newTermLayout = new ConstraintLayout(this);
            listTerms.addView(newTermLayout);
            newTermLayout.setLayoutParams(llparams);
            newTermLayout.setBackgroundResource(R.drawable.customborder);
            //Checkbox
            //CheckBox cb = new CheckBox(this);
            TextView cb = new TextView(this);
            cbParams.setMargins(0,0,20, 0);
            cb.setLayoutParams(cbParams);
            cb.setId(View.generateViewId());
            cb.setText(term.getDisplayName());
            newTermLayout.addView(cb);

            //Textview
            TextView startDateLbl = new TextView(this);
            startDateLbl.setText("Start Date: " + term.getStartDateString());
            startDateLbl.setId(View.generateViewId());
            startDateLbl.setVisibility(View.GONE);
            newTermLayout.addView(startDateLbl);
            //Textview
            TextView endDateLbl = new TextView(this);
            endDateLbl.setText("End Date: " + term.getEndDateString());
            endDateLbl.setId(View.generateViewId());
            endDateLbl.setVisibility(View.GONE);
            newTermLayout.addView(endDateLbl);


            //Down Arrow
            ImageView downArrow = new ImageView(this);
            downArrow.setImageResource(R.drawable.ic_down_arrow);
            downArrow.setId(View.generateViewId());
            newTermLayout.addView(downArrow);
            downArrow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (startDateLbl.getVisibility() == View.VISIBLE) {
                        startDateLbl.setVisibility(View.GONE);
                        endDateLbl.setVisibility(View.GONE);
                        downArrow.setImageResource(R.drawable.ic_down_arrow);
                    } else {
                        startDateLbl.setVisibility(View.VISIBLE);
                        endDateLbl.setVisibility(View.VISIBLE);
                        downArrow.setImageResource(R.drawable.ic_up_arrow);
                    }
                }

            });
            //Constraint Layout
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(newTermLayout);
            constraintSet.connect(cb.getId(),ConstraintSet.LEFT,downArrow.getId(),ConstraintSet.RIGHT,25);
            constraintSet.connect(cb.getId(),ConstraintSet.TOP,downArrow.getId(),ConstraintSet.TOP,0);
            constraintSet.connect(startDateLbl.getId(),ConstraintSet.LEFT,downArrow.getId(),ConstraintSet.LEFT,0);
            constraintSet.connect(startDateLbl.getId(),ConstraintSet.TOP,downArrow.getId(),ConstraintSet.BOTTOM,20);
            constraintSet.connect(endDateLbl.getId(),ConstraintSet.LEFT,downArrow.getId(),ConstraintSet.LEFT,0);
            constraintSet.connect(endDateLbl.getId(),ConstraintSet.TOP,startDateLbl.getId(),ConstraintSet.BOTTOM,20);
            constraintSet.applyTo(newTermLayout);
        }
    }
}