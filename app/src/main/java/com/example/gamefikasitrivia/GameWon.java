package com.example.gamefikasitrivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import info.hoang8f.widget.FButton;

public class GameWon extends Activity {
    FButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
        back = (FButton) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameWon.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void PlayAgain(View view) {
        Intent intent = new Intent(GameWon.this, MainGameActivity.class);
        startActivity(intent);
        finish();
    }
}
