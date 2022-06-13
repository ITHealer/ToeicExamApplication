package com.example.toeicexamapplication.vocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toeicexamapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_vocabulary_words extends AppCompatActivity implements ValueEventListener {

    DatabaseReference databaseReference;
    Button btnDone;
    ImageButton imbtnRemember;
    ListWordAdapter adapterword;
    List<Word> wordList;
    ListView listView;
    TextView txtTuVung,txtLoaiTu,txtPhienAm,txtNghia, txtTopicName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_words);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));

        txtTuVung = findViewById(R.id.tV_word);
        txtLoaiTu = findViewById(R.id.tV_type);
        txtNghia = findViewById(R.id.tV_mean);
        txtPhienAm = findViewById(R.id.tV_pronun);
        txtTopicName = findViewById(R.id.tV_topic);
        imbtnRemember = findViewById(R.id.btn_remember);


        Intent intent = getIntent();
        String ChuDe = intent.getStringExtra("ChuDe");
        String ChuDe1 = intent.getStringExtra("ChuDe1");
        txtTopicName.setText(ChuDe);
        txtTuVung.setText(ChuDe1);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Vocabulary").child(ChuDe).child(ChuDe1);
        databaseReference.addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
            Word tu = snapshot.getValue(Word.class);
            txtLoaiTu.setText(tu.getLoaiTu());
            txtPhienAm.setText(tu.getPhienAm());
            txtNghia.setText(tu.getNghia());

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

}