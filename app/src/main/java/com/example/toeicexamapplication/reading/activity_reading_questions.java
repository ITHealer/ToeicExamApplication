package com.example.toeicexamapplication.reading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.vocabulary.ListTopicAdapter;
import com.example.toeicexamapplication.vocabulary.Topic;
import com.example.toeicexamapplication.vocabulary.VocabularyActivity;
import com.example.toeicexamapplication.vocabulary.activity_list_word;
import com.example.toeicexamapplication.vocabulary.activity_vocabulary_words;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_reading_questions extends AppCompatActivity implements ValueEventListener {
    private TextView Reading;
    ListView rccView;
    List<Topic> readingList;
    ListReading readingAdapter;
    DatabaseReference databaseReference, databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_questions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));

        rccView = findViewById(R.id.rccView);
        Intent intent = getIntent();
        String ChuDe = intent.getStringExtra("ChuDe");
        String Content = intent.getStringExtra("Content");
        readingList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reading").child(ChuDe);
        readingAdapter = new ListReading(this,R.layout.item_reading,readingList);
        rccView.setAdapter(readingAdapter);

        databaseReference.addValueEventListener(this);

        rccView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ChuDe1 = readingList.get(i).getName_topic();
                if (ChuDe1.equals("Context"))
                {
                    Intent intent=new Intent(activity_reading_questions.this, activity_content.class);
                    intent.putExtra("ChuDe",ChuDe);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(activity_reading_questions.this, activity_reading_answers.class);
                    intent.putExtra("ChuDe", ChuDe);
                    intent.putExtra("ChuDe1", ChuDe1);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
       Iterable<DataSnapshot> nodeChild = snapshot.getChildren();
        for (DataSnapshot data : nodeChild) {
            String name = data.getKey();
            Topic topic = new Topic(name);
            readingList.add(topic);
            readingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}