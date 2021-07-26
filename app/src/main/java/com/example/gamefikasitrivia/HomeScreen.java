package com.example.gamefikasitrivia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity {
    FButton playGame, materi, highscore, quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initViews();

        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomeScreen.this, MateriActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(HomeScreen.this, ShowHighscore.class);
                startActivity(intent3);
                finish();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        playGame = (FButton) findViewById(R.id.playGame);
        materi = (FButton) findViewById(R.id.materi);
        quit = (FButton) findViewById(R.id.quit);
        highscore = (FButton) findViewById(R.id.highscore);
        tQ = (TextView) findViewById(R.id.tQ);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        materi.setTypeface(typeface);
        quit.setTypeface(typeface);
        highscore.setTypeface(typeface);
        tQ.setTypeface(typeface);
    }
}
