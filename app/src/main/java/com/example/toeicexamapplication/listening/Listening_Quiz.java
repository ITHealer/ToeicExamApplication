package com.example.toeicexamapplication.listening;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toeicexamapplication.MainActivity;
import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.account.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

public class Listening_Quiz extends AppCompatActivity {

    TextView tvscore,tvquestcount;
    RadioGroup rdgchoices;
    RadioButton rdbA,rdbB,rdbC,rdbD;
    Button btnconfirm;
    Button btnquit;
    ImageView imglisten;
    ArrayList<Listening> listen;
    MediaPlayer mediaPlayer;
    ImageButton imgBT;
    Bitmap myBitmap;
    User user;
    URI uri;
    private FirebaseAuth myAuth;
    int questioncurrent = 0;
    int questiontrue = 0;
    int answer=0;
    int score=0;
    String URL = "https://github.com/Lap2000/songs/raw/main/Bay-Giua-Ngan-Ha-Nam-Cuong.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cPrimary)));
        addControls();
        //layUser();
        AddArrayCLN();
        shownextquestion(questioncurrent,listen);
        addEvents();
    }

    private void addEvents() {
        // Create MediaPlayer.
        mediaPlayer=  new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {mp.start();}
        });
        CountDownTimer countDownTimer=new CountDownTimer(3000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                showanswer();
            }

            @Override
            public void onFinish() {
                btnconfirm.setEnabled(true);
                shownextquestion(questioncurrent,listen);
            }
        };
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkans();
                questioncurrent++;
                countDownTimer.start();
                if(questioncurrent == listen.size()+1){
                    Toast.makeText(Listening_Quiz.this, "Congratulation!!!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Listening_Quiz.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        // When the video file ready for playback.
        this.imgBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://example.coom/mysong.mp3
                //String mediaURL = MediaPlayerUtils.URL_MEDIA_SAMPLE;
                String mediaURL = URL;
                MediaPlayerUtils.playURLMedia(Listening_Quiz.this, mediaPlayer, mediaURL);
                //doStart();
            }
        });
        btnquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                doStop();
                Intent intent
                        = new Intent(Listening_Quiz.this,
                        ListeningActivity.class);
                startActivity(intent);
            }
        });
    }
    private void AddArrayCLN(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference().child("Listening");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Listening ls = dataSnapshot.getValue(Listening.class);
                    listen.add(ls);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Listening_Quiz.this, "Get Listen_Quiz fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void shownextquestion(int pos, ArrayList<Listening> lt){
        if(pos > 0) doStop();
        tvquestcount.setText("Question: "+(questioncurrent)+"/"+lt.size()+"");
        rdgchoices.clearCheck();
        rdbA.setBackground(this.getResources().getDrawable(R.drawable.bgbtn));
        rdbB.setBackground(this.getResources().getDrawable(R.drawable.bgbtn));
        rdbC.setBackground(this.getResources().getDrawable(R.drawable.bgbtn));
        rdbD.setBackground(this.getResources().getDrawable(R.drawable.bgbtn));

        if(pos == lt.size()){
            Toast.makeText(Listening_Quiz.this, "Congratulation!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                uri = new URI(lt.get(pos).getImage());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            String url = lt.get(pos).getImage();
            byte[] encodeByte = url.getBytes();
            Bitmap img = BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
            //imglisten.setImageBitmap(img);
            imglisten.setImageURI(Uri.parse(lt.get(pos).getImage()));

            String URLaudio = lt.get(pos).getAudio();
            URL = URLaudio;
            answer = lt.get(pos).getTrue();
            rdbA.setText(lt.get(pos).getA());
            rdbB.setText(lt.get(pos).getB());
            rdbC.setText(lt.get(pos).getC());
            rdbD.setText(lt.get(pos).getD());
        }
    }
    public void checkans(){
        btnconfirm.setEnabled(false);
        if(rdbA.isChecked()){
            if(1==answer) {
                score+=20;
                questiontrue++;
            }
        }
        if(rdbB.isChecked()){
            if(2==answer) {
                score+=20;
                questiontrue++;
            }
        }
        if(rdbC.isChecked()){
            if(3==answer) {
                score+=20;
                questiontrue++;
            }
        }
        if(rdbD.isChecked()){
            if(4==answer) {
                score+=20;
                questiontrue++;
            }
        }
        tvscore.setText("Score: "+score+"");
    }
    public void showanswer(){
        if(1==answer) {
            rdbA.setBackground(this.getResources().getDrawable(R.drawable.button_true));
            rdbB.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbC.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbD.setBackground(this.getResources().getDrawable(R.drawable.button_false));
        }
        if(2==answer) {
            rdbA.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbB.setBackground(this.getResources().getDrawable(R.drawable.button_true));
            rdbC.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbD.setBackground(this.getResources().getDrawable(R.drawable.button_false));
        }
        if(3==answer) {
            rdbA.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbB.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbC.setBackground(this.getResources().getDrawable(R.drawable.button_true));
            rdbD.setBackground(this.getResources().getDrawable(R.drawable.button_false));
        }
        if(4==answer) {
            rdbA.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbB.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbC.setBackground(this.getResources().getDrawable(R.drawable.button_false));
            rdbD.setBackground(this.getResources().getDrawable(R.drawable.button_true));
        }
    }
    private void layUser() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://learningenglishproject-a5bc5-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = firebaseDatabase.getReference("User");
        String email = "", hoTen= "", phone = "";
        int pointListen = 0, pointReading= 0 ;
        myRef.child(myAuth.getCurrentUser().getUid()).child("email").setValue(email);
        myRef.child(myAuth.getCurrentUser().getUid()).child("hoTen").setValue(hoTen);
        myRef.child(myAuth.getCurrentUser().getUid()).child("pointListening").setValue(pointListen);
        myRef.child(myAuth.getCurrentUser().getUid()).child("pointReading").setValue(pointReading);
        myRef.child(myAuth.getCurrentUser().getUid()).child("sdt").setValue(phone);
        user = new User(myAuth.getCurrentUser().getUid(), hoTen, email, phone, pointReading,pointListen);
    }

    private void addControls() {
        listen = new ArrayList<>();
        tvscore = (TextView)findViewById(R.id.txtscoreLN);
        tvquestcount = (TextView) findViewById(R.id.txtquestcountLN);
        rdgchoices = (RadioGroup) findViewById(R.id.radiochoices);
        rdbA = (RadioButton) findViewById(R.id.rdbA);
        rdbB = (RadioButton) findViewById(R.id.rdbB);
        rdbC = (RadioButton) findViewById(R.id.rdbC);
        rdbD = (RadioButton) findViewById(R.id.rdbD);
        btnconfirm = (Button) findViewById(R.id.btnconfirmLN);
        btnquit = (Button)findViewById(R.id.btnQuitLN);
        imglisten = (ImageView) findViewById(R.id.imgHinh);
        imgBT = (ImageButton) findViewById(R.id.imgBT);
    }
    private void doStop()  {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
    }
}