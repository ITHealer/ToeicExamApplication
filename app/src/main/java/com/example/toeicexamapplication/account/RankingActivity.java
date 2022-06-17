package com.example.toeicexamapplication.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.grammar.Grammar;
import com.example.toeicexamapplication.grammar.GrammarActivity;
import com.example.toeicexamapplication.grammar.Grammar_List;
import com.example.toeicexamapplication.grammar.ListGrammarAdapter;
import com.example.toeicexamapplication.listening.Listening_Quiz;
import com.example.toeicexamapplication.reading.Reading;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {

    private TextView tvHoTenRank, tvEmailRank, tvPointRank;

    DatabaseReference myRef;
    List<Ranking> userList;
    ListView lvUserRank;
    RankingAdapter listUserAdapter;
    Ranking ranking;

    private FirebaseDatabase rootNode;

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));

        getWidgets();

        myRef = FirebaseDatabase.getInstance().getReference().child("User");
        lvUserRank = (ListView) findViewById(R.id.userranklist);
        userList = new ArrayList<>();
        getListUserFromRealtimeDatabase();
        listUserAdapter = new RankingAdapter(this,R.layout.row_ranking,userList);
        lvUserRank.setAdapter(listUserAdapter);
    }

    public void getListUserFromRealtimeDatabase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        myRef = rootNode.getReference("User");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    String name = user.getHoTen();
                    int pointReading = user.getPointReading();
                    int pointListening = user.getPointListening();
                    int sum = pointReading + pointListening;
                    ranking = new Ranking(name, sum);
                    userList.add(ranking);
                    listUserAdapter.notifyDataSetChanged();
                }

//                User user = snapshot.getValue(User.class);
//                tvHoTenRank.setText(user.getHoTen());
//                int pointReading = user.getPointReading();
//                int pointListening = user.getPointListening();
//                int sum = pointReading + pointListening;
//                tvPointRank.setText(String.valueOf(sum));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RankingActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getWidgets() {
        tvHoTenRank = (TextView) findViewById(R.id.tvhotenrank);
        tvPointRank = (TextView) findViewById(R.id.tvpointrank);
        lvUserRank = findViewById(R.id.userranklist);
    }

}