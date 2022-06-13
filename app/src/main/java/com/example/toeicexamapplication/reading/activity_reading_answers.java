package com.example.toeicexamapplication.reading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.vocabulary.Topic;
import com.example.toeicexamapplication.vocabulary.Word;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_reading_answers extends AppCompatActivity implements ValueEventListener {

    private TextView Reading;
    DatabaseReference databaseReference;
    TextView cauhoi,A,B,C,D,True;
    Button Content,Confirm;
    String ketqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_answers);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cauhoi = (TextView) findViewById(R.id.tV_question);
        A = findViewById(R.id.op1);
        B = findViewById(R.id.op2);
        C = findViewById(R.id.op3);
        D = findViewById(R.id.op4);
        True = findViewById(R.id.da_True);
        Content = findViewById(R.id.btnContent);
        Confirm = findViewById(R.id.btnconfirm);
        //A = findViewById(R.id.op1);
        Intent intent = getIntent();
        String ChuDe = intent.getStringExtra("ChuDe");
        String ChuDe1 = intent.getStringExtra("ChuDe1");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reading").child(ChuDe).child(ChuDe1);

        databaseReference.addValueEventListener(this);
        Content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity_reading_answers.this, activity_content.class);
                intent.putExtra("ChuDe",ChuDe);
                startActivity(intent);
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                True.setText("Correct answer is : "+ketqua);
            }
        });

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Reading question = snapshot.getValue(Reading.class);
        cauhoi.setText(question.getQuestion()); ;
        A.setText(question.getA());
        B.setText(question.getB());
        C.setText(question.getC());
        D.setText(question.getD());
        ketqua = question.getTrue();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}