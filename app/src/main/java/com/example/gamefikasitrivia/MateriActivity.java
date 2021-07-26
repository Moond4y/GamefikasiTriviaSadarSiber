package com.example.gamefikasitrivia;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MateriActivity extends AppCompatActivity {

    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private MateriAdapter adapter;
    private ArrayList<Materi> materii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        adapter = new MateriAdapter(this);

        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MateriActivity.this, MateriExtended.class);
                intent.putExtra("i", i);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.materi_list);
        dataDescription = getResources().getStringArray(R.array.materi_description);
        dataPhoto = getResources().obtainTypedArray(R.array.materi_icon);
    }

    private void addItem() {
        materii = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Materi materi = new Materi();
            materi.setPhoto(dataPhoto.getResourceId(i, -1));
            materi.setName(dataName[i]);
            materi.setDescription(dataDescription[i]);
            materii.add(materi);
        }

        adapter.setMateri(materii);
    }
}