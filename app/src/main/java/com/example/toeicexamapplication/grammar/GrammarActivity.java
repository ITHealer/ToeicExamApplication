package com.example.toeicexamapplication.grammar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.listening.Listening_Quiz;
import com.example.toeicexamapplication.reading.ListReading;
import com.example.toeicexamapplication.reading.ReadingActivity;
import com.example.toeicexamapplication.reading.activity_content;
import com.example.toeicexamapplication.reading.activity_reading_questions;
import com.example.toeicexamapplication.vocabulary.Topic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GrammarActivity extends AppCompatActivity implements ValueEventListener {

    DatabaseReference myRef;
    List<Tense> tenseList;
    ListView listView;
    Tense tense_Name;
    ListGrammarAdapter listGrammarAdapter;
    TextView tvTense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));
        myRef = FirebaseDatabase.getInstance().getReference().child("Grammar");
        listView = (ListView) findViewById(R.id.lvListgrammar);
        tenseList = new ArrayList<>();
        listGrammarAdapter = new ListGrammarAdapter(this,R.layout.row_grammar,tenseList);
        listView.setAdapter(listGrammarAdapter);
        myRef.addValueEventListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(GrammarActivity.this, Grammar_List.class);
                String name = tenseList.get(i).getTense_Name();
                intent.putExtra("Tenthi", name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Iterable<DataSnapshot> nodeChild = snapshot.getChildren();
        for(DataSnapshot data : nodeChild)
        {
            String name = data.getKey();
            Tense tense = new Tense(name);
            tenseList.add(tense);
            listGrammarAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(GrammarActivity.this, "Fail", Toast.LENGTH_SHORT).show();
    }
}