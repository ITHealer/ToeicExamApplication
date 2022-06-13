package com.example.toeicexamapplication.reading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.vocabulary.VocabularyActivity;
import com.example.toeicexamapplication.vocabulary.activity_list_word;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_content extends AppCompatActivity implements ValueEventListener {

    TextView content;
    Button btnlistquestion;
    String noidung;
    DatabaseReference databaseReference, databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        content = findViewById(R.id.tv_content);
        btnlistquestion = findViewById(R.id.btnlistquestion);

        Intent intent = getIntent();
        String ChuDe = intent.getStringExtra("ChuDe");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reading").child(ChuDe).child("Context");
        databaseReference.addValueEventListener(this);
        btnlistquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_content.this, activity_reading_questions.class);
                intent.putExtra("ChuDe", ChuDe);
                intent.putExtra("Content",noidung);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
         noidung = snapshot.getValue().toString();
        content.setText(noidung);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}