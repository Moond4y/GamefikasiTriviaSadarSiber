package com.example.gamefikasitrivia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import info.hoang8f.widget.FButton;

public class Time_Up extends AppCompatActivity {
    FButton playAgainButton, back;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__up);

        playAgainButton = (FButton) findViewById(R.id.playAgainButton);
        timeUpText = (TextView) findViewById(R.id.timeUpText);
        back = (FButton) findViewById(R.id.backButton);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time_Up.this, MainGameActivity.class);
                startActivity(intent);
                finish();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time_Up.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        timeUpText.setTypeface(typeface);
        playAgainButton.setTypeface(typeface);
        back.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Time_Up.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }
}
