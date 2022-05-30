package com.example.toeicexamapplication.vocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.vocabulary.VocabularyActivity;
import com.example.toeicexamapplication.vocabulary.activity_vocabulary_words;

public class VocabularyActivity extends AppCompatActivity {
    private TextView topics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));
        int i;
        topics = findViewById(R.id.tV_topic);
        topics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VocabularyActivity.this, activity_vocabulary_words.class);
                startActivity(intent);
            }
        });
    }
}