package com.example.toeicexamapplication.listening;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.reading.ReadingActivity;
import com.example.toeicexamapplication.reading.activity_reading_questions;

public class ListeningActivity extends AppCompatActivity {

    private TextView Let;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));
        Let = findViewById(R.id.textView);
        Let.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListeningActivity.this, Listening_Quiz.class);
                startActivity(intent);
            }
        });
    }
}