package com.example.toeicexamapplication.grammar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.reading.ReadingActivity;
import com.example.toeicexamapplication.reading.activity_reading_questions;

public class GrammarActivity extends AppCompatActivity {

    private LinearLayout layout_HTD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));
        layout_HTD = findViewById(R.id.layout_HTD);
        layout_HTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GrammarActivity.this, Grammar_List.class);
                startActivity(intent);
            }
        });
    }
}