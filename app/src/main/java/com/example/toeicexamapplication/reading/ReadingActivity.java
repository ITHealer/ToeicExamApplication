package com.example.toeicexamapplication.reading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.account.MyProfileActivity;
import com.example.toeicexamapplication.databinding.FragmentHomeBinding;
import com.example.toeicexamapplication.grammar.GrammarActivity;
import com.example.toeicexamapplication.listening.ListeningActivity;
import com.example.toeicexamapplication.reading.ReadingActivity;
import com.example.toeicexamapplication.vocabulary.VocabularyActivity;

import com.example.toeicexamapplication.grammar.GrammarActivity;

public class ReadingActivity extends AppCompatActivity {

    private TextView Reading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));

        Reading = findViewById(R.id.tv_R);
        Reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReadingActivity.this, activity_reading_questions.class);
                startActivity(intent);
            }
        });

    }
}