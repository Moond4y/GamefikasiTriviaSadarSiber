package com.example.gamefikasitrivia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowHighscore extends AppCompatActivity {
        DatabaseReference databaseReference;
        ListView listshow;
        ArrayList<String> arrList= new ArrayList<>();
        ArrayAdapter<String> arrAdp;
        Module module;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_highscore);
            module=((Module)getApplicationContext());
            databaseReference= FirebaseDatabase.getInstance().getReference("Highscore" );
            listshow= (ListView) findViewById(R.id.listviewtxt);
            arrAdp= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrList);
            listshow.setAdapter(arrAdp);
            //Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show();
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value= dataSnapshot.getValue(Highscore.class).toString();
                    arrList.add(value);
                    arrAdp.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    @Override
    public void onBackPressed() {
    Intent intent = new Intent(this, HomeScreen.class);
    startActivity(intent);
    finish();
    }
}