package com.example.gamefikasitrivia;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {
    EditText tID,tName,tEmail,tPassword,tConfirmPassword;
    Button btnRegister,btnShow;
    ListView ListUser;
    DatabaseReference databaseReference;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        result=(TextView)findViewById(R.id.textView2);
        tID=(EditText) findViewById(R.id.tID);
        tName=(EditText) findViewById(R.id.tName);
        tEmail=(EditText) findViewById(R.id.tEmail);
        tPassword=(EditText) findViewById(R.id.tPassword);
        tConfirmPassword=(EditText) findViewById(R.id.tConfirmPassword);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        databaseReference= FirebaseDatabase.getInstance().getReference("User");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArrayList();
            }
        });

    }
    private void  addArrayList(){
        final String ID = tID.getText().toString().trim();
        final String email=tEmail.getText().toString().trim();
        final String name =tName.getText().toString().trim();
        final String password =tPassword.getText().toString().trim();
        String comfirmpassword =tConfirmPassword.getText().toString().trim();

        if(TextUtils.isEmpty(ID)) {
            tID.setError("Tolong masukkan NPM/NIP!");
        }else if (TextUtils.isEmpty(name)){
            tName.setError("Tolong masukkan Username!");
        }else if(TextUtils.isEmpty(email)){
            tEmail.setError("Tolong masukkan Email!");
        }else if(TextUtils.isEmpty(password)){
            tPassword.setError("Tolong masukkan Password!");
        }else if(password.length()<8){
            tPassword.setError("Password terlalu pendek!");
        } else if(!password.equals(comfirmpassword)){
            tConfirmPassword.setError("Tolong masukkan password yang sama!");
        }else{

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(name).exists()) {
                        Toast.makeText(RegisterActivity.this,"Pengguna sudah terdaftar",Toast.LENGTH_LONG).show();

                    } else {
                        databaseReference.child(name).child("id").setValue(ID);
                        databaseReference.child(name).child("email").setValue(email);
                        databaseReference.child(name).child("username").setValue(name);
                        try {
                            databaseReference.child(name).child("password").setValue(Security.hash(password));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(RegisterActivity.this,"Pengguna berhasil terdaftar",Toast.LENGTH_LONG).show();
                        Cleartxt();
                        Intent intphto =new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intphto);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }

    }
    private void Cleartxt(){
        tID.setText("");
        tEmail.setText("");
        tName.setText("");
        tPassword.setText("");
        tConfirmPassword.setText("");
        result.setText("");
        tID.requestFocus();

    }

}


