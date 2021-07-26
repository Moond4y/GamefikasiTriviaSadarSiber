package com.example.gamefikasitrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MateriExtended extends AppCompatActivity {
    int i;
    private TextView judul,isi;
    private String[] dataName;
    private String[] dataDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_extended);
        Bundle bundle = getIntent().getExtras();
        i = bundle.getInt("i");

        judul = findViewById(R.id.judulText);
        isi = findViewById(R.id.isiText);
        prepare();

        judul.setText(dataName[i]);
        isi.setText(dataDescription[i]);



    }
    private void prepare() {
        dataName = getResources().getStringArray(R.array.materi_list);
        dataDescription = getResources().getStringArray(R.array.materi_description);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MateriActivity.class);
        startActivity(intent);
        finish();
    }
}