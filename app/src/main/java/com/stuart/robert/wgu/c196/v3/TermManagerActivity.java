package com.stuart.robert.wgu.c196.v3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        //Create new term
        TextView newTermName = findViewById(R.id.termNameTextView);
        Term newTerm = new Term(newTermName.getText().toString());
        Terms.addNewTerm(newTerm);

        Intent intent = new Intent(this, com.stuart.robert.wgu.c196.v3.MainActivity.class);
        //String message = newTermName.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, newTerm);
        startActivity(intent);
    }
}