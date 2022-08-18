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
                //addTermToView();
                Intent intent = new Intent(this, TermManagerActivity.class);
                startActivity(intent);

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
        listTerms.setLayoutParams(listTermsLP);

        ArrayList<Term> terms = Terms.getTerms();
        for(Term term : terms) {
            System.out.println(term.getDisplayName());
        }





        //Layout and Box
        ConstraintLayout newTermLayout = new ConstraintLayout(this);
        listTerms.addView(newTermLayout);
        //llparams.setMargins(0,0,20, 0);

        newTermLayout.setLayoutParams(llparams);
        newTermLayout.setBackgroundResource(R.drawable.customborder);


        //Checkbox
        CheckBox cb = new CheckBox(this);
        //cb.setText(newTerm.getDisplayName());

        //Textview
        TextView tv = new TextView(this);
        tv.setText("Hello world");
        tv.setId(View.generateViewId());
        tv.setVisibility(View.GONE);
        newTermLayout.addView(tv);

        //Down Arrow
        ImageView downArrow = new ImageView(this);
        downArrow.setImageResource(R.drawable.ic_down_arrow);
        downArrow.setId(View.generateViewId());
        newTermLayout.addView(downArrow);
        // Down Arrow Click Listener
        downArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv.setVisibility(View.VISIBLE);
            }
        });

        cbParams.setMargins(0,0,20, 0);
        cb.setLayoutParams(cbParams);
        cb.setId(View.generateViewId());
        newTermLayout.addView(cb);

        //Constraint Layout
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(newTermLayout);
        constraintSet.connect(cb.getId(),ConstraintSet.LEFT,downArrow.getId(),ConstraintSet.RIGHT,25);
        constraintSet.connect(cb.getId(),ConstraintSet.TOP,downArrow.getId(),ConstraintSet.TOP,0);
        constraintSet.connect(tv.getId(),ConstraintSet.LEFT,downArrow.getId(),ConstraintSet.LEFT,0);
        constraintSet.connect(tv.getId(),ConstraintSet.TOP,downArrow.getId(),ConstraintSet.BOTTOM,20);
        constraintSet.applyTo(newTermLayout);

    }
}